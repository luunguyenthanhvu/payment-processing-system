package vuluu.com.paymentprocessingsystem.domain;

import java.math.BigDecimal;
import vuluu.com.paymentprocessingsystem.enums.PaymentStatus;

/**
 * @author VuLuu
 */
public class CreditCardPayment extends Payment {

  private BigDecimal limit;

  public CreditCardPayment(
      String paymentId,
      BigDecimal amount,
      PaymentStatus status,
      BigDecimal limit) {

    super(paymentId, amount, status);
    this.limit = limit;
  }

  public BigDecimal getLimit() {
    return limit;
  }

  public void setLimit(BigDecimal limit) {
    this.limit = limit;
  }

  /**
   * A credit card uses the available balance first.
   * If the balance is insufficient, the full payment amount
   * can be charged against the available credit limit.
   */
  @Override
  public boolean validate(BigDecimal money) {

    if (getAmount().compareTo(money) >= 0) {
      return true;
    }

    return limit.compareTo(money) >= 0;
  }

  @Override
  public void pay(BigDecimal money) {

    if (!validate(money)) {
      System.out.println("Insufficient funds");
      return;
    }

    // Use available balance first
    if (getAmount().compareTo(money) >= 0) {
      setAmount(getAmount().subtract(money));
      return;
    }

    // Balance is insufficient, use credit limit
    limit = limit.subtract(money);
  }
}
