import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class TresEnRaya {
    private static char tablero[] = new char[9];
    private static int victorias=0, derrotas=0;
    private static final char charUsuario='x', charMaquina='o';

    //Resetea el tablero con los numeros de casilla
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("MENU");
            System.out.println("1. Jugar");
            System.out.println("2. Ver estadísticas");
            System.out.println("0. Salir");
            try {
                System.out.print("Seleccionar opcion (1-2) o 0 para salir:");
                opcion = input.nextInt();
            } catch (InputMismatchException ime) {
                opcion=100;
            }
            input.nextLine();
            switch (opcion) {
                case 1:
                    jugar(input);
                    break;
                case 2:
                    System.out.println("Victorias: "+victorias);
                    System.out.println("Derrotas: "+derrotas+"\n");
                    break;
                case 0:
                    System.out.println("Fin de programa \n");
                    break;
                default:
                    System.out.println("Opción no válida \n");
                    break;
            }
        } while (opcion!=0);
        input.close();
    }
    private static void numerarTablero() {
        for (int i=0; i<9; i++) {
            tablero[i]=Integer.toString(i+1).charAt(0);
        }
    }

    private static void vaciarTablero() {
        for (int i=0; i<9; i++) {
            tablero[i]=' ';
        }
    }

    private static void mostrarTablero() {
        for (int i=0; i<7; i++) {
            switch (i) {
                case 0,2,4,6:
                    System.out.println(" - - - - - - - ");
                    break;
                case 1:
                    System.out.println(" | "+tablero[0]+" | "+tablero[1]+" | "+tablero[2]+" | ");
                    break;
                case 3:
                    System.out.println(" | "+tablero[3]+" | "+tablero[4]+" | "+tablero[5]+" | ");
                    break;
                case 5:
                    System.out.println(" | "+tablero[6]+" | "+tablero[7]+" | "+tablero[8]+" | ");
                    break;
            }
        }


        private static void jugar(Scanner input) {
            numerarTablero();
            mostrarTablero();
            vaciarTablero();
            System.out.println("La maquina juega con el caracter '"+charMaquina+"'.");
            System.out.println("El usuario juega con el caracter '"+charUsuario+"'.");
            switch (partida(input)) {
                case charUsuario:
                    System.out.println("VICTORIA! \n");
                    victorias++;
                    break;
                case charMaquina:
                    System.out.println("DERROTA! \n");
                    derrotas++;
                    break;
                default:
                    System.out.println("EMPATE! \n");
                    break;
            }

        }

        private static boolean turnoInicial() {
            if ((new Random().nextInt(10))%2 == 0) {
                System.out.print("Comienza usted. ");
                return true;
            } else {
                System.out.print("Comienza la máquina. ");
                return false;
            }
        }

        private static char partida(Scanner input) {
            boolean turno=turnoInicial();
            System.out.print("Pulse ENTER para comenzar la partida...");
            input.nextLine();
            int fichas=0, casilla;
            char ganador=' ';

            do {
                mostrarTablero();
                if (fichas<9) {
                    if (turno) {
                        do {
                            System.out.print("Turno del usuario. Colocar en casilla (1-9)?");
                            casilla=input.nextInt();
                            if (tablero[casilla-1]!=' ') {
                                System.out.println("La casilla "+casilla+" esta ocupada");
                                casilla=0;
                            }
                        } while (casilla<1 || casilla>9);
                        input.nextLine();
                        tablero[casilla-1]=charUsuario;
                    } else {
                        System.out.println("Turno de la máquina...");
                        ocuparCasilla();
                        System.out.println("Hecho!");
                    }
                    turno=!turno;
                }
                ganador=victoria();
                fichas++;
            } while (fichas<10 && ganador==' ');
            mostrarTablero();
            return ganador;
        }



    }
}