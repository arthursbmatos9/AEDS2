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


void swap(struct Jogador* jogador1, struct Jogador* jogador2) {
    struct Jogador aux = *jogador1;
    *jogador1 = *jogador2;
    *jogador2 = aux;
}


void selecaoRec(struct Jogador* escolhido, int tamArray, int i){
  if(i < tamArray-1){     //condicao de parada: i(posicao) ser maior ou igual que a posicao do ultimo elemento
    int menor= i;

    for(int j= i+1; j< tamArray; j++){
        nComp++;
        if(strcmp(escolhido[menor].nome, escolhido[j].nome) > 0)
          menor = j;
      }
    
    swap(&escolhido[menor], &escolhido[i]);
    nMov++;
    selecaoRec(escolhido, tamArray, i+1);   //chama novamente a selecao
  }
}

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
  selecaoRec(escolhido, tamArray, 0);
  float fim= (float)clock();
  float tempo = fim- inicio;

  for(int i= 0; i< tamArray; i++){
      imprimir(escolhido, i);
  }

FILE* log= fopen("matricula_selecaoRecursiva.txt", "w");
fprintf(log, "801778\t%d\t%d\t%f", nComp, nMov, tempo);
  fclose(log);
}