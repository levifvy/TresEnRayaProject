public class Joc {
    private char[][] taulell;
    private short torn;

    public Joc() {
        taulell = new char[3][3];
        novaPartida();
    }

    public char[][] getTaulell() {
        return taulell;
    }

    public short getTorn() {
        return torn;
    }

    public void novaPartida() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                taulell[i][j] = '-';
            }
        }
        torn = 0;
    }

    public void jugar(short fila, String columna) {
        short col = Short.parseShort(columna);
        if (taulell[fila][col] == '-') {
            taulell[fila][col] = (torn % 2 == 0) ? 'X' : 'O';
            torn++;
        }
    }

    public boolean jugadaGuanyadora(short fila, short columna) {
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
