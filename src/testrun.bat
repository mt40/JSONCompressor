javac -classpath jlex.jar;javacup.jar *.java
java -classpath .;jlex.jar;javacup.jar HIRCompiler %1 %2
java -jar HIRInterpreter.jar %2