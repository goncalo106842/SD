package guiao1;

// A classe implemeta Runnable para poder ser executada numa Thread
public class Increment implements Runnable {
    private final long I; // até onde esta thread vai contar

    //Construtor -> recebe o numero até onde queremos contar
    public Increment(long I) {
        this.I = I;
    }

    //Método que é executado quando a thread is started
    @Override
    public void run() {
        for(long i = 1; i <= I; i++) {
            //Thread.currentThread().getName() devolve o nome da thread que está a correr
            System.out.println(Thread.currentThread().getName() + "->" + i);
        }
    }
}
