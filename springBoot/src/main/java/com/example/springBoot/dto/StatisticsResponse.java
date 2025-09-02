package com.example.springBoot.dto;

import java.util.DoubleSummaryStatistics;

public class StatisticsResponse {

    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public StatisticsResponse(DoubleSummaryStatistics doubleSummaryStatistics){
        this.count=doubleSummaryStatistics.getCount();
        this.sum= doubleSummaryStatistics.getSum();
        this.avg=doubleSummaryStatistics.getAverage();
        this.min=doubleSummaryStatistics.getMin();
        this.max=doubleSummaryStatistics.getMax();
    }

    public long getCount() {
        return count;
    }

    public double getAvg() {
        return avg;
    }

    public double getSum() {
        return sum;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
