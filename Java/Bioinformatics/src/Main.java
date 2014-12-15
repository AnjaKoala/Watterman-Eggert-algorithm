import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {

		String redak = "-ACACACTA";
		String stupac = "-AGCACACA";
		int[][] gotovaMatrica = new int[redak.length()][stupac.length()];
		NapraviMatricu napraviMatricu = new NapraviMatricu(stupac, redak);
		List<Integer> listaNajboljegPuta = new ArrayList<>();
		gotovaMatrica = napraviMatricu.make();
		napraviMatricu.printMatrix(gotovaMatrica);
		FindBest findBest = new FindBest(gotovaMatrica);
		listaNajboljegPuta=findBest.BestPath();
		FindSecondBest findSecondBest = new FindSecondBest(gotovaMatrica,stupac,redak);
		findSecondBest.pripremi(listaNajboljegPuta);
		napraviMatricu.PrintajSlova(listaNajboljegPuta);
	}
}
