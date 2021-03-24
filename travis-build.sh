#!/bin/bash

mkdir lib
cd lib

curl https://build.lwjgl.org/stable/lwjgl.zip --output lwjgl.zip
unzip lwjgl.zip
rm lwjgl.zip

mkdir lib

mv lwjgl/lwjgl.jar lib
mv lwjgl/lwjgl-natives-linux.jar lib
mv lwjgl-opengl/lwjgl-opengl.jar lib
mv lwjgl-opengl/lwjgl-opengl-natives-linux.jar lib
mv lwjgl-glfw/lwjgl-glfw.jar lib
mv lwjgl-glfw/lwjgl-glfw-natives-linux.jar lib

rm -rf lw*
rm build.txt
rm LICENSE

mv lib lwjgl

mkdir junit
cd junit

curl https://search.maven.org/remotecontent?filepath=junit/junit/4.13.2/junit-4.13.2.jar --output junit.jar
curl https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar --output hamcrest-core.jar

cd ../..
pwd
ls lib
ls lib/junit

javac -cp .:./src:./test:./lib/lwjgl/lwjgl.jar:./lib/lwjgl/lwjgl-glfw.jar:./lib/lwjgl/lwjgl-opengl.jar:./lib/junit/junit.jar:./lib/junit/hamcrest-core.jar -d ./bin test/TestRunner.java
java -cp ./bin:./lib/lwjgl/lwjgl.jar:./lib/lwjgl/lwjgl-glfw.jar:./lib/lwjgl/lwjgl-glfw-natives-linux.jar:./lib/lwjgl/lwjgl-natives-linux.jar:./lib/lwjgl/lwjgl-opengl.jar:./lib/lwjgl/lwjgl-opengl-natives-linux.jar:./lib/junit/junit.jar:./lib/junit/hamcrest-core.jar -Djava.library.path=./lib/lwjgl TestRunner