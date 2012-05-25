/*
 * Matt Tucker
 * GeoZen LLC
 * Copyright (c) 2012. All rights reserved.
 */

package com.geozen

import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import org.joda.time.format._
import org.joda.time._

@RunWith(classOf[JUnitRunner])
class JodaTest extends Specification {

  "joda" should {

    "convert a datetime to a local datetime" in {
      val timestamp = 1337966535000L;
      val dt = new DateTime(timestamp)
      val timeZone = DateTimeZone.forID("America/Denver");
      val ldt = dt.toDateTime(timeZone).toLocalDateTime
      println("dt = " + ldt)
      success
    }

    "convert a local datetime to a datetime" in {
      val localDateTime = new LocalDateTime(2012, 5, 25, 11, 22, 15);
      val timeZone = DateTimeZone.forID("America/Denver");
      val dateTime = localDateTime.toDateTime(timeZone)
      println("timestamp = " + dateTime.getMillis)
      success
    }

    "parse a local date" in {
      val str = "2012-05-25";
      val fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
      val dt = fmt.parseDateTime(str).toLocalDate
      println("local date: " + dt)
      success
    }

    "parse local date time" in {
      val str = "2012-11-3T00:00:00";
      val fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
      val dt = fmt.parseDateTime(str).toLocalDateTime
      println("local datetime: " + dt)

      success
    }

    "parse ios8601 date time" in {
      //yyyy-mm-ddTHH:MM:SS.SSS
      var str = "2012-05-25T11:30:00Z";
      val fmt = ISODateTimeFormat.dateTimeNoMillis();
      val dt = fmt.parseDateTime(str)
      println("ISO8601: " + dt)

      str = "2012-05-25T11:30:00-07:00";
      val dt2 = fmt.parseDateTime(str)
      println("ISO8601: " + dt2)
      success
    }

    "measure and format a period of time" in {

      val fmt = ISODateTimeFormat.dateTimeNoMillis();
      val start1 = fmt.parseDateTime("2012-05-25T11:30:00Z")

      val period1 = new Period(start1, new DateTime());
      val fmt1 = ISOPeriodFormat.standard()
      println(fmt1.print(period1))

      val start2 = new DateTime()
      val fmt2 = new PeriodFormatterBuilder()
        .printZeroAlways()
        .appendHours()
        .appendSuffix(" hour", " hours")
        .appendSeparator(", ")
        .appendMinutes()
        .appendSuffix(" min", " mins")
        .appendSeparator(", ")
        .appendSeconds()
        .appendSuffix(" sec", " secs")
        .appendSeparator(", ")
        .appendMillis()
        .appendSuffix(" ms", " ms")
        .toFormatter();

      val period2 = new Period(start2, new DateTime());
      println(fmt2.print(period2))
      success
    }

    "parse local date using extractor" in {
      val ldt0 = new LocalDateTime(2012, 05, 25, 11, 30);
      val ldt = AsLocalDateTime(ldt0.toString);
      println("local datetime: " + ldt)
      ldt0.toDateTime().getMillis() must_== ldt.toDateTime().getMillis()
    }

    "parse local date w/o ms using extractor" in {
      val ldt = AsLocalDateTime("2012-05-25T00:00:30");
      println("local datetime: " + ldt)
      success
    }
  }
}
