/******************************************************************************
Leia dois números. 
Se um deles for maior que 45, realize a soma dos mesmos. 
Caso contrário, se os dois forem maior que 20, realize a subtração do maior pelo menor, 
    senão, se um deles for menor do que 10 e o outro diferente de 0 realize a divisão do primeiro pelo segundo. 
    Finalmente, se nenhum dos casos solicitados for válido, mostre seu nome na tela.
*******************************************************************************/
import java.util.Scanner;

class SomaNumeros
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int valor1 = scanner.nextInt();
		int valor2 = scanner.nextInt();
		
		if(valor1 > 45 || valor2 > 45)
		    System.out.println(valor1 + valor2);
		else if(valor1 > 20 && valor2 > 20)
		    {
		    if(valor1 > valor2)
		        System.out.println(valor1 - valor2);
		    else
		        System.out.println(valor2 - valor1);
		    }
		else if(valor1 < 10 && valor2 != 0)
		    System.out.println(valor1 / valor2);        
		else if(valor2 < 10 && valor1 != 0)
		    System.out.println(valor2 / valor1);
		 else
		    System.out.println("Arthur");
		 
		scanner.close();   
	}
}
