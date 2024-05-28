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
                inputValido = true;
            } catch (NumberFormatException e) {
                mostrarMissatgeError("Introdueix un valor vàlid.");
            }
        } while (!inputValido);

        return opcion;
    }

    public void mostrarTaulell(char[][] taulell, int torn) {
        System.out.println("Taulell:");
        for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell[i].length; j++) {
                System.out.print(taulell[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Torn del jugador: " + ((torn % 2 == 0) ? "O" : "X"));
    }

    public int[] recollirJugada() {
        int[] jugada = new int[2];
        System.out.print("Introdueix la fila (-1 per a guardar partida): ");
        jugada[0] = sc.nextInt();
        if (jugada[0] == -1) {
            jugada[1] = -1;
            sc.nextLine(); // Consume newline
            return jugada;
        }
        System.out.print("Introdueix la columna (-1 per a guardar partida): ");
        jugada[1] = sc.nextInt();
        sc.nextLine(); // Consume newline
        return jugada;
    }

    public int mostrarMenuConfiguracio() {
        int mida = -1;
        boolean inputValido = false;

        do {
            System.out.println("\nConfiguració:");
            System.out.println("1. Canviar mida del taulell");
            System.out.println("2. Tornar enrere");
            System.out.print("Selecciona una opció: ");
            int opcio = Integer.parseInt(sc.nextLine());

            switch (opcio) {
                case 1:
                    System.out.print("Introdueix la nova mida del taulell (3-10): ");
                    mida = Integer.parseInt(sc.nextLine());
                    if (mida >= 3 && mida <= 10) {
                        inputValido = true;
                    } else {
                        mostrarMissatgeError("La mida ha de ser entre 3 i 10.");
                    }
                    break;
                case 2:
                    return -1;
                default:
                    mostrarMissatgeError("Introdueix una opció vàlida.");
                    break;
            }
        } while (!inputValido);

        return mida;
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
}
