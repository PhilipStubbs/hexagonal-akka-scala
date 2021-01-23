package com.example

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Directives, Route}
import com.example.adaptors.primary.akka.http.Routes.{DateRoute, PingRoute}
import com.example.adaptors.secondary.fake.FakeBusinessHourAdaptor

import scala.util.Failure
import scala.util.Success
import domain.services.BusinessService

//#main-class
object BootApp {
  //#start-http-server
  private def startHttpServer(routes: Route)(implicit system: ActorSystem[_]): Unit = {
    // Akka HTTP still needs a classic ActorSystem to start
    import system.executionContext

    val futureBinding = Http().newServerAt("localhost", 8080).bind(routes)
    futureBinding.onComplete {
      case Success(binding) =>
        val address = binding.localAddress

        system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort)
      case Failure(ex) =>
        system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
  }

  //#start-http-server
  def main(args: Array[String]): Unit = {
    //#server-bootstrapping
    val rootBehavior = Behaviors.setup[Nothing] { context =>

      // creates  Services
      val businessService = new BusinessService(FakeBusinessHourAdaptor)

      // creates routs
      val pingRoutes = new PingRoute()(context.system)
      val dateRoutes = new DateRoute(businessService)(context.system)

      val allRoutes = Directives.concat(pingRoutes.route, dateRoutes.route)

      startHttpServer(allRoutes)(context.system)

      Behaviors.empty
    }
    val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")
    //#server-bootstrapping

  }
}
//#main-class
