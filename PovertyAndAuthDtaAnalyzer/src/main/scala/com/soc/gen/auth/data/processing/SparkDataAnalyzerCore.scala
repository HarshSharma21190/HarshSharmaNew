package com.soc.gen.auth.data.processing

class SparkDataAnalyzerCore(reader: SparkDataReader) {

  val parseCsv = readFileWithHeader("csv") _

  def readFileWithHeader(format: String)(delimiter: String)(path: String) = reader.readInput(format, true, delimiter, path)

}

object SparkDataAnalyzerCore {
  def getAnalyzer(reader: SparkDataReader): SparkDataAnalyzerCore = new SparkDataAnalyzerCore(reader)
}