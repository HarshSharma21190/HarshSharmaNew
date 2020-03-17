package com.soc.gen.auth.data.processing


import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.DataTypes

object SparkPovertyEstimatorApp extends App {

  import AnalyserUtils._

  val spark = SparkSessionBuilder.getOrCreateSparkSession("PovertyAnalyticsApp")

  val analyzer = SparkDataAnalyzerCore.getAnalyzer(new SparkDataReader(spark))

  val fileToParse = getProperty("target.csv.file", "resources/PovertyEstimates.csv")

  val basePovertyData = analyzer.parseCsv(",")(fileToParse)

  print(s"baseDataCount Size ${basePovertyData.count()} ")

  basePovertyData.printSchema()

  val urbanInf2003Data = basePovertyData
    .withColumn("Urban_Influence_Code_2003", col("Urban_Influence_Code_2003").cast(DataTypes.IntegerType))
    .withColumn("Rural_urban_Continuum_Code_2013", col("Rural_urban_Continuum_Code_2013").cast(DataTypes.IntegerType))
    .filter("Urban_Influence_Code_2003 % 2 == 1 ")
    .filter("Rural_urban_Continuum_Code_2013 % 2 == 0")

  urbanInf2003Data.printSchema()

  print(s"urbanInf2003Data Size ${urbanInf2003Data.count()} ")


  urbanInf2003Data.show()

  spark.close()

}




