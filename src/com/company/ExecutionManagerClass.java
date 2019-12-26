package com.company;

import java.util.ArrayList;
import java.util.Date;

public class ExecutionManagerClass implements ExecutionManager {
    @Override
    public Context execute(Runnable... tasks) {
        ArrayList<Thread> threadList = new ArrayList<>();
        //Создаю для каждой таски свой поток
        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threadList.add(thread);
        }

        ContextClass context = new ContextClass(threadList);
        //запускаю
        for (Thread thread : threadList) {
            try {
                Date date = new Date();
                thread.start();
                Thread waitThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            thread.join();
                            context.incCompletedCount();
                            context.addTimeToStatistic((int) ((new Date()).getTime() - date.getTime()));
                        } catch (Exception e) {
                           System.out.println("this is exception");
                        }

                    }
                });
                waitThread.start();



            } catch (Exception e) {
                System.out.println("I caught error");
                context.incFailedCount();
            }
        }

        context.doCallBack();
        return context;

    }
}
