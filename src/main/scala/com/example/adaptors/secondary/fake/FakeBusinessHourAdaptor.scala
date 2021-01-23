package com.example.adaptors.secondary.fake

import com.example.domain.ports.BusinessHourRepo
import org.joda.time.DateTime

object FakeBusinessHourAdaptor extends BusinessHourRepo{
  override def add(date: DateTime): DateTime = date

  override def fetch(startDate: DateTime, endDate: DateTime): List[DateTime] = List(startDate,endDate)

  override def delete(date: DateTime): Unit = println(s"deleteing $date")
}
