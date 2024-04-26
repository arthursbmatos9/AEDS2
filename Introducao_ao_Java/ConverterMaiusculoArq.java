/******************************************************************************
Leia o nome de dois arquivos, abra o primeiro, converta seu 
conteúdo para letra maiúscula e salve o no segundo
*******************************************************************************/
class ConverterMaiusculoArq
{
	public static void main(String[] args) {
	    
	    Arq.openRead("arq1.txt");
	    String conteudo = Arq.readAll();
	    String maiuscula = conteudo.toUpperCase();
	    Arq.close();
	    
	    Arq.openWrite("arq2.txt");
	    Arq.println(maiuscula);
	    Arq.close();
	}
}
