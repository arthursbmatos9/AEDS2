/******************************************************************************
Faça um programa que mostre na tela os 10 primeiros números pares
*******************************************************************************/
import java.util.Scanner;  //NAO FOI USADO

class DezPrimeiros
{
	public static void main(String[] args) {
        
        int numeroPar = 2;
        int qtd = 0;
        
        while(qtd < 10)
        {
            System.out.println(numeroPar);
            numeroPar = numeroPar + 2;
            qtd++;
        }
	}
}
