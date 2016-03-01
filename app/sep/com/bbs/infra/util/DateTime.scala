package sep.com.bbs.infra.util

import java.text.{SimpleDateFormat, DateFormat}
import java.util.{Locale, Date}

import play.Logger

object DateTime {
  val DATE_FORMAT = "yyyy-MM-dd H:m:s"
  
  def getDate(str: String = "", inputFormat: String = ""): Date = {
    val dateFormat = if(inputFormat != "") inputFormat else DATE_FORMAT

    val format: DateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH)
    if(str == "") new Date()
    else format.parse(str)

  }
  def toString(d: Date): String = {
    val format: DateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
    format.format(d)
  }

}
