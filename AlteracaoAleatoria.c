#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char *letra)
{
    return (letra[0] == 'F' && letra[1] == 'I' && letra[2] == 'M' && strlen(letra) >= 3);
}

int main()
{
    char palavra[1000];

    do
    {
        char novaPalavra[1000] = "";
        int tam = 0, x = 0;

        fgets(palavra, sizeof(palavra), stdin);

        if (!isFim(palavra))
        { // somente realizar caso a palavra lida seja difernete de FIM
            while (palavra[x] != '\0' && palavra[x] != '\n')
            {
                tam++;
                x++;
            }

            // OBS: codigo de iniciar semente foi fornecido em Java, entao foi necessario adapta-lo para a linguagem C
            srand(time(NULL)); // Inicializar a semente para a função rand() usando o tempo atual

            char valor1 = 'a' + (rand() % 26); // Gerar um valor entre 'a' e 'z'
            char valor2 = 'a' + (rand() % 26); // Gerar outro valor entre 'a' e 'z'

            for (int i = 0; i < tam; i++)
            {
                if (palavra[i] == valor1)
                    novaPalavra[i] = valor2;
                else
                    novaPalavra[i] = palavra[i];
            }

            printf("%s\n", novaPalavra);

            valor1 = 'a' + (rand() % 26); // Gerar um valor entre 'a' e 'z'
            valor2 = 'a' + (rand() % 26); // Gerar outro valor entre 'a' e 'z'
        }
    } while (!isFim(palavra));

    return 0;
}
