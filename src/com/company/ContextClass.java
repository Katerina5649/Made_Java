package com.company;

import java.util.ArrayList;

public class ContextClass implements Context {
    private final ArrayList<Thread> threadList;
    private boolean isInterrupted = false;
    private int completedCount = 0;
    private int failedCount = 0;
    private Runnable callBack = null;
    private ExecutionStatisticsClass executionStatistics;

    public  void addTimeToStatistic(int time){
        executionStatistics.addTimeToStatistic(time);
    }

    public void doCallBack() {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (Thread task : threadList) {
                            try {
                                task.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (callBack != null) {
                                callBack.run();
                            }
                        }
                    }
                }
        );
        thread.start();

    }


    ContextClass(ArrayList<Thread> threadList) {
        this.threadList = threadList;
        executionStatistics = new ExecutionStatisticsClass();
    }

    public void incFailedCount() {
        this.failedCount++;
    }

    public void incCompletedCount() {
        this.completedCount++;
    }

    ;

    //возвращает количество тасков, которые на текущий момент успешно выполнились.
    @Override
    public int getCompletedTaskCount() {
        return completedCount;
    }

    //возвращает количество тасков, при выполнении которых произошел Exception.
    @Override
    public int getFailedTaskCount() {
        return failedCount;
    }

    //отменяет выполнения тасков, которые еще не начали выполняться.
    @Override
    //todo
    public void interrupt() {
        this.isInterrupted = true;
        for (Thread task : threadList) {
            task.stop();
        }
    }

    //возвращает количество тасков, которые не были выполнены из-за отмены (вызовом предыдущего метода).
    @Override
    public int getInterruptedTaskCount() {
        if (isInterrupted) {
            return this.threadList.size() - this.failedCount - this.completedCount;
        }
        return 0;
    }


    //вернет true, если все таски были выполнены или отменены, false в противном случае.
    @Override
    public boolean isFinished() {
        return (failedCount == 0) && (isInterrupted || (threadList.size() == completedCount));
    }

    // После завершения всех тасков должен выполниться callback (ровно 1 раз).
    @Override
    public void onFinish(Runnable callback) {
        this.callBack = callback;

    }

    //возвращает статистиску по времени выполнения задач.
    @Override
    public ExecutionStatistics getStatistics() {
        return executionStatistics;
    }

    //блокирует текущий поток, из которого произошел вызов, до тех пор пока не выполнятся все задачи.
    @Override
    public void awaitTermination() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while((this.failedCount + this.completedCount != threadList.size()) && (!isInterrupted)){
            currentThread.wait();
        }

    }
}
