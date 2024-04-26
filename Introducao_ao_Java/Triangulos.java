/******************************************************************************
Faça um programa que leia três números reais representando os lados  de um triângulo e informe se seu triângulo é Equilátero,
Isósceles ou Escaleno
*******************************************************************************/
import java.util.Scanner;

class Triangulos
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		double lado1, lado2, lado3;
		
		System.out.println("Digite os lados do triangulo: ");
		lado1 = scanner.nextDouble();
		lado2 = scanner.nextDouble();
		lado3 = scanner.nextDouble();
		
		if(lado1 == lado2 && lado1 == lado3)
		    System.out.println("\nTriangulo Equilatero");
		else if(lado1 == lado2 && lado1 != lado3 || lado2 == lado3 && lado2 != lado1)
		    System.out.println("\nTriangulo Isosceles");
		else
		    System.out.println("\nTriangulo Escaleno");
		
		scanner.close();
	}
}
