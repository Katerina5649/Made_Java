package com.company;

class Execute implements Runnable {
    String name;

    Execute(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " start");
        Thread thread = Thread.currentThread();
        try {
            thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (name.equals("error")){
            try {
                throw new Exception("error");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " finished");
    }
}