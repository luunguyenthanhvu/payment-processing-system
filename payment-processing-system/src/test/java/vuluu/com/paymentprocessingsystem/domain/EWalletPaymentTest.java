package vuluu.com.paymentprocessingsystem.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import vuluu.com.paymentprocessingsystem.enums.EWalletType;
import vuluu.com.paymentprocessingsystem.enums.PaymentStatus;

class EWalletPaymentTest {

  @Test
  void shouldPayUsingMomo() {

    EWalletPayment payment = new EWalletPayment(
        "EW001",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        EWalletType.MOMO
    );

    payment.pay(new BigDecimal("200"));

    assertEquals(
        new BigDecimal("300"),
        payment.getAmount()
    );

    assertEquals(
        EWalletType.MOMO,
        payment.getEwalletType()
    );
  }

  @Test
  void shouldPayUsingZaloPay() {

    EWalletPayment payment = new EWalletPayment(
        "EW002",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        EWalletType.ZALO_PAY
    );

    payment.pay(new BigDecimal("200"));

    assertEquals(
        new BigDecimal("300"),
        payment.getAmount()
    );

    assertEquals(
        EWalletType.ZALO_PAY,
        payment.getEwalletType()
    );
  }

  @Test
  void shouldPayWhenAmountEqualsBalance() {

    EWalletPayment payment = new EWalletPayment(
        "EW003",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        EWalletType.MOMO
    );

    payment.pay(new BigDecimal("500"));

    assertEquals(
        new BigDecimal("0"),
        payment.getAmount()
    );
  }

  @Test
  void shouldNotPayWhenBalanceIsInsufficient() {

    EWalletPayment payment = new EWalletPayment(
        "EW004",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        EWalletType.MOMO
    );

    payment.pay(new BigDecimal("600"));

    assertEquals(
        new BigDecimal("500"),
        payment.getAmount()
    );
  }

  @Test
  void shouldValidateWhenBalanceIsEnough() {

    EWalletPayment payment = new EWalletPayment(
        "EW005",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        EWalletType.MOMO
    );

    assertTrue(payment.validate(new BigDecimal("500")));
  }

  @Test
  void shouldRejectWhenBalanceIsInsufficient() {

    EWalletPayment payment = new EWalletPayment(
        "EW006",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        EWalletType.MOMO
    );

    assertFalse(payment.validate(new BigDecimal("501")));
  }
}
