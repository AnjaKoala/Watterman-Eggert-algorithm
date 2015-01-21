red=`tput setaf 1`
green=`tput setaf 2`
col3=`tput setaf 3`
col4=`tput setaf 4`
col5=`tput setaf 5`
reset=`tput sgr0`

echo
echo ${red}-------------------C Sharp------------------------------
echo
mono C\ Sharp/binary.exe $1 $2 | ./pretty
echo
echo ${red}--------------------Java--------------------------------${green}
echo
java -cp bin Main $1 $2 | ./pretty
echo
echo ${col3}--------------------Python------------------------------
echo
python Python/python.py $1 $2 | ./pretty
echo
echo ${col4}---------------------Ruby-------------------------------
echo
ruby Ruby/project.rb $1 $2 | ./pretty
echo
echo ${col5}----------------------C--------------------------------
echo
./bin/c $1 $2 | ./pretty
echo ${reset}
