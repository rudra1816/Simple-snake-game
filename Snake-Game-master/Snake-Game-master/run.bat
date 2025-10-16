@echo off
echo Compiling Snake Game...
javac -cp . *.java
if %errorlevel% neq 0 (
    echo Compilation failed. Make sure Java JDK is installed.
    pause
    exit /b 1
)

echo Running Snake Game...
java -cp . snippet.Main
pause