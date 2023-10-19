#include <stdbool.h>
#include <stdio.h>
#include <string.h>

bool isFim(char *letra) // funcao fornecida no PDF para verificar se é FIM
{
  return (letra[0] == 'F' && letra[1] == 'I' && letra[2] == 'M' &&
          strlen(letra) >= 3);
}

int main() {
  char palavra[1000];

  do{
    fgets(palavra, sizeof(palavra), stdin);
    int tam = 0;
    int i = 0;
    bool igual = true;

    if (!isFim(palavra)) {
      while (palavra[i] != '\n' && palavra[i] != '\0') // verificando tamanho da string
      {
        tam++;    //aumenta o tamanho
        i++;      //passa pra proxima letra
      }

      for (int j = 0; j <= tam; j++) {
        if (palavra[j] != palavra[tam - 1]) {  //caso as letras nao sejam iguais, tornar o bool falso e encerrar o loop
          igual = false;
          tam = 0;
        }

        else
          tam--;   //caso letras iguais, passar pras proximas letras (aumenta o 'j' e diminui o 'tam')
      }

      if (igual == true)
        printf("SIM\n");
      else
        printf("NAO\n");
    }
  } while (!isFim(palavra));

  return 0;
}