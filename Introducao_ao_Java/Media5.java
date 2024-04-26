/******************************************************************************
Faça um programa que leia a nota de 5 alunos e mostre na tela a média 
das mesmas
*******************************************************************************/
import java.util.Scanner;

class Media5
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		double[] notas = new double[5];
		int i = 0;
		double soma = 0;
		
		while(i < 5)
		{
		    notas[i] = scanner.nextDouble();
		    i++;
		    soma += notas[i];
		}
		
		System.out.println("Media = " + soma / 5);
		
		scanner.close();
	}
}
