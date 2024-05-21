#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct Jogador {
  int id;
  char nome[100];
  int altura;
  int peso;
  int anoNascimento;
  char *universidade;
  char *cidadeNascimento;
  char *estadoNascimento;
} Jogador;

struct Jogador jogadores[3922];
int n = 3922;

/////////////////////////////////////////////////////////////////////////////////////////////////
typedef struct Celula {
  struct Jogador *elemento;
  struct Celula *prox;
} Celula;

Celula *primeiro;
Celula *ultimo;

Celula *novaCelula(Jogador *jogEscolhido) {
  Celula *nova = (Celula *)malloc(sizeof(Celula));

  nova->elemento = jogEscolhido;
  nova->prox = NULL;

  return nova;
}

Celula *start() {
  Jogador *aux = (Jogador *)malloc(sizeof(Jogador));

  aux->id = 0;
  strcpy(aux->nome, "N/I");
  aux->altura = 0;
  aux->peso = 0;
  aux->anoNascimento = 0;
  aux->universidade = "N/I";
  aux->cidadeNascimento = "N/I";
  aux->estadoNascimento = "N/I";

  Celula *criada = novaCelula(aux);

  return criada;
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////

typedef struct Lista {
  struct Celula *inicio;
  struct Celula *prox;
} Lista;

typedef struct Hash {
  struct Lista *lista[25];
  int tamanho;
} Hash;

Hash *startHash() {
  Hash *aux = (Hash *)malloc(sizeof(Hash));

  aux->tamanho = 25;

  for (int i = 0; i < aux->tamanho; i++) {
    aux->lista[i] = (Lista *)malloc(sizeof(Lista));
    aux->lista[i]->inicio = NULL;
    aux->lista[i]->prox = NULL;
  }

  return aux;
}

int h(int altura) { return altura % 25; }

bool pesquisar(Hash *hash, Jogador *jogEsc) {
  bool existe = false;

  int pos = h(jogEsc->altura);
  Celula *inicioLista = hash->lista[pos]->inicio;

  while (inicioLista != NULL && existe == false) {
    if (strcmp(inicioLista->elemento->nome, jogEsc->nome) == 0) {
      existe = true;
    }
    inicioLista = inicioLista->prox;
  }

  return existe;
}

void inserirHash(Hash *tabHash, int altura, Jogador *jogEscolhido) {

  int posTabHash = h(altura);

  Celula *novaCelula = (Celula *)malloc(sizeof(Celula));
  novaCelula->elemento = jogEscolhido;
  novaCelula->prox =
      tabHash->lista[posTabHash]->inicio; // add no inicio da lista

  tabHash->lista[posTabHash]->inicio = novaCelula; // atualizando o inicio
}

Jogador *buscaPorNome(char *nome) {
  Jogador *aux = (Jogador *)malloc(sizeof(Jogador));

  for (int i = 0; i < n; i++) {
    if (strcmp(nome, jogadores[i].nome) == 0) {
      aux = &jogadores[i];
    }
  }

  return aux;
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

bool vazio(char *palavra) { return (palavra[0] == '\0'); }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
int tamanho() {
  int tam = 0;

  for (Celula *i = primeiro; i != NULL; i = i->prox) {
    tam++;
  }
  return tam;
}

Celula *pegaPosicao(int pos) {
  Celula *resp = primeiro;
  for (int i = 0; i < pos; i++) {
    resp = resp->prox;
  }

  return resp;
}

void inserir(Jogador *jog) {
  ultimo->prox = novaCelula(jog);
  ultimo = ultimo->prox;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
int main() {
  FILE *arq = fopen("/tmp/players.csv", "r");
  FILE *arqLog = fopen("matricula_hashIndireta", "w");

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

  float inicio = (float)clock();

  Hash *inicioHash = startHash();

  int jogEscolhido = 0;

  while (jogEscolhido != -1) {

    jogEscolhido = ler();

    if (jogEscolhido != -1) {
      inserirHash(inicioHash, jogadores[jogEscolhido].altura,
                  &jogadores[jogEscolhido]);
    }
  }

  bool existe;
  char charProblematico[2] = {13};
  char nomePesq[50];

  fgets(nomePesq, sizeof(nomePesq), stdin);
  nomePesq[strcspn(nomePesq, "\n")] = '\0';
  nomePesq[strcspn(nomePesq, charProblematico)] = '\0';

  while (strcmp(nomePesq, "FIM") != 0) {
    Jogador *jogAssociado = buscaPorNome(nomePesq);
    existe = pesquisar(inicioHash, jogAssociado);

    printf("%s %s\n", nomePesq, (existe ? "SIM" : "NAO"));

    fgets(nomePesq, sizeof(nomePesq), stdin);
    nomePesq[strcspn(nomePesq, "\n")] = '\0';
    nomePesq[strcspn(nomePesq, charProblematico)] = '\0';
  }

  float fim = (float)clock();
  float tempoExec = fim - inicio;

  fprintf(arqLog, "801778 %f", tempoExec);
  fclose(arqLog);
}