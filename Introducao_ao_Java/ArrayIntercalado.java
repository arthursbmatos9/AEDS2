/******************************************************************************
Faça um programa para ler dois vetores contendo N elementos cada. Em 
seguida, mostre os elementos de forma intercalada
*******************************************************************************/
class ArrayIntercalado
{
	public static void main(String[] args) {
	    
	    System.out.print("Quantos numeros voce quer armazenar no vetor 1? ");
	    int n1 = MyIO.readInt();
	    
	    double[] numeros1 = new double[n1];
	    
	    for(int i= 0; i< n1; i++)
	    {
	        System.out.print("\nDigite o valor: ");
	        numeros1[i] = MyIO.readDouble();
	    }
	    
	    System.out.print("Quantos numeros voce quer armazenar no vetor 2? ");
	    int n2 = MyIO.readInt();
	    
	    double[] numeros2 = new double[n2];
	    
	    for(int i= 0; i< n2; i++)
	    {
	        System.out.print("\nDigite o valor: ");
	        numeros2[i] = MyIO.readDouble();
	    }
	    
	    int menor;
	    if(n1 < n2)
	        menor = n1;
	    else
	        menor = n2;
	    
	    for(int i= 0; i< menor; i++)
	    {
	        System.out.print(" " + numeros1[i] +"  "+ numeros2[i]);
	    }
	}
}
