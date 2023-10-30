import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.net.*;

public class LerHTML {
    public static void main(String args[]) {
        String endereco;
        String html, html2;
        

        do {
                                    //criando os contadores dos char pedidos
            int a = 0, e = 0, i = 0, o = 0, u = 0, aa = 0, ee = 0, ii = 0, oo = 0, uu = 0, aaa = 0, eee = 0, iii = 0,
                ooo = 0, uuu = 0, aaaa = 0, oooo = 0, aaaaa = 0, eeeee = 0, iiiii = 0, ooooo = 0, uuuuu = 0,
                consoantes = 0, br = 0, table = 0;
            
            endereco = MyIO.readLine();  //lendo o nome do site

            if (isFim(endereco) == false) {  //veficando se nao é o FIM
                html = MyIO.readLine();   //lendo a url
                html2 = getHtml(html);    //salvando em html2 o html lido acima

                for (int x = 0; x < html2.length(); x++) { 
                    char letra = html2.charAt(x);                   //contando os chars
                    if (letra == 'a')
                        a++;
                    else if (letra == 'e')
                        e++;
                    else if (letra == 'i')
                        i++;
                    else if (letra == 'o')
                        o++;
                    else if (letra == 'u')
                        u++;
                    else if (letra == '\u00E1') // á
                        aa++;
                    else if (letra == '\u00E9') // é
                        ee++;
                    else if (letra == '\u00ED') // í
                        ii++;
                    else if (letra == '\u00F3') // ó
                        oo++;
                    else if (letra == '\u00FA') // ú
                        uu++;
                    else if (letra == '\u00E0') // à
                        aaa++;
                    else if (letra == '\u00E8') // è
                        eee++;
                    else if (letra == '\u00EC') // ì
                        iii++;
                    else if (letra == '\u00F2') // ò
                        ooo++;
                    else if (letra == '\u00F9') // ù
                        uuu++;
                    else if (letra == '\u00E3') // ã
                        aaaa++;
                    else if (letra == '\u00F5') // õ
                        oooo++;
                    else if (letra == '\u00E2') // â
                        aaaaa++;
                    else if (letra == '\u00EA') // ê
                        eeeee++;
                    else if (letra == '\u00EE') // î
                        iiiii++;
                    else if (letra == '\u00F4') // ô
                        ooooo++;
                    else if (letra == '\u00FB') // û
                        uuuuu++;
                    else if (isConsoante(letra))
                        consoantes++;
                    else if (isBR(html2, x) == true)
                    {
                        br++;
                        consoantes-= 2;
                    }
                    else if (isTable(html2, x) == true)
                    {
                        table++;
                        a--;
                        e--;
                        consoantes-= 3;
                    }

                }

                MyIO.println("a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") á(" + aa + ") é(" + ee
                        + ") í(" + ii + ") ó(" + oo + ") ú(" + uu + ") à(" + aaa + ") è(" + eee + ") ì(" + iii + ") ò("
                        + ooo + ") ù(" + uuu + ") ã(" + aaaa + ") õ(" + oooo + ") â(" + aaaaa + ") ê(" + eeeee + ") î("
                        + iiiii + ") ô(" + ooooo + ") û(" + uuuuu + ") consoante(" + consoantes + ") <br>(" + br
                        + ") <table>("
                        + table + ") " + endereco);
            }
        } while (isFim(endereco) == false);
    }

    public static boolean isConsoante(char letra) {    //verificando se o char é consoante
        
        return (letra == 'b' || letra == 'c' || letra == 'd' || letra == 'f' || letra == 'g' || letra == 'h'
                || letra == 'j' || letra == 'k' || letra == 'l' || letra == 'm' || letra == 'n' || letra == 'p' ||
                letra == 'q' || letra == 'r'|| letra == 's' || letra == 't' ||
                letra == 'v' || letra == 'w' || letra == 'x' || letra == 'y' || letra == 'z');
    }

    public static boolean isBR(String html, int x) {   //verificando se o char comeca uma String <br>
        boolean br;
        if (html.charAt(x) == '\u003C') {
            if (html.charAt(x + 1) == 'b') {
                if (html.charAt(x + 2) == 'r') {
                    if (html.charAt(x + 3) == '\u003E')
                        br = true;
                    else
                        br = false;
                } else
                    br = false;
            } else
                br = false;
        } else
            br = false;
        return br;
    }

    public static boolean isTable(String html, int x) {  //verificando se o char comeca uma String <table>
        boolean table;
        if (html.charAt(x) == '\u003C') {
            if (html.charAt(x + 1) == 't') {
                if (html.charAt(x + 2) == 'a') {
                    if (html.charAt(x + 3) == 'b') {
                        if (html.charAt(x + 4) == 'l') {
                            if (html.charAt(x + 5) == 'e') {
                                if (html.charAt(x + 6) == '\u003E')
                                    table = true;
                                else
                                    table = false;
                            } else
                                table = false;
                        } else
                            table = false;
                    } else
                        table = false;
                } else
                    table = false;
            } else
                table = false;
        } else
            table = false;
        return table;
    }

    public static boolean isFim(String s) {           //verificando se a palavra lida é FIM
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String getHtml(String endereco) {       //codigo fornecido pelos professores
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
    }
}
