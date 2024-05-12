import java.util.Scanner;

public class Main {
    private static TUI tui;
    private static Joc joc;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        tui = new TUI();
        joc = new Joc();

        boolean sortir = false;
        while (!sortir) {
            int opcio = tui.mostrarMenu();
            switch (opcio) {
                case 1:
                    novaPartida();
                    break;
                case 2:
                    carregarPartida();
                    break;
                case 3:
                    configuracio();
                    break;
                case 4:
                    sortir = true;
                    break;
                default:
                    tui.mostrarMissatge("Opció invàlida.");
            }
        }
    }

    private static void novaPartida() {
        joc.novaPartida();
        jugarPartida();
    }

    private static void carregarPartida() {
        // Implementar la lógica para cargar una partida guardada
        jugarPartida();
    }

    private static void configuracio() {
        // Implementar la lógica para mostrar el menú de configuración
    }

    private static void sortir() {
        System.exit(0);
    }

    private static void jugarPartida() {
        boolean partidaAcabada = false;
        while (!partidaAcabada) {
            tui.mostrarTaulell(joc.getTaulell(), joc.getTorn());
            int[] jugada = tui.recollirJugada();
            joc.jugar(jugada[0], jugada[1]);
            if (joc.jugadaGuanyadora(jugada[0], jugada[1])) {
                tui.mostrarTaulell(joc.getTaulell(), joc.getTorn());
                int guanyador = (joc.getTorn() == 0) ? 1 : 0;
                tui.fiDePartida(guanyador);
                partidaAcabada = true;
            } else if (joc.getTorn() == 9) {
                tui.mostrarTaulell(joc.getTaulell(), joc.getTorn());
                tui.fiDePartida(-1);
                partidaAcabada = true;
            }
        }
    }
}
