class RecCiframentoCesar {
    public static void main(String args[])
    {
        String palavra;

        do{
            palavra = MyIO.readLine();
            int i= 0;                     //iniciando um contador para ser o charAt(i)

            if(!isFim(palavra))  //so vai fazer caso a palavra seja diferente de FIM
            {
                String pCifrada = troca(palavra, i);  //chamando a funcao TROCA, que realiza o ciframento (mandando a palavra e o contador)

                MyIO.println(pCifrada);
            }

        }while(!isFim(palavra)); //realizar enquanto for diferente de FIM
    }

    public static String troca(String palavra, int i)
    {
        String pCifrada = ""; //iniciando uma string para armazrnar a palavra cifrada
        char lCifrada;        //iniciando uma string para armazrnar a letra cifrada, para adicionar na palavra cifrada
        
        if(i >= palavra.length())  //quando o contador chegar no final, retornar a palavra cifrada
            return pCifrada;
        else
        {
            lCifrada = (char)(palavra.charAt(i) + 3);   //cifrando a letra individual
            pCifrada+= lCifrada + troca(palavra, i+1);  //adicionando a letra cifrada à palavra e chamando novamente a funcao para cifrar a proxima letra
        }

        return pCifrada;
    }

    public static boolean isFim(String s)
    {
       return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}
