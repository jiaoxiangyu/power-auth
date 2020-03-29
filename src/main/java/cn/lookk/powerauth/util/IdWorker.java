package cn.lookk.powerauth.util;


import cn.lookk.handleexception.exception.Assert;

import java.util.Date;

public class IdWorker {
    private long workerId;//机器ID
    private long datacenterId;//数据中心ID
    private long sequence = 0L;

    private static long twepoch = 1288834974657L;

    private static long workerIdBits = 5L;
    private static long datacenterIdBits = 5L;
    private static long maxWorkerId = -1L ^ (-1L << (int)workerIdBits);
    private static long maxDatacenterId = -1L ^ (-1L << (int)datacenterIdBits);
    private static long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << (int)sequenceBits);

    private long lastTimestamp = -1L;
    private static Object syncRoot = new Object();


    public IdWorker(long workerId, long datacenterId){
        try {
            // sanity check for workerId
            Assert.isTrue(workerId > maxWorkerId || workerId < 0, 412, String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));

            Assert.isTrue(datacenterId > maxDatacenterId || datacenterId < 0, 412, String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));

            this.workerId = workerId;
            this.datacenterId = datacenterId;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long nextId() {

            synchronized (syncRoot) {
                long timestamp = timeGen();
                try {

                    Assert.isTrue(timestamp < lastTimestamp, 412, String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));

                    if (lastTimestamp == timestamp) {
                        sequence = (sequence + 1) & sequenceMask;
                        if (sequence == 0) {
                            timestamp = tilNextMillis(lastTimestamp);
                        }
                    } else {
                        sequence = 0L;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lastTimestamp = timestamp;

                return ((timestamp - twepoch) << (int) timestampLeftShift) | (datacenterId << (int) datacenterIdShift) | (workerId << (int) workerIdShift) | sequence;
            }

    }


    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return new Date().getTime();
    }
}

