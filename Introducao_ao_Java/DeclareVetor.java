/******************************************************************************
Declare um vetor com contendo os elementos 10, 5, 8, 2 e 8. Em seguida, 
mostre os elementos contidos no array
*******************************************************************************/
class DeclareVetor
{
	public static void main(String[] args) {
		int[] vetor = new int[]{10, 5, 8, 2, 8};
		
		for(int i= 0; i< 5; i++)
		{
		    System.out.print(vetor[i] + " ");
		}
	}
}
