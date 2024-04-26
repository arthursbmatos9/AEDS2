/******************************************************************************
• Faça um programa que leia N números inteiros e mostre na tela a soma do 
primeiro e do último, a do segundo e do penúltimo, a do terceiro e do 
antepenúltimo
*******************************************************************************/
class SomaAlternada
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
	    
	    int i= 0;
	    while(i< n/2)
	    {
	        double soma = numeros[i] + numeros[n-i- 1];
	        System.out.println(soma);
	        
	        i++;
	    }
	}
}
