rm -f bin/*.class
javac src/*.java -d bin/
cd bin
java -cp "../lib/postgresql-42.5.4.jar:." Main