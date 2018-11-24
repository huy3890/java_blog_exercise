package com.exercise.util;

import java.util.Date;

public class DateHelper {
  public static Date convertDateFromUnixTime(String unixTime) {
    return new Date(Long.valueOf(unixTime) * 1000L);
  }

}
