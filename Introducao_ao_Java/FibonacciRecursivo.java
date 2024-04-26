/******************************************************************************
 Fibonacci Recursivo [0-1-1-2-3-5-8-13-21-34-55-89...]
*******************************************************************************/
class FibonacciRecursivo
{
	public static void main(String[] args) {
	    
	    int numero = MyIO.readInt();
	    
	    int resp = calculeFib(numero);
	    System.out.println("\n" + resp);
	}
	
	public static int calculeFib(int numero) {
	    int resp;
	    if(numero == 0 || numero == 1)
	        resp = 1;
	    else
	        resp = (calculeFib(numero-1)) + (calculeFib(numero-2));
	    
	    return resp;
	}
}
