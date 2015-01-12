import java.util.ArrayList;
import java.util.List;


public class MakeMatrix {
	
	String arrayColumn = null;
	String arrayRow = null;
	int match = 10;
	int mismatch= -9;
	int insertion= -20;
	int deletions = -20;
	int[][] matrix = null;
	int hit = 0;

	public MakeMatrix(String arrayColumn, String arrayRow){
		//two arrays for building a matrix
		this.arrayColumn = arrayColumn;
		this.arrayRow = arrayRow;
		matrix = new int[arrayRow.length()][arrayColumn.length()];
	}
	
	public int[][] makeSecond(int[][] matrix2, int column1, int row1){
		// filling in row by row
		// variable hit, is telling us is its a match in two strings or not
		// column1 and row1 are telling us where to begin recalculate the matrix for the second time..
		//we don't have to recalculate the whole matrix
		for(int j=row1; j< arrayRow.length(); j++){	
			for (int i=column1; i<arrayColumn.length(); i++){	
				if(matrix2[j][i]>0){
					matrix2[j][i] = 1;
					if(arrayColumn.charAt(i)==arrayRow.charAt(j)){
						hit = 1;
						matrix2[j][i]= s(matrix2[j][i-1],matrix2[j-1][i],matrix2[j-1][i-1], hit); //checking for the best value
					}
					else {
						hit = 0;
						matrix2[j][i]= s(matrix2[j][i-1],matrix2[j-1][i],matrix2[j-1][i-1], hit);
					}
				}
			}			
		}	
		return matrix2;
	}
	
	public int[][] make(){		
		
		//filling in row by row
		//making a matrix for the first time
		for (int i=1; i<arrayRow.length(); i++){	
			for(int j=1; j< arrayColumn.length(); j++){				
				if(arrayColumn.charAt(j)==arrayRow.charAt(i)){
					hit = 1;
					matrix[i][j]= s(matrix[i][j-1],matrix[i-1][j],matrix[i-1][j-1], hit);
				}
				else {
					hit = 0;
					matrix[i][j]= s(matrix[i][j-1],matrix[i-1][j],matrix[i-1][j-1], hit);
				}
			}			
		}	
		return matrix;
	}
	
	public int s(int delete, int insert, int swich, int hit){
		//checking for the best value in matrix cell, returns the best value
		int maxS = 0;
		int addDeletions = deletions + delete;
		int addInsertions = insertion + insert;
		int addSwich = mismatch + swich;
		
		if(hit == 1){
			maxS = match + swich;		
		}	
		else{
			maxS = Math.max(Math.max(Math.max(addDeletions, addInsertions),addSwich),0);
		}		
		return maxS;
		
	}	
	
	public void printMatrix(int[][] doneMatrix){
		
		for (int row = 0; row < arrayRow.length(); row++) {
	        for (int col = 0; col <arrayColumn.length(); col++) {
	            System.out.printf("%4d", doneMatrix[row][col]);
	        }
	        System.out.println();
	    }	
		
	}
	
	public void printLetters(List<Integer> path){	
		// first printing the row string and than the colomn string
		int iPrevious = 100;
		int jPrevious = 100;
		char empty = '-';
		List<Character> row = new ArrayList<>();
		List<Character> colomn = new ArrayList<>();
		List<Character> realRow = new ArrayList<>();
		List<Character> realColomn = new ArrayList<>();
		for (int i=0; i<path.size(); i+=2){
			if(path.get(i)!=iPrevious){
				colomn.add(arrayRow.charAt(path.get(i)));
				iPrevious = path.get(i);
			}
			else{
				colomn.add(empty);
			}
			if(path.get(i+1)!=jPrevious){ //checking if it's a deletion or insertion if it is
				row.add(arrayColumn.charAt(path.get(i+1)));
				jPrevious = path.get(i+1);
			}
			else{
				row.add(empty); //adding an empty sign
			}		
		}
		for(int i=row.size()-1;i>=0;i--){
			realRow.add(row.get(i));
		}//adding a string, which will print in the end
		for(int i=colomn.size()-1;i>=0;i--){
			realColomn.add(colomn.get(i));
		}//adding a string, which will print in the end
		System.out.println(realRow);
		System.out.println(realColomn);
	}
}
