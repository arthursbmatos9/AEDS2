/******************************************************************************
Leia o nome de dois arquivos e copie o conteúdo do primeiro 
para o segundo invertendo a ordem dos caracteres. O último 
caractere no arquivo de entrada será o primeiro do de saída, o 
penúltimo caractere será o segundo...
*******************************************************************************/
class InverterString
{
	public static void main(String[] args) {
	    
	    Arq.openRead("arq1.txt");
	    String conteudo = Arq.readAll();
	    Arq.close();
	    
	    Arq.openWrite("arq2.txt");
	    int tamanho = conteudo.length();
	    for(int i= tamanho - 1; i>= 0; i--)
	    {
	        Arq.print(conteudo.charAt(i));
	    }
	    Arq.close();
	}
}
