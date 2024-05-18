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
        System.out.print("Introdueix la columna (-1 per a guardar partida): ");
        jugada[1] = sc.nextInt();
        return jugada;
    }

    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            System.out.println("Empat!");
        } else {
            System.out.println("Guanyador: Jugador " + ((guanyador == 1) ? "X" : "O"));
        }
    }

    public int medidaTablero() {
        int medida = 3;
        int opcion;
        do {
            System.out.println("Vols canviar la mida del tauler?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Introdueix la nova mida (3-10): ");
                    medida = sc.nextInt();
                    break;
            }
        } while (opcion != 2);
        return medida;
    }

    public void mostrarMissatgeError(String missatge) {
        System.out.println(missatge);
    }

    public void mostrarMissatge(String missatge){
        System.out.println(missatge);
    }

    public void errorGuardarConfiguracio(String missatge){
        System.out.println(missatge);
    }

    public void errorGuardarPartida(String missatge){
        System.out.println(missatge);
    }
}
