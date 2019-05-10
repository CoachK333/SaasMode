package com.boot.data.daily_work.demand_management.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtil {
    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.clear();
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static long generateId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return bytesToLong(uuid.getBytes());
    }
}
