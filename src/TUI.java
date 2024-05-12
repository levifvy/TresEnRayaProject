import java.util.Scanner;

public class TUI {
    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("1. Nova partida");
        System.out.println("2. Carregar partida");
        System.out.println("3. Configuració");
        System.out.println("4. Sortir");
        System.out.print("Escull una opció: ");
        return sc.nextInt();
    }

    public void mostrarTaulell(char[][] taulell, int torn) {
        String jugador = (torn % 2 == 0) ? "X" : "O";
        System.out.println("Torn del jugador " + jugador);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(taulell[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] recollirJugada() {
        int fila, columna;
        System.out.print("Introduce la fila (0-2): ");
        fila = sc.nextInt();
        System.out.print("Introduce la columna (0-2): ");
        columna = sc.nextInt();
        return new int[]{fila, columna};
    }

    public void fiDePartida(int guanyador) {
        if (guanyador == -1) {
            System.out.println("Empat!");
        } else {
            String jugador = (guanyador % 2 == 0) ? "O" : "X";
            System.out.println("Guanya el jugador " + jugador);
        }
    }

    public void mostrarMissatge(String missatge) {
        System.out.println(missatge);
    }
}
