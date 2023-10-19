#include <stdbool.h>
#include <stdio.h>
#include <string.h>


int main() {

  int n;
  scanf("%d", &n);
  
  double valorLido, valor;
  float valor2[1000];

  FILE *arq = fopen("arq.txt", "w");  //abrindo arquivo no modo de escrita 'w'

  for (int i = 0; i < n; i++) 
  {
    scanf(" %lf", &valorLido);          //lendo valor 
    fprintf(arq, " %f", valorLido);    //salvando valor lido no arquivo 'arq'
  }

  fclose(arq);


  
  arq = fopen("arq.txt", "r");      //abrindo novamente o arquivo, mas no modo de leitura 'r'

  int tam = 0;             //tamanho do array
  while (fscanf(arq, " %f", &valor2[tam]) == 1) {   //calcular numero de numeros no arquivo 
        tam++;            //vai aumentando até que tenham valores no array
  }

  
  for (int i = tam -1; i >= 0; i--) 
  {
    
    fscanf(arq, " %f", &valor2[i]);   //lendo o valor do arquivo e salvando em valor2

    if(valor2[i] == (int)valor2[i])
      printf("%d\n", (int)valor2[i]);
    else 
      printf("%.3f\n", valor2[i]);        //mostrando na tela o valor2 - que foi lido do arquivo

  }

  fclose(arq);

  return 0;
}


