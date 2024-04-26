/******************************************************************************
Leia o nome de um arquivo e mostre seu conteúdo na tela
*******************************************************************************/
class LeArquivo
{
	public static void main(String[] args) {
	    
	    Arq.openRead("arthur.txt");
	    
	    String conteudo = Arq.readAll();
	    System.out.println(conteudo);
	    
	    Arq.close();
	}
}
