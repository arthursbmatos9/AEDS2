/******************************************************************************
Faça um programa para ler a nota de cinco alunos, calcular e mostrar: a 
soma e a média das mesmas e a menor nota
*******************************************************************************/
class SomaMediaMenorNota
{
	public static void leia(double notas[])
	{
	    for(int i= 0; i< 5; i++)
	    {
	        System.out.print("Digite nota: ");
	        notas[i] = MyIO.readDouble();
	    }
	}
	
	public static void calculeEMostre(double notas[])
	{
	    double soma = 0;
	    double menor = notas[0];
	    
	    for(int i= 0; i< 5; i++)
	    {
	        soma+= notas[i];
	        if(notas[i] < menor)
	            menor = notas[i];
	    }
	    
	    double media = soma/5;
	    
	    System.out.print("\nSoma das notas = " + soma + "; Media das notas = " + media + "; Menor nota: " + menor);
	}
	
	public static void main(String[] args) 
	{
	    double[] notas = new double[5];
	    
	    leia(notas);
	    calculeEMostre(notas);
	}
}
