package PracticeQues;

public class SecureBankingTransactionEngine {

    public static void main(String[] args) {

        System.out.println();

        System.out.println("-------------------");
        System.out.println("Banking Transaction System:");

        try {
            BankAccount a1 = new CurrentAccount("1234", "lime", 10000);

            a1.showdetails();

            a1.withdraw(100, "atm withdrawal");
            a1.withdraw(10);
            a1.showdetails();

            a1.deposit(200000, "cash");
            a1.showdetails();

            BankAccount a2 = new SavingsAccount("2345", "nime", 2000);

            a2.showdetails();

            a2.withdraw(20, "cash withdrawal");
            a2.withdraw(20);
            a2.showdetails();

            a2.withdraw(1000000);

            a2.deposit(100, "online");
            a2.showdetails();

        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

    }

}

abstract class BankAccount {

    private String accountnumber;
    private String holdername;
    private long balance;

    BankAccount(String accountnumber, String holdername, long balance) throws InvalidAmountException {
        setAccountNumber(accountnumber);
        setHolderName(holdername);
        setBalance(balance);
    }

    void setAccountNumber(String accountnumber) {
        if (accountnumber == null || accountnumber.isEmpty()) {
            throw new IllegalArgumentException("invalid account number...");
        }

        else {
            this.accountnumber = accountnumber;
        }
    }

    void setHolderName(String holdername) {
        if (holdername == null || holdername.isEmpty()) {
            throw new IllegalArgumentException("invalid holder name...");
        }

        else {
            this.holdername = holdername;
        }
    }

    void setBalance(long balance) throws InvalidAmountException {
        if (balance < 0) {
            throw new InvalidAmountException("invalid balance...");
        }

        else {
            this.balance = balance;
        }
    }

    String getAccountNumber() {
        return this.accountnumber;
    }

    String getHolderName() {
        return this.holdername;
    }

    long getBalance() {
        return this.balance;
    }

    abstract void withdraw(long amount, String remarks) throws InvalidAmountException;

    abstract void withdraw(long amount) throws InvalidAmountException;

    abstract void deposit(long amount, String mode) throws InvalidAmountException;

    abstract void deposit(long amount) throws InvalidAmountException;

    abstract void showdetails();

}

class SavingsAccount extends BankAccount {

    SavingsAccount(String accountnumber, String holdername, long balance) throws InvalidAmountException {
        super(accountnumber, holdername, balance);
        System.out.println("Savings Account:");
    }

    @Override
    void showdetails() {
        System.out.println("account number:" + getAccountNumber());
        System.out.println("holder name:" + getHolderName());
        System.out.println("balance:" + getBalance());
        System.out.println();
    }

    @Override
    void withdraw(long amount, String remarks) throws InvalidAmountException {
        long avl_bal = getBalance();

        if (amount > avl_bal) {
            throw new InsufficientBalanceException("insufficient balance...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid withdraw amount...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            System.out.println("remarks:" + remarks);
            setBalance(avl_bal - amount);
            System.out.println();
        }
    }

    @Override
    void withdraw(long amount) throws InvalidAmountException {
        long avl_bal = getBalance();

        if (amount > avl_bal) {
            throw new InsufficientBalanceException("insufficient balance...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid withdraw amount...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            setBalance(avl_bal - amount);
            System.out.println();
        }
    }

    @Override
    void deposit(long amount, String mode) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            long curr_bal = getBalance();
            setBalance(curr_bal + amount);
            System.out.println("deposited:" + amount);
            System.out.println("mode:" + mode);
            System.out.println();
        }
    }

    @Override
    void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            long curr_bal = getBalance();
            setBalance(curr_bal + amount);
            System.out.println("deposited:" + amount);
            System.out.println("mode:cash");
            System.out.println();
        }
    }

}

class CurrentAccount extends BankAccount {

    CurrentAccount(String accountnumber, String holdername, long balance) throws InvalidAmountException {
        super(accountnumber, holdername, balance);
        System.out.println("Current Account:");
    }

    @Override
    void showdetails() {
        System.out.println("account number:" + getAccountNumber());
        System.out.println("holder name:" + getHolderName());
        System.out.println("balance:" + getBalance());
        System.out.println();
    }

    @Override
    void withdraw(long amount, String remarks) throws InvalidAmountException {
        long avl_bal = getBalance();

        if (amount > avl_bal) {
            throw new InsufficientBalanceException("insufficient balance...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid withdraw amount...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            System.out.println("remarks:" + remarks);
            setBalance(avl_bal - amount);
            System.out.println();
        }
    }

    @Override
    void withdraw(long amount) throws InvalidAmountException {
        long avl_bal = getBalance();

        if (amount > avl_bal) {
            throw new InsufficientBalanceException("insufficient balance...");
        }

        else if (amount <= 0) {
            throw new InvalidAmountException("invalid withdraw amount...");
        }

        else {
            System.out.println("withdrawn:" + amount);
            setBalance(avl_bal - amount);
            System.out.println();
        }
    }

    @Override
    void deposit(long amount, String mode) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            long curr_bal = getBalance();
            setBalance(curr_bal + amount);
            System.out.println("deposited:" + amount);
            System.out.println("mode:" + mode);
            System.out.println();
        }
    }

    @Override
    void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("invalid amount...");
        }

        else {
            long curr_bal = getBalance();
            setBalance(curr_bal + amount);
            System.out.println("deposited:" + amount);
            System.out.println("mode:cash");
            System.out.println();
        }
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