package PracticeQues;

public class SecureDigitalWalletTransactionEngine {

    public static void main(String[] args) {

        System.out.println();

        System.out.println("Digital Wallet System:");

        try {

            DigitalWallet d1 = new BasicWallet("12", "lime", 100000);

            d1.getDetails();

            d1.addMoney(100, 234);
            d1.addMoney(200, "234");
            d1.getDetails();

            d1.transfer(100);
            d1.transfer(2000, "by upi...");
            d1.getDetails();

            DigitalWallet d2 = new PremiumWallet("34", "nime", 3000000);

            d2.getDetails();

            d2.addMoney(100, 345);
            d2.addMoney(2020, "347");

            d2.getDetails();

            d2.transfer(100);
            d2.transfer(300, "by card...");

            d2.getDetails();

        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }

        catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        finally {
            System.out.println("Transction completed...");
        }

        System.out.println();

    }

}

interface WalletOperations {
    void addMoney(long amount, int upiId) throws InvalidAmountException;

    void addMoney(long amount, String cardNumber) throws InvalidAmountException;

    void transfer(long amount, String remarks) throws InvalidAmountException;

    void transfer(long amount) throws InvalidAmountException;

    void getDetails();
}

abstract class DigitalWallet implements WalletOperations {
    private String walletId;
    private String ownername;
    private long balance;

    DigitalWallet(String walletId, String ownername, long balance) throws InvalidAmountException {
        setWalletId(walletId);
        setOwnerName(ownername);
        setBalance(balance);
    }

    void setWalletId(String walletId) {
        if (walletId == null || walletId.isEmpty()) {
            throw new IllegalArgumentException("invalid wallet id...");
        }

        else {
            this.walletId = walletId;
        }
    }

    void setOwnerName(String ownername) {
        if (ownername == null || ownername.isEmpty()) {
            throw new IllegalArgumentException("invalid name...");
        }

        else {
            this.ownername = ownername;
        }
    }

    void setBalance(long balance) throws InvalidAmountException {
        if (balance < 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            this.balance = balance;
        }
    }

    String getWalletId() {
        return this.walletId;
    }

    String getOwnerName() {
        return this.ownername;
    }

    long getBalance() {
        return this.balance;
    }

}

class BasicWallet extends DigitalWallet {

    BasicWallet(String walletId, String ownername, long balance) throws InvalidAmountException {
        super(walletId, ownername, balance);
    }

    private final long minbalance = 100;

    @Override
    public void addMoney(long amount, int upiId) throws InvalidAmountException {

        if (amount < 0) {
            throw new InvalidAmountException("amount can't be less than 0...");
        }

        else {
            long curr_bal = getBalance();
            System.out.println("money added:" + amount);
            System.out.println("mode:upi");
            System.out.println("upiId:" + upiId);
            setBalance(curr_bal + amount);
            System.out.println();
        }

    }

    @Override
    public void addMoney(long amount, String cardNumber) throws InvalidAmountException {

        if (amount < 0) {
            throw new InvalidAmountException("amount can't be less than 0...");
        }

        else {
            long curr_bal = getBalance();
            System.out.println("money added:" + amount);
            System.out.println("mode:card");
            System.out.println("card number:" + cardNumber);
            setBalance(curr_bal + amount);
            System.out.println();
        }

    }

    @Override
    public void transfer(long amount, String remarks) throws InvalidAmountException {

        long curr_bal = getBalance();

        if (amount > curr_bal) {
            throw new InsufficientBalanceException("insufficent balance...");
        }

        else if (curr_bal - amount < minbalance) {
            throw new InsufficientBalanceException("limit exceeded...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            System.out.println("transfered:" + amount);
            System.out.println("remarks:" + remarks);
            setBalance(curr_bal - amount);
            System.out.println();
        }

    }

    @Override
    public void transfer(long amount) throws InvalidAmountException {

        long curr_bal = getBalance();

        if (amount > curr_bal) {
            throw new InsufficientBalanceException("insufficent balance...");
        }

        else if (curr_bal - amount < minbalance) {
            throw new InsufficientBalanceException("limit exceeded...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            System.out.println("transfered:" + amount);
            setBalance(curr_bal - amount);
            System.out.println();
        }

    }

    @Override
    public void getDetails() {
        System.out.println("Basic Wallet:");
        System.out.println("wallet Id:" + getWalletId());
        System.out.println("owner name:" + getOwnerName());
        System.out.println("balance:" + getBalance());
        System.out.println();
    }

}

class PremiumWallet extends DigitalWallet {

    PremiumWallet(String walletId, String ownername, long balance) throws InvalidAmountException {
        super(walletId, ownername, balance);
    }

    private final long cashback = 10;

    @Override
    public void addMoney(long amount, int upiId) throws InvalidAmountException {

        if (amount < 0) {
            throw new InvalidAmountException("amount can't be less than 0...");
        }

        else {
            long curr_bal = getBalance();
            System.out.println("money added:" + amount);
            System.out.println("mode:upi");
            System.out.println("upiId:" + upiId);
            setBalance(curr_bal + amount);
            System.out.println();
        }

    }

    @Override
    public void addMoney(long amount, String cardNumber) throws InvalidAmountException {

        if (amount < 0) {
            throw new InvalidAmountException("amount can't be less than 0...");
        }

        else {
            long curr_bal = getBalance();
            System.out.println("money added:" + amount);
            System.out.println("mode:card");
            System.out.println("card number:" + cardNumber);
            setBalance(curr_bal + amount);
            System.out.println();
        }

    }

    @Override
    public void transfer(long amount, String remarks) throws InvalidAmountException {

        long curr_bal = getBalance();

        if (amount > curr_bal) {
            throw new InsufficientBalanceException("insufficent balance...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            System.out.println("transfered:" + amount);
            System.out.println("remarks:" + remarks);
            setBalance(curr_bal - amount + cashback);
            System.out.println();
        }

    }

    @Override
    public void transfer(long amount) throws InvalidAmountException {

        long curr_bal = getBalance();

        if (amount > curr_bal) {
            throw new InsufficientBalanceException("insufficent balance...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            System.out.println("transfered:" + amount);
            setBalance(curr_bal - amount + cashback);
            System.out.println();
        }

    }

    @Override
    public void getDetails() {
        System.out.println("Premium Wallet:");
        System.out.println("wallet Id:" + getWalletId());
        System.out.println("owner name:" + getOwnerName());
        System.out.println("balance:" + getBalance());
        System.out.println();
    }

}

class InvalidAmountException extends Exception {

    InvalidAmountException(String message) {
        super(message);
    }

}

class InsufficientBalanceException extends RuntimeException {
    InsufficientBalanceException(String message) {
        super(message);
    }
}