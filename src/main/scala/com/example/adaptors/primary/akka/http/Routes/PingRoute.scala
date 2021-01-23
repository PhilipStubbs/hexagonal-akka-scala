package com.example.adaptors.primary.akka.http.Routes

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, get, pathEnd, pathPrefix}
import akka.http.scaladsl.server.Route
import akka.util.Timeout

class PingRoute(implicit val system: ActorSystem[_]) {


  private implicit val timeout = Timeout.create(system.settings.config.getDuration("my-app.routes.ask-timeout"))


  val route: Route =
    pathPrefix("ping" ) {
      pathEnd {
        get {
          println(s"PING")
          complete(StatusCodes.OK, "PING")
        }
      }
    }
}
