package com.example.adaptors.primary.akka.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.example.domain.models.{AddDateRequest, FetchDateRequest}
import spray.json.DefaultJsonProtocol

object JsonFormats extends DefaultJsonProtocol with SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)

  implicit val fetchDateRequestJsonFormat = jsonFormat2(FetchDateRequest)
  implicit val addDateRequestJsonFormat = jsonFormat1(AddDateRequest)

}
