#!/bin/bash

sudo apt update
sudo apt install unzip

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

cd ..

