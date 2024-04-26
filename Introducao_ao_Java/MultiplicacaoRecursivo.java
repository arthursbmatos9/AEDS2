/******************************************************************************
Faça um método recursivo que receba dois números inteiros e retorne a 
multiplicação do primeiro pelo segundo fazendo somas
*******************************************************************************/
class MultiplicacaoRecursivo
{
	public static void main(String[] args) {
	    
	    int n1 = MyIO.readInt();
	    int n2 = MyIO.readInt();
	    
	    int resp = calcule(n1, n2);
	    System.out.println(resp);
	}
	
	public static int calcule(int n1, int n2) {            //5*4 = 5+5+5+5
	    int resp = 0;
	    
	    if(n2 > 0)      //se algum dos elementos for == 0, a multiplicacao resulta em 0
	        resp = n1 + calcule(n1, n2 - 1);
	        
	   return resp;
	}
}
