package Controller;

import java.util.concurrent.Semaphore;

public class ShowSemaforo extends Thread {


    private int tempo;
    private int timeout;
    private static int totalingressos= 100;

    private Semaphore semaforo;


    public ShowSemaforo(Semaphore semaforo){
        this.semaforo= semaforo;
    }

    @Override
    public void run() {
        login();
        compra();
        try {
            semaforo.acquire();
            validacao();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaforo.release();
        }
    }

    private void login() {
        tempo= (int) (Math.random()* 1951)+ 50;

        if (tempo > 1000){
            System.out.println("O cliente #" + getId() + " recebeu um timeout.");
            timeout= 1;
        }
    }

    private void compra() {
        if (timeout!=1){
            tempo= (int) (Math.random()* 2001)+ 1000;
            if (tempo > 2500){
                System.out.println("O cliente #" + getId() + " estourou o tempo de sessão");
                timeout = 1;
            }
        }

    }

    private void validacao() {
        int ingressos= (int) (Math.random()* 4) + 1;
        if (ingressos <= totalingressos){
            System.out.println("O cliente #"+ getId() +" comprou "+ ingressos +" ingressos.");
            totalingressos-= ingressos;
            System.out.println("Ingressos disponíveis: "+ totalingressos);
        }
        else {
            System.out.println("O cliente #"+ getId() +" não conseguiu comprar, pois os ingressos estavam indisponíveis. ");
        }
    }
}
