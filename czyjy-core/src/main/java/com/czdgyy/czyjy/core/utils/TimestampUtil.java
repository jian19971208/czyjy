package com.czdgyy.czyjy.core.utils;

import java.time.Instant;

public class TimestampUtil {

    private TimestampUtil(){

    }

    public static long getNowMicros() {
        Instant now = Instant.now();
        return now.getEpochSecond() * 1_000_000 + now.getNano() / 1_000;
    }

}
