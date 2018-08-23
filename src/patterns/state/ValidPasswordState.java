package patterns.state;

/// Valid passoword state support all operations
public class ValidPasswordState implements State {
    /// The constructor needs a caller class in the field to change state
    ATM atm;

    public ValidPasswordState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void printBalance(Account account) {
        System.out.println("Current balance is: " + account.getBalance());
    }

    @Override
    public void cashOut(Account account, Double amount) {
        account.update(-amount);
        System.out.println("Cashed $" + amount);
        this.printBalance(account);
    }

    @Override
    public void deposit(Account account, Double amount) {
        account.update(amount);
        System.out.println("Deposit $" + amount);
        this.printBalance(account);
    }
}
