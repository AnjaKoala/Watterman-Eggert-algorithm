import java.util.ArrayList;
import java.util.List;


public class FindSecondBest {
	
	int[][] matrix = null;
	int duljinaStupac = 0;
	int duljinaRedak = 0;
	String stupac = null;
	String redak = null;
	
	public FindSecondBest(int[][] matrix, String stupac, String redak){	
		this.matrix = matrix;
		this.stupac = stupac;
		this.redak = redak;
		
	}	
	public void pripremi(List<Integer> listaPuta){
		MakeMatrix napraviMatricu = new MakeMatrix(stupac, redak);
		List<Integer> listaNajboljegDrugogPuta = new ArrayList<>();
		int[][] matrix2 = null;
		for (int i=0; i<listaPuta.size(); i+=2){	
			matrix[listaPuta.get(i)][listaPuta.get(i+1)]=0;		
		}
		matrix2=napraviMatricu.makeSecond(matrix,listaPuta.get(listaPuta.size()-2), listaPuta.get(listaPuta.size()-1));
		napraviMatricu.printMatrix(matrix2);
		FindBest findSecondBest = new FindBest(matrix2);
		listaNajboljegDrugogPuta=findSecondBest.BestPath();
		napraviMatricu.printLetters(listaNajboljegDrugogPuta);
	}	
}
