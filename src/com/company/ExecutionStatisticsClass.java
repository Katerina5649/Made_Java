package com.company;

public class ExecutionStatisticsClass implements ExecutionStatistics {
    private int minExecutionTimeInMs = 0, maxExecutionTimeInMs = 0, averageExecutionTimeInMs = 0;
    private int dataSize = 0;


    public void addTimeToStatistic(int time) {
        minExecutionTimeInMs = Math.min(minExecutionTimeInMs, time);
        maxExecutionTimeInMs = Math.max(maxExecutionTimeInMs, time);
        averageExecutionTimeInMs += time;
        if (minExecutionTimeInMs == 0) {
            minExecutionTimeInMs = time;
        }
        dataSize++;
    }

    @Override
    public int getMinExecutionTimeInMs() {
        return minExecutionTimeInMs;
    }

    @Override
    public int getMaxExecutionTimeInMs() {
        return maxExecutionTimeInMs;
    }

    @Override
    public int getAverageExecutionTimeInMs() {
        return averageExecutionTimeInMs;
    }
}
