package View;

import Controller.ShowSemaforo;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaforo= new Semaphore(1);



        for (int i=1; i<=300; i++){
            Thread show= new ShowSemaforo(semaforo);
            show.start();
        }
    }
}
