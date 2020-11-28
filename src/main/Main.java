package main;

import main.db.DbHandler;


public class Main extends Thread{
    
    public static void main(String[] args) {
        
        DbHandler dbHandler = new DbHandler();
        dbHandler.insertPerson("Una persona con ñ");
        
    }
    
}