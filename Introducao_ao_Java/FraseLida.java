/******************************************************************************
• Leia o nome de um arquivo e uma frase e salve essa frase nesse arquivo
*******************************************************************************/
class FraseLida
{
	public static void main(String[] args) {
		
		System.out.println("Digite uma frase para salva-la no arquivo arquivo.txt: " );
		
		
		Arq.openWrite("arquivo.txt");
		
		String frase = MyIO.readLine();
		Arq.println(frase);
		
		Arq.close();
	}
}
