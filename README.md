# Projector
A basic 3D world sandbox type game.

Run with Jar files:
    In the release folder, download the folder that corresponds with your operating system (linux, windows, mac).
    Once downloaded enter the folder and run with "java -jar Projector.jar".

Helpful Commands to compile and run on linux:
Compile:
    javac -cp .:./src:./lib/lwjgl/lwjgl.jar:./lib/lwjgl/lwjgl-glfw.jar:./lib/lwjgl/lwjgl-opengl.jar -d ./bin src/Game/Game.java

Run:
    java -cp ./bin:./lib/lwjgl/lwjgl.jar:./lib/lwjgl/lwjgl-glfw.jar:./lib/lwjgl/lwjgl-glfw-natives-linux.jar:./lib/lwjgl/lwjgl-natives-linux.jar:./lib/lwjgl/lwjgl-opengl.jar:./lib/lwjgl/lwjgl-opengl-natives-linux.jar -Djava.library.path=./lib/lwjgl Game/Game

Note:
    Make sure to provide the correct native path based on os. E.g.lwjgl-natives-macos.jar.
    Also, on macos, you may need to use the-XstartOnFirstThread flag when running with the java command.
