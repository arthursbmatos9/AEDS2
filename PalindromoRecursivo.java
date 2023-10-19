public class PalindromoRecursivo 
{
    
    public static void main(String args[])
    {
        
        String palavra;
        
        do{                                
            palavra = MyIO.readLine();       
            int tam = palavra.length();    //iniciando um contador para ler o fim das palavras
            int i= 0;                      //iniciando um contador para ler o inicio das palavras
            
            if(!isFim(palavra))  //somente realizar o palindromo caso a palavra lida seja diferente de FIM
            {
                boolean ehPalindromo = palindromo(palavra, i, tam);  //chamando a funcao PALINDROMO levando a palavra e os contadores inicial e o final
            
            if(ehPalindromo == true)         
                System.out.println("SIM"); 
            else                      
                System.out.println("NAO");
            }    

        }while(!isFim(palavra));  //somente repetir caso a palavra lida seja diferente de FIM
    }

    public static boolean palindromo(String palavra, int i, int tam)
    {
        boolean ehPalindromo;

        if(i >= tam)                  //condicao de parada: apos todos elementos serem lidos e serem iguais
            ehPalindromo = true;

        else{
            if(palavra.charAt(i) != palavra.charAt(tam - 1))   //caso encontrar letras diferentes, colocar FALSE e nao chamar novamente a funcao PALINDROMO
                ehPalindromo = false;
            else
                ehPalindromo = palindromo(palavra, i + 1, tam - 1);   //caso as letras forem iguais, aumentar o contador 'i' e diminuir o 'tam' para comparar
                                                                    //as proximas letras (chamando a funcao novamente)
        }
        
       return ehPalindromo;
    }

    public static boolean isFim(String s)
    {
       return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}


