#include <stdbool.h>
#include <stdio.h>
#include <string.h>

bool isFim(char *letra) // funcao fornecida no PDF para verificar se é FIM
{
  return (letra[0] == 'F' && letra[1] == 'I' && letra[2] == 'M' && strlen(letra) >= 3);
}

bool ehPalindromo(char* letra, int i, int tam)
{
  bool palindromo = true;

  if(i >= tam)            //retornar 'palindromo'[true] apos verificar todas letras e nao encontrar nenhuma letra diferente
    return palindromo;
  else
  {
    if (letra[i] != letra[tam - 1])      //caso as letras nao sejam iguais, tornar o bool falso e encerrar o loop
    {  
        palindromo = false;
        tam = 0;
    }

    else                 //caso as letras sejam iguais, chamar novamente a funcao, mas adicionando 1 a letra da esquerda e diminuindo 1 a letra da direita
      ehPalindromo(letra, i+1, tam-1);

    return palindromo;  //retornando 'palindromo' caso o loop se torne 'false' no 'if'
  }
  
  
}

int main() {
  char palavra[1000];

  do{
    fgets(palavra, sizeof(palavra), stdin);
    int tam = 0;
    int i = 0;
   

    if (!isFim(palavra))  //somente realizar se nao for digitado FIM
    {  
      while (palavra[i] != '\n' && palavra[i] != '\0') // verificando tamanho da string
      {
        tam++;    //aumenta o tamanho
        i++;      //passa pra proxima letra
      }

       bool igual = ehPalindromo(palavra, 0, tam);  //chamando funcao que verifica se as letras sao iguais

      if (igual == true)
        printf("SIM\n");
      else
        printf("NAO\n");
    }
  } while (!isFim(palavra));  //realizar enquanto nao for digitado FIM

  return 0;
}