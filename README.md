# Watterman-Eggert-algorithm
http://www.fer.unizg.hr/predmet/bio

This is an implementation of Watterman-Eggert algorithm.
The algorithm is published as five different excecutable files programmed in five different programming languages:
 - C# ------- C Sharp/WattermanEggert/WattermanEggert/program.cs
 - Java ----- Java/Bioinformatics/src/Main.java
 - Python --- Python/python.py
 - Ruby ----- Ruby/project.rb
 - C    ----- C/example.c
 
 These programs are intended to be run on biolinux platform.
 Every one of these programs takes two filenames as arguments:
  paths to first and second textual file to be aligned.
 
 Examples/ directory contains sample inputs.
 
 To compile into bin/ directory, run:
   ./build.sh
  NOTE: build.sh requires root rights because it tries to install missing packages.
 
 To run and compare all implementations, run any one of these commands:

   ./run_all.sh Examples/1.txt Examples/2.txt
   
   ./run_all.sh Examples/3.txt Examples/4.txt
   
   ./run_all.sh Examples/5.txt Examples/6.txt
   
   ./run_all.sh Examples/7.txt Examples/8.txt
   
  NOTE: Pipe output through ./pretty to make it human readable.
 



