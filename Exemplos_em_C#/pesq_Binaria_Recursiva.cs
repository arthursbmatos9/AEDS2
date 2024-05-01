using System;

class Program {
  public static void Main (string[] args) {

    int[] numeros = new int[5];
    int contador = 0;
    
    preenchaArray(numeros);

    Console.WriteLine("Digite chave: ");
    int chave = int.Parse(Console.ReadLine());
    int existe = pesquisaBinaria(numeros, chave, 0, 4, ref contador);  //array, chave, esq, dir    //ref usado para passar como parametro uma varivel que nao pode ser criada em toda instancia, apenas uma unica vez

    if(existe >= 0)
      Console.Write("Elemento " + chave + " encontrado.");
    else
      Console.Write("Elemento " + chave + " nao encontrado.");

    
      Console.WriteLine("\nForam feitas " + contador + " comparacoes.");
  }

  public static void preenchaArray(int[] numeros)
  {
    for(int i= 0; i< 5; i++)
    {
      Console.WriteLine("Digite numero: ");
      numeros[i] = int.Parse(Console.ReadLine());
    }
    Array.Sort(numeros);    //ordenando o array
  }

  public static int pesquisaBinaria(int[] numeros, int chave, int esq, int dir, ref int contador)
  {
    if(esq > dir)
        return -1;
    else
    {
      int meio = (esq+dir) /2;
      contador++;
      
      if(chave == numeros[meio])
        return meio;
      else if(chave > numeros[meio])
        return pesquisaBinaria(numeros, chave, meio+1, dir, ref contador);
      else
        return pesquisaBinaria(numeros, chave, esq, meio-1, ref contador);
    }
  }
}