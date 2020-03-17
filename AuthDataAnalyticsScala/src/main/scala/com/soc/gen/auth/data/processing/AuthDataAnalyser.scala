package com.soc.gen.auth.data.processing

import scala.util.Try

object AuthDataAnalyser extends App {

  // For all the AUA greater than “650000” fetch the SA which are only numeric and res_state_name is not Delhi.

  val locationPath = sys.props.getOrElse("auth.data.path", "resources/auth.csv")

  val authDataHandler: DataReader[String] = AuthDataReader.getReader(locationPath)

  val splitData = authDataHandler.read

  import AuthDataAnalyserUtils._

  val targetedAuthData = splitData.tail.collect({
    case row => {
      val splitRow = row.split(",")
      val aua = splitRow(2)
      AuthData(splitRow(0), splitRow(1), aua.toLongOpt, splitRow(3), splitRow(128))
    }
  }).filter(authData => authData.resState != "Delhi" && authData.aua.isDefined && authData.aua.getOrElse(-999L) > 65000L)


  targetedAuthData.foreach(println)


  authDataHandler.close()


}


object AuthDataAnalyserUtils {

  implicit class StringConverter(p: String) {

    def toLongOpt = {
      try {
        Some(p.toLong)
      } catch {
        case e: Exception => None
      }
    }
  }

}



