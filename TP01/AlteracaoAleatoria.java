import java.util.Scanner;
import java.util.Random;

class AlteracaoAleatoria
{
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
	    Random gerador = new Random();                                    //gerador de letras aleatórias fornecido pelos professores
        gerador.setSeed(4);
        char valor1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));     //atribuindo primeiras letras a valor1 e valor
        char valor2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));  
        
        String palavra;
        
        do{                                                  //codigo vai ser executado enquanto a palavra lido for diferente de "FIM"
            palavra = sc.nextLine();                         //lendo palavra
            int tam = palavra.length();                     //pegando tamanho da palavra
            String novaPalavra = "";                        //criando uma nova string para armazenar a palavra com letras trocadas    
            
            if(!isFim(palavra))     //caso a palavra seja "FIM", o codigo nao alterará as letras de "FIM"
            {
                
                for(int i= 0; i< tam; i++)
                {
                    if(palavra.charAt(i) == valor1)    //vendo se letra é igual à aquela que deve ser trocada
                        novaPalavra+= valor2;          //se for, trocar por valor2 (acrescentar na string nova)
                    else
                        novaPalavra+= palavra.charAt(i);  //caso nao seja, acrescentar na string a letra normal
                }
        
                System.out.println(novaPalavra);   //printar a palavra com letras trocadas
            }
            valor1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));     //atribuindo outras letras a valor1 e valor2
            valor2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            
        }while(!isFim(palavra));  //FECHANDO linha 17 - codigo vai ser executado enquanto a palavra lido for diferente de "FIM"
        
        sc.close();  //fechando scanner de leitura
        
	}

    public static boolean isFim(String s)
    {
       return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}






















