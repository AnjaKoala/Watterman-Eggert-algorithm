import java.util.ArrayList;
import java.util.List;


public class FindBest {
	
	int[][] matrix = null;
	List<Integer> bestPath = new ArrayList<>();
	public FindBest(int[][] matrix){	
		this.matrix = matrix;		
	}
	
	public List<Integer> BestPath(){
		int max = 0;
		int row1 = 0;
		int colomn1 = 0;
		List<Integer> listOfTheBest = new ArrayList<>();
		for(int row=1; row< matrix.length; row++){
			for (int col=1; col<matrix[row].length; col++){//finding the max value in matrix 	
				if(matrix[row][col]> max){					
					max = matrix[row][col];	
					colomn1 = col;
					row1 = row;
				}
			}			
		}
		listOfTheBest.add(row1);
		listOfTheBest.add(colomn1);
		bestPath.add(max);
		while(row1-1>0 && colomn1-1>0){		//finding the best path in matrix, max value -> up
			max = Math.max(Math.max(matrix[row1-1][colomn1-1], matrix[row1][colomn1-1]), matrix[row1-1][colomn1]);
			if(max==0)
				break;
			bestPath.add(max);
			if(max==matrix[row1-1][colomn1-1]){	
				colomn1-=1;
				row1-=1;		
				listOfTheBest.add(row1);
				listOfTheBest.add(colomn1);	
			}				
			if(max==matrix[row1][colomn1-1]){
				colomn1-=1;
				listOfTheBest.add(row1);
				listOfTheBest.add(colomn1);
			}
			if(max==matrix[row1-1][colomn1]){
				row1-=1;
				listOfTheBest.add(row1);
				listOfTheBest.add(colomn1);
			}
		}
//		System.out.println(bestPath);
		return listOfTheBest;
	}	
}
