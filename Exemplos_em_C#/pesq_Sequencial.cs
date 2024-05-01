using System;

class Program {
  public static void Main (string[] args) 
  {  
    int[] aleatorios = new int[20];
    int x = 10;
    
    preencheVetor(aleatorios);
    
    int qtdX = verificaVetor(aleatorios, x);
    Console.WriteLine("\nNumero de X no vetor = " + qtdX);
    
    escreveVetor(aleatorios);
    
  }

  public static void preencheVetor(int[] aleatorios)
  {
    Random random = new Random();        //criando o objeto random da class Random, para gerar numers aleatorios
    for (int i = 0; i < aleatorios.Length; i++) {
      aleatorios[i] = random.Next(50);     //gerando numeros aleatorios ate 50
    }
  }

  public static int verificaVetor(int[] aleatorios, int x)
  {
    int qtdX= 0, nComparasoes = 0;  
    for(int i= 0; i< 20; i++)
    {
      nComparasoes++;
      if(aleatorios[i] == x)   //verificando se o numero gerado é igual a X (foi declarado como 10)
        qtdX++;
    }

    Console.Write("\nNumero de comparacoes realizadas = " + nComparasoes);

    return qtdX;
  }

  public static void escreveVetor(int[] aleatorios)
  {
    for(int i= 0; i< 20; i++)
    {
      Console.Write(aleatorios[i] + "; ");
    }
  }
}