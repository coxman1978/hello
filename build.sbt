name := "hello"

version := "1.0-SNAPSHOT"


resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "com.decodified" %% "scala-ssh" % "0.6.4"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies ++= Seq(
  "org.bouncycastle" % "bcprov-jdk16" % "1.46",
  "com.jcraft" % "jzlib" % "1.1.3"
)

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.0" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit"  % "2.2.3"% "test"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings


val sampleIntTask = taskKey[Int]("A sample int task")

sampleIntTask := {
  val sum = scala.util.Random.nextInt() + 67
  println("Sum:=> " + sum)
  sum
}

val sampleIntTaskBy2 = taskKey[Int]("double the sample int task")

sampleIntTaskBy2 := {
  val times2 = sampleIntTask.value * 2
  println("Times 2: " + times2)
  times2
}
