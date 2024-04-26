/******************************************************************************
Faça um programa para ler um número inteiro N e N elementos de um 
array. Em seguida, se N for par mostrar na tela a soma do 1o e 2o 
elemento, 3o e 4o, …
*******************************************************************************/
class Soma1com2e3com4
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
	    
	    if(n%2 == 0)
	    {
	        for(int i= 0; i< n; i+=2)
	        {
	            double soma = numeros[i] + numeros[i+1];
	            System.out.println("Soma = " + soma);
	        }
	    }
	}
}
