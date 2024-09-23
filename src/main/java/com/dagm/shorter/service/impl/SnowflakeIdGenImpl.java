package com.dagm.shorter.service.impl;


import com.dagm.shorter.service.LeafGenService;

public class SnowflakeIdGenImpl implements LeafGenService {

    /**
     * Start timestamp (2024-09-23 17:00:20)
     */
    private final long twepoch = 1727082020000L;

    /**
     * Number of bits occupied by the machine ID.
     */
    private final long workerIdBits = 5L;

    /**
     * Number of bits occupied by the data identifier ID.
     */
    private final long datacenterIdBits = 5L;

    /**
     * Maximum supported machine ID is 31.
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /**
     * Maximum supported data identifier ID is 31.
     */
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);

    /**
     * Number of bits occupied by the sequence in the ID.
     */
    private final long sequenceBits = 12L;

    /**
     * Shift the machine ID left by 12 bits.
     */
    private final long workerIdShift = sequenceBits;

    /**
     * Shift the data identifier ID left by 17 bits (12 + 5).
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * Shift the timestamp left by 22 bits (5 + 5 + 12).
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * Generate the sequence mask, which is 4095 (0b111111111111 = 0xfff = 4095).
     */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /**
     * Worker machine ID (0 to 31).
     */
    private long workerId;

    /**
     * Data center ID (0 to 31).
     */
    private long datacenterId;

    /**
     * Sequence within milliseconds (0 to 4095).
     */
    private long sequence = 0L;

    /**
     * Timestamp of the last generated ID.
     */
    private long lastTimestamp = -1L;


    /**
     * @param workerId     Worker ID (0 to 31).
     * @param datacenterId Data center ID (0 to 31).
     */
    public SnowflakeIdGenImpl(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("workerId cannot be greater than %d or less than 0", maxWorkerId)
            );
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenterId cannot be greater than %d or less than 0", maxDatacenterId)
            );
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }


    /**
     * Obtain the next ID (thread-safe).
     *
     * @return Snowflake ID
     */
    public synchronized Long getLeafId() {
        long timestamp = timeGen();

        // If the current time is less than the last ID generation timestamp, it indicates that the system clock has moved backwards.
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Rejecting ID generation for %d milliseconds", lastTimestamp - timestamp)
            );
        }

        if (lastTimestamp == timestamp) {
            // Within the same millisecond, the sequence number increments.
            sequence = (sequence + 1) & sequenceMask;
            // Sequence number overflow.
            if (sequence == 0L) {
                // Block until the next millisecond to obtain a new timestamp.
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // When the timestamp changes, the sequence number is reset.
            sequence = 0L;
        }

        // Timestamp of the last generated ID.
        lastTimestamp = timestamp;

        // Shift and combine using bitwise OR to form a 64-bit ID.
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * Block until the next millisecond to obtain a new timestamp.
     *
     * @param lastTimestamp Timestamp of the last generated ID.
     * @return Current timestamp.
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * "Return the current time in milliseconds."
     *
     * @return Current timestamp.
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
