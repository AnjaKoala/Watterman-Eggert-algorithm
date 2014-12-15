import java.util.ArrayList;
import java.util.List;


public class NapraviMatricu {
	
	String nizStupac = null;
	String nizRedak = null;
	int match = 10;
	int mismatch= -9;
	int insertion= -20;
	int deletions = -20;
	int[][] matrix = null;
	int pogodak = 0;

	public NapraviMatricu(String nizStupac, String nizRedak){
		
		this.nizStupac = nizStupac;
		this.nizRedak = nizRedak;
		matrix = new int[nizRedak.length()][nizStupac.length()];
	}
	
	public int[][] makeSecond(int[][] matrix2, int stupac1, int redak1){
		//popunjavamo redak po redak
		for (int i=stupac1; i<nizStupac.length(); i++){	
			for(int j=redak1; j< nizRedak.length(); j++){	
				if(matrix2[i][j]>0){
					matrix2[i][j] = 1;
					if(nizStupac.charAt(j)==nizRedak.charAt(i)){
						pogodak = 1;
						matrix2[i][j]= s(matrix2[i][j-1],matrix2[i-1][j],matrix2[i-1][j-1], pogodak);
					}
					else {
						pogodak = 0;
						matrix2[i][j]= s(matrix2[i][j-1],matrix2[i-1][j],matrix2[i-1][j-1], pogodak);
					}
				}
			}			
		}	
		return matrix2;
	}
	
	public int[][] make(){		
		
		//popunjavamo redak po redak
		for (int i=1; i<nizRedak.length(); i++){	
			for(int j=1; j< nizStupac.length(); j++){				
				if(nizStupac.charAt(j)==nizRedak.charAt(i)){
					pogodak = 1;
					matrix[i][j]= s(matrix[i][j-1],matrix[i-1][j],matrix[i-1][j-1], pogodak);
				}
				else {
					pogodak = 0;
					matrix[i][j]= s(matrix[i][j-1],matrix[i-1][j],matrix[i-1][j-1], pogodak);
				}
			}			
		}	
		return matrix;
	}
	
	public int s(int brisanje, int umetanje, int zamjena, int pogodak){
		
		int maxS = 0;
		int zbrojBrisanje = deletions + brisanje;
		int zbrojUmetanje = insertion + umetanje;
		int zbrojZamjena = mismatch + zamjena;
		
		if(pogodak == 1){
			maxS = match + zamjena;		
		}	
		else{
			maxS = Math.max(Math.max(Math.max(zbrojBrisanje, zbrojUmetanje),zbrojZamjena),0);
		}		
		return maxS;
		
	}	
	
	public void printMatrix(int[][] gotovaMatrica){
		
		for (int row = 0; row < gotovaMatrica.length; row++) {
	        for (int col = 0; col <gotovaMatrica[row].length; col++) {
	            System.out.printf("%4d", gotovaMatrica[col][row]);
	        }
	        System.out.println();
	    }	
		
	}
	
	public void PrintajSlova(List<Integer> listaPuta){	
		// prvo red pa stupac
		int iPredhodni = 100;
		int jPredhodni = 100;
		char prazno = '-';
		List<Character> red = new ArrayList<>();
		List<Character> stupac = new ArrayList<>();
		List<Character> redPravi = new ArrayList<>();
		List<Character> stupacPravi = new ArrayList<>();
		for (int i=0; i<listaPuta.size(); i+=2){
			if(listaPuta.get(i)!=iPredhodni){
				stupac.add(nizRedak.charAt(listaPuta.get(i)));
				iPredhodni = listaPuta.get(i);
			}
			else{
				stupac.add(prazno);
			}
			if(listaPuta.get(i+1)!=jPredhodni){
				red.add(nizStupac.charAt(listaPuta.get(i+1)));
				jPredhodni = listaPuta.get(i+1);
			}
			else{
				red.add(prazno);
			}		
		}
		for(int i=red.size()-1;i>=0;i--)
			redPravi.add(red.get(i));
		for(int i=stupac.size()-1;i>=0;i--)
			stupacPravi.add(stupac.get(i));
		System.out.println(redPravi);
		System.out.println(stupacPravi);
	}
}
