import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		//at the beginning of every string there has to be a minus sign for program to work correctly!!!
	//	String row = "-AGTCCGAGGGCTACTCTACTGAAC";
	//	String colomn = "-GAGGGCTT";
		String row= args[0];
		String colomn =args[1];
		int[][] doneMatrix = new int[row.length()][colomn.length()];
		MakeMatrix makeMatrix = new MakeMatrix(colomn, row);
		List<Integer> bestPath = new ArrayList<>();
		doneMatrix = makeMatrix.make();
		makeMatrix.printMatrix(doneMatrix);
		FindBest findBest = new FindBest(doneMatrix);
		bestPath=findBest.BestPath();
		makeMatrix.printLetters(bestPath);
		FindSecondBest findSecondBest = new FindSecondBest(doneMatrix,colomn,row);
		findSecondBest.pripremi(bestPath);	
	}
}
