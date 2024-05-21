import java.io.RandomAccessFile;

class Jogador {
    public static Jogador[] jogadores = new Jogador[3922];
    public static int n = 3922;

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

    public static int ler() {
        // verificacao se o 'pub in' é FIM
        String resp = MyIO.readLine();
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

        Lista listaJog = new Lista(3922); // criando a LISTA para armazenar os jogadores
        int jogEscolhido = 0;

        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                listaJog.inserirFim(jogadores[jogEscolhido]);
            }
        }

        int qtdAlteracoes = MyIO.readInt();
        String alteracao, infos[];

        for (int i = 0; i < qtdAlteracoes; i++) {
            alteracao = MyIO.readLine(); // armazena a linha lida, ex: IF 999
            infos = alteracao.split(" "); // salva separadamente o IF de 999

            if (alteracao.charAt(0) == 'I') {
                if (alteracao.charAt(1) == 'I') {
                    listaJog.inserirInicio(jogadores[Integer.parseInt(infos[1])]);

                } else if (alteracao.charAt(1) == 'F') {
                    listaJog.inserirFim(jogadores[Integer.parseInt(infos[1])]);

                } else { // inserir em determinada posicao
                    listaJog.inserir(jogadores[Integer.parseInt(infos[2])],
                            Integer.parseInt(infos[1]));
                }
            }

            else { // alteracao.charAt(0) == 'R')
                if (alteracao.charAt(1) == 'I') {
                    listaJog.removerInicio();

                } else if (alteracao.charAt(1) == 'F') {
                    listaJog.removerFim();

                } else { // remover em determinada posicao
                    listaJog.remover(Integer.parseInt(infos[1]));
                }
            }
        }

        listaJog.mostrar();
    }

    public static boolean isFim(String s) {

        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

}

class Lista {
    private Jogador[] listaDeJogadores; // criando uma LISTA para guardar os jogadores
    private int tamLista; // tamanho da LISTA

    public Lista() {
        this(3922);
    }

    public Lista(int tam) {
        listaDeJogadores = new Jogador[tam];
        tamLista = 0;
    }

    public void inserirInicio(Jogador jogador) throws Exception {
        if (tamLista >= listaDeJogadores.length)
            throw new Exception("Erro");

        // levando todos elementos uma "casa" para a direita, para liberar o primeiro
        // espaco
        for (int i = tamLista; i > 0; i--) {
            listaDeJogadores[i] = listaDeJogadores[i - 1];
        }

        listaDeJogadores[0] = jogador;
        tamLista++;
    }

    public void inserir(Jogador jogador, int pos) throws Exception {
        if (pos > tamLista || pos < 0)
            throw new Exception("Erro");

        // levando todos elementos depois da posicao uma "casa" para a direita, para
        // liberar o primeiro espaco
        for (int i = tamLista; i > pos; i--) {
            listaDeJogadores[i] = listaDeJogadores[i - 1];
        }

        listaDeJogadores[pos] = jogador;
        tamLista++;
    }

    public void inserirFim(Jogador jogador) {
        listaDeJogadores[tamLista] = jogador; // OBS: PODERIA SER SOMENTE
        listaDeJogadores[tamLista++] = jogador;
        tamLista++;
    }

    public Jogador removerInicio() throws Exception {
        if (tamLista == 0)
            throw new Exception("Erro");

        Jogador removido = listaDeJogadores[0];
        System.out.println("(R) " + removido.getNome());

        // levando todos elementos uma "casa" para a esquerda
        for (int i = 0; i < tamLista - 1; i++) {
            listaDeJogadores[i] = listaDeJogadores[i + 1];
        }

        tamLista--;
        return removido;
    }

    public Jogador remover(int pos) throws Exception {
        if (tamLista == 0 || pos < 0 || pos > tamLista)
            throw new Exception("Erro");

        Jogador removido = listaDeJogadores[pos];
        System.out.println("(R) " + removido.getNome());

        // levando todos elementos depois da posicao uma "casa" para a esquerda
        for (int i = pos; i < tamLista - 1; i++) {
            listaDeJogadores[i] = listaDeJogadores[i + 1];
        }

        tamLista--;
        return removido;
    }

    public Jogador removerFim() throws Exception {
        if (tamLista == 0)
            throw new Exception("Erro");

        Jogador removido = listaDeJogadores[tamLista - 1];
        System.out.println("(R) " + removido.getNome());

        tamLista--;
        return removido;
    }

    public void mostrar() {

        for (int i = 0; i < tamLista; i++) {
            System.out.println("[" + i + "] ## " + listaDeJogadores[i].getNome() + " ## "
                    + +listaDeJogadores[i].getAltura() + " ## " + listaDeJogadores[i].getPeso() +
                    " ## "
                    + listaDeJogadores[i].getAnoNascimento() + " ## " +
                    listaDeJogadores[i].getUniversidade() + " ## "
                    + listaDeJogadores[i].getCidadeNascimento() + " ## " +
                    listaDeJogadores[i].getEstadoNascimento()
                    + " ## ");
        }
    }
}