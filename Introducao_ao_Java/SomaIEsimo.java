/******************************************************************************
Faça um programa que leia n números e mostre a soma do i-ésimo com o 
(2*i+1)-ésimo termo até que (2*i+1) < n
*******************************************************************************/
class SomaIEsimo
{
	public static void main(String[] args) {
	    
	    System.out.print("Quantos numeros voce quer armazenar? ");
	    int n = MyIO.readInt();
	    
	    double[] numeros = new double[n];
	    double soma = 0;
	    
	    for(int i= 0; i< n; i++)
	    {
	        System.out.print("\nDigite o valor: ");
	        numeros[i] = MyIO.readDouble();
	    }
	    
	    for(int i= 0; (2*i+ 1) < n; i++)
	    {
	        soma = soma + numeros[i] + numeros[(2*i+ 1)];
	    }
	    
	    System.out.println("\nSoma = " + soma);
	}
}
