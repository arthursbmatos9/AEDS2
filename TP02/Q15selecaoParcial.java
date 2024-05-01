import java.io.RandomAccessFile;
import java.util.*;

class Jogador {
    public static Jogador[] jogadores = new Jogador[3922];
    public static int n = 3922;
    public static int nComp = 0;
    public static int nMov = 0;

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public void setId(int x) {
        id = x;
    }

    public int getId() {
        return id;
    }

    public void setNome(String n) {
        nome = n;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(int a) {
        altura = a;
    }

    public int getAltura() {
        return altura;
    }

    public void setPeso(int p) {
        peso = p;
    }

    public int getPeso() {
        return peso;
    }

    public void setUniversidade(String u) {
        if (u.length() >= 1)
            universidade = u;
        else
            universidade = "nao informado";
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setAnoNascimento(int a) {
        anoNascimento = a;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setCidadeNascimento(String c) {
        if (c.length() >= 1)
            cidadeNascimento = c;
        else
            cidadeNascimento = "nao informado";
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setEstadoNascimento(String e) {
        if (e.length() >= 1)
            estadoNascimento = e;
        else
            estadoNascimento = "nao informado";
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public Jogador(String[] atributos) {
        setId(Integer.parseInt(atributos[0]));
        setNome(atributos[1]);
        setAltura(Integer.parseInt(atributos[2]));
        setPeso(Integer.parseInt(atributos[3]));
        setUniversidade(atributos[4]);
        setAnoNascimento(Integer.parseInt(atributos[5]));
        setCidadeNascimento(atributos[6]);
        setEstadoNascimento(atributos[7]);
    }

    public Jogador() {
        setId(0);
        setNome("nome"); // esse construtor nao esta sendo usado
        setAltura(0);
        setPeso(0);
        setUniversidade("universidade");
        setAnoNascimento(0);
        setCidadeNascimento("cidadeNascimento");
        setEstadoNascimento("estadoNascimento");
    }

    public static void clone(Jogador[] jogadores, int pos) {
        Jogador clone = new Jogador();

        clone.setId(jogadores[pos].getId());
        clone.setNome(jogadores[pos].getNome());
        clone.setAltura(jogadores[pos].getAltura());
        clone.setPeso(jogadores[pos].getPeso());
        clone.setUniversidade(jogadores[pos].getUniversidade());
        clone.setAnoNascimento(jogadores[pos].getAnoNascimento());
        clone.setCidadeNascimento(jogadores[pos].getCidadeNascimento());
        clone.setEstadoNascimento(jogadores[pos].getEstadoNascimento());
        // return clone;
    }

    public static int ler() {
        // verificacao se o 'pub in' é FIM
        String resp = MyIO.readLine();
        int resp2;

        if (isFim(resp)) // se isFim resultar em true, retornar -1 para poder usar na main
            resp2 = -1;
        else {
            resp2 = Integer.parseInt(resp); // caso isFim resultar em false, retornar o que foi digitado pelo usuario no
                                            // formato INT
        }

        return resp2;

    }

    public static void imprimir(int pos, Jogador[] jogadores) {
        System.out.println("[" + jogadores[pos].getId() + " ## " + jogadores[pos].getNome() + " ## "
                + jogadores[pos].getAltura() + " ## " + jogadores[pos].getPeso() + " ## "
                + jogadores[pos].getAnoNascimento() + " ## " + jogadores[pos].getUniversidade() + " ## "
                + jogadores[pos].getCidadeNascimento() + " ## "
                + jogadores[pos].getEstadoNascimento() + "]");
    }

    public static void main(String args[]) throws Exception {

        RandomAccessFile file = new RandomAccessFile("/tmp/players.csv", "r");
        RandomAccessFile arquivoLog = new RandomAccessFile("matricula_selecao.txt","rw");

        // array 'Jogador jogadores' já foi criado lá em cima
        String linha;
        String[] atributos; // nao precisa por o tamanho pq o metodo SPLIT calcula sozinho, vai criando a
                            // medida do necessario

        linha = file.readLine(); // ignorar primeira linha do arquivo CSV, que nao tem dados

        for (int i = 0; i < 3922; i++) {
            linha = file.readLine(); // lendo a linha e dividindo-a, salvando em cada posicao de do array de string
                                     // 'atributos'
            atributos = linha.split(",", -1); // colocar '-1' para manter os atributos vazios, caso contrario eles vao
                                              // ser deletados

            jogadores[i] = new Jogador(atributos); // criando instancia de Jogador - chamando construtor (ele chama os
                                                   // SETS), passando os atributos
        }

        Jogador[] array = new Jogador[n];
        int tamArray = 0;

        int jogEscolhido = 0;
        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                array[tamArray] = jogadores[jogEscolhido]; //atribuindo valores ao array a ser ordenado 
                tamArray++;
            }
        }

        double inicio = System.currentTimeMillis();  //pegando tempo inicial
        ordenaSelecao(array, tamArray);
        double fim = System.currentTimeMillis();  //pegando tempo final

        for (int i = 0; i < 10; i++) {   //imprimindo array ja ordenado
            imprimir(i, array);
        }

        double tempoExec = fim - inicio;

        arquivoLog.writeChars("801778\t"+nComp +"\t"+nMov+"\t"+tempoExec);

        file.close();
        arquivoLog.close();
    }

    public static void ordenaSelecao(Jogador[] array, int n) {
        for (int i = 0; i < (n-1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++) {
                if ((array[j].getNome()).compareTo(array[menor].getNome()) < 0) { 
                    menor = j;
                    nComp++;
                }
            }

            swap(menor, i, array);
            nMov += 3;
        }
    }

    public static void swap(int i, int j, Jogador[] array) {
        Jogador aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }

    public static boolean isFim(String s) {

        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

}