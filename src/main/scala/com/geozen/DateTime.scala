/*
 * Matt Tucker
 * GeoZen LLC
 * Copyright (c) 2012. All rights reserved.
 */

package com.geozen

import org.joda.time.{ DateTime, LocalDate, LocalDateTime }
import org.joda.time.format.{ ISODateTimeFormat, DateTimeFormat }

object AsDateTime {
  val fmt = ISODateTimeFormat.dateTimeNoMillis();

  def unapply(s: String): Option[DateTime] = {
    try {
      val dt = fmt.parseDateTime(s);
      Some(dt)
    } catch {
      case _ => None
    }
  }

}

object AsLocalDate {
  val fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

  def apply(s: String): LocalDate = {
    fmt.parseDateTime(s).toLocalDate
  }

  def unapply(s: String): Option[LocalDate] = {
    try {
      val dt = fmt.parseDateTime(s);
      Some(dt.toLocalDate)
    } catch {
      case _ => None
    }
  }

}

object AsLocalDateTime {
  val fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
  val fmt_ms = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

  def apply(s: String): LocalDateTime = {
    try {
      fmt_ms.parseDateTime(s).toLocalDateTime
    } catch {
      case _ => fmt.parseDateTime(s).toLocalDateTime
    }
  }

  def unapply(s: String): Option[LocalDateTime] = {
    try {
      val dt = fmt.parseDateTime(s);
      Some(dt.toLocalDateTime)
    } catch {
      case _ => try {
        val dt = fmt_ms.parseDateTime(s);
        Some(dt.toLocalDateTime)
      } catch {
        case _ => None
      }
    }
  }

}

