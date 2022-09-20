package com.solvd.metro.Conn;

public class MyThread extends Thread {

    @Override
    public void run(){
        for(int i = 0; i < 3; i++){
            System.out.println("Try to create and run thread" + MyThread.currentThread().getName());
        }

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
    }
}
