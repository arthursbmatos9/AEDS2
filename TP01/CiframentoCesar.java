class CiframentoCesar {
    public static void main(String args[])
    {
        String palavra;

        do{
            palavra = MyIO.readLine();
            
            if(!isFim(palavra))         //so vai executar os comando abaixo se a palavra nao for FIM
            {
                String pCifrada = "";         //criando uma string para armazenar a palavra cifrada

                for(int i= 0; i< palavra.length(); i++)
                {
                    char letra = palavra.charAt(i);          //atribuindo a letra o "chatAt" da posicao i
                    char lCifrada = (char)(letra + 3);      //criando a letra cifrada, que é a letra normal + 3 (deslocamento em ASCII)
                    pCifrada += lCifrada;                  //juntando a letra cifrada para a palavra cifrada
                    
                }

                MyIO.println(pCifrada); 
            }

        }while(!isFim(palavra));   //vai fazer o codigo enquanto a leitura da palavra for diferente de FIM
    }

    public static boolean isFim(String s)
    {
       return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}
