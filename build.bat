::@echo off
javac Game.java View.java Controller.java Model.java Tube.java Json.java Mario.java Sprite.java Goombas.java Fireball.java
:: note: for the above line you can use*.javac insteade of keep writing every file name
if %errorlevel% neq 0 (
	echo There was an error; exiting now.	
) else (
	echo Compiled correctly!  Running Game...
	java Game
:: to clean up your folder from the created files	
	del *.class 
	
)

