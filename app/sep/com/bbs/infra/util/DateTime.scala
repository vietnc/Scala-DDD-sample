package sep.com.bbs.infra.util

import java.text.{SimpleDateFormat, DateFormat}
import java.util.{Locale, Date}

object DateTime {
  def getDate(str: String): Date = {
    val format: DateFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s", Locale.ENGLISH)
    format.parse(str)
  }
  def toString(d: Date): String = {
    val format: DateFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s", Locale.ENGLISH)
    format.format(d)
  }

}
