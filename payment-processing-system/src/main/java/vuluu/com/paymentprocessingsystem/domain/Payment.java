package vuluu.com.paymentprocessingsystem.domain;

import java.math.BigDecimal;
import vuluu.com.paymentprocessingsystem.enums.PaymentStatus;

/**
 * @author VuLuu
 */
public abstract class Payment {

  private String paymentId;
  private BigDecimal amount;
  private PaymentStatus status;

  public Payment(String paymentId, BigDecimal amount, PaymentStatus status) {
    this.paymentId = paymentId;
    this.amount = amount;
    this.status = status;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  /**
   * Checks whether the payment has enough available balance.
   *
   * @param money payment amount
   * @return true if the payment is valid, otherwise false
   */
  public boolean validate(BigDecimal money) {
    return this.amount.compareTo(money) >= 0;
  }

  public abstract void pay(BigDecimal money);
}
