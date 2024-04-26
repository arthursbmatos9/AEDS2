/******************************************************************************
Leia 10 números inteiros, selecione o maior e imprima seu valor na tela.
*******************************************************************************/
import java.util.Scanner;

class MaiorValor
{
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    
	    int[] numeros = new int[10];
	    
	    for(int i= 0; i< 10; i++)
	    {
	        numeros[i] = scanner.nextInt();
	    }
	    
	    int maior = numeros[0];
	    
	    for(int i= 1; i< 10; i++)
	    {
	        if(numeros[i] > maior)
	            maior = numeros[i];
	    }
	    
	    System.out.println("\nMaior = " + maior);
	    
	    scanner.close();
	}
}
