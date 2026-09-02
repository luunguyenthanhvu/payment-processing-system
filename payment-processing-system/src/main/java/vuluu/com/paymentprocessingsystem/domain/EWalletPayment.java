package vuluu.com.paymentprocessingsystem.domain;

import java.math.BigDecimal;
import vuluu.com.paymentprocessingsystem.enums.EWalletType;
import vuluu.com.paymentprocessingsystem.enums.PaymentStatus;

/**
 * @author VuLuu
 */
public class EWalletPayment extends Payment {

  private EWalletType ewalletType;

  public EWalletPayment(
      String paymentId,
      BigDecimal amount,
      PaymentStatus status,
      EWalletType ewalletType) {

    super(paymentId, amount, status);
    this.ewalletType = ewalletType;
  }

  public EWalletType getEwalletType() {
    return ewalletType;
  }

  public void setEwalletType(EWalletType ewalletType) {
    this.ewalletType = ewalletType;
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
