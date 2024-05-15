import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Joc joc = new Joc();
        TUI tui = new TUI();
        int opcio;

        do {
            opcio = tui.mostrarMenu();
            System.out.println("Opcio seleccionada: " + opcio);
            switch (opcio) {
                case 1:
                    novaPartida(joc, tui);
                    break;
                case 2:
                    carregarPartida(joc, tui);
                    break;
                case 3:
                    configuracio(joc, tui);
                    break;
                case 4:
                    sortir(tui);
                    break;
                default:
                    opcionNoEsperada(tui);
                    break;
            }
        } while (opcio != 4);
    }

    private static void novaPartida(Joc joc, TUI tui) {
        int mida = joc.getTaulell() != null ? joc.getTaulell().length : tui.medidaTablero();
        joc.novaPartida(mida);
        tui.mostrarTaulell(joc.getTaulell(), joc.getTorn());
        jugarPartida(joc, tui);
    }

    private static void jugarPartida(Joc joc, TUI tui) {
        boolean partidaAcabada = false;
        int[] jugada;
        while (!partidaAcabada) {
            jugada = tui.recollirJugada();
            if (jugada[0] == -1 && jugada[1] == -1) {
                guardarPartida(joc);
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

    private static void guardarPartida(Joc joc) {
        try {
            File savedGamesFolder = new File("savedgames");
            if (!savedGamesFolder.exists()) {
                savedGamesFolder.mkdir();
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = formatter.format(new Date());
            FileWriter writer = new FileWriter("savedgames/" + fileName);
            writer.write(String.valueOf(joc.getTorn() % 2 == 0 ? 2 : 1) + "\n");
            char[][] taulell = joc.getTaulell();
            for (char[] fila : taulell) {
                for (char casella : fila) {
                    writer.write(casella + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la partida.");
        }
    }

    private static void carregarPartida(Joc joc, TUI tui) {
        // Lògica per carregar partida
    }

    private static void configuracio(Joc joc, TUI tui) {
        int mida = tui.medidaTablero();
        joc.guardarConfiguracio(mida);
    }

    private static void sortir(TUI tui) {
        tui.mostrarMissatge("Adeu!");
    }

    private static void opcionNoEsperada(TUI tui){
        tui.mostrarMissatgeError("Introdueix un valor entre 1 i 4.");
    }
}