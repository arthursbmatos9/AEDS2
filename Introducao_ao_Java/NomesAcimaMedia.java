/******************************************************************************
Faça um programa que leia a nota e o nome de 5 alunos e mostre na tela 
o nome daqueles que ficaram acima da média do grupo
*******************************************************************************/
class NomesAcimaMedia
{
	public static void leiaPessoa(float nota[], String nome[])
	{
	    for(int i= 0; i< 5; i++)
	    {
	        System.out.print("Digite nome e depois nota: ");
	        nome[i] = MyIO.readString();
	        nota[i] = MyIO.readFloat();
	    }
	}
	
	public static float calculeMedia(float nota[])
	{
	    float soma = 0;
	    for(int i= 0; i< 5; i++)
	    {
	        soma+= nota[i];
	    }
	    
	    return soma/5;
	}
	   
	public static void escrevaNomes(float nota[], String nome[], float media)
	{
	    for(int i= 0; i< 5; i++)
	    {
	        if(nota[i] > media)
	            System.out.println("Aluno " + nome[i] + " esta acima da media.");
	    }
	}
	    
	public static void main(String[] args) 
	{
		float[] nota = new float[5];
		String[] nome = new String[5];
		
		leiaPessoa(nota, nome);
		float media = calculeMedia(nota);
		escrevaNomes(nota, nome, media);
		
	}
}
