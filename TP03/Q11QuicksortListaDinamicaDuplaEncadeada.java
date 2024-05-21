import java.io.*;

class Jogador {
    public static Jogador[] jogadores = new Jogador[3922];
    public static int n = 3922;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

        Lista listaJog = new Lista(); // criando a LISTA para armazenar os jogadores
        int jogEscolhido = 0;

        while (jogEscolhido != -1) { // verificando se isFim (dentro do ler())
            jogEscolhido = ler();

            if (jogEscolhido != -1) {
                listaJog.inserirFim(jogadores[jogEscolhido]);
            }
        }

        // System.out.println(
        // listaJog.primeiro.elemento.getNome() + " - " +
        // listaJog.ultimo.elemento.getNome() + "\n\n");

        ordenaQuicksort(listaJog);

        listaJog.mostrar();
    }

    public static boolean isFim(String s) {

        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void ordenaQuicksort(Lista listaJog) {
        ordenaQuicksort(listaJog.primeiro.prox, listaJog.ultimo, listaJog);
    }

    public static void ordenaQuicksort(Celula p, Celula u, Lista list) {
        Celula i = p, j = u;
        // Jogador pivo = u.elemento;
        String pivo = pegaMeio(list);
        // System.out.println("------" + pivo);

        while (i != j && j.prox != i) {
            while (i.elemento.getEstadoNascimento().compareTo(pivo) < 0) {
                i = i.prox;
                // System.out.println(i.elemento.getNome() + " aaaa");
            }
            while (j.elemento.getEstadoNascimento().compareTo(pivo) > 0 && j.ant != null) {
                // System.out.println(j.elemento.getNome() + " bbbb");
                j = j.ant;
            }

            if (i != j && j.ant != i) {
                swap(i, j);
                i = i.prox;
                j = j.ant;
            }
        }

        if (p != j)
            ordenaQuicksort(p, j, list);
        if (i != u)
            ordenaQuicksort(i, u, list);
    }

    public static void swap(Celula i, Celula j) {
        Jogador tmp = i.elemento;
        i.elemento = j.elemento;
        j.elemento = tmp;
    }

    public static String pegaMeio(Lista lista) {
        Celula meio = lista.primeiro;

        int tam = tamanhoLista(lista);
        for (int i = 0; i < tam / 2; i++) {
            meio = meio.prox;
        }

        String doMeio = meio.elemento.getNome();
        return doMeio;
    }

    public static int tamanhoLista(Lista lista) {
        int tam = 0;
        for (Celula i = lista.primeiro; i != lista.ultimo; i = i.prox) {
            tam++;
        }
        return tam;
    }
}

class Celula {
    public Jogador elemento;
    public Celula prox;
    public Celula ant;

    public Celula() {
        elemento = new Jogador();
        prox = null;
    }

    public Celula(Jogador x) {
        elemento = x;
        prox = null;
    }
}

class Lista {
    public Celula primeiro;
    public Celula ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirFim(Jogador jogador) {
        ultimo.prox = new Celula(jogador); // cria uma nova celula para onde o ultimo aponta e desloca o ultimo para ela
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void mostrar() {
        for (Celula i = primeiro; i != null; i = i.prox) {
            System.out.println("[" + i.elemento.getId() + " ## " + i.elemento.getNome() +
                    " ## "
                    + i.elemento.getAltura() + " ## " + i.elemento.getPeso() + " ## "
                    + i.elemento.getAnoNascimento() + " ## " + i.elemento.getUniversidade() +
                    " ## "
                    + i.elemento.getCidadeNascimento() + " ## " +
                    i.elemento.getEstadoNascimento()
                    + "]");
        }
    }

    public int tamanho() {
        int tam = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, tam++)
            ; // aumenta o contador ate que a ultima posicao aponte para null
        return tam;
    }

    void ordenaQuicksort(int esq, int dir, int array) { // mandar o dir com -1
        int i = esq, j = dir;
        int pivo = array[(esq + dir) / 2];

        while (i <= j) {
            while (array[i] < pivo) {
                i++;
            }
            while (array[j] > pivo) {
                j--;
            }

            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }

        if (esq < j)
            ordenaQuicksort(esq, j, array);
        if (i < dir)
            ordenaQuicksort(i, dir, array);
    }

    void swap(int i, int j, int[] array) {
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
}