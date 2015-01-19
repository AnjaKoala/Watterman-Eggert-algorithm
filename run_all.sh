echo -------------------C Sharp------------------------------
mono C\ Sharp/binary.exe $1 $2 | ./pretty
echo --------------------Java--------------------------------
java -cp bin Main $1 $2 | ./pretty
echo --------------------Python------------------------------
python Python/python.py $1 $2 | ./pretty
echo ---------------------Ruby-------------------------------
ruby Ruby/project.rb $1 $2 | ./pretty
echo ----------------------C--------------------------------
#./bin/c $1 $2 | ./pretty
