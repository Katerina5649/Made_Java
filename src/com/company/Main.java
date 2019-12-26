package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Execute[] tasks = new Execute[]{new Execute("1"), new Execute("2"), new Execute("3")
                , new Execute("4"), new Execute("5") , new Execute("error")};
        ExecutionManager executionManager = new ExecutionManagerClass();
        Context context  = executionManager.execute(tasks);
        Thread thread = Thread.currentThread();
        thread.sleep(2000);


    }
}
