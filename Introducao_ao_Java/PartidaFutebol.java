/******************************************************************************
Seja uma partida de futebol, leia os números de gols do mandante e do 
visitante e imprima quem foi o vencedor ou se foi empate
*******************************************************************************/
public class PartidaFutebol
{
	public static void main(String[] args) {
	    
		System.out.println("Digite os gols do mandante: ");
		int mandante = MyIO.readInt();
		
		System.out.println("Digite os gols do visitate: ");
		int visitante = MyIO.readInt();
		
		if(mandante == visitante)
		    System.out.println("\nEMPATE");
		else if(mandante > visitante)
		    System.out.println("\nMANDANTE GANHOU");
		else
		    System.out.println("\nVISITANTE GANHOU");
	}
}
