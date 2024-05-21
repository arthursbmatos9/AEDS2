import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Principal {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        String aux = br.readLine();
        int n = Integer.parseInt(aux);

        Matriz[] matrizes = new Matriz[n * 2];
        for (int i = 0; i < n * 2; i++) {
            matrizes[i] = Matriz.lerMatriz(br); // le os valores da matriz
        }

        for (int i = 0; i < n * 2; i += 2) {
            matrizes[i].mostrarDiagonalPrincipal();
            System.out.println();
            matrizes[i].mostrarDiagonalSecundaria();

            Matriz matrizSoma = matrizes[i].soma(matrizes[i + 1]);
            System.out.println();
            matrizSoma.imprimirMatriz();

            Matriz matrizMult = matrizes[i].multiplicacao(matrizes[i + 1]);
            matrizMult.imprimirMatriz();
        }
    }
}

class Celula {
    public int elemento;
    public Celula sup, inf, dir, esq;

    public Celula(int x) {
        this.elemento = x;
        this.sup = this.dir = this.inf = this.esq = null;
    }

    public Celula() {
        this(0);
    }

}

class Matriz {

    public Celula inicio;
    public int linha, coluna;

    public Matriz() {
        this(3, 3);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        alocacaoDeCelulas();
    }

    public void alocacaoDeCelulas() { // cria em si a matriz, alocando as celulas
        inicio = new Celula();
        Celula temp = inicio;
        for (int i = 1; i < coluna; i++) {
            temp.dir = new Celula();
            temp.dir.esq = temp;
            temp = temp.dir;
        }

        Celula tmp = inicio;
        for (int l = 1; l < linha; l++, tmp = tmp.inf) {
            Celula i = tmp;
            i.inf = new Celula();
            i.inf.sup = i;
            Celula j = i.inf;
            for (int c = 1; c < coluna; c++, j = j.dir) {
                i = i.dir;
                i.inf = new Celula();
                i.inf.sup = i;
                i.inf.esq = j;
                j.dir = i.inf;
            }
        }
    }

    public void setElemento(int l, int c, int x) {
        Celula atual = inicio;
        int i, j;
        for (i = 0; i < c; i++) {
            atual = atual.dir;
        }
        for (j = 0; j < l; j++) {
            atual = atual.inf;
        }
        atual.elemento = x;
    }

    public int getElemento(int l, int c) {
        Celula atual = inicio;
        int i, j;
        for (i = 0; i < c; i++) {
            atual = atual.dir;
        }
        for (j = 0; j < l; j++) {
            atual = atual.inf;
        }
        return atual.elemento;
    }

    public Matriz soma(Matriz m) { // faz a soma dos elementos que estao nas mesmas posicao da matriz
        Matriz resultado = null;

        if (this.linha == m.linha && this.coluna == m.coluna) {
            resultado = new Matriz(this.linha, this.coluna);
            for (int i = 0; i < linha; i++) {
                for (int j = 0; j < coluna; j++) {
                    int elemento = this.getElemento(i, j) + m.getElemento(i, j);
                    resultado.setElemento(i, j, elemento);
                }
            }
        }

        return resultado;
    }

    public Matriz multiplicacao(Matriz m) { // multiplica as matrizes, como na matematica
        Matriz resultado = null;
        if (this.coluna == m.linha) {
            resultado = new Matriz(this.linha, m.coluna);

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < m.coluna; j++) {
                    int soma = 0;
                    for (int k = 0; k < this.coluna; k++) {
                        soma += this.getElemento(i, k) * m.getElemento(k, j);
                    }
                    resultado.setElemento(i, j, soma);
                }
            }
        }
        return resultado;

    }

    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < this.linha; i++) {
            System.out.print(getElemento(i, i) + " ");
        }
    }

    public void mostrarDiagonalSecundaria() {
        for (int i = 0; i < this.linha; i++) {
            System.out.print(getElemento(i, this.coluna - 1 - i) + " ");
        }
    }

    public void imprimirMatriz() {
        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < this.coluna; j++) {
                System.out.print(getElemento(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static Matriz lerMatriz(BufferedReader br) throws Exception {
        String linhasS = br.readLine();
        int linhas = Integer.parseInt(linhasS);
        String colunasS = br.readLine();
        int colunas = Integer.parseInt(colunasS);
        Matriz m = new Matriz(linhas, colunas);

        for (int i = 0; i < linhas; i++) {
            String entrada = br.readLine(); // le os valores de cada linha da matriz e salva nas linhas, usando split
            String[] valores = entrada.split(" ");
            for (int j = 0; j < colunas; j++) {
                m.setElemento(i, j, Integer.parseInt(valores[j]));
            }
        }

        return m;
    }
}