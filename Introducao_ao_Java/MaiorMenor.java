/******************************************************************************
Faça um método que receba um array de inteiros e mostre na tela o maior e o menor elementos do array.
Repita o exercício acima fazendo menos comparações com os elementos do array
*******************************************************************************/
import java.util.Scanner;

class MaiorMenor
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] inteiros = new int[5];
		
		for(int i= 0; i< 5; i++){
		    inteiros[i] = sc.nextInt();
		}
		
		int maior = inteiros[0];
		int menor = inteiros[0];
		
		for(int i= 1; i< 5; i++){
		    if(inteiros[i] > maior)
		        maior = inteiros[i];
		    if(inteiros[i] < menor)
		        menor = inteiros[i];
		}
		
		System.out.println("Maior = " + maior + " ; Menor = " + menor);
	}
}
