package com.example.adaptors.primary.akka.http.Routes

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, path, pathPrefix, post}
import akka.http.scaladsl.server.{Directives, Route}
import akka.util.Timeout
import com.example.adaptors.primary.akka.http.JsonFormats
import com.example.domain.models.{AddDateRequest, FetchDateRequest}
import com.example.domain.services.BusinessService
import org.joda.time.DateTime

class DateRoute(businessService: BusinessService)(implicit val system: ActorSystem[_]) {

  import JsonFormats._

  private implicit val timeout = Timeout.create(system.settings.config.getDuration("my-app.routes.ask-timeout"))

  val route: Route =
    pathPrefix("v1") {
      Directives.concat(
        path("fetchDate") {
          get {
            entity(as[FetchDateRequest]) { request =>

              businessService.fetchOpenTime(DateTime.parse(request.startDate), DateTime.parse(request.endDate))

              complete(StatusCodes.OK, FetchDateRequest(request.startDate, request.endDate))
            }
          }
        },
        path("addDate") {
          post {
            entity(as[AddDateRequest]) { request =>

              businessService.addOpenTime(DateTime.parse(request.date))

              complete(StatusCodes.OK, AddDateRequest(request.date))
            }
          }
        }
      )
    }
}
