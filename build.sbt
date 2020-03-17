import sbt.Keys.{libraryDependencies, scalaVersion, scalacOptions}
import sbt.{ExclusionRule, project}


name := "SparkPrograms"

version := "0.1"

scalaVersion := "2.12.10"



lazy val sparkDepdencies = Seq(
  "org.apache.spark" %% "spark-core" % "2.4.5",
  "org.apache.spark" %% "spark-sql" % "2.4.5"
)



lazy val `poverty-auth-estimates` = (project in file("PovertyAndAuthDtaAnalyzer"))
  .settings(Defaults.itSettings: _*)
  .settings(libraryDependencies ++= sparkDepdencies)



