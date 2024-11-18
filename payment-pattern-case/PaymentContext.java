public class PaymentContext {
  private final PaymentMethod paymentMethod;

  public PaymentContext(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
  }

  public String pay(double amount) {
      return paymentMethod.pay(amount);
  }
}
