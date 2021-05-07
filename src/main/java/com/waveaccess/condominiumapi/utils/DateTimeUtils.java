package com.waveaccess.condominiumapi.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class DateTimeUtils {

    public static Instant convert(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC);
    }

    public static LocalTime convert(Instant instant) {
        return LocalTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
