package vuluu.com.paymentprocessingsystem.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import vuluu.com.paymentprocessingsystem.enums.PaymentStatus;

class CreditCardPaymentTest {

  @Test
  void shouldUseBalanceWhenBalanceIsEnough() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC001",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    payment.pay(new BigDecimal("300"));

    assertEquals(
        new BigDecimal("200"),
        payment.getAmount()
    );

    assertEquals(
        new BigDecimal("1000"),
        payment.getLimit()
    );
  }

  @Test
  void shouldUseEntireLimitWhenBalanceIsInsufficient() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC002",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    payment.pay(new BigDecimal("800"));

    assertEquals(
        new BigDecimal("500"),
        payment.getAmount()
    );

    assertEquals(
        new BigDecimal("200"),
        payment.getLimit()
    );
  }

  @Test
  void shouldUseBalanceWhenAmountEqualsBalance() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC003",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    payment.pay(new BigDecimal("500"));

    assertEquals(
        new BigDecimal("0"),
        payment.getAmount()
    );

    assertEquals(
        new BigDecimal("1000"),
        payment.getLimit()
    );
  }

  @Test
  void shouldUseLimitWhenPaymentEqualsLimit() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC004",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    payment.pay(new BigDecimal("1000"));

    assertEquals(
        new BigDecimal("500"),
        payment.getAmount()
    );

    assertEquals(
        new BigDecimal("0"),
        payment.getLimit()
    );
  }

  @Test
  void shouldNotPayWhenBalanceAndLimitAreInsufficient() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC005",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    payment.pay(new BigDecimal("1500"));

    assertEquals(
        new BigDecimal("500"),
        payment.getAmount()
    );

    assertEquals(
        new BigDecimal("1000"),
        payment.getLimit()
    );
  }

  @Test
  void shouldValidateUsingBalance() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC006",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    assertTrue(
        payment.validate(new BigDecimal("300"))
    );
  }

  @Test
  void shouldValidateUsingLimit() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC007",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    assertTrue(
        payment.validate(new BigDecimal("800"))
    );
  }

  @Test
  void shouldRejectWhenBalanceAndLimitAreInsufficient() {

    CreditCardPayment payment = new CreditCardPayment(
        "CC008",
        new BigDecimal("500"),
        PaymentStatus.PENDING,
        new BigDecimal("1000")
    );

    assertFalse(
        payment.validate(new BigDecimal("1500"))
    );
  }
}
