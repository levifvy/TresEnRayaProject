public class Joc {
    private char[][] taulell;
    private int torn;

    public char[][] getTaulell() {
        return taulell;
    }

    public int getTorn() {
        return torn;
    }

    public void novaPartida() {
        taulell = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
        if (taulell[fila][0] == simbol && taulell[fila][1] == simbol && taulell[fila][2] == simbol) {
            return true;
        }

        // Comprova columna
        if (taulell[0][columna] == simbol && taulell[1][columna] == simbol && taulell[2][columna] == simbol) {
            return true;
        }

        // Comprova diagonal
        if (fila == columna && taulell[0][0] == simbol && taulell[1][1] == simbol && taulell[2][2] == simbol) {
            return true;
        }

        if (fila + columna == 2 && taulell[0][2] == simbol && taulell[1][1] == simbol && taulell[2][0] == simbol) {
            return true;
        }

        return false;
    }
}
