package com.soc.gen.auth.data.processing

import com.soc.gen.auth.data.processing.AnalyserUtils.getProperty
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.DataTypes


object SparkAuthDataAnalyzerApp extends App {

  val spark = SparkSessionBuilder.getOrCreateSparkSession("AuthDataAnalyticsApp")

  val analyzer = SparkDataAnalyzerCore.getAnalyzer(new SparkDataReader(spark))

  val fileToParse = getProperty("target.csv.file", "resources/auth.csv")

  val baseAuthData = analyzer.parseCsv(",")(fileToParse)

  print(s"baseAuthDataCount Size ${baseAuthData.count()}")

  val targetAuthData = baseAuthData
    .select(col("auth_code"), col(("subreq_id")), col("aua"), col("sa"), col("res_state_name"))
    .withColumn("aua", col("aua").cast(DataTypes.LongType))
    .filter("aua > 65000 and res_state_name!='Delhi'")
    .filter(col("sa").rlike("""^[0-9]*$"""))


  print(s"total auth rows = ${targetAuthData.count()}")

  targetAuthData.show(50)

  spark.close()


}
