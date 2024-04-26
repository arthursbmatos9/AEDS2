/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
class ExemploArq01Escrita
{
  public static void main (String[]args)
  {
      
    Arq.openWrite ("exemplo.txt");
    
    Arq.println (1);
    Arq.println (5.3);
    Arq.println ('X');
    Arq.println (true);
    Arq.println ("Algoritmos");
    
    Arq.close ();
  }
}