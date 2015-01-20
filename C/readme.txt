The program needs 2 sequence texts shaped like a fasta file or 
fasta files itself of which the first file entered is greater in size or 
equal to the second one.

1) Windows
In command prompt program uses 2 files as arguments, example:

program.exe 1.txt 2.txt

Output is shown on console and written in a new file called out.txt .

2) Ubuntu
For compiling and running:

g++ program.cpp -std=c++11 -o program
./program 1.txt 2.txt
