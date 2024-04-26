/******************************************************************************
 Faça um programa que leia um número inteiro n e mostre na tela o n-ésimo termo da sequência de Fibonacci [0,1,1,2,3,5,8,13,21,34...]
*******************************************************************************/
import java.util.Scanner;

class Fibonacci
{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite um numero N para ver os N-primeiros termos de fibonacci: ");
        int N = sc.nextInt();
        
        int fib1 = 0;
        int fib2 = 1;
        int fib3;
        int i= 0;
        
        while(i < N){
            System.out.print(fib1 + " , ");
            fib3 = fib2 + fib1;
            fib1 = fib2;
            fib2 = fib3;
            i++;
        }
        
        
        
        sc.close();
    }
}