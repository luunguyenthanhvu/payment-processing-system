package vuluu.com.paymentprocessingsystem.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import vuluu.com.paymentprocessingsystem.enums.PaymentStatus;

class BankTransferPaymentTest {

  @Test
  void shouldPayWhenBalanceIsEnough() {

    BankTransferPayment payment = new BankTransferPayment(
        "BT001",
        new BigDecimal("1000"),
        PaymentStatus.PENDING
    );

    payment.pay(new BigDecimal("300"));

    assertEquals(
        new BigDecimal("700"),
        payment.getAmount()
    );
  }

  @Test
  void shouldPayWhenAmountEqualsBalance() {

    BankTransferPayment payment = new BankTransferPayment(
        "BT002",
        new BigDecimal("1000"),
        PaymentStatus.PENDING
    );

    payment.pay(new BigDecimal("1000"));

    assertEquals(
        new BigDecimal("0"),
        payment.getAmount()
    );
  }

  @Test
  void shouldNotPayWhenBalanceIsInsufficient() {

    BankTransferPayment payment = new BankTransferPayment(
        "BT003",
        new BigDecimal("1000"),
        PaymentStatus.PENDING
    );

    payment.pay(new BigDecimal("1500"));

    assertEquals(
        new BigDecimal("1000"),
        payment.getAmount()
    );
  }

  @Test
  void shouldValidateWhenBalanceIsEnough() {

    BankTransferPayment payment = new BankTransferPayment(
        "BT004",
        new BigDecimal("1000"),
        PaymentStatus.PENDING
    );

    assertTrue(payment.validate(new BigDecimal("500")));
  }

  @Test
  void shouldRejectWhenBalanceIsInsufficient() {

    BankTransferPayment payment = new BankTransferPayment(
        "BT005",
        new BigDecimal("1000"),
        PaymentStatus.PENDING
    );

    assertFalse(payment.validate(new BigDecimal("1500")));
  }
}
