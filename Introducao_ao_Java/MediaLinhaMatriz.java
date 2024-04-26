/******************************************************************************
Faça um programa que leia os elementos de uma matriz e mostre a média 
dos elementos de cada linha
*******************************************************************************/
class MediaLinhaMatriz
{
    public static final int nLin = 3;
    public static final int nCol = 3;
    
	public static void main(String[] args) {
	    
		int[][] matriz = new int[nLin][nCol];
	    int soma = 0;
	    
	    for(int i= 0; i< nLin; i++)
	    {
	        for(int j= 0; j< nCol; j++)
	        {
	            System.out.print("Digite o termo " + i + j + ": ");
	            matriz[i][j] = MyIO.readInt();
	            soma+= matriz[i][j];
	        }
	        
	        System.out.println("\nMedia dos elementos da linha = " + soma/nCol);
	        soma = 0;
	    }
	    
	   
	}
}
