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
- ./build.sh
NOTE: build.sh requires root rights because it tries to install missing packages.

To run and compare all implementations, run any one of these commands:
 - ./run_all.sh Examples/examples_corrected/1.txt Examples/examples_corrected/2.txt
 - ./run_all.sh Examples/examples_corrected/3.txt Examples/examples_corrected/4.txt
 - ./run_all.sh Examples/examples_corrected/5.txt Examples/examples_corrected/6.txt
 - ./run_all.sh Examples/examples_corrected/7.txt Examples/examples_corrected/8.txt
 - ./run_all.sh Examples/examples_corrected/9.txt Examples/examples_corrected/10.txt

NOTE: run_all.sh pipes output through ./pretty to make it human readable.




