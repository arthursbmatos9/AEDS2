/******************************************************************************
• Faça um programa que leia N números inteiros e mostre na tela a soma 
daqueles que forem múltiplos de três
******************************************************************************/
class SomaMultiplosTres
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
	    
	    System.out.print("Sao multiplos de tres: ");
	    
	    for(int i= 0; i< n; i++)
	    {
	        if(numeros[i] % 3 == 0)
	            System.out.print("; " + numeros[i]);
	    }
	}
}
