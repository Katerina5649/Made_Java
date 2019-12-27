package com.company;

import java.util.ArrayList;
import java.util.Date;

public class ExecutionManagerClass implements ExecutionManager {
    @Override
    public Context execute(Runnable... tasks) {
        ContextClass context = new ContextClass();
        ArrayList<Thread> threadList = new ArrayList<>();

        //Создаю для каждой таски свой поток
        for (Runnable task : tasks) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Date date = new Date();
                        task.run();
                        context.addTimeToStatistic((int) ((new Date()).getTime() - date.getTime()));
                        context.incCompletedCount();
                    } catch (Exception e) {
                        context.incFailedCount();
                    }
                }
            });
            context.addThreadToList(thread);
            threadList.add(thread);
        }

        //запускаю
        for (Thread thread : threadList) {
            thread.start();
        }

        context.doCallBack();
        return context;

    }
}
