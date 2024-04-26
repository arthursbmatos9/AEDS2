/******************************************************************************
Faça um programa para ler uma palavra. Em seguida, mostre o número de 
caracteres da mesma e seu número de caracteres maiúsculos
*******************************************************************************/
class NumeroCaracteresENumeroMaiusculos
{
	public static void main(String args[]){
	    
	    String palavra = MyIO.readString();
	    int tamanho = palavra.length();
	    int maiusculos = 0;
	    
	    for(int i= 0; i< tamanho; i++){
	        if(Character.isUpperCase(palavra.charAt(i)))
	            maiusculos++;
	    }
	    
	    System.out.println("\nNumero de caracteres = " + tamanho + "; Numero de caracteres maiusculos = " + maiusculos);
	}
}
