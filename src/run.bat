java -cp jlex.jar JLex.Main c.jlex
java -cp javacup.jar java_cup.Main c.cup
javac -classpath jlex.jar;javacup.jar *.java
java -classpath .;jlex.jar;javacup.jar JSONCompressor %1 %2