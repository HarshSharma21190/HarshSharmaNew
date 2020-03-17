package com.soc.gen.auth.data.processing

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSessionBuilder {

  def getOrCreateSparkSession(appName: String) =
    SparkSession.builder().appName(appName)
      .config(new SparkConf().setMaster("local[*]").setAppName(appName))
      .getOrCreate()

}
