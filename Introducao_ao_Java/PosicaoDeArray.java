/******************************************************************************
Faça um programa que leia os elementos de um array de tamanho n e 
mostre a posição do menor elemento do array
*******************************************************************************/
class PosicaoDeArray
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
	    
	    double menor = numeros[0];
	    int pMenor = 0;
	    
	    for(int i= 0; i< n; i++)
	    {
	        if(numeros[i] < menor)
	        {
	            menor = numeros[i];
	            pMenor = i;
	        }
	    }
	    
	    System.out.println("Posicao do menor = " + pMenor);
	}
}
