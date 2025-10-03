package guiao1;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Versão do banco protegida com locks

public class BanckLock {
    private final Bank bank;
    private final Lock lock = new ReentrantLock();

    public BanckLock(Bank bank) {
        this.bank = bank;
    }

    public void depositLock(int value) {
        lock.lock();// entra na secção critica
        try {
            bank.deposit(value);
        } finally {
            lock.unlock(); // garante que o lock é libertado mesmo se der erro
        }
    }

    public int balanceLock() {
        lock.lock();
        try {
            return bank.balance();
        } finally {
            lock.unlock();
        }
    }
}
