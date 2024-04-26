/******************************************************************************
Faça um programa para ler uma palavra. Em seguida, mostre a primeira 
ocorrência da letra A
*******************************************************************************/
class PrimeiraOcorrenciaLetraA
{
	public static void main(String[] args) {
	    
	    String palavra = MyIO.readString();
	    boolean existe = false;
	    int posicao = 0;
	    int i= 0;
	    
	    while(!existe)
	    {
	        if(palavra.charAt(i) == 'A' || palavra.charAt(i) == 'a')
	        {
	            existe = true;
	            posicao = i;
	        }
	        else
	            i++;
	    }
	    
	    System.out.println("\nPrimeira ocorrencia ocorreu na posicao " + posicao);
	}
}
