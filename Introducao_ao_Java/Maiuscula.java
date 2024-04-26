/******************************************************************************
Leia o nome de um arquivo e mostre seu conteúdo convertido para letras maiúsculas

*******************************************************************************/
class Maiuscula
{
	public static void main(String[] args) {
	    
	    Arq.openRead("arthur.txt");
	    
	    String copia = Arq.readAll();
	    String copiaMaiuscula = copia.toUpperCase();
	    
	    Arq.close();
	    
	    System.out.println(copiaMaiuscula);
	}
}
