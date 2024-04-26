/******************************************************************************
Faça um programa que leia um número inteiro N indicando a nota máxima em uma prova P. 
Em seguida, leia a nota de 5 alunos (entre 0 e N)  
mostre na tela: 
(i) a média da turma, 
(ii) o número de alunos cuja nota foi menor que a média da Universidade (suponha 60%)
(iii) a quantidade de alunos com conceito A (mais de 90%).
*******************************************************************************/
import java.util.Scanner;

class MediaeQtd
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite a nota maxima da prova: ");
		double N = scanner.nextDouble();
		double[] notas = new double[5];
		double soma = 0;
		int i = 0;
		int abaixoMedia = 0;
		int acimaA = 0;
		
		while(i < 5)
		{
		    System.out.println("Digite a nota do aluno: ");
		    notas[i] = scanner.nextDouble();
		    soma+= notas[i];
		    
		    if(notas[i] < N*0.6)
		        abaixoMedia++;
		    if(notas[i] > N*0.9)
		        acimaA++;
		        
		    i++;
		}
		
		System.out.println("\nMedia da turma = " + soma/5 + "; Abaixo da media = " + abaixoMedia + "; Conceito A = " + acimaA);
		
		
		
		
		scanner.close();
	}
}
