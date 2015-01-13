import pprint
import sys

# i represents number of the row, j represents the number of the column
matrix_max_1 = [0, 0, 0]; # [value of the field, i coordinate, j coordinate]
matrix_max_2 = [0, 0, 0]; # [value of the field, i coordinate, j coordinate]
path_1 = []; # first best path
path_2 = []; # second best path
path_2_i = []; # second best path by rows
path_2_j = []; # second best path by columns
i_backup = -5;
j_backup = -5;
#

#function that is called in a case that there is a match
def matching(i, j):
	count = 10; #10 is the maximum prize
	if (matrix[i-1][j-1] != 0):
		count += matrix[i-1][j-1];
	matrix[i][j] = count;
	return;
#

#function that is called in the case that there is not a match
def nonmatching(i, j):
	if (matrix[i-1][j-1] == 0 and matrix[i-1][j]==0 and matrix[i][j-1]==0):
		matrix[i][j]=0;
	else:
			change1 = matrix[i-1][j-1] - 9;
			deletion = matrix[i-1][j] - 20;
			insertion= matrix[i][j-1] - 20;
			max_op = max(change1, deletion, insertion);
			if (max_op < 0):
				matrix[i][j] = 0;
			else:
				matrix[i][j] = max_op;
	return;
#



#########################
#########################
#Beginning of the program

#Reading the input parameters
first_File = sys.argv[1];
second_File = sys.argv[2];
#

#Reading the string values of the Genome Sequence
with open (first_File, "r") as myfile:
    array2=myfile.read().replace('\n', '')
with open (second_File, "r") as myfile:
    array1=myfile.read().replace('\n', '')
#

#Creating matrix
matrix = [[0 for x in range(len(array1)+2)] for x in range(len(array2)+2)];
#

#Insertion of "-" signs and Genome Sequence String in the matrix
for i in range(len(array2) + 2):
	for j in range(len(array1) + 2):
		if (i == 0 and j == 0) or (i == 0 and j == 1) or (i == 1 and j == 0):
			matrix[i][j]="-";
		elif (j == 0):
			matrix[i][0] = array2[i - 2];
		elif (i == 0):
			matrix[0][j] = array1[j- 2];
#
			
#First round calculation of matrix 		
for i in range(2, len(array2) + 2):
	for j in range(2, len(array1) + 2):
		if (matrix[i][0] != "-" and matrix[0][j] != "-" and matrix[i][j] != "-"):
			if (matrix[i][0] == matrix[0][j]):
				matching(i, j);
				if (matrix[i][j] > matrix_max_1[0]):
					matrix_max_1[0]=matrix[i][j];
					matrix_max_1[1]=i;
					matrix_max_1[2]=j;
			else:
				nonmatching(i, j);
				if (matrix[i][j] > matrix_max_1[0]):
					matrix_max_1[0]=matrix[i][j];
					matrix_max_1[1]=i;
					matrix_max_1[2]=j;
#

#pprint.pprint(matrix);
#print("----------------------");

path_1.append([matrix_max_1[1], matrix_max_1[2]]);
matrix[matrix_max_1[1]][matrix_max_1[2]] = "-50";
i=matrix_max_1[1];
j=matrix_max_1[2];

#First path calculation and putting the values of the first best path into -50 just for later recognition
while (1):
		diagonal = matrix[i-1][j-1];
		upp = matrix[i-1][j];
		left = matrix[i][j-1];
		if (diagonal==0 and upp==0 and left==0):
			matrix[i][j] = "-50";
			break;
		if diagonal >= max(upp, left):
			path_1.append([i-1, j-1]);
			matrix[i][j] = "-50";
			i-=1;
			j-=1;
		elif upp >= max(diagonal, left):
			path_1.append([i-1, j]);
			matrix[i][j] = "-50";
			i-=1;
		else:
			path_1.append([i, j-1]);
			matrix[i][j] = "-50";
			j-=1;
#
#print path_1;
# drugi krug
#print path_1;
#pprint.pprint(matrix);

#Recalculation of the matrix
for i in range(2, len(array2) + 2):
	for j in range(path_1[-1][1] ,len(array1) + 2):
		if matrix[i][j] == "-50":
			matrix[i][j] = 0;
			continue;
		elif (matrix[i][0] == matrix[0][j]):
			matching(i, j);
		else:
			nonmatching(i, j);
#

#Finding the matrix maximum
for i in range(2, len(array2) + 2):
	for j in range(2, len(array1) + 2):
		if (matrix[i][j] > matrix_max_2[0]):
			matrix_max_2[0]=matrix[i][j];
			matrix_max_2[1]=i;
			matrix_max_2[2]=j;
#

path_2.append([matrix_max_2[1], matrix_max_2[2]]);
i=matrix_max_2[1];
j=matrix_max_2[2];

#Finding the best second path
while (1):
		diagonal = matrix[i-1][j-1];
		upp = matrix[i-1][j];
		left = matrix[i][j-1];
		if (i_backup == i):
			path_2_i.append("-");
			path_2_j.append(matrix[0][j]);
		elif (j_backup == j):
			path_2_j.append("-");
			path_2_i.append(matrix[i][0]);
		else:
			path_2_i.append(matrix[i][0]);
			path_2_j.append(matrix[0][j]);
		i_backup = i;
		j_backup = j;
		if (diagonal==0 and upp==0 and left==0):
			break;
		elif diagonal >= max(upp, left):
			path_2.append([i-1, j-1]);
			i-=1;
			j-=1;
		elif upp >= max(diagonal, left):
			path_2.append([i-1, j]);
			i-=1;
		else:
			path_2.append([i, j-1]);
			j-=1;
#

#pprint.pprint(matrix);
#print path_2;

path_2_i = path_2_i[::-1];
path_2_j = path_2_j[::-1];
print path_2_i;
print path_2_j;
	
