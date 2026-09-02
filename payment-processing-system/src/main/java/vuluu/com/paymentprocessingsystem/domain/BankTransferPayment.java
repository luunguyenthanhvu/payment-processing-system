package vuluu.com.paymentprocessingsystem.domain;

import java.math.BigDecimal;
import vuluu.com.paymentprocessingsystem.enums.PaymentStatus;

/**
 * @author VuLuu
 */
public class BankTransferPayment extends Payment {

  public BankTransferPayment(
      String paymentId,
      BigDecimal amount,
      PaymentStatus status) {

    super(paymentId, amount, status);
  }

  @Override
  public void pay(BigDecimal money) {

    if (!validate(money)) {
      System.out.println("Insufficient funds");
      return;
    }

    setAmount(getAmount().subtract(money));
  }
}
