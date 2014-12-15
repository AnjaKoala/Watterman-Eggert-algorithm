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
		int red = 0;
		int stupac = 0;
		List<Integer> listaNajboljih = new ArrayList<>();
		for (int col=1; col<matrix.length; col++){	
			for(int row=1; row< matrix[col].length; row++){				
				if(matrix[row][col]> max){					
					max = matrix[row][col];	
					stupac = col;
					red = row;
				}
			}			
		}
		listaNajboljih.add(red);
		listaNajboljih.add(stupac);
		bestPath.add(max);
		while(red-1>0 && stupac-1>0){		
			max = Math.max(Math.max(matrix[red-1][stupac-1], matrix[red][stupac-1]), matrix[red-1][stupac]);
			if(max==0)
				break;
			bestPath.add(max);
			if(max==matrix[red-1][stupac-1]){	
				stupac-=1;
				red-=1;		
				listaNajboljih.add(red);
				listaNajboljih.add(stupac);	
			}				
			if(max==matrix[red][stupac-1]){
				stupac-=1;
				listaNajboljih.add(red);
				listaNajboljih.add(stupac);
			}
			if(max==matrix[red-1][stupac]){
				red-=1;
				listaNajboljih.add(red);
				listaNajboljih.add(stupac);
			}
		}
		System.out.println(bestPath);
		return listaNajboljih;
	}	
}
