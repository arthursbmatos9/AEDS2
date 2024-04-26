/******************************************************************************
Faça um programa que leia n números inteiros, armazene-os em um 
arquivo, leia-os do arquivo e mostre-os na tela

printf -> escreve na tela
fprintf -> salva no arquivo
scanf -> lê teclado
fscanf -> lê arquivo

*******************************************************************************/
#include <stdio.h>
#define n 5

int main()
{
    FILE* arq = fopen("Teste.txt", "w");
    
    int termo;
    
    for(int i= 0; i< n; i++)
    {
        printf("Digite um termo: ");    //Escreve na tela
        scanf("%i", &termo);           //Leitura da entrada padrão
        fprintf(arq, " %i", termo);   //Escreve no arquivo
    }
    
    fclose(arq);
    
    arq = fopen("Teste.txt", "r");
    
    for(int i= 0; i< n; i++)
    {
        fscanf(arq, " %i", &termo);         //Lê do arquivo
        printf("Termo lido: %i\n", termo); 
    } 
    
    fclose(arq);

    return 0;
}
