import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Joc {
    private char[][] taulell;
    private int torn;

    public char[][] getTaulell() {
        return taulell;
    }

    public int getTorn() {
        return torn;
    }

    public void novaPartida(int mida) {
        taulell = new char[mida][mida];
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                taulell[i][j] = '-';
            }
        }
        torn = 1;
    }

    public void jugar(int fila, int columna) {
        if (taulell[fila][columna] == '-') {
            taulell[fila][columna] = (torn % 2 == 0) ? 'X' : 'O';
            torn++;
        }
    }

    public boolean jugadaGuanyadora(int fila, int columna) {
        char simbol = taulell[fila][columna];

        // Comprova fila
        if (comprovarFila(fila, simbol)) return true;

        // Comprova columna
        if (comprovarColumna(columna, simbol)) return true;

        // Comprova diagonal principal
        if (fila == columna && comprovarDiagonalPrincipal(simbol)) return true;

        // Comprova diagonal secundaria
        if (fila + columna == taulell.length - 1 && comprovarDiagonalSecundaria(simbol)) return true;

        return false;
    }

    private boolean comprovarFila(int fila, char simbol) {
        for (int j = 0; j < taulell.length; j++) {
            if (taulell[fila][j] != simbol) return false;
        }
        return true;
    }

    private boolean comprovarColumna(int columna, char simbol) {
        for (int i = 0; i < taulell.length; i++) {
            if (taulell[i][columna] != simbol) return false;
        }
        return true;
    }

    private boolean comprovarDiagonalPrincipal(char simbol) {
        for (int i = 0; i < taulell.length; i++) {
            if (taulell[i][i] != simbol) return false;
        }
        return true;
    }

    private boolean comprovarDiagonalSecundaria(char simbol) {
        for (int i = 0; i < taulell.length; i++) {
            if (taulell[i][taulell.length - 1 - i] != simbol) return false;
        }
        return true;
    }

    public void guardarConfiguracio(int mida) {
        try {
            FileWriter writer = new FileWriter("config");
            writer.write(String.valueOf(mida));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la configuració.");
        }
    }
}
