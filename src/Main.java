public class Main {
    private static Joc joc;
    private static TUI tui;

    public static void main(String[] args) {
        joc = new Joc();
        tui = new TUI();

        // Cargar configuración al inicio
        joc.carregarConfiguracio();

        int opcio;

        do {
            opcio = tui.mostrarMenu();
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
                    sortir();
                    break;
                default:
                    opcionNoEsperada();
                    break;
            }
        } while (opcio != 4);
    }

    private static void novaPartida() {
        int mida = joc.getTaulell() != null ? joc.getTaulell().length : joc.getMidaTaulell();
        joc.novaPartida(mida);
        tui.mostrarTaulell(joc.getTaulell(), joc.getTorn());
        jugarPartida();
    }

    private static void jugarPartida() {
        boolean partidaAcabada = false;
        int[] jugada;
        while (!partidaAcabada) {
            jugada = tui.recollirJugada();
            if (jugada[0] == -1 && jugada[1] == -1) {
                joc.guardarPartida();
                break;
            }
            joc.jugar(jugada[0], jugada[1]);
            tui.mostrarTaulell(joc.getTaulell(), joc.getTorn());
            if (joc.jugadaGuanyadora(jugada[0], jugada[1]) || joc.getTorn() > joc.getTaulell().length * joc.getTaulell().length) {
                partidaAcabada = true;
                tui.fiDePartida(joc.jugadaGuanyadora(jugada[0], jugada[1]) ? ((joc.getTorn() - 1) % 2) + 1 : 0);
            }
        }
    }

    private static void carregarPartida() {
        String[] partidesGuardades = joc.llistarPartidesGuardades();
        if (partidesGuardades == null || partidesGuardades.length == 0) {
            tui.mostrarMissatge("No hi ha partides guardades.");
            return;
        }

        int opcio = tui.mostrarPartidesGuardades(partidesGuardades);
        if (opcio >= 0 && opcio < partidesGuardades.length) {
            joc.carregarPartida(partidesGuardades[opcio]);
            tui.mostrarTaulell(joc.getTaulell(), joc.getTorn());
            jugarPartida();
        }
    }

    private static void configuracio() {
        int novaMida = tui.mostrarMenuConfiguracio();
        if (novaMida != -1) {
            joc.guardarConfiguracio(novaMida);
        }
    }

    private static void sortir() {
        tui.mostrarMissatge("Adeu!");
    }

    private static void opcionNoEsperada() {
        tui.mostrarMissatgeError("Introdueix un valor entre 1 i 4.");
    }
}
