package guiao1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //exerciseIncrement(10, 100);
        //exerciseBankNoLock(10, 1000, 1);
        exerciseBankWithLock(10, 1000, 1);
    }

        //Exercicio 1 -> Criar N threads que contam de 1 até I
         private static void exerciseIncrement(int N, long I) throws InterruptedException {
             System.out.println("$$Exercicio 1 Increment$$");

             Thread[] threads = new Thread[N];

            // criar threads
            for (int i = 0; i < N; i++) {
                threads[i] = new Thread(new Increment(I), "Thread-" + i);
            }

            // arrancar
            for (Thread t : threads) t.start();

            // esperar que terminem
            for (Thread t : threads) t.join();

            System.out.println(Thread.currentThread().getName() + ": finished!");
        }

        // Exercício 2 -> Crie a partir dele um programa em que N threads concorrentes fazem I
        // depósitos de um mesmo valor V nessa conta.
        // Quando todas as threads terminarem, observe se o valor final na conta é o valor
        //esperado de N × I × V
        private static void exerciseBankNoLock(int N, long I, int V) throws InterruptedException {
            Bank bank = new Bank();

            Thread[] threads = new Thread[N];
            //Criar N threads
            for(int i = 0; i < N; i++) {
                threads[i] = new Thread(() -> { // fazer () -> {...}  é um lambda basicamente criar um runnable
                    for(long j = 0; j < I; j++) bank.deposit(V); // a Thread vai chamar I vezes o bank.deposit(V)
                }, "T" + i); // vai aparecer T0...T1.. quando chamo Thread.currentThread().getName()
            }

            // iniciar as threads
            for(int i = 0; i < N; i++) {
                threads[i].start();
            }

            //esperar que as threads acabem
            for(int i = 0; i < N; i++) {
                threads[i].join();
            }

            long expected= (long) N * I * V;
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + bank.balance());
            //Nota: aumentar o N ou I aumenta a probabilidade de perdas
        }

        // Exercicio 3 -> 3 Partindo do exercício anterior e usando um ReentrantLock, crie uma exclusão mútua que
        //garanta a atomicidade das operações de consulta e depósito.
        private static void exerciseBankWithLock(int N, long I, int V) throws InterruptedException {
            Bank bank = new Bank();
            BanckLock banckLock = new BanckLock(bank);

            Thread[] threads = new Thread[N];
            //Criar N threads
            for(int i = 0; i < N; i++) {
                threads[i] = new Thread(() -> { // fazer () -> {...}  é um lambda basicamente criar um runnable
                    for(long j = 0; j < I; j++) banckLock.depositLock(V); // a Thread vai chamar I vezes o bank.deposit(V)
                }, "T" + i); // vai aparecer T0...T1.. quando chamo Thread.currentThread().getName()
            }

            // iniciar as threads
            for(int i = 0; i < N; i++) {
                threads[i].start();
            }

            //esperar que as threads acabem
            for(int i = 0; i < N; i++) {
                threads[i].join();
            }

            long expected= (long) N * I * V;
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + banckLock.balanceLock());

        }


    }
