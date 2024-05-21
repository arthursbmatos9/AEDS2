import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

class Jogador {
    public static Jogador[] jogadores = new Jogador[3922];
    public static int n = 3922;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        String resp = br.readLine();
        int resp2;

        if (isFim(resp)) // se isFim resultar em true, retornar -1 para poder usar na main
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

    public static void main(String args[]) throws Exception {

        RandomAccessFile file = new RandomAccessFile("/tmp/players.csv", "r");
        RandomAccessFile arqLog = new RandomAccessFile("matricula_arvoreTrie.txt",
                "rw");

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

            jogadores[i] = new Jogador(atributos); // criando instancia de Jogador -chamando construtor (ele chama os
            // SETS), passando os atributos
        }

        file.close();

        double inicio = System.currentTimeMillis();

        ArvoreTrie arvore1 = new ArvoreTrie(); // criando primeira arvore trie
        ArvoreTrie arvore2 = new ArvoreTrie(); // criando segunda arvore trie
        ArvoreTrie arvore3 = new ArvoreTrie(); // criando terceira arvore trie (merge 1 e 2)

        int jogEscolhido = 0;
        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                String jogEscolhidoString = pegaNome(jogEscolhido);
                arvore1.inserir(jogEscolhidoString); // inserindo na primeira arvore
            }
        }

        int jogEscolhido2 = 0;
        while (jogEscolhido2 != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido2 = ler();

            if (jogEscolhido2 != -1) {
                String jogEscolhido2String = pegaNome(jogEscolhido2);
                arvore2.inserir(jogEscolhido2String); // inserindo na segunda arvore
            }
        }

        String copia = arvore1.copiar(); // copia a arvore1 por meio de um mostrar
        String[] aSerInseridos = copia.split("\n"); // divide os itens lidos no array de strings

        for (int i = 0; i < aSerInseridos.length; i++) {
            arvore3.inserir(aSerInseridos[i]); // copiando a arvore1 na arvore do merge
        }

        String copia2 = arvore2.copiar(); // copia a arvore2 por meio de um mostrar
        String[] aSerInseridos2 = copia2.split("\n"); // divide os itens lidos no array de strings

        for (int i = 0; i < aSerInseridos2.length; i++) {
            boolean jaExiste = arvore1.pesquisar(aSerInseridos2[i]);
            if (!jaExiste) {
                arvore3.inserir(aSerInseridos2[i]); // so insere na arvore merge caso ja nao tenha sido inserido
            }
        }

        String nomePesquisa = br.readLine();
        while (nomePesquisa.equals("FIM") == false) { // pesquisa o nome na arvore3 (merge)
            boolean existe = arvore3.pesquisar(nomePesquisa);
            System.out.println(nomePesquisa + (existe == true ? " SIM" : " NAO")); // printa se achou ou nao

            nomePesquisa = br.readLine();
        }

        double fim = System.currentTimeMillis();
        double tempoExec = fim - inicio;
        int nCompTotal = arvore1.nComp + arvore2.nComp;

        arqLog.writeChars("801778\t" + tempoExec + "\t" + nCompTotal);
        arqLog.close();
    }

    public static boolean isFim(String s) {

        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String pegaNome(int x) { // recebe um ID e retorna o nome do jogador
        return jogadores[x].getNome();
    }

    public static int pegaAltura(String x) { // recebe um nome e retorna a altura do jogador
        int altura = 0;
        for (int i = 0; i < 3922; i++) {
            if (jogadores[i].getNome().compareTo(x) == 0) {
                altura = jogadores[i].getAltura();
                i = 3922;
            }
        }
        return altura;
    }
}

class ArvoreTrie {
    private No raiz;
    public int nComp;

    public ArvoreTrie() {
        raiz = new No();
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(" " + s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {
        boolean resp;
        if (no.prox[s.charAt(i)] == null) {
            resp = false;
            nComp++;
        } else if (i == s.length() - 1) {
            resp = (no.prox[s.charAt(i)].folha == true);
            nComp++;
        } else if (i < s.length() - 1) {
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
            nComp++;
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {
        if (no.prox[s.charAt(i)] == null) {
            nComp++;
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if (i == s.length() - 1) {
                nComp++;
                no.prox[s.charAt(i)].folha = true;
            } else {
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }

        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
            nComp++;
            inserir(s, no.prox[s.charAt(i)], i + 1);

        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if (no.folha == true) {
            System.out.println("Palavra:" + (s + no.elemento));
        } else {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    public String copiar() {
        return copiar("", raiz);
    }

    public String copiar(String s, No no) {
        if (no.folha == true) {
            return s + no.elemento + "\n";
        } else {
            String resultado = "";
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    resultado += copiar(s + no.elemento, no.prox[i]);
                }
            }
            return resultado;
        }
    }

}

class No {
    public char elemento;
    public final int tamanho = 255;
    public No[] prox;
    public boolean folha;

    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;
        prox = new No[tamanho];
        for (int i = 0; i < tamanho; i++) {
            prox[i] = null;
        }
        folha = false;
    }

    public static int hash(char x) {
        return (int) x;
    }
}