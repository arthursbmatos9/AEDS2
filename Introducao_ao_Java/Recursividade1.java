/******************************************************************************
T(0) = 1
T(1) = 2
T(n) = T(n-1) * T(n-2) - T(n-1)

*******************************************************************************/
class Recursividade1
{
	public static void main(String[] args) {
	    
	    System.out.println("Digite um numero n: ");
	    int n = MyIO.readInt();
	    
	    int resp = calcule(n);
	    System.out.println(resp);
	}
	
	public static int calcule(int n){
	    int resp;
	    
	    if(n == 0)
	        resp = 1;
	    else if(n == 1)
	        resp = 2;
	    else
	        resp = calcule(n - 1) * calcule(n - 2) - calcule(n - 1);
	        
	    return resp;
	}
}