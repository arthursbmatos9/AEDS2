/******************************************************************************
Faça um programa para ler uma palavra. Em seguida, mostre os números 
de caracteres, letras, não letras, vogais e consoantes
*******************************************************************************/
class NumeroLetrasVogaisConsoantes
{
	public static void main(String[] args) {
	    
	    String palavra = MyIO.readString();
	    int qtdCarac = palavra.length();
	    int qtdLetras = 0;
	    int qtdNaoLetras = 0;
	    int qtdVogais = 0;
	    int qtdConsoantes = 0;
	    
	    for(int i= 0; i< qtdCarac; i++)
	    {
	        if(ehLetra(palavra.charAt(i)))
	        {
	            qtdLetras++;
	            if(ehVogal(palavra.charAt(i)))
	                qtdVogais++;
	            else
	                qtdConsoantes++;
	        }
	        
	        else
	            qtdNaoLetras++;
	    }
	    
	    System.out.println("Caracteres = " + qtdCarac + "; Letras = " + qtdLetras + "; Nao letras = " + qtdNaoLetras + "; Vogais = " + qtdVogais + "; Consoantes = " + qtdConsoantes);
	}
	
	public static boolean ehLetra(char c) {
	    
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean ehVogal(char c) {
        
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
