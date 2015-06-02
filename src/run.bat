java -cp jlex.jar JLex.Main c.jlex
java -cp javacup.jar java_cup.Main c.cup
javac -classpath .;json-simple-1.1.1.jar;jlex.jar;javacup.jar *.java
java -classpath .;json-simple-1.1.1.jar;jlex.jar;javacup.jar JSONCompressor %1 %2
@echo off
clean