/******************************************************************************
Faça um programa que leia a nota de um aluno e escreva na tela: 
“Parabéns, muito bom” (se a nota >= 80); “Parabéns, aprovado” (se a 
nota >= 70 && nota < 80); e, caso contrário, “Infelizmente, reprovado".
*******************************************************************************/
import java.util.Scanner;

class NotaAluno{
    public static void main(String args[]){
        double nota;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite sua nota: ");
        nota = scanner.nextDouble();
        
        if(nota >= 80)
            System.out.println("Parabéns, muito bom");
        else if(nota >= 70 && nota < 80)
            System.out.println("Parabéns, aprovado");
        else
            System.out.println("Infelizmente, reprovado");
        
        scanner.close();
    }
}