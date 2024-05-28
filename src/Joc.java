import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Joc {
    private char[][] taulell;
    private int torn;
    private int midaTaulell = 3; // Mida predeterminada

    public char[][] getTaulell() {
        return taulell;
    }

    public int getTorn() {
        return torn;
    }

    public int getMidaTaulell() {
        return midaTaulell;
    }

    public void novaPartida(int mida) {
        taulell = new char[mida][mida];
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                taulell[i][j] = '-';
            }
        }
        torn = 1;
        midaTaulell = mida;
    }

    public void jugar(int fila, int columna) {
        if (taulell[fila][columna] == '-') {
            taulell[fila][columna] = (torn % 2 == 0) ? 'O' : 'X';
            torn++;
        }
    }

    public boolean jugadaGuanyadora(int fila, int columna) {
        char simbol = taulell[fila][columna];
        return comprovarFila(fila, simbol) || comprovarColumna(columna, simbol)
                || comprovarDiagonalPrincipal(simbol) || comprovarDiagonalSecundaria(simbol);
    }

    private boolean comprovarFila(int fila, char simbol) {
        for (int j = 0; j <= taulell.length - 3; j++) {
            if (taulell[fila][j] == simbol && taulell[fila][j + 1] == simbol && taulell[fila][j + 2] == simbol) {
                return true;
            }
        }
        return false;
    }

    private boolean comprovarColumna(int columna, char simbol) {
        for (int i = 0; i <= taulell.length - 3; i++) {
            if (taulell[i][columna] == simbol && taulell[i + 1][columna] == simbol && taulell[i + 2][columna] == simbol) {
                return true;
            }
        }
        return false;
    }

    private boolean comprovarDiagonalPrincipal(char simbol) {
        for (int i = 0; i <= taulell.length - 3; i++) {
            for (int j = 0; j <= taulell.length - 3; j++) {
                if (taulell[i][j] == simbol && taulell[i + 1][j + 1] == simbol && taulell[i + 2][j + 2] == simbol) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean comprovarDiagonalSecundaria(char simbol) {
        for (int i = 0; i <= taulell.length - 3; i++) {
            for (int j = 2; j < taulell.length; j++) {
                if (taulell[i][j] == simbol && taulell[i + 1][j - 1] == simbol && taulell[i + 2][j - 2] == simbol) {
                    return true;
                }
            }
        }
        return false;
    }

    public void guardarConfiguracio(int mida) {
        try (FileWriter writer = new FileWriter("config")) {
            writer.write(String.valueOf(mida));
            midaTaulell = mida; // Actualizar la mida del taulell
        } catch (IOException e) {
            // Gestionar l'error de manera apropiada si es necesario
        }
    }

    public void carregarConfiguracio() {
        try {
            File file = new File("config");
            if (file.exists()) {
                String config = new String(Files.readAllBytes(Paths.get("config")));
                midaTaulell = Integer.parseInt(config.trim());
            }
        } catch (IOException e) {
            // Gestionar l'error de manera apropiada si es necesario
        }
    }

    public void guardarPartida() {
        try {
            File savedGamesFolder = new File("savedgames");
            if (!savedGamesFolder.exists()) {
                savedGamesFolder.mkdir();
            }

            String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
            FileWriter writer = new FileWriter("savedgames/" + fileName);
            writer.write(String.valueOf(torn % 2 == 0 ? 2 : 1) + "\n");
            for (char[] fila : taulell) {
                for (char casella : fila) {
                    writer.write(casella + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            // Gestionar l'error de manera apropiada si es necesario
        }
    }

    public String[] llistarPartidesGuardades() {
        File savedGamesFolder = new File("savedgames");
        if (!savedGamesFolder.exists()) {
            return null;
        }
        return savedGamesFolder.list((dir, name) -> name.endsWith(".txt"));
    }

    public void carregarPartida(String nomPartida) {
        try {
            File file = new File("savedgames/" + nomPartida);
            java.util.List<String> lines = Files.readAllLines(Paths.get(file.toURI()));

            torn = Integer.parseInt(lines.get(0));
            int mida = lines.size() - 1;
            taulell = new char[mida][mida];

            for (int i = 0; i < mida; i++) {
                String[] row = lines.get(i + 1).split(" ");
                for (int j = 0; j < mida; j++) {
                    taulell[i][j] = row[j].charAt(0);
                }
            }
        } catch (IOException e) {
            // Gestionar l'error de manera apropiada si es necesario
        }
    }
}
