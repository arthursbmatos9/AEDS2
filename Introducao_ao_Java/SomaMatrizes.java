/******************************************************************************
Faça um programa que leia duas matrizes com os mesmos números de 
linhas e colunas, faça a soma das mesmas e imprima na tela a matriz 
resultante
*******************************************************************************/
class SomaMatrizes
{
	public static final int nLin = 3;
	public static final int nCol = 3;
	
	public static void leiaMatriz(int matriz[][])
	{
	    for(int i= 0; i< nLin; i++)
	    {
	        for(int j= 0; j< nCol; j++)
	        {
	            System.out.print("Digite o termo " + i + j + ": ");
	            matriz[i][j] = MyIO.readInt();
	        }
	    }
	}
	
	public static void preencheMatriz3(int matriz1[][], int matriz2[][], int matriz3[][])
	{
	     for(int i= 0; i< nLin; i++)
	    {
	        for(int j= 0; j< nCol; j++)
	        {
	            matriz3[i][j] = matriz1[i][j] + matriz2[i][j];
	        }
	    }
	}
	
	public static void mostraMatriz3(int matriz3[][])
	{
	    System.out.println("\nMatriz resultante: ");
	    for(int i= 0; i< nLin; i++)
	    {
	        for(int j= 0; j< nCol; j++)
	        {
	            System.out.print(matriz3[i][j] + " ");
	        }
	        
	        System.out.println("");
	    }
	}
	
	public static void main(String[] args) {
	    
	    int[][] matriz1 = new int[nLin][nCol];
	    int[][] matriz2 = new int[nLin][nCol];
	    int[][] matriz3 = new int[nLin][nCol]; 
	    
	    leiaMatriz(matriz1);
	    leiaMatriz(matriz2);
	    
	    preencheMatriz3(matriz1, matriz2, matriz3);
	    mostraMatriz3(matriz3);
	}
	
}
