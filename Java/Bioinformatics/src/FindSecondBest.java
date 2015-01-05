import java.util.ArrayList;
import java.util.List;


public class FindSecondBest {
	
	int[][] matrix = null;
	int lenghtColomn = 0;
	int lenghtRow = 0;
	String colomn = null;
	String row = null;
	
	public FindSecondBest(int[][] matrix, String colomn, String row){	
		this.matrix = matrix;
		this.colomn = colomn;
		this.row = row;
		
	}	
	public void pripremi(List<Integer> path){
		MakeMatrix makeMatrix = new MakeMatrix(colomn, row); 
		List<Integer> bestSecondPath = new ArrayList<>();
		int[][] matrix2 = null;
		for (int i=0; i<path.size(); i+=2){	
			matrix[path.get(i)][path.get(i+1)]=0;		
		}
		matrix2=makeMatrix.makeSecond(matrix,path.get(path.size()-2), path.get(path.size()-1)); //recalculating the matrix
		makeMatrix.printMatrix(matrix2);
		FindBest findSecondBest = new FindBest(matrix2); //finding the path for the second matrix
		bestSecondPath=findSecondBest.BestPath();
		makeMatrix.printLetters(bestSecondPath);
	}	
}
