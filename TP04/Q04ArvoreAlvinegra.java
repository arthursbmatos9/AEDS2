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
        RandomAccessFile arqLog = new RandomAccessFile("matricula_alvinegra", "rw");

        // array 'Jogador jogadores' já foi criado lá em cima
        String linha;
        String[] atributos; // nao precisa por o tamanho pq o metodo SPLIT calcula sozinho, vai criando a
        // medida do necessario

        linha = file.readLine(); // ignorar primeira linha do arquivo CSV, que nao tem dados

        for (int i = 0; i < 3922; i++) {
            linha = file.readLine(); // lendo a linha e dividindo-a, salvando em ca posicao de do array de string
            // 'atributos'
            atributos = linha.split(",", -1); // colocar '-1' para manter os atributos vazios, caso contrario eles vao
            // ser deletados

            jogadores[i] = new Jogador(atributos); // criando instancia de Jogador - chamando construtor (ele chama os
            // SETS), passando os atributos
        }

        file.close();

        double inicio = System.currentTimeMillis();

        Alvinegra arvore = new Alvinegra(); // criando a ARVORE para armazenar os jogadores
        int jogEscolhido = 0;

        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                arvore.inserir(pegaNome(jogEscolhido)); // inserindo nome
            }
        }

        String nomePesquisa = br.readLine();

        while (nomePesquisa.equals("FIM") == false) {
            boolean ex = arvore.pesquisar(nomePesquisa);
            System.out.println(nomePesquisa + " " + arvore.caminho + (ex == true ? "SIM"
                    : "NAO"));

            arvore.caminho = "raiz "; // voltando o caminho para somente 'raiz' para poder salvar dnv
            nomePesquisa = br.readLine();
        }

        double fim = System.currentTimeMillis();
        double tempoExec = fim - inicio;
        arqLog.writeChars("801778\t" + arvore.nComp + "\t" + tempoExec);
        arqLog.close();

    }

    public static boolean isFim(String s) {

        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String pegaNome(int id) {
        return jogadores[id].getNome(); // recebe o ID e retorna o nome correspondente
    }
}

class Alvinegra {
    private NoAN raiz; // raiz da arvore.
    public String caminho = "raiz "; // vai salvar o caminho
    public int nComp = 0;

    public Alvinegra() {
        raiz = null;
    }

    public boolean pesquisar(String elemento) {
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(String elemento, NoAN i) {
        boolean resp;
        if (i == null) {
            nComp++;
            resp = false;
        } else if (elemento.compareTo(i.elemento) == 0) {
            nComp++;
            resp = true;
        } else if (elemento.compareTo(i.elemento) < 0) {
            nComp++;
            caminho += "esq ";
            resp = pesquisar(elemento, i.esq);
        } else {
            nComp++;
            caminho += "dir ";
            resp = pesquisar(elemento, i.dir);
        }
        return resp;
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    private void caminharCentral(NoAN i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    private void caminharPre(NoAN i) {
        if (i != null) {
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
    }

    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    private void caminharPos(NoAN i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
        }
    }

    public void inserir(String elemento) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(elemento);
            // System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento +
            // ").");

            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            if (elemento.compareTo(raiz.elemento) < 0) {
                raiz.esq = new NoAN(elemento);
                // System.out.println(
                // "Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" +
                // raiz.esq.elemento + ").");
            } else {
                raiz.dir = new NoAN(elemento);
                // System.out.println(
                // "Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" +
                // raiz.dir.elemento + ").");
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (elemento.compareTo(raiz.elemento) < 0) {
                raiz.esq = new NoAN(elemento);
                // System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento +
                // "), esq ("
                // + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else if (elemento.compareTo(raiz.dir.elemento) < 0) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                // System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento +
                // "), esq ("
                // + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
                // System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento +
                // "), esq ("
                // + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (elemento.compareTo(raiz.elemento) > 0) {
                raiz.dir = new NoAN(elemento);
                // System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento +
                // "), esq ("
                // + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else if (elemento.compareTo(raiz.esq.elemento) > 0) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                // System.out.println("Antes, dois elementos(E). Agora, raiz(" +raiz.elemento +
                // "), esq ("
                // + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
                // System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento +
                // "), esq ("
                // + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            // System.out.println("Arvore com tres ou mais elementos...");
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.compareTo(avo.elemento) > 0) { // rotacao a esquerda ou direita-esquerda
                if (i.elemento.compareTo(pai.elemento) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.compareTo(pai.elemento) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.compareTo(bisavo.elemento) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
            // System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e
            // avo.esq / avo.dir("
            // + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
        } // if(pai.cor == true)
    }

    private void inserir(String elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            if (elemento.compareTo(pai.elemento) < 0) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.compareTo(i.elemento) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.compareTo(i.elemento) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        // System.out.println("Rotacao DIR(" + no.elemento + ")");
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        // System.out.println("Rotacao ESQ(" + no.elemento + ")");
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

class NoAN {
    public boolean cor;
    public String elemento;
    public NoAN esq, dir;

    public NoAN() {
        this("");
    }

    public NoAN(String elemento) {
        this(elemento, false, null, null);
    }

    public NoAN(String elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN(String elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
