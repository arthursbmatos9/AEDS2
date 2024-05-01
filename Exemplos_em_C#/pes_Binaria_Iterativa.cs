using System;

class Program {
  public static int n = 10;
  public static void Main (string[] args) {
    
    int[] numeros = new int[n];
    int chave = 10;
    
    preencheVetor(numeros);
    bool existe = verificaVetor(numeros, chave);
    
    Console.WriteLine(existe);
    escreveVetor(numeros);
  }

  public static void preencheVetor(int[] numeros)
  {
    Random random = new Random();
    for(int i= 0; i< n; i++)
    {
      numeros[i] = random.Next(15);
    }
  }

  public static bool verificaVetor(int[] numeros, int chave)
  {
    bool existe = false;
    int esq = 0, dir = n - 1, meio, qtdComp = 0;

    while(esq <= dir)
    {
      meio = (dir+esq) / 2;
      qtdComp++;
      
      if(numeros[meio] == chave)
      {
        existe = true;
        esq = n;
      }
      else if(chave > numeros[meio])
        esq = meio + 1;
      else
        dir = meio - 1;
    }

    Console.WriteLine("Foram realizadas " + qtdComp + " comparacoes.");
    return existe;
  }

  public static void escreveVetor(int[] numeros)
  {
    foreach(int valor in numeros)
    {
      Console.Write(valor + "; ");
    }
  }
}