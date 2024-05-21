#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define MAXTAM 6  
    //definindo o tamanho como 6 (para guardar 5 elementos)

struct Jogador {
  int id;
  char nome[100];
  int altura;
  int peso;
  int anoNascimento;
  char *universidade;
  char *cidadeNascimento;
  char *estadoNascimento;
};

struct Jogador jogadores[3922];
int n = 3922;

struct Jogador* filaCircular[MAXTAM + 1];
int primeiro = 0, ultimo = 0;

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

void imprimir(int pos) {
  printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogadores[pos].id,
         jogadores[pos].nome, jogadores[pos].altura, jogadores[pos].peso,
         jogadores[pos].anoNascimento, jogadores[pos].universidade,
         jogadores[pos].cidadeNascimento, jogadores[pos].estadoNascimento);
}

bool vazio(char *palavra) { return (palavra[0] == '\0'); }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
struct Jogador* remover() {
  if(primeiro == ultimo){
    printf("Erro ao remover!");
    exit(1);
  }

  struct Jogador* removido = filaCircular[primeiro];
  primeiro = (primeiro + 1) % MAXTAM;  //att valor do primeiro

  return removido;
}

void inserir(struct Jogador *jogEscolhido) {   
  if(((ultimo + 1) % MAXTAM) == primeiro){
    remover();     //caso fila estiver cheia, remover o primeiro (FILA) para inserir o que foi pedido
  }

  filaCircular[ultimo] = jogEscolhido;
  ultimo = (ultimo + 1) % MAXTAM;   //att valor do ultimo

  int soma = 0, contador = 0;                 //questao pede para mostrar a media das alturas dos jogadores a cada leitura
  for(int i= primeiro; i!= ultimo; i= (i+1) % MAXTAM){
    soma+= filaCircular[i]->altura;
    contador++;
  }

  float media = (float)soma/contador;
  int mediaInt = (int)media;

  if(media - mediaInt >= 0.5){
    mediaInt = mediaInt + 1;
  }
  
  printf("%d\n", mediaInt);
}


void mostrar(struct Jogador *filaCircular, int i, int j) {
  printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", j,
         filaCircular->nome, filaCircular->altura,
         filaCircular->peso, filaCircular->anoNascimento,
         filaCircular->universidade, filaCircular->cidadeNascimento,
         filaCircular->estadoNascimento);
}
/////////////////////////////////////////////////////////////////////////////////////////////////

void lerDados() {
  int qtdAlteracoes;
  scanf("%d", &qtdAlteracoes);
  fgetc(stdin);

  char linhaLida[30];
  char operacao[10];  //salvar o 'I' - se for 'R' nao precisa, pois é so um valor
  char valor1[10];    //salvar o ID
  struct Jogador *jog;

  for(int i= 0; i< qtdAlteracoes; i++){
    fgets(linhaLida, sizeof(linhaLida), stdin);
    linhaLida[strcspn(linhaLida, "\n")] = '\0';

    if(strcmp(linhaLida, "R") == 0){  //se for R chamar a funcao remover
      struct Jogador* removido = remover();
      printf("(R) %s\n", removido->nome);   //questao pede para mostrar o nome do removido apos sua remocao
    }

    else{
      char* token = strtok(linhaLida, " ");  //se for I, dividir o 'I' do ID que deseja inserir
      strcpy(operacao, token);

      token = strtok(NULL, " ");
      strcpy(valor1, token);

      inserir(&jogadores[atoi(valor1)]); //mandando o jogador de ID = valor1  //atoi = transformar string para inteiro
    }
  }
  
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
int main() {
  FILE *arq = fopen("/tmp/players.csv", "r");

  char linha[200];                  // salvar a linha aqui
  fgets(linha, sizeof(linha), arq); // apenas para ignorar primeira linha

  char *atributos[8]; // criando um ponteiro para array de tamanho 8 para
                      // armazenar os atributos

  for (int i = 0; i < 8; ++i) { // Alocar espaço de memória para cada string
    atributos[i] = (char *)calloc(
        100, sizeof(char)); // quantas vezes quer alocar cada tamanho (preenche
                            // com '\0' os espacos sobrando, por ex: tem tamanho
                            // 100 mas usou so 10, completa com 90 '\0')
  }

  for (int i = 0; i < 3922; i++) { // le cada linha por vez e salva seus dados
                                   // em seus respectivos atributos
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

    jogadores[i].universidade =
        "nao informado"; // setando universidade como NAO INFORMADO[n precisa de
                         // alcoar memoria pq ja sabe-se o tamanho]
    atributos[4] = (char *)calloc(
        100, sizeof(char)); // alocando espaco para ler o atributo[nao sabemos o
                            // tamanho certo]
    fscanf(arq, "%[^,]", atributos[4]);
    if (!vazio(atributos[4])) // caso nao seja vazio, mudar o "nao informado"
                              // para o nome lido da universidade
      jogadores[i].universidade = atributos[4];
    fgetc(arq);

    fscanf(arq, "%[^,]", atributos[5]);
    jogadores[i].anoNascimento = atoi(atributos[5]);
    fgetc(arq);

    jogadores[i].cidadeNascimento = "nao informado";
    atributos[6] = (char *)calloc(100, sizeof(char));
    fscanf(arq, "%[^,]", atributos[6]);
    if (!vazio(atributos[6]))
      jogadores[i].cidadeNascimento = atributos[6];
    fgetc(arq);

    jogadores[i].estadoNascimento = "nao informado";
    atributos[7] = (char *)calloc(100, sizeof(char));
    fscanf(arq, "%[^,\n]", atributos[7]);
    if (!vazio(atributos[7]))
      jogadores[i].estadoNascimento = atributos[7];
    fgetc(arq);
  }

  fclose(arq);

  int jogEscolhido = 0;
  while (jogEscolhido != -1) {
    jogEscolhido = ler();
  
    if (jogEscolhido != -1)
      inserir(&jogadores[jogEscolhido]);
  }

  lerDados(); // aqui que a "magica" acontece, faz todas as insercoes e remocoes
              // do PUB.IN

  int j= 0;
  for (int i = primeiro; i != ultimo; i = (i + 1) % MAXTAM, j++){
    mostrar(filaCircular[i], i, j);
  }
}