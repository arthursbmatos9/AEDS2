/******************************************************************************
Faça um programa que leia uma String e um caractere e mostre na tela 
quantas vezes esse caractere aparece na String
*******************************************************************************/
class QtdCharNumaString
{
	public static void main(String[] args) {
	    
	    String palavra = MyIO.readLine();
	    char letra = MyIO.readChar();
	    int qtd = 0;
	    
	    for(int i= 0; i< palavra.length(); i++)
	    {
	        if(palavra.charAt(i) == letra)
	            qtd++;
	    }
	    
	    System.out.println("\nA letra '" + letra + "' aparece " + qtd + " vezes na string '" + palavra + "'");
	}
}
