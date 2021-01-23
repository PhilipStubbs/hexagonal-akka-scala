package com.example.domain.ports

import org.joda.time.DateTime

trait BusinessHourRepo {

  def add(date: DateTime): DateTime

  def fetch(startDate: DateTime, endDate: DateTime): List[DateTime]

  def delete(date:DateTime): Unit
}
