import java.io.RandomAccessFile;
import java.io.IOException;

class Arquivo {

    public static void main(String args[]) throws Exception{  //jogando excecao
        
        int numero = MyIO.readInt();
        
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");  //criando a class RandomAcessFile na class Arquivo e
                                                                                  //abrindo "data.txt" no modo de ler e escrever 'rw'


        for (int i = 0; i < numero; i++) 
        {
            double valor = MyIO.readDouble();       //le o valor e salva em 'valor'
            file.writeDouble(valor);               //escreve o valor no arquivo
        }

        file.close();



        file = new RandomAccessFile("data.txt", "r");  //abrindo novamente o arquivo, mas no modo de leitura 'r'

        for (int j = numero - 1; j >= 0; j--) 
        {
            file.seek(j * 8);     //cada numero é um double, ou seja, ocupa 8 bytes. é necessario multiplicar j por 8 para obter o inicio de cada valor
            double valor2 = file.readDouble();   //atribuindo a valor2 o valor lido no arquivo
            
            if(valor2 == (int)valor2)         //caso valor real seja igual ao valor inteiro (.0), imprimir o valor de forma inteira para 
                MyIO.println((int) valor2);                                                          //evitar printar 10.0 por exemplo
            else
                MyIO.println(valor2);   //caso numero for real, mostrar com ponto '.'
        }
    
        
        file.close();    
        
        
    }
}

