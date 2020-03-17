package com.soc.gen.auth.data.processing

import org.apache.spark.sql.SparkSession

class SparkDataReader(spark: SparkSession) {

  def readInput(format: String, header: Boolean, delimiter: String, path: String) =
    spark.read.options(Map("header" -> "true", "delimiter" -> delimiter))
      .csv(path)


}
