matrix_max = [0, 0, 0];
path_1 = [];
prolaz = 1;

def matching(i, j):
	count = 2; #10 je nagrada za match
	if (matrix[i-1][j-1] != 0):
		count += matrix[i-1][j-1];
	matrix[i][j] = count;
	return;

def nonmatching(i, j):
	if (matrix[i-1][j-1] == 0 and matrix[i-1][j]==0 and matrix[i][j-1]==0):
		matrix[i][j]=0;
	else:	
		change = matrix[i-1][j-1] -1; #kazna za zamjenu je -1 
		swap0 = matrix[i-1][j] - 2;   #kazna za prazninu je -2 
		swap1= matrix[i][j-1] - 2;    #kazna za prazninu je -2
		max_op = max(change, swap0, swap1);
		if (max_op < 0):
			matrix[i][j] = 0;
		else:
			matrix[i][j] = max_op;
	return;

import pprint


#array1 = "ACACACTA";
#array2 = "AGCACACA";
array1 = "GGCTCAATCA";
array2 = "ACCTAAGG";

matrix = [[0 for x in range(len(array1)+2)] for x in range(len(array2)+2)];



for i in range(len(array2) + 2):
	for j in range(len(array1) + 2):
		if (i == 0 and j == 0) or (i == 0 and j == 1) or (i == 1 and j == 0):
			matrix[i][j]="-";
		elif (j == 0):
			matrix[i][0] = array2[i - 2];
		elif (i == 0):
			matrix[0][j] = array1[j- 2];

#prvi krug price			
for i in range(len(array2) + 2):
	for j in range(len(array1) + 2):
		if (matrix[i][0] != "-" and matrix[0][j] != "-" and matrix[i][j] != "-"):
			if (matrix[i][0] == matrix[0][j]):
				matching(i, j);
				if (matrix[i][j] > matrix_max[0]):
					matrix_max[0]=matrix[i][j];
					matrix_max[1]=i;
					matrix_max[2]=j;
			else:
				nonmatching(i, j);
				if (matrix[i][j] > matrix_max[0]):
					matrix_max[0]=matrix[i][j];
					matrix_max[1]=i;
					matrix_max[2]=j;

#trazenje puta
path_1.append((matrix_max[1], matrix_max[2]));
i=matrix_max[1];
j=matrix_max[2];
while (prolaz):
		diagonal = matrix[i-1][j-1];
		upp = matrix[i-1][j];
		left = matrix[i][j-1];
		if (diagonal==0 and upp==0 and left==0):
			break;
		if diagonal >= max(upp, left):
			path_1.append((i-1, j-1));
			i-=1;
			j-=1;
		elif upp >= max(diagonal, left):
			path_1.append((i-1, j));
			i-=1;
		else:
			path_1.append((i, j-1));
			j-=1;
	
pprint.pprint(matrix);
print matrix_max;
print path_1;


	
