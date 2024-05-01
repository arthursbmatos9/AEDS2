using System;

class Program {
  public static int n = 100;
  
  public static void Main (string[] args) 
  {
    Random random = new Random();
    
    int[] array = new int[n];

    for(int i= 0; i< n; i++)
    {
      array[i] = random.Next(200); //preenchendo array e limitando numeros a 200
    }

    Console.WriteLine("Array nao ordenado: ");
    printaArray(array);
  /////////////////////////////////////////////////////
  //ordenando array
    for(int i= 0; i< (n-1); i++)
    {
      int menor = i;
      
      for(int j= (i+1); j< n; j++)
      {
        if(array[j] < array[menor])
            menor = j;
      }  //fim for j
      
      swap(menor, i, array);
    } //fim for i
  /////////////////////////////////////////////////////
    Console.WriteLine("\n\nArray ordenado: ");
    printaArray(array);
    
  } //fim Main

  
  public static void swap(int menor, int j, int[] array)
  {
    int temp = array[menor];
    array[menor] = array[j];
    array[j] = temp;
  }  //fim swap

  
  public static void printaArray(int[] array)
  {
    for(int i= 0; i< n; i++)
    {
      Console.Write(array[i] + "; ");
    }
  }  //fim printaArray
  
}  //fim class