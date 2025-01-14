public class PaymentContext {
  private final PaymentMethod paymentMethod;

  public PaymentContext(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
  }

  public String pay(double amount) {
      double userBalance = paymentMethod.getBalance();
      if (userBalance < amount) {
        return "-1";
      } else {
        return paymentMethod.pay(amount);
      }
  }
}
