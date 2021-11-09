::@echo off
javac Game.java View.java Controller.java Model.java Brick.java Mario.java Json.java Sprite.java CoinBrick.java Coin.java
if %errorlevel% neq 0 (
	echo There was an error; exiting now.	
) else (
	echo Compiled correctly!  Running Game...
	java Game	
)

