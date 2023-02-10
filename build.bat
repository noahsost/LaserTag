::@echo off
javac Main.java Gui.java
if %errorlevel% neq 0 (
	echo There was an error; exiting now.	
) else (
	echo Compiled correctly!  Running Game...
	java Main	
)
cmd /k