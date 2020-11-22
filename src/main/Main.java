package main;

import main.db.DbHandler;

public class Main extends Thread{
    static DbHandler dbHandler = new DbHandler();
    
    public static void main(String[] args) {
        
        double start, stop;
        int n = 20;
        start = System.nanoTime();        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < n; j++)
                (new Main()).start();
            sleepExecution(2);
        }
        stop = System.nanoTime();
        System.out.println("\n pool se tardó: " + (stop - start) + " ns");
        
                
    }
    @Override
    public void run() {
        //dbHandler.insertPerson("Eduardo");
        dbHandler.insertPersonWithoutPool("Horacio");
    }
    
    public static void sleepExecution(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) { }
    }
    
}