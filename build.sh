sudo apt-get update
sudo apt-get install mono-mcs mono-dmcs g++ gcc
dmcs C\ Sharp/WattermanEggert/WattermanEggert/Program.cs -out:bin/csharp.exe
javac -d bin/ Java/Bioinformatics/src/*.java
gcc C/final/fin1.c -o bin/c
g++ pretty.cpp -o pretty
