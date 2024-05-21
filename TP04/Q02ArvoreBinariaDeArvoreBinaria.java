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
            resp2 = Integer.parseInt(resp); // caso isFim resultar em false, retornar que foi digitado pelo usuario no
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

        // array 'Jogador jogadores' já foi criado lá em cima
        String linha;
        String[] atributos; // nao precisa por o tamanho pq o metodo SPLIT calcul sozinho, vai criando a
        // medida do necessario

        linha = file.readLine(); // ignorar primeira linha do arquivo CSV, que na tem dados

        for (int i = 0; i < 3922; i++) {
            linha = file.readLine(); // lendo a linha e dividindo-a, salvando em cada posicao de do array de string
            // 'atributos'
            atributos = linha.split(",", -1); // colocar '-1' para manter os atributo vazios, caso contrario eles vao
            // ser deletados

            jogadores[i] = new Jogador(atributos); // criando instancia de Jogador chamando construtor (ele chama os
            // SETS), passando os atributos
        }

        file.close();

        ArvoreArvore arvore = new ArvoreArvore(); // criando arvore de arvores
        int[] elementos = { 7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13, 14 };

        for (int i = 0; i < elementos.length; i++) {
            arvore.inserir(elementos[i]);
        }

        int jogEscolhido = 0;
        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                int modAltura = (jogadores[jogEscolhido].getAltura() % 15);
                arvore.inserir(pegaNome(jogEscolhido), modAltura); // mandando nome d jogador escolhido e sua MOD
                // altura
            }
        }

        // agora vamos pesquisar se um jogador esta na arvore crida

        String nomePesquisa = br.readLine();
        while (nomePesquisa.equals("FIM") == false) {
            boolean existe = arvore.mostrar(nomePesquisa);
            System.out.println((existe == true ? " SIM" : " NAO"));

            nomePesquisa = br.readLine();
        }

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

class ArvoreArvore {
    public No raiz;

    // criando estrutura da 1a arvore
    public ArvoreArvore() {
        this.raiz = null;
    }

    // chamando funcao que insere na 1a arvore
    public void inserir(int modAltura) throws Exception {
        if (this.raiz == null) {
            this.raiz = new No(modAltura); // aqui cria o primeiro NÓ
        } else {
            inserir(modAltura, this.raiz);
        }
    }

    // inserindo valores na 1a arvore
    public No inserir(int modAltura, No i) throws Exception {
        if (i == null) {
            i = new No(modAltura); // aqui cria os outros NÓS
        } else if (modAltura > i.elemento) {
            i.dir = inserir(modAltura, i.dir);
        } else if (modAltura < i.elemento) {
            i.esq = inserir(modAltura, i.esq);
        } else {
            throw new Exception("MOD da altura ja existe");
        }

        return i;
    }

    public void caminhar(No i) throws Exception {
        if (i != null) {
            caminhar(i.esq);
            System.out.println(i.elemento);
            caminhar(i.dir);
        }
    }

    // chamando funcao que insere na 2a arvore
    public void inserir(String s, int modAltura) throws Exception {
        inserir(s, modAltura, raiz);
    }

    // procura na arvore de numeros a altura primeiro, e quando acha, insere na
    // segunda arvore
    public void inserir(String s, int modAltura, No i) throws Exception {
        if (i == null) {
            throw new Exception("ERRO");

        } else if (modAltura > i.elemento) {
            inserir(s, modAltura, i.dir);

        } else if (modAltura < i.elemento) {
            inserir(s, modAltura, i.esq);

        } else { // achou o NO da primeira arvore com altura igual
            i.outro = inserir(s, i.outro); // chamando funcao de inserir na segundo arvore
        }
    }

    // realmente insere o nome na segunda arvore
    private No2 inserir(String s, No2 i) throws Exception {
        if (i == null) {
            i = new No2(s);

        } else if (s.compareTo(i.elemento) < 0) {
            i.esq = inserir(s, i.esq);

        } else if (s.compareTo(i.elemento) > 0) {
            i.dir = inserir(s, i.dir);

        } else {
            throw new Exception("ERRO: elemento ja existe!");
        }

        return i;
    }

    public boolean mostrar(String nome) {
        System.out.print(nome + " raiz");
        return mostrar(nome, raiz);
    }

    public boolean mostrar(String nome, No i) {
        boolean resp = false;
        if (i != null) {
            if (!resp) {
                resp = mostrar(nome, i.outro);
            }
            if (!resp) {
                System.out.print(" esq");
                resp = mostrar(nome, i.esq);
            }
            if (!resp) {
                System.out.print(" dir");
                resp = mostrar(nome, i.dir);
            }
        }
        return resp;
    }

    public boolean mostrar(String nome, No2 i) {
        boolean resp = false;
        if (i != null) {
            if (nome.equals(i.elemento)) {
                resp = true;
            } else {
                System.out.print(" ESQ");
                resp = mostrar(nome, i.esq);
                if (!resp) {
                    System.out.print(" DIR");
                    resp = mostrar(nome, i.dir);
                }
            }
        }

        return resp;
    }

}

class No {
    public int elemento;
    public No esq;
    public No dir;
    public No2 outro;

    public No(int elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
        this.outro = null;
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;
    }
}

class No2 {
    public String elemento;
    public No2 esq;
    public No2 dir;

    public No2(String elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
    }

    public No2(String elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
