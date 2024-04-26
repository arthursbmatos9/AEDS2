/******************************************************************************
Faça um programa que leia n números e os armazene em um array e, em seguida, mostre cada número na tela.
***************************************************************************/
class MostreArray
{
	public static void main(String[] args) {
	    
	    System.out.print("Quantos numeros voce quer armazenar? ");
	    int n = MyIO.readInt();
	    
	    double[] numeros = new double[n];
	    
	    for(int i= 0; i< n; i++)
	    {
	        System.out.print("\nDigite o valor: ");
	        numeros[i] = MyIO.readDouble();
	    }
	    
	    for(int i= 0; i< n; i++)
	    {
	        System.out.print(numeros[i] + " ");
	    }
	    
	}
}
