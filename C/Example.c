#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>

#define MATCH 1
#define MISMATCH -1
#define INDEL -2

struct alignment
{
       char symbol1;
       char symbol2;
};

struct cell
{
       int value;
       int direction;
       int d;
};

/*function for counting input sequence length*/
int readcounter (FILE *file) {
   int c;
   int seqcounter=0;
   int i=0;
   int descrstar=0;
   int descrend=0;
   
   if (file) {
   while ((c = fgetc(file)) != EOF) {
         if (c=='>') descrstar=1;
         if (c=='\n') descrend=1;
         if ((descrstar==1)&&(descrend==1)) {
                                            seqcounter++; }}}
                                            
   return seqcounter;                                
   
}

/*function for retreaving sequence from files*/
void createsequence(FILE *file, char *sequencearray) {
   int c;
   int seqcounter=0;
   int i=0;
   int descrstar=0;
   int descrend=0;
     
  if (file) {
   while ((c = fgetc(file)) != EOF) {
         if (c=='>') descrstar=1;
         if (c=='\n') descrend=1;
         if ((descrstar==1)&&(descrend==0)) { putchar(c);}
         if ((descrstar==1)&&(descrend==1)) {
                                            putchar(c);
                                            sequencearray[i]=c;
                                            i++; }}}  
     
     }




/*function for printing alignment matrix*/
void printmatrix (struct cell **matrix, int x, int y, char a[], char b[]) {
	int i,j;
	
	printf("\n x=%d y=%d \n",x,y);
	
	i=0;
    printf("  ");
          while (a[i]!='\0') {
          putchar(a[i]);
          i++;
          }
	
	printf("\n");
	
	for (i = 0; i < x; i++) {
              for (j = 0; j < y; j++) {
          if ((i==0)&(j==0)) {printf(" ");}  
          else if (j==0) {putchar(b[i-1]);}
          printf("%d",matrix[i][j].value); 
          }
          printf("\n");
          }
}


/*function for calculating the value and direction of a cell in alignment matrix*/
struct cell calculate_cell (int a, int b, int c, int match) {
    struct cell calculated_cell;
    int i=0;
    int m[]={0,0,0};
    int result_value=0;
    
    
    m[0]=a+match;
    m[1]=b+INDEL;
    m[2]=c+INDEL;
    
    result_value=m[0];
    
    for (i = 1; i < 3; i++) {
        if (m[i]>result_value) {result_value=m[i];}
        }
    
    if (result_value<0) {result_value=0;} 
    
    calculated_cell.value=result_value;
    
    
    if (result_value==m[0]) {calculated_cell.direction=0;}
    
	if (result_value==m[1]) 
		{if (m[1]==m[0]) calculated_cell.direction=0;
			else calculated_cell.direction=1;}
		
    if (result_value==m[2]) 
    {if (m[2]==m[0]) calculated_cell.direction=0;
			else calculated_cell.direction=2;}
    
    
    return calculated_cell; }


/*function for calculating the alignment matrix*/
void compare (char x[], char y[], struct cell **matrix, int m, int n) {

	int i,j;
	int a,b,c;
    int xpom,ypom;
    
    for (i = 1; i < m; i++) {
      for (j = 1; j < n; j++) {
          xpom=y[i-1];
          ypom=x[j-1];
          
          a=matrix[i-1][j-1].value;
          b=matrix[i-1][j].value;
          c=matrix[i][j-1].value;
          
          if (matrix[i][j].value==-1) {
          if (xpom==ypom) { matrix[i][j]=calculate_cell(a,b,c,MATCH); }
          else {matrix[i][j]=calculate_cell(a,b,c,MISMATCH);}
          }
          
                          
          }
      }
	
	
}
	
	

/*function for finding the best alignment cells in the matrix*/
void find_path (char x[], char y[], struct cell **matrix, int m, int n) {
	int i,j;
	int imax,jmax;
	int pom=0;
	struct cell currentcell;
	
	
	
	/*finding cell with maximum value*/
	
	pom=matrix[m][n].value;
           for (i = m; i > -1; i--) {
              for (j = n; j > -1; j--) {
                  if (pom<matrix[i][j].value)
                   {
                   pom=matrix[i][j].value;
                   imax=i;
                   jmax=j;
                   }
                  }
              }
	
	printf ("\n , i:%d, j:%d \n", i,j);
	
	printf ("\nMaximum value cell:\n");
	printf ("V:%d , x:%d, y:%d", pom, imax,jmax);
	printf("\n");
	
	/*finding alignment starting from cell with maximum value*/
	
	int z=imax+jmax;
	currentcell=matrix[imax][jmax];
	
	i=imax; j=jmax;
          
          
    
          
    char *alignx;
    alignx = (char *)malloc(z * sizeof(char));      
    
	char *aligny;
    aligny = (char *)malloc(z * sizeof(char)); 
	
	int u;      
    for (u = 0; u <z; u++) {
          alignx[u]='-';
          aligny[u]='-';
		  }
	
	      
    if (imax>jmax) {z=imax;}
    if (imax<jmax) {z=jmax;}
          
    int imin=0; int jmin=0;
    
    int c;
    c=z;
    
    int alignmentlength=0;
    
    while (currentcell.value!=0) {
                
                currentcell=matrix[i][j];
                if (currentcell.value==0) break;
                
                matrix[i][j].d=1;
                
                alignx[z]=x[j-1];
                aligny[z]=y[i-1];
                
				printf("\n V=%d, x=%d, y=%d",matrix[i][j].value,i,j);
				
				
                if ((currentcell.direction==0)&&(currentcell.value!=0)) {i--;j--;}
                
                if ((currentcell.direction==1)&&(currentcell.value!=0)){
                                              alignx[z]='-';
                                              i--;}
                if ((currentcell.direction==2)&&(currentcell.value!=0)){
                                              aligny[z]='-';
                                              j--;}
                
                z--;
                alignmentlength++;
                }
          
          imin=i; jmin=j;
          
          printf ("\nMinimum value cell:\n");
	      printf ("V:%d , x:%d, y:%d", matrix[imin][jmin].value, imin,jmin);
	      printf ("\nAlignment length: %d", alignmentlength);
	      printf("\n");
          
          printf("\n");
          
          
          i=0;
          printf("  ");
          for (i = 0; i <c; i++) {
          putchar(aligny[i]);
          }
          
          printf("\n");
          
          i=0;
          printf("  ");
          for (i = 0; i <c; i++) {
          putchar(alignx[i]);
          }
          
          printf("\n");
	
	      free(alignx);
          free(aligny); 
	
	
}

main()
{
	  
	  int seqcounter=0;
      int seqcounter2=0;
      int i=0;
      int j=0;
	  
	  int m;
	  int n; 
	
	  char *c1;
      char *c2;
   
      FILE *file;
      file = fopen("test1.txt", "r");
   
      FILE *file2;
      file2 = fopen("test2.txt", "r");
	
	  seqcounter=readcounter(file);
      seqcounter2=readcounter(file2);
         
      printf("\n \n");
   
      c1 = (char *)malloc(seqcounter * sizeof(char)); 
   
      for (i = 0; i < seqcounter; i++) {
        c1[i]='-';}
  
  
     c2 = (char *)malloc(seqcounter * sizeof(char)); 
   
     for (i = 0; i < seqcounter2; i++) {
        c2[i]='-';}
	
	rewind(file);
    rewind(file2);
  
    createsequence(file, c1); 
  
    printf("\n \n");
  
    createsequence(file2, c2); 
     
 
    fclose(file);
    fclose(file2);
    
    printf("\n Counter1=%d \n",seqcounter);
    printf("\n Counter2=%d \n",seqcounter2);
    
    for (i = 0; i < seqcounter; i++) {
        putchar(c1[i]);}
    
    printf("\n");
        
    for (i = 0; i < seqcounter2; i++) {
        putchar(c2[i]);}
      
      
      m=seqcounter;
      n=seqcounter2;
      
      printf("\n  m=%d n=%d \n", m, n);
      
      /* creating matrix H*/
      
      
      
      struct cell** table1;  
      table1 = (struct cell**) malloc((m+1)*sizeof(struct cell*));  
        for (i = 0; i < (m+1); i++)  {
		table1[i] = (struct cell*) malloc((n+1)*sizeof(struct cell)); }
      
      for (i = 0; i < m+1; i++) {
              for (j = 0; j < n+1; j++) {
              	if ((i==0)||(j==0)) {
              	table1[i][j].value=0;
              	table1[i][j].d=0; }
              	else {
              		table1[i][j].value=-1;
              	    table1[i][j].d=0;
				  }
              }
          }
      
      
      
	  /* printing the zero matrix*/
	  /*printmatrix (table1, m+1, n+1, c1, c2 );*/
      
      printf("\n \n");
      
      /*calculating the matrix */
      compare(c1,c2,table1,m+1,n+1);
      
      /* printing the matrix*/
      /*printmatrix (table1, m+1, n+1, c1, c2 );*/
    
      /*calculating the path */
      find_path(c1,c2,table1,m,n);
      
      printf("\n \n");
      
      /*creating the matrix H* */  
    struct cell** table2;  
      table2 = (struct cell**) malloc((m+1)*sizeof(struct cell*));  
        for (int i = 0; i < (m+1); i++)  {
		table2[i] = (struct cell*) malloc((n+1)*sizeof(struct cell)); }
      
      for (i = 0; i < m+1; i++) {
              for (j = 0; j < n+1; j++) {
              	if ((i==0)||(j==0)) {table2[i][j].value=0;}
              	else if (table1[i][j].d==1) { table2[i][j].value=0;}
              	else { table2[i][j].value=-1; }
              	table2[i][j].d=0;
              }
          }
      
          
      /*printmatrix (table2, m+1, n+1, c1, c2 );*/    
    
      compare(c1,c2,table2,m+1,n+1);
      
      /*printmatrix (table2, m+1, n+1, c1, c2 ); */
      
      printf("\n \n");
      
      
      
      /*calculating the path */
      find_path(c1,c2,table2,m,n);
      
       
    
free(c1);
free(c2);
    
free(table1);
free(table2);    
    
      
printf("\n \n");
system("PAUSE");

} 
