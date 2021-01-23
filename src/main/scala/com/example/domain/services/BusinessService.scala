package com.example.domain.services

import com.example.domain.ports.BusinessHourRepo
import org.joda.time.DateTime

class BusinessService(businessHourRepo: BusinessHourRepo) {

  def addOpenTime(date: DateTime): DateTime ={
    businessHourRepo.add(date)
  }

  def fetchOpenTime(startDate: DateTime, endDate: DateTime): List[DateTime] ={
    println(s"${startDate}")
    println(s"${endDate}")

    businessHourRepo.fetch(startDate, endDate)
  }

  def deleteOpenTimes(date: DateTime): Unit ={
    businessHourRepo.add(date)
  }
}
