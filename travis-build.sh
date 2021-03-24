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

curl https://search.maven.org/search?q=g:junit%20AND%20a:junit --output junit.jar
curl https://search.maven.org/artifact/org.hamcrest/hamcrest-core/1.3/jar --output hamcrest-core.jar

cd ../..
pwd
ls
ls lib
ls lib/lwjgl
ls lib/junit