public interface PaymentMethod {
  // Abstract functionnya si paymentMethod
  String pay(double amount);
  double getBalance();
}
