#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct Jogador {
  int id;
  char nome[100];
  int altura;
  int peso;
  int anoNascimento;
  char* universidade;
  char* cidadeNascimento;
  char* estadoNascimento;
};

struct Jogador jogadores[3922];

int n = 3922;
int nMov= 0;
int nComp= 0;

bool isFim(char *leitura) {
  bool ehFim;
  if (leitura[0] == 'F' && leitura[1] == 'I' && leitura[2] == 'M')
    ehFim = true;
  else
    ehFim = false;
  return ehFim;
}

int ler() {
  int resp;
  char leitura[20];

  fgets(leitura, sizeof(leitura), stdin);

  if (isFim(leitura))
    resp = -1;
  else
    resp = atoi(leitura);

  return resp;
}

void imprimir(struct Jogador* j, int pos) {
  printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", j[pos].id,
         j[pos].nome, j[pos].altura, j[pos].peso,
         j[pos].anoNascimento, j[pos].universidade,
         j[pos].cidadeNascimento, j[pos].estadoNascimento);
}

bool vazio(char* palavra){
  return (palavra[0] == '\0');
}

//=============================================================================
struct Jogador getMax(struct Jogador* array, int n) {
    struct Jogador maior = array[0];

    for (int i = 1; i < n; i++) {
        if(maior.id < array[i].id){
            maior = array[i];
        }
    }
    return maior;
}
//=============================================================================
  void radcountingSort(struct Jogador* array, int n, int exp) {
        int count[10];
        struct Jogador output[n];

        //Inicializar cada posicao do array de contagem 
        for (int i = 0; i < 10; count[i] = 0, i++);

        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; i++) {
            count[(array[i].id /exp) % 10]++;
        }

        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for (int i = 1; i < 10; i++) {
            count[i] += count[i-1];
        }

        //Ordenando
        for (int i = n-1; i >= 0; i--) {
            output[count[(array[i].id / exp) % 10] - 1] = array[i];
            count[(array[i].id / exp) % 10]--;
        }

        //Copiando para o array original
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }

void radixsort(struct Jogador* array, int n) {
    //Array para contar o numero de ocorrencias de cada elemento
    struct Jogador max = getMax(array, n);
    for (int exp = 1; max.id / exp > 0; exp *= 10) {
            radcountingSort(array, n, exp);
    }
}
//=============================================================================

int main() {
  FILE *arq = fopen("/tmp/players.csv", "r");

  char linha[200];                  // salvar a linha aqui
  fgets(linha, sizeof(linha), arq); // apenas para ignorar primeira linha

  char* atributos[8];  //criando um ponteiro para array de tamanho 8 para armazenar os atributos 
  
  for (int i = 0; i < 8; ++i) {      //Alocar espaço de memória para cada string
      atributos[i] = (char*)calloc(100, sizeof(char)); //quantas vezes quer alocar cada tamanho (preenche com '\0' os espacos sobrando, por ex: tem tamanho 100 mas usou so 10, completa com 90 '\0')
    }
  
  for (int i = 0; i < 3922; i++) { // le cada linha por vez e salva seus dados em seus respectivos atributos
    fscanf(arq, "%[^,]", atributos[0]);
    jogadores[i].id = atoi(atributos[0]);
    fgetc(arq);
    
    fscanf(arq, "%[^,]", atributos[1]);
    strcpy(jogadores[i].nome, atributos[1]);
    fgetc(arq);
    
    fscanf(arq, "%[^,]", atributos[2]);
    jogadores[i].altura = atoi(atributos[2]);
    fgetc(arq);
    
    fscanf(arq, "%[^,]", atributos[3]);
    jogadores[i].peso = atoi(atributos[3]);
    fgetc(arq);

    jogadores[i].universidade = "nao informado";      //setando universidade como NAO INFORMADO[n precisa de alcoar memoria pq ja sabe-se o tamanho]
    atributos[4] = (char*)calloc(100, sizeof(char));  //alocando espaco para ler o atributo[nao sabemos o tamanho certo]
    fscanf(arq, "%[^,]", atributos[4]);
    if(!vazio(atributos[4]))        //caso nao seja vazio, mudar o "nao informado" para o nome lido da universidade
      jogadores[i].universidade = atributos[4];
    fgetc(arq);

    fscanf(arq, "%[^,]", atributos[5]);
    jogadores[i].anoNascimento = atoi(atributos[5]);
    fgetc(arq);

    jogadores[i].cidadeNascimento = "nao informado";
    atributos[6] = (char*)calloc(100, sizeof(char));
    fscanf(arq, "%[^,]", atributos[6]);
    if(!vazio(atributos[6]))
      jogadores[i].cidadeNascimento = atributos[6];
    fgetc(arq);
    
    jogadores[i].estadoNascimento = "nao informado";
    atributos[7] = (char*)calloc(100, sizeof(char));
    fscanf(arq, "%[^,\n]", atributos[7]);
    if(!vazio(atributos[7]))
      jogadores[i].estadoNascimento = atributos[7];
    fgetc(arq);
  }

//NAO É NECESSARIO DAR FREE PQ NAO SE TRATA DE ESTRUTURA FLEXIVEL
  
   /* //nao é mais necessario usar "atributos", entao temos que liberar a memoria, como se fosse atributos = null
   for (int i = 0; i < 8; i++) {
        free(atributos[i]);
    }    */
  
  fclose(arq);


  struct Jogador escolhido[3922];
  int tamArray= 0;
  int jogEscolhido = 0;
  
  while (jogEscolhido != -1) {
    jogEscolhido = ler();

    if (jogEscolhido != -1){
      escolhido[tamArray] = jogadores[jogEscolhido];
      tamArray++;
    }
  }

  float inicio= (float)clock();
  radixsort(escolhido, tamArray);
  float fim= (float)clock();
  float tempo = fim- inicio;

  for(int i= 0; i< tamArray; i++){
      imprimir(escolhido, i);
  }

FILE* log= fopen("matricula_radixsort.txt", "w");
fprintf(log, "801778\t%d\t%d\t%f", nComp, nMov, tempo);
  fclose(log);
}