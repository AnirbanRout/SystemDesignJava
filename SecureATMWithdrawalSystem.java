package PracticeQues;

public class SecureATMWithdrawalSystem {

    public static void main(String[] args) {

        System.out.println();

        System.out.println("----------");
        System.out.println("Atm System:");

        try {

            ATMAccount a1 = new SalaryAccount("1234", "lime", 10000);

            a1.details();

            a1.withdraw(100, 12);
            a1.withdraw(200);

            a1.withdraw(200);
            a1.withdraw(0);
            a1.withdraw(-100);

            a1.details();

            ATMAccount a2 = new SavingsAccount("234", "nime", 30000);

            a2.details();

            a2.withdraw(100);
            a2.withdraw(200, 24);
            a2.withdraw(300);
            a2.withdraw(400, 56);

            a2.withdraw(300);
            a2.withdraw(0);
            a2.withdraw(-100);

            a2.details();

        }

        catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }

        catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------");

        System.out.println();

    }

}

interface AccountsFunctions {
    void withdraw(long amount, int pin) throws InvalidTransactionException;

    void withdraw(long amount) throws InvalidTransactionException;

    void details();
}

abstract class ATMAccount implements AccountsFunctions {
    private String accountNumber;
    private String holdername;
    private long balance;

    ATMAccount(String accountNumber, String holdername, long balance) {
        setAccountNumber(accountNumber);
        setHolderName(holdername);
        setBalance(balance);
    }

    protected void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("invalid account number...");
        }

        else {
            this.accountNumber = accountNumber;
        }
    }

    protected void setHolderName(String holdername) {
        if (holdername == null || holdername.isEmpty()) {
            throw new IllegalArgumentException("invalid holder name...");
        }

        else {
            this.holdername = holdername;
        }
    }

    protected void setBalance(long balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("invalid starting balance...");
        }

        else {
            this.balance = balance;
        }
    }

    protected String getAccountNumber() {
        return this.accountNumber;
    }

    protected String getHolderName() {
        return this.holdername;
    }

    protected long getBalance() {
        return this.balance;
    }

}

class SavingsAccount extends ATMAccount {

    SavingsAccount(String accountNumber, String holdername, long balance) {
        super(accountNumber, holdername, balance);
    }

    private int min_balance = 100;
    private int limit = 4;

    @Override
    public void withdraw(long amount, int pin) throws InvalidTransactionException {

        long curr_bal = getBalance();

        if (amount <= 0) {
            throw new InvalidTransactionException("invalid amount...");
        }

        else if (limit <= 0) {
            throw new InvalidTransactionException("limit exhuasted...");
        }

        else if (amount > curr_bal) {
            throw new InsufficientFundsException("insufficient funds...");
        }

        else if (curr_bal - amount < min_balance) {
            throw new InsufficientFundsException("insufficient balance...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            System.out.println("pin:" + pin);
            setBalance(curr_bal - amount);
            limit--;
            System.out.println();
        }

    }

    @Override
    public void withdraw(long amount) throws InvalidTransactionException {

        long curr_bal = getBalance();

        if (amount <= 0) {
            throw new InvalidTransactionException("invalid amount...");
        }

        else if (limit <= 0) {
            throw new InvalidTransactionException("limit exhuasted...");
        }

        else if (amount > curr_bal) {
            throw new InsufficientFundsException("insufficient funds...");
        }

        else if (curr_bal - amount < min_balance) {
            throw new InsufficientFundsException("insufficient balance...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            setBalance(curr_bal - amount);
            limit--;
            System.out.println();
        }

    }

    @Override
    public void details() {
        System.out.println("Savings Account:");
        System.out.println("account number:" + getAccountNumber());
        System.out.println("holder name:" + getHolderName());
        System.out.println("balance:" + getBalance());
        System.out.println();
    }

}

class SalaryAccount extends ATMAccount {

    private int min_balance = 500;
    private int limit = 2;

    SalaryAccount(String accountNumber, String holdername, long balance) {
        super(accountNumber, holdername, balance);
    }

    @Override
    public void withdraw(long amount, int pin) throws InvalidTransactionException {

        long curr_bal = getBalance();

        if (amount <= 0) {
            throw new InvalidTransactionException("invalid amount...");
        }

        else if (limit <= 0) {
            throw new InvalidTransactionException("limit exhausted...");
        }

        else if (amount > curr_bal) {
            throw new InsufficientFundsException("insufficient fund...");
        }

        else if (curr_bal - amount < min_balance) {
            throw new InsufficientFundsException("insufficient balance...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            System.out.println("pin:" + pin);
            setBalance(curr_bal - amount);
            limit--;
            System.out.println();
        }

    }

    @Override
    public void withdraw(long amount) throws InvalidTransactionException {

        long curr_bal = getBalance();

        if (amount <= 0) {
            throw new InvalidTransactionException("invalid amount...");
        }

        else if (limit <= 0) {
            throw new InvalidTransactionException("limit exhausted...");
        }

        else if (amount > curr_bal) {
            throw new InsufficientFundsException("insufficient funds...");
        }

        else if (curr_bal - amount < min_balance) {
            throw new InsufficientFundsException("insufficient balance...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            setBalance(curr_bal - amount);
            limit--;
            System.out.println();
        }

    }

    @Override
    public void details() {
        System.out.println("Salary Account:");
        System.out.println("account number:" + getAccountNumber());
        System.out.println("holder name:" + getHolderName());
        System.out.println("balance:" + getBalance());
        System.out.println();
    }

}

class InvalidTransactionException extends Exception {

    InvalidTransactionException(String message) {
        super(message);
    }

}

class InsufficientFundsException extends RuntimeException {

    InsufficientFundsException(String message) {
        super(message);
    }

}