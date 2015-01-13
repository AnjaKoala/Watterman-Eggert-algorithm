import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//input arguments are two files, each file consists of one string!
//		String colomn = "-AAGAC";
//		String row = "-AAGA";		
		String fileName = args[0];
		String fileName2 = args[1];
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        BufferedReader input2 = new BufferedReader(new FileReader(fileName2));
        String begin = "-";
        String line = null;
        String line1 = null;
        String colomn = null;
        String row = null;
        int firstRow = 0;
        int firstColomn = 0;
        try {
            while (( line = input.readLine()) != null){
            	if(firstColomn==0){
            		colomn = begin + line;
            		firstColomn = 1;
            		line="";
            	}
            	colomn = colomn + line;
            }
//            System.out.println(colomn);
            while (( line1 = input2.readLine()) != null){
            	if(firstRow==0){
            		row = begin + line1;
            		line1="";
            		firstRow=1;
            	}
            	row = row + line1;
            }
//            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }  
		int[][] doneMatrix = new int[row.length()][colomn.length()];
		MakeMatrix makeMatrix = new MakeMatrix(colomn, row);
		List<Integer> bestPath = new ArrayList<>();
		doneMatrix = makeMatrix.make();
	//	makeMatrix.printMatrix(doneMatrix);
		FindBest findBest = new FindBest(doneMatrix);
		bestPath=findBest.BestPath();
//		makeMatrix.printLetters(bestPath);
		FindSecondBest findSecondBest = new FindSecondBest(doneMatrix,colomn,row);
		findSecondBest.pripremi(bestPath);	
	}
}
