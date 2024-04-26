/******************************************************************************
T(0) = 1
T(n) = T(n-1)^2
*******************************************************************************/
class ExRecursividade2
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
	    else
	    {
	        int n_1 = calcule(n - 1);
	         resp = n_1 * n_1;   
	    }
	        
	   return resp;
	}
}
