/******************************************************************************
Leia o nome de dois arquivos e copie o conteúdo do primeiro para o último
*******************************************************************************/
class CopiarArquivos
{
	public static void main(String[] args) {
	    
	    Arq.openRead("arq1.txt");
	        String conteudo = Arq.readAll();
	    Arq.close();
	    
	    Arq.openWrite("arq2.txt");
	        Arq.println(conteudo);
	    Arq.close();
	    
	}
}
