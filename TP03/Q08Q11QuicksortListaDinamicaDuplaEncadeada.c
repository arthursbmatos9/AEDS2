#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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

struct Celula {
  struct Jogador *elemento;
  struct Celula *prox;
  struct Celula *ant;
};

struct Celula *primeiro;
struct Celula *ultimo;

struct Celula *novaCelula(struct Jogador *jogEscolhido) {
  struct Celula *nova = (struct Celula *)malloc(sizeof(struct Celula));

  nova->elemento = jogEscolhido;
  nova->prox = NULL;
  nova->ant = NULL;

  return nova;
}

struct Celula *start() {
  struct Jogador *aux = (struct Jogador *)malloc(sizeof(struct Jogador));

  aux->id = 0;
  strcpy(aux->nome, "N/I");
  aux->altura = 0;
  aux->peso = 0;
  aux->anoNascimento = 0;
  aux->universidade = "N/I";
  aux->cidadeNascimento = "N/I";
  aux->estadoNascimento = "N/I";

  struct Celula *criada = novaCelula(aux);

  return criada;
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
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
int tamanho() {
  int tam = 0;

  for (struct Celula *i = primeiro; i != NULL; i = i->prox) {
    tam++;
  }
  return tam;
}

void swap(struct Celula *a, struct Celula *b) {
  struct Jogador *tmp = a->elemento;
  a->elemento = b->elemento;
  b->elemento = tmp;
}

struct Celula *pegaPosicao(int pos) {
  struct Celula *resp = primeiro;
  for (int i = 0; i < pos; i++) {
    resp = resp->prox;
  }

  return resp;
}


void quicksortRec(int esq , int dir)
{
  int i = esq, j = dir;

  struct Celula* a = pegaPosicao(esq);
  struct Celula* b = pegaPosicao(dir);

  struct Jogador* pivo = pegaPosicao(esq )->elemento;

  while (i <= j)
  {
      while ((strcmp(a->elemento->estadoNascimento , pivo->estadoNascimento) < 0) || ((strcmp(a->elemento->estadoNascimento, pivo->estadoNascimento) == 0) && (strcmp(a->elemento->nome, pivo->nome) < 0)))
      {
          a = a->prox;
          i++;
      }
      while (strcmp(b->elemento->estadoNascimento , pivo->estadoNascimento) > 0 || ((strcmp(b->elemento->estadoNascimento, pivo->estadoNascimento) == 0) && (strcmp(b->elemento->nome, pivo->nome) > 0)))
      {
          b = b->ant;
          j--;
      }
      if (i <= j)
      {
          swap(a,b);
          a = a->prox;
          i++;
          b = b->ant;
          j--;
      }
  }

  if(esq < j)
  {
      quicksortRec(esq, j);
  }
  if(i < dir)
  {
      quicksortRec(i, dir);
  }
}

void inserir(struct Jogador *jog) {
  ultimo->prox = novaCelula(jog);
  ultimo->prox->ant = ultimo;
  ultimo = ultimo->prox;
}

void remover() {
  struct Celula *tmp = primeiro;
  primeiro = primeiro->prox;
}

void mostrar() {
  for (struct Celula *i = primeiro; i != NULL; i = i->prox) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", i->elemento->id,
           i->elemento->nome, i->elemento->altura, i->elemento->peso,
           i->elemento->anoNascimento, i->elemento->universidade,
           i->elemento->cidadeNascimento, i->elemento->estadoNascimento);
  }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////
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

  struct Celula *inicial = start(); // comeca a lista
  primeiro = inicial;
  ultimo = inicial;

  int jogEscolhido = 0;

  while (jogEscolhido != -1) {

    jogEscolhido = ler();

    if (jogEscolhido != -1) {
      inserir(&jogadores[jogEscolhido]);
    }
  }

  remover();

  quicksortRec(0, tamanho() - 1);

  mostrar();
}