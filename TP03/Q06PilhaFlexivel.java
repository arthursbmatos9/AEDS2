import java.io.*;

class Jogador {
    public static Jogador[] jogadores = new Jogador[3922];
    public static int n = 3922;

    static final BufferedReader bb = new BufferedReader(new InputStreamReader(System.in));

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

    public Jogador clone(Jogador[] jogadores, int pos) {
        Jogador clone = new Jogador();

        clone.setId(jogadores[pos].getId());
        clone.setNome(jogadores[pos].getNome());
        clone.setAltura(jogadores[pos].getAltura());
        clone.setPeso(jogadores[pos].getPeso());
        clone.setUniversidade(jogadores[pos].getUniversidade());
        clone.setAnoNascimento(jogadores[pos].getAnoNascimento());
        clone.setCidadeNascimento(jogadores[pos].getCidadeNascimento());
        clone.setEstadoNascimento(jogadores[pos].getEstadoNascimento());

        return clone;
    }

    public static int ler() throws Exception {
        // verificacao se o 'pub in' é FIM
        String resp = bb.readLine();

        int resp2;

        if (isFim(resp) == true) // se isFim resultar em true, retornar -1 para poder usar na main
            resp2 = -1;
        else
            resp2 = Integer.parseInt(resp); // caso isFim resultar em false, retornar o que foi digitado pelo usuario no
        // formato INT

        return resp2;

    }

    public static void imprimir(int pos) {
        System.out.println("[" + jogadores[pos].getId() + " ## " +
                jogadores[pos].getNome() + " ## "
                + jogadores[pos].getAltura() + " ## " + jogadores[pos].getPeso() + " ## "
                + jogadores[pos].getAnoNascimento() + " ## " +
                jogadores[pos].getUniversidade() + " ## "
                + jogadores[pos].getCidadeNascimento() + " ## "
                + jogadores[pos].getEstadoNascimento() + "]");
    }

    public static boolean isFim(String s) {
        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String args[]) throws Exception {

        RandomAccessFile file = new RandomAccessFile("/tmp/players.csv", "r");

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

        file.close();

        Pilha pilhaJog = new Pilha(); // criando a PILHA para armazenar os jogadores
        int jogEscolhido = 0;

        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                pilhaJog.inserir(jogadores[jogEscolhido]);
            }

        }

        String qtdAlteracoesS = bb.readLine();
        int qtdAlteracoes = Integer.parseInt(qtdAlteracoesS);
        String alteracao, infos[];

        for (int i = 0; i < qtdAlteracoes; i++) {
            alteracao = bb.readLine(); // armazena a linha lida, ex: I 999

            if (alteracao.charAt(0) == 'R')
                pilhaJog.remover();

            else {
                infos = alteracao.split(" "); // salva separadamente o I de 999
                pilhaJog.inserir(jogadores[Integer.parseInt(infos[1])]);
            }
        }

        int tamanhoPilha = pilhaJog.calculaTamanho();
        pilhaJog.mostrar(pilhaJog.topo, tamanhoPilha - 2);
    }

}

class Celula {
    public Jogador elemento;
    public Celula prox;

    public Celula() {
        elemento = new Jogador();
        prox = null;
    }

    public Celula(Jogador x) {
        elemento = x;
        prox = null;
    }
}

class Pilha {
    public Celula topo;

    public Pilha() {
        topo = new Celula();
    }

    public void inserir(Jogador jog) {
        Celula tmp = new Celula(jog);
        tmp.prox = topo;
        topo = tmp;
    }

    public Jogador remover() throws Exception {
        if (topo == null) {
            throw new Exception("ERRO");
        }

        Jogador removido = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;

        System.out.println("(R) " + removido.getNome());
        return removido;
    }

    public void mostrar(Celula i, int n) {

        if (i.prox.prox != null) {
            mostrar(i.prox, n - 1);
        }

        System.out.println("[" + n + "] ## " + i.elemento.getNome() + " ## "
                + +i.elemento.getAltura() + " ## " + i.elemento.getPeso() + " ## "
                + i.elemento.getAnoNascimento() + " ## " + i.elemento.getUniversidade() +
                " ## "
                + i.elemento.getCidadeNascimento() + " ## " +
                i.elemento.getEstadoNascimento()
                + " ## ");

    }

    public int calculaTamanho() {
        int tam = 0;
        for (Celula i = topo; i != null; i = i.prox) {
            tam++;
        }

        return tam;
    }
}