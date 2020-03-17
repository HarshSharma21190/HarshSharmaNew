package com.soc.gen.auth.data.processing

object AnalyserUtils {

  def getProperty(name: String, default: String) = sys.props.getOrElse(name, default)

}