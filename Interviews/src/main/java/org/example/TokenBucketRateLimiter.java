package org.example;

import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {

    private final long capacity;
    private final double refillTokensPerNano;
    private double availableTokens;
    private long lastRefillTime;

    public TokenBucketRateLimiter(
            long capacity, long refillTokens, long refillPeriod,
            TimeUnit unit) {

        if (capacity <= 0 || refillTokens <= 0 || refillPeriod <= 0) {
            throw new IllegalArgumentException(
                    "Rate limiter parameters must be positive");
        }

        this.capacity = capacity;
        this.availableTokens = capacity;
        this.refillTokensPerNano =
                (double) refillTokens / unit.toNanos(refillPeriod);
        this.lastRefillTime = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        refill();

        if (availableTokens >= 1.0) {
            availableTokens -= 1.0;
            return true;
        }

        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        long elapsed = now - lastRefillTime;

        availableTokens = Math.min(
                capacity,
                availableTokens + elapsed * refillTokensPerNano
        );

        lastRefillTime = now;
    }

    public static void main(String[] args)
            throws InterruptedException {

        TokenBucketRateLimiter limiter =
                new TokenBucketRateLimiter(
                        5, 5, 1, TimeUnit.SECONDS);

        for (int i = 1; i <= 7; i++) {
            System.out.println(
                    "Request " + i + ": " + limiter.allowRequest());
        }

        Thread.sleep(1000);
        for (int i = 1; i <= 3; i++) {
            System.out.println(
                    "Request " + i + ": " + limiter.allowRequest());
        }

        System.out.println(
                "After refill: " + limiter.allowRequest());
    }
}

