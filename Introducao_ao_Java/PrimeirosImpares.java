/******************************************************************************
Faça um programa que leia um número inteiro N e mostre na tela os N primeiros números inteiros ímpares
*******************************************************************************/
import java.util.Scanner;

class PrimeirosImpares
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite um numero N para ver os N primeiros numeros inteiros impares: ");
		int N = scanner.nextInt();
		int i = 0;
		int impar = 1;
		
		while(i < N)
		{
		    System.out.println(impar);
		    impar+= 2;
		    i++;
		}
		
		scanner.close();
	}
}
