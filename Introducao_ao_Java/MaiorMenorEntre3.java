/******************************************************************************
• Leia 3 números inteiros, selecione o menor e o maior e imprima os seus 
respectivos valores na tela
*******************************************************************************/
class MaiorMenorEntre3
{
	public static void main(String[] args) {
		int[] numeros = new int[3];
		
		for(int i= 0; i< 3; i++)
		{
		    numeros[i] = MyIO.readInt();
		}
		
		int maior = numeros[0];
		int menor = numeros[0];
		
		for(int i= 1; i< 3; i++)
		{
		    if(numeros[i] > maior)
		        maior = numeros[i];
		    else if(numeros[i] < menor)
		        menor = numeros[i];
		}
		
		System.out.println("Maior = " + maior + "; Menor = " + menor);
	}
}
