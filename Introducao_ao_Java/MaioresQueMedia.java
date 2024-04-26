/******************************************************************************
 Faça um programa que leia n números inteiros, calcule a média 
desses valores e mostre aqueles que forem maiores que a média
*******************************************************************************/
class MaioresQueMedia
{
	public static void main(String[] args) {
		
		System.out.print("Digite a qtd de termos (n): ");
		int n = MyIO.readInt();
		
		int soma = 0;
		double media;
		int[] numeros = new int[n];
		
		for(int i= 0; i< n; i++){
		    numeros[i] = MyIO.readInt();
		    soma+= numeros[i];
		}
		
		media = soma/n;
		
		for(int i= 0; i< n; i++){
		    if(numeros[i] > media)
		        System.out.println("Acima da media: " + numeros[i]);
		}
		
	
	}
}
