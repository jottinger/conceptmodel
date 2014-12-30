name := "implicits"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.neo4j" % "neo4j" % "[2.1.6,)",
  "org.testng" % "testng" % "6.8.13" % Test,
  "org.anormcypher" %% "anormcypher" % "[0.4.4,)"
)

resolvers ++= Seq(
  "anormcypher" at "http://repo.anormcypher.org/",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
)