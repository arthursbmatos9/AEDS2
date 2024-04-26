/******************************************************************************
• Faça uma classe retângulo contendo os atributos base e altura, pelo menos dois métodos construtores, o método double getArea() 
que retorna a área do retângulo, o método double getPerimetro() que retorna o perímetro do retângulo e o método double getDiagonal()
que retorna a diagonal do retângulo.
•Faça também uma classe Lixao contendo um método main sendo que esse terá dois objetos do tipo retângulo e chamará os métodos desenvolvidos
na classe retângulo
*******************************************************************************/
class Retangulo
{
    private double base;
    private double altura;
    
    public Retangulo(double base, double altura){
        this.base = base;
        this.altura = altura;
    }
    
    public Retangulo(double base){
        this.base = base;
    }
    
    public double getArea(){
        return base*altura;
    }
    
    public double getPerimetro(){
        return base*2 + altura*2;
    }
    
    public double getDiagonal(){
        return (base*altura)/2;
    }
}

class Lixao
{
    public static void main(String args[]){
        Retangulo R1 = new Retangulo(2, 3);
        Retangulo R2 = new Retangulo(3,5);
    }
}