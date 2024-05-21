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

struct Jogador *listaDeJogadores[3922];
int tamLista = 0;

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
void inserirInicio(struct Jogador *jogEscolhido) {
  if (tamLista >= 3922) {
    printf("Erro");
    exit(1);
  }

  for (int i = tamLista; i > 0;
       i--) { // deslocando os jogadores uma "casa" para a direita, para liberar
              // a primeira posicao
    listaDeJogadores[i] = listaDeJogadores[i - 1];
  }

  listaDeJogadores[0] = jogEscolhido;
  tamLista++;
}

void inserir(struct Jogador *jogEscolhido, int pos) {
  if (pos < 0 || pos > 3922 || tamLista >= 3922) {
    printf("Erro");
    exit(1);
  }

  for (int i = tamLista; i > pos;
       i--) { // deslocando os jogadores uma "casa" para a direita, para liberar
              // a posicao desejada
    listaDeJogadores[i] = listaDeJogadores[i - 1];
  }

  listaDeJogadores[pos] = jogEscolhido;
  tamLista++;
}

void inserirFim(struct Jogador *jogEscolhido) {
  if (tamLista >= 3922) {
    printf("Erro");
    exit(1);
  }

  listaDeJogadores[tamLista] = jogEscolhido;
  tamLista++;
}

struct Jogador *removerInicio() {
  if (tamLista == 0) {
    printf("Erro");
    exit(1);
  }

  struct Jogador *removido = listaDeJogadores[0];
  printf("(R) %s\n", removido->nome);

  for (int i = 0; i < tamLista - 1; i++) { // deslocando os jogadores uma "casa" para a esquerda, para ocupar
                                          // o buraco do inicio
    listaDeJogadores[i] = listaDeJogadores[i + 1];
  }

  tamLista--;
  return removido;
}

struct Jogador *remover(int pos) {
  if (pos < 0 || pos > 3922 || pos > tamLista) {
    printf("Erro");
    exit(1);
  }

  struct Jogador *removido = listaDeJogadores[pos];
  printf("(R) %s\n", removido->nome);

  for (int i = pos; i < tamLista;
       i++) { // deslocando os jogadores uma "casa" para a esquerda, para ocupar
              // o buraco da posicao
    listaDeJogadores[i] = listaDeJogadores[i + 1];
  }

  tamLista--;
  return removido;
}

struct Jogador *removerFim() {
  if (tamLista == 0) {
    printf("Erro");
    exit(1);
  }

  struct Jogador *removido = listaDeJogadores[tamLista - 1];
  printf("(R) %s\n", removido->nome);
  
  tamLista--;
  return removido;
}

void mostrar(struct Jogador *listaDeJogadores, int i) {

  printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", i,
         listaDeJogadores->nome, listaDeJogadores->altura,
         listaDeJogadores->peso, listaDeJogadores->anoNascimento,
         listaDeJogadores->universidade, listaDeJogadores->cidadeNascimento,
         listaDeJogadores->estadoNascimento);
}
/////////////////////////////////////////////////////////////////////////////////////////////////

void lerDados() {
  int qtdAlteracoes;
  scanf("%d", &qtdAlteracoes);
  fgetc(stdin); // usado para mudar a linha, consumir o \n. Caso estivesse lendo
                // de arquivo usar fgetc(_nomeArq_)

  char linhaLida[30];
  char operacao[10];
  char valor1[10];
  char valor2[10];
  struct Jogador *jog;

  for (int i = 0; i < qtdAlteracoes; i++) { // ler a linha e dividir usando STRTOK
    fgets(linhaLida, sizeof(linhaLida), stdin);
    linhaLida[strcspn(linhaLida, "\n")] = '\0';  //usado para remover o \n que foi lido do teclado (substituir por \0)

    if (strcmp(linhaLida, "RI") == 0) { // caso seja RI - chamar a funcao apenas 
      removerInicio();
    }

    else if(strcmp(linhaLida, "RF") == 0) { // caso seja RF - chamar a funcao apenas
      removerFim();
    }

    else{
      char* token = strtok(linhaLida, " "); // salva a operacao, ex: IF
      strcpy(operacao, token);

      if (strcmp(operacao, "II") == 0) { // caso seja II - pegar o ID com TOKEN
        token = strtok(NULL, " ");
        strcpy(valor1, token);

        jog = &jogadores[atoi(valor1)];
        inserirInicio(jog);
      }

      else if (strcmp(operacao, "I*") == 0) { // caso seja I* - pegar a posicao e ID com TOKEN
        token = strtok(NULL, " ");
        strcpy(valor1, token);
        token = strtok(NULL, " ");
        strcpy(valor2, token);
        
        jog = &jogadores[atoi(valor2)];
        inserir(jog, atoi(valor1));
      }

      else if (strcmp(operacao, "IF") == 0) { // caso seja I* - pegar o ID com TOKEN
        token = strtok(NULL, " ");
        strcpy(valor1, token);

        jog = &jogadores[atoi(valor1)];
        inserirFim(jog);
      }

      else if (strcmp(operacao, "R*") == 0) { // caso seja I* - pegar a posicao com TOKEN
        token = strtok(NULL, " ");
        strcpy(valor1, token);

        remover(atoi(valor1));
      }
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
      inserirFim(&jogadores[jogEscolhido]);
  }

  lerDados(); // aqui que a "magica" acontece, faz todas as insercoes e remocoes
              // do PUB.IN

  for (int i = 0; i < tamLista; i++) {
    mostrar(listaDeJogadores[i], i);
  }
}