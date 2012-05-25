name := "joda"

scalaVersion := "2.9.1"

//
// Library dependencies
//
// libraryDependencies ++= Seq(
//	groupID % artifactID % revision,
//	groupID % otherID % otherRevision
// )
//
// If you use groupID %% artifactID % revision rather than groupID % artifactID % revision 
// (the difference is the double %% after the groupID), sbt will add your project's Scala version to the artifact name. 
// This is just a shortcut. 
//
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "1.10" % "test",
  "junit" % "junit" % "4.9" % "test",
  "joda-time" % "joda-time" % "2.1" % "test",
  "org.joda" % "joda-convert" % "1.1" % "test"
)


resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "http://oss.sonatype.org/content/repositories/releases")


