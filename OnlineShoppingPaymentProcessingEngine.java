package OopsPrac;

public class OnlineShoppingPaymentProcessingEngine {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("------------");
        System.out.println("Online Shopping Payment:");

        try {

            Payment p1 = new CreditCard("12", "lime");

            p1.processPayment(100, 12);
            p1.processPayment(200, "face");
            p1.getDetails();

            Payment p2 = new Upi("23", "nime");

            p2.processPayment(200, 2345);
            p2.processPayment(345, "eye");
            p2.getDetails();

        }

        catch (InvalidPaymentException e) {
            System.out.println(e.getMessage());
        }

        catch (PaymentFailedException e) {
            System.out.println(e.getMessage());
        }

        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-------------");

        System.out.println();

    }

}

interface PaymentFunctions {
    void processPayment(long amount, int otp) throws InvalidPaymentException;

    void processPayment(long amount, String biometric) throws InvalidPaymentException;
}

abstract class Payment implements PaymentFunctions {
    private String transactionId;
    private String username;

    Payment(String transactionId, String username) throws InvalidPaymentException {
        setTransactionId(transactionId);
        setUsername(username);
    }

    protected void setTransactionId(String transactionId) {
        if (transactionId == null
                || transactionId.isEmpty()) {
            throw new IllegalArgumentException("invalid transaction id...");
        }

        else {
            this.transactionId = transactionId;
        }
    }

    protected void setUsername(String username) {
        if (username == null
                || username.isEmpty()) {
            throw new IllegalArgumentException("invalid username...");
        }

        else {
            this.username = username;
        }
    }

    protected String getTransactionId() {
        return this.transactionId;
    }

    protected String getUsername() {
        return this.username;
    }

    abstract void getDetails();

}

class CreditCard extends Payment {

    CreditCard(String transactionId, String username) throws InvalidPaymentException {
        super(transactionId, username);
    }

    private long cashback = 10;

    boolean validateotp(int otp) {
        String s = Integer.toString(otp);
        if (s == null || s.isEmpty() || s.length() < 6) {
            return true;
        }

        else {
            return false;
        }
    }

    private long totalamount = 0;
    private long balance = 1000;

    @Override
    public void processPayment(long amount, int otp) throws InvalidPaymentException {

        if (amount <= 0) {
            throw new InvalidPaymentException("invalid amount...");
        }

        else if (amount > balance || balance <= 0) {
            throw new PaymentFailedException("insufficient funds...");
        }

        else if (validateotp(otp)) {
            throw new PaymentFailedException("invalid otp...");
        }

        else {
            System.out.println("payment:" + (amount - cashback));
            System.out.println("done using otp:" + otp);
            totalamount += amount - cashback;
            balance -= amount - cashback;
            System.out.println();
        }
    }

    @Override
    public void processPayment(long amount, String biometric) throws InvalidPaymentException {

        if (biometric == null
                || biometric.isEmpty()) {
            throw new IllegalArgumentException("invalid biometric...");
        }

        if (amount <= 0) {
            throw new InvalidPaymentException("invalid amount...");
        } else if (amount > balance || balance <= 0) {
            throw new PaymentFailedException("insufficient funds...");
        }

        else {
            System.out.println("payment:" + (amount - cashback));
            System.out.println("biometric:" + biometric);
            totalamount += amount - cashback;
            balance -= amount - cashback;
            System.out.println();
        }
    }

    @Override
    public void getDetails() {
        System.out.println();
        System.out.println("using credit card:");
        System.out.println("transaction id:" + getTransactionId());
        System.out.println("holder name:" + getUsername());
        System.out.println("amount paid:" + totalamount);
        System.out.println("remaining balance:" + balance);
        System.out.println();
    }

}

class Upi extends Payment {

    Upi(String transactionId, String username) throws InvalidPaymentException {
        super(transactionId, username);
    }

    private long totalamount = 0;
    private long balance = 2000;

    private long cashback = 20;

    boolean validateotp(int otp) {
        String s = Integer.toString(otp);
        if (s == null || s.isEmpty() || s.length() < 6) {
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public void processPayment(long amount, int otp) throws InvalidPaymentException {

        if (amount <= 0) {
            throw new InvalidPaymentException("invalid amount...");
        }

        else if (amount > balance || balance <= 0) {
            throw new PaymentFailedException("insufficient funds...");
        }

        else if (validateotp(otp)) {
            throw new PaymentFailedException("invalid otp...");
        }

        else {
            System.out.println("payment:" + (amount - cashback));
            System.out.println("done using otp:" + otp);
            totalamount += amount - cashback;
            balance -= amount - cashback;
            System.out.println();
        }
    }

    @Override
    public void processPayment(long amount, String biometric) throws InvalidPaymentException {

        if (biometric == null
                || biometric.isEmpty()) {
            throw new IllegalArgumentException("invalid biometric...");
        }

        if (amount <= 0) {
            throw new InvalidPaymentException("invalid amount...");
        }

        else if (amount > balance || balance <= 0) {
            throw new PaymentFailedException("insufficient funds...");
        }

        else {
            System.out.println("payment:" + (amount - cashback));
            System.out.println("biometric:" + biometric);
            totalamount += amount - cashback;
            balance -= amount - cashback;
            System.out.println();
        }
    }

    @Override
    public void getDetails() {
        System.out.println();
        System.out.println("using upi:");
        System.out.println("transaction id:" + getTransactionId());
        System.out.println("holder name:" + getUsername());
        System.out.println("amount paid:" + totalamount);
        System.out.println("remaining balance:" + balance);
        System.out.println();
    }

}

class InvalidPaymentException extends Exception {

    InvalidPaymentException(String message) {
        super(message);
    }

}

class PaymentFailedException extends RuntimeException {

    PaymentFailedException(String message) {
        super(message);
    }

}
