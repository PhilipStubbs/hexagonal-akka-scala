package com.example.domain.ports

import org.joda.time.DateTime

trait BusinessHourRepo {

  // add new open time
  def add(date: DateTime): DateTime

  // fetch open times between two dates
  def fetch(startDate: DateTime, endDate: DateTime): List[DateTime]

  def delete(date:DateTime): Unit
}
