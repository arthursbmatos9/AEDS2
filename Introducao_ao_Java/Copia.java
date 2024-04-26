/******************************************************************************
Faça um programa que abre um arquivo e cria uma cópia
*******************************************************************************/
class Copia
{
	public static void main(String[] args) {
	    
		Arq.openRead("exemplo.txt");
		String str = Arq.readAll();
		Arq.close();
		
		Arq.openWrite("copia.txt");
		Arq.println(str);
		Arq.close();
	}
}
