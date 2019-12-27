package com.company;

import java.util.ArrayList;

class Execute implements Runnable {
    String name;
    ArrayList<Integer> list;

    Execute(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " start");
        Thread thread = Thread.currentThread();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (name.equals("error")) {
            //будет NullPointerException
            list.add(0);

        } else {
            System.out.println(name + " finished");
        }

    }
}