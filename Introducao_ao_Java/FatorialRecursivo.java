/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
class FatorialRecursivo
{
	public static void main(String[] args) {
		
		System.out.println("Digite um numero para ver seu fatorial: ");
		int numero = MyIO.readInt();
		
		int resp = fat(numero);
		System.out.println(resp);
	}
	
	public static int fat(int numero){
	   
	    int resp;
	    
	    if(numero == 0 || numero == 1)
	        resp = 1;
	    else
	        resp = numero * fat(numero - 1);
	        
	   return resp;
	 }   
}

