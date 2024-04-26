/******************************************************************************
O banco do Zé abriu uma linha de crédito para os seus clientes. O valor máximo da prestação não poderá ultrapassar 40% do salário bruto. 
Fazer um algoritmo que permita entrar com o salário bruto e o valor da prestação e informar se o empréstimo será concedido.
*******************************************************************************/
import java.util.Scanner;

class LinhaCredito
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite salario bruto: ");
		double salarioBruto = scanner.nextDouble();
		
		System.out.println("Digite valor da prestacao: ");
		double prestacao = scanner.nextDouble();
		
		if(prestacao <= salarioBruto * 1.4)
		    System.out.println("\nEMPRESTIMO CONCEDIDO");
		else
		    System.out.println("\nEMPRESTIMO NAO CONCEDIDO");
		
		scanner.close();
	}
}
