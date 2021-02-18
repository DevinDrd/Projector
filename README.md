# Projector
A basic 3D world sandbox type game.

Helpful Commands to compile and run on linux:
Compile:
    javac -cp .:./src:./lib/lwjgl/lwjgl.jar:./lib/lwjgl/lwjgl-glfw.jar:./lib/lwjgl/lwjgl-opengl.jar -d ./bin src/Game.java

Run:
    java -cp ./bin:./lib/lwjgl/lwjgl.jar:./lib/lwjgl/lwjgl-glfw.jar:./lib/lwjgl/lwjgl-glfw-natives-linux.jar:./lib/lwjgl/lwjgl-natives-linux.jar:./lib/lwjgl/lwjgl-opengl.jar:./lib/lwjgl/lwjgl-opengl-natives-linux.jar -Djava.library.path=./lib/lwjgl Game

Note:
    Make sure to provide the correct native path based on os. E.g.lwjgl-natives-macos.jar.
    Also, on macos, you may need to use the XstartOnFirstThread flag when running with the java command.