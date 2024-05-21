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
        RandomAccessFile arqLog = new RandomAccessFile("matricula_hashreserva.txt",
                "rw");

        // array 'Jogador jogadores' já foi criado lá em cima
        String linha;
        String[] atributos; // nao precisa por o tamanho pq o metodo SPLIT calcula sozinho, vai criando a
        // medida do necessario

        for (int i = 0; i < 3922; i++) {
            linha = file.readLine(); // ignorar primeira linha do arquivo CSV, que nao

            linha = file.readLine(); // le a linha e dividindo-a, salvando em cada posicao de do array de string
            // 'atributos'
            atributos = linha.split(",", -1); // colocar '-1' para manter os atributos vazios, caso contrario eles vao
            // ser deletados

            jogadores[i] = new Jogador(atributos); // criando instancia de Jogador - chamando construtor (ele chama os
            // SETS), passando os atributos
        }

        file.close();
        TabHash tabela = new TabHash(); // criando a Tabela Hash para armazenar os jogadores
        int jogEscolhido = 0;

        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                tabela.inserir(jogadores[jogEscolhido]); // inserindo na tabela
            }
        }

        double inicio = System.currentTimeMillis(); // pegando tempo

        String nomePesquisado = "";

        while (nomePesquisado.equals("FIM") == false) {
            nomePesquisado = br.readLine();

            if (nomePesquisado.equals("FIM") == false) {
                Jogador buscado = pegaJogador(nomePesquisado); // associando o nome ao Jogador
                boolean existe = tabela.pesquisar(buscado);
                System.out.println(nomePesquisado + " " + (existe ? "SIM" : "NAO")); // se existir, mostrar SIM
            }
        }

        double fim = System.currentTimeMillis();
        double tempoExec = fim - inicio;

        arqLog.writeChars("801778\t" + tempoExec);
        arqLog.close();
    }

    public static boolean isFim(String s) {
        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static Jogador pegaJogador(String nome) {
        Jogador resp = new Jogador();

        for (int i = 0; i < 3922; i++) {
            if (nome.equals(jogadores[i].getNome())) {
                resp = jogadores[i];
                i = 3922;
            }
        }
        return resp;
    }

}

class TabHash {
    public Jogador tabela[];
    public int tam1;
    public int tam2;
    public int tam;
    public int posReserva;
    public final Jogador nulo = new Jogador();

    public TabHash() {
        this(13, 7);
    }

    public TabHash(int tam1, int tam2) {
        this.tam1 = tam1;
        this.tam2 = tam2;
        this.tam = tam1 + tam2;
        this.tabela = new Jogador[this.tam];
        posReserva = 0; // posicao que vai contar a area de reserva

        for (int i = 0; i < tam1; i++) {
            tabela[i] = nulo; // setando os valores da tabela como nulo, para poder inserir depois
        }
    }

    public int hash(int x) {
        return x % tam1;
    }

    public void inserir(Jogador x) {
        if (x != nulo) {
            int pos = hash(x.getId());
            if (tabela[pos] == nulo) { // se a posicao na tabela hash estiver disponivel, inserir la
                tabela[pos] = x;
            } else if (posReserva < tam2) { // se tiver ocupado, ver se tem espaco na reserva
                tabela[tam1 + posReserva] = x;
                posReserva++;
            }
            // else = NAO HA ESPACO NA TABELA HASH NEM NA RESERVA
        }
    }

    public boolean pesquisar(Jogador x) {
        boolean existe = false;
        int pos = hash(x.getId());
        if (tabela[pos] == x) { // ver se esta na posicao da tabela hash
            existe = true;
        } else if (tabela[pos] != nulo) { // se nao tiver, ver se esta na area de reserva
            for (int i = 0; i < posReserva; i++) {
                if (tabela[tam1 + i] == x) {
                    existe = true;
                    i = posReserva; // para encerrar o loop for
                }
            }
        }
        return existe;
    }
}
