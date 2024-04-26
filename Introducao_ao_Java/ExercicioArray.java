/*Faça um método que receba um array de inteiros e um número inteiro x e retorne um valor booleano informando se o elemento x está 
contido no array*/
/* Repita o exercício acima considerando que os elementos do array estão ordenados de forma crescente. Uma sugestão seria começar a
pesquisa pelo meio do array*/

import java.util.Scanner;

class ExercicioArray
{
    public static int pecaX(Scanner scanner){
        int x = scanner.nextInt();
        return x;
    }
    
    public static boolean verifique(int numeros[], int x){
        boolean existe = false;
        int i= 0;
        
        while(!existe && i< 5){
            if(numeros[i] == x)
                existe = true;
            else
                i++;
        }
        
        return existe;
    }
    
    public static void main (String[] args) {
    
        Scanner scanner = new Scanner(System.in);
    
        int[] numeros = new int[5];
    
        for(int i= 0; i< 5; i++){
            numeros[i] = scanner.nextInt();      //preenchendo array
        }
    
        int x = pecaX(scanner);         //pedindo o valor a ser buscado
    
        boolean existe = verifique(numeros, x);
        System.out.println(existe);
    }
    
}
