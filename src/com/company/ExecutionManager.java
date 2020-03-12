package com.company;


public interface ExecutionManager {
    Context execute(Runnable... tasks);
}
