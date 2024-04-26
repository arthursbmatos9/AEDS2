/******************************************************************************
Faça um programa para ler um número inteiro N e N elementos de um 
array. Em seguida, calcular e mostrar o número de elementos pares e o de 
divisíveis por três
*******************************************************************************/
class NumeroParesNumeroDivisiveisPorTres
{
	public static void calculePares(double numeros[], int n)
	{
	    int nPares = 0;
	    
	    for(int i= 0; i< n; i++)
	    {
	        if(numeros[i]%2 == 0)
	            nPares++;
	    }
	    
	    System.out.println("\nNumero de pares = " + nPares);
	}
	
	public static void calculeTres(double numeros[], int n)
	{
	    int nTres = 0;
	    
	    for(int i= 0; i< n; i++)
	    {
	        if(numeros[i]%3 == 0)
	            nTres++;
	    }
	    
	    System.out.println("\nNumero de divisiveis por tres = " + nTres);
	}
	
	public static void main(String[] args) 
	{
	    System.out.print("Quantos numeros voce quer armazenar? ");
	    int n = MyIO.readInt();
	    
	    double[] numeros = new double[n];
	    
	    for(int i= 0; i< n; i++)
	    {
	        System.out.print("\nDigite o valor: ");
	        numeros[i] = MyIO.readDouble();
	    }
		
		calculePares(numeros, n);
		calculeTres(numeros, n);
	}
}
