/******************************************************************************
Faça um programa que leia os elementos de uma matriz com L linhas e C 
colunas e mostre na tela os elementos da matriz em formato de grid
*******************************************************************************/
class MostreMatriz
{
	public static void main(String[] args)
	{
	    System.out.print("Digite o n de linhas: ");
	    int nLin = MyIO.readInt();
	    System.out.print("Digite o n de colunas: ");
	    int nCol = MyIO.readInt();
	    
	    int[][] matriz = new int[nLin][nCol]; 
	    
	    for(int i= 0; i< nLin; i++)
	    {
	        for(int j= 0; j< nCol; j++)
	        {
	            System.out.print("Digite o termo " + i + j + ": ");
	            matriz[i][j] = MyIO.readInt();
	        }
	    }
	    
	    for(int i= 0; i< nLin; i++)
	    {
	        
	        for(int j= 0; j< nCol; j++)
	        {
	            System.out.print(matriz[i][j] + " ");
	        }
	       
	        System.out.print("\n");
	   }
	}
}
