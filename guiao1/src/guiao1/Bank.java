package guiao1;

class Bank {
    //classe interna(nested class) funciona como se fosse só uma conta
    //que depois se juntam varias dessas e populam o banco
    private static class Account {
        private int balance;

        Account(int balance) { this.balance = balance; }

        int balance() { return balance; }

        boolean deposit(int value) {
            balance += value;
            return true;
        }

    }


    // Our single account, for now
    private Account savings = new Account(0);

    // Account balance
    public int balance() {
        return savings.balance();
    }

    // Deposit
    boolean deposit(int value) {
        return savings.deposit(value);
    }

}