import java.util.Scanner;

public class TUI {
    Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        int opcion = 0;
        boolean inputValido = false;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Nova partida");
            System.out.println("2. Carregar partida");
            System.out.println("3. Configuració");
            System.out.println("4. Sortir");
            System.out.print("Selecciona una opció: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion >= 1 && opcion <= 4) {
                    inputValido = true;
                } else {
                    System.out.println("Introdueix un valor entre 1 i 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introdueix un valor entre 1 i 4.");
            }
        } while (!inputValido);

        return opcion;
    }

    public void mostrarTaulell(char[][] taulell, int torn) {
        System.out.println("\nTaulell:");
        for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell[i].length; j++) {
                System.out.print(taulell[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Torn del jugador: " + ((torn % 2 == 0) ? "X" : "O"));
    }

    public int[] recollirJugada() {
        int[] jugada = new int[2];
        System.out.print("Introdueix la fila (-1 per a guardar partida): ");
        jugada[0] = sc.nextInt();
        if (jugada[0] == -1) {
            jugada[1] = -1;
            return jugada;
        }
        System.out.print("Introdueix la columna: ");
        jugada[1] = sc.nextInt();
        return jugada;
    }

    public int medidaTablero() {
        System.out.print("Introdueix la mida del taulell: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            System.out.println("Empat!");
        } else {
            System.out.println("El jugador " + guanyador + " ha guanyat!");
        }
    }

    public void mostrarMissatge(String missatge) {
        System.out.println(missatge);
    }

    public void mostrarMissatgeError(String missatge) {
        System.err.println(missatge);
    }

    public int mostrarPartidesGuardades(String[] partidesGuardades) {
        System.out.println("\nPartides guardades:");
        for (int i = 0; i < partidesGuardades.length; i++) {
            System.out.println((i + 1) + ". " + partidesGuardades[i]);
        }
        System.out.print("Selecciona una partida per carregar (introdueix el número): ");
        return Integer.parseInt(sc.nextLine()) - 1;
    }

    public void errorGuardarConfiguracio(String missatge){
        System.out.println(missatge);
    }

    public void errorGuardarPartida(String missatge){
        System.out.println(missatge);
    }
    public void errorCatchCarregarPartida(String missatge){
        System.out.println(missatge);
    }
}
