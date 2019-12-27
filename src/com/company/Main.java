package com.company;

public class Main {
    static class CallBack implements Runnable {

        @Override
        public void run() {
            System.out.println("This is callback");
        }
    }

    //Тестирование
    public static void main(String[] args) throws InterruptedException {
        CallBack callBack = new CallBack();
        Execute[] tasks = new Execute[]{new Execute("1"), new Execute("2"), new Execute("3")
                , new Execute("4"), new Execute("5"), new Execute("error")};
        ExecutionManager executionManager = new ExecutionManagerClass();
        Context context = executionManager.execute(tasks);
        context.onFinish(callBack);
        Thread thread = Thread.currentThread();
        thread.sleep(1000);
        context.interrupt();
        context.awaitTermination();
        System.out.println(context.getCompletedTaskCount());
        System.out.println(context.getFailedTaskCount());
        ExecutionStatistics statistic = context.getStatistics();

    }

}
