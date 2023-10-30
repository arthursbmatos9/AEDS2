/******************************************************************************
Crie um método iterativo que recebe uma string como parâmetro e retorna true se essa é um palíndromo. Na saída padrão, para cada 
linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada
pode ter caracteres não letras.
*******************************************************************************/
import java.util.Scanner;

class PalindromoJava
{
    public static Scanner sc = new Scanner(System.in);      
    
    public static void main(String args[])
    {
        
        String palavra;
        
        do{                                //do-while usado para verificar se a palavra escrita é FIM, caso seja, abortar o codigo
            palavra = sc.nextLine();       //lendo palavra
            int tam = palavra.length();    //calculando tamanho
            boolean igual = true;          //apostando que a palavra é palindroma
            int i= 0;                      //iniciando um contador em 0
            
            if(!isFim(palavra))  //caso a palavra seja FIM, o codigo nao verificara se "FIM" é palindroma (fecha na linha 39)
            {
                while(igual && i< tam){    //enquanto a palavra seja palindroma e o contador esq ser menor que o contador dir...
                    if(palavra.charAt(i) != palavra.charAt(tam-1))   //verificando se é palindromo (primeira com ultimo, segundo com penultimo...)
                        igual = false;    //caso nao seja palindromo
                    else
                    {
                        i++;         //se for palindromo, aumentar o da esq e diminuir o da dir para verificar outros palindromos
                        tam--;
                    }
                }
            
            if(igual == true)         //se for palindromo
                System.out.println("SIM");
            else                      //se nao for palindromo
                System.out.println("NAO");
            }                         //fechando a verificacao se a palavra é FIM
        }while(!isFim(palavra));  //fazer tudo isso enquanto for diferente de FIM
        
        sc.close();  //fechando scanner    
    }

    public static boolean isFim(String s)
    {
       return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}
