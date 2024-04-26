/******************************************************************************
Faça um programa que leia um número inteiro N e mostre na tela os N 
primeiros números da sequência 1, 5, 12, 16, 23, 27 34
*******************************************************************************/
import java.util.Scanner;

class Sequencia4e7
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite um numero N para ver os N primeiros números da sequência 1, 5, 12, 16, 23, 27... ");
		int N = scanner.nextInt();
		
		int valorSequencia = 1;
		int i = 0;
		
		
		while(i < N)
		{
		    System.out.println(valorSequencia);
		    valorSequencia+= 4;
		    i++;
		    
		    if(i < N)
		    {
		        System.out.println(valorSequencia);
		        valorSequencia+= 7;
		        i++;
		    }
		}
		
		scanner.close();
	}
}
