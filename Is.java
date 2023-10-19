import java.util.Scanner;
class Is
{
    public static Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
	    
	    String palavra;
	    
	    do{                            //LINHA 25 - fazendo a leitura ate que seja escrito FIM
	        palavra = sc.nextLine();
	        
	        if(!isFim(palavra))  //caso seja escrito FIM o codigo nao executa as proximas linhas
	        {
	                                //chamando as funcoes verificadoras
	            boolean v = vogais(palavra);      
	            boolean c = consoantes(palavra);
	            boolean i = inteiro(palavra);
	            boolean r = real(palavra);
	            
	                                        //mostrando na tela os resultados pedidos
	            System.out.println((v ? "SIM" : "NAO") + " " + (c ? "SIM" : "NAO") + " " + (i ? "SIM" : "NAO") + " " + (r ? "SIM" : "NAO"));
	        }
	        
	    }while(!isFim(palavra));  //LINHA 10 - repetir ate que seja escrito FIM
	}
	
	public static boolean vogais(String palavra)
	{
	    String maiuscula = palavra.toUpperCase();  //transformando palavra para maiusculo
	    boolean vogal = true;                   //apostando que todas letras sao vogais
	    
	    for(int i= 0; i< maiuscula.length(); i++)  
	    {
	        char letra = maiuscula.charAt(i);
			
	        if(letra != 'A' && letra != 'E' && letra != 'I' && letra != 'O' && letra != 'U')  //verificando se a letra não é uma vogal
	            vogal = false;
	    }
	    
	    return vogal;
	}
	
	public static boolean consoantes(String palavra)
	{
	    String maiuscula = palavra.toUpperCase(); //transformando palavra para maiusculo
	    boolean consoante = true;               //apostando que todas letras sao consoantes
	    
	    for(int i= 0; i< maiuscula.length(); i++)
	    {
	        char letra = maiuscula.charAt(i);
			
	        if (letra >= 'A' && letra <= 'Z')   //excluindo possibilidades de caracteres especiais
	        {
	            if(letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U') //verificando se a letra não é uma consoante
	                consoante = false;
	        }
	    }
	    
	    return consoante;
	}
	
	public static boolean inteiro(String palavra)
	{
	    boolean inteiro = true;      //apostando que todos caracteres sao numeros
	    
	    for(int i= 0; i< palavra.length(); i++)
	    {
	        if(!Character.isDigit(palavra.charAt(i))) //verificando se o char não é um dígito (número)  
	            inteiro = false;       //inteiro se torna false e encerra o loop
	    }
	        
	   return inteiro;
	}
	
	public static boolean real(String palavra)
	{
	    boolean real = true;               //apostando que todos caracteres sao numeros reais
	    
	    for(int i= 0; i< palavra.length(); i++)
	    {
	        if(!Character.isDigit(palavra.charAt(i)))  //verificando se o char não é um dígito (número)
	        {
	            if(palavra.charAt(i) != '.' && palavra.charAt(i) != ',')  //no caso de reais, considerar , e .
	                real = false;  //real se torna false
	        }
	    }
	        
	   return real;
	}

	public static boolean isFim(String s)
    {
       return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}



