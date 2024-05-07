import java.security.KeyStore;
import java.util.Scanner;
import java.util.Random;
public class Main {

    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static Scanner teclado=new Scanner(System.in);

    public static int [][]crearTablero(){
        int[][]matriz=new int[6][7];
        for (int i=0; i< matriz.length;i++){
            for(int j=0;j< matriz[0].length;j++){
                matriz[i][j]=0;
            }
        }
        return matriz;
    }



    public static void pintarTablero(int[][]matriz,char[]lista_letras){
        for(int i=0;i<= matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                if(i==matriz.length){
                    System.out.print(ANSI_WHITE+" "+ lista_letras[j]+" "+ANSI_RESET+" ");
                }else if (matriz[i][j]==0){
                    System.out.print(ANSI_WHITE_BACKGROUND+"   "+ANSI_RESET+" ");
                }else if(matriz[i][j]==1){
                    System.out.print(ANSI_RED_BACKGROUND+" "+ANSI_BLACK+"1"+" "+ANSI_RESET+" ");
                }else if(matriz[i][j]==2) {
                    System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_BLACK + "2" +" "+ANSI_RESET+ " ") ;
                }
            }System.out.println("\n");
        }
    }

    public static int[][] insertarFicha(int[][]matriz,int jugador,char[]lista_letras) {
        System.out.println("===>JUGADOR " + jugador);
        System.out.println("Introduce una columna: ");
        int columna=0;
        char letra = teclado.next().charAt(0);
        while (letra < 97 || letra > 103) {
            System.out.println("¡¡¡COLUMNA ERRONEA!!! ");
            System.out.println("===>JUGADOR " + jugador);
            System.out.println("Introduce una columna: ");
            letra = teclado.next().charAt(0);
        }
        if (letra == 97) {
            columna = 0;
        } else if (letra == 98) {
            columna = 1;
        } else if (letra == 99) {
            columna = 2;
        } else if (letra == 100) {
            columna = 3;
        } else if (letra == 101) {
            columna = 4;
        } else if (letra == 102) {
            columna = 5;
        } else {
            columna = 6;
            System.out.println(letra);
        }

        for (int j = matriz.length-1; j>=0; j--) {
                    if(matriz[j][columna]==0){
                        matriz[j][columna]=jugador;
                        break;
                    }else{
                        continue;
                    }
                }


        pintarTablero(matriz,lista_letras);
        return matriz;
        }


        //Funcion para comprobar que la columna en la que se introduce ficha no esta comppleta
        public static boolean columnaCompleta(int[][]matriz,int columnaInput){
            if(columnaInput!=-1){
                if(matriz[0][columnaInput]!=0){
                    return true;
                }else {
                    return false;
                }
            }
            return false;
        }

        //Funcion para comprobar que el tablero no esta completo y acaba la partida en tablas
        public static boolean tableroCompleto(int[][]matriz){

                for(int columna=0;columna < matriz[0].length;columna++){
                    if(matriz[0][columna]==0){
                        return false;
                    }
                }
            return true;
        }






        public static boolean comprobrarHorizontalVertical(int [][]matriz){
            for(int fila=0;fila< matriz.length;fila++){
                for(int columna=0;columna< matriz[0].length;columna++){
                    if(columna<4){
                        int num=matriz[fila][columna];
                        int num1=matriz[fila][columna+1];
                        int num2=matriz[fila][columna+2];
                        int num3=matriz[fila][columna+3];
                        if(num!=0&&num==num1&&num==num2&&num==num3){
                            return true;
                        }
                    }
                    if(fila<3){
                        int num_v=matriz[fila][columna];
                        int num1_v=matriz[fila+1][columna];
                        int num2_v=matriz[fila+2][columna];
                        int num3_v=matriz[fila+3][columna];
                        if(num_v!=0&&num_v==num1_v&&num_v==num2_v&&num_v==num3_v){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public static boolean comprobarDiagonales(int [][]matriz){

            for(int filas = 3; filas < matriz.length; filas++){
                for(int columnas = 0; columnas < matriz[0].length - 3; columnas++){
                    if (matriz[filas][columnas] != 0 && matriz[filas][columnas] == matriz[filas-1][columnas+1] && matriz[filas-1][columnas+1] == matriz[filas-2][columnas+2] && matriz[filas-2][columnas+2] == matriz[filas-3][columnas+3]) {
                        System.out.println("Diagonal_1");
                        return true;
                    }
                }
            }
            for(int fila = 0; fila < matriz.length - 3; fila++){
                for(int columna = 0; columna < matriz[0].length - 3; columna++){
                    if (matriz[fila][columna] != 0 && matriz[fila][columna] == matriz[fila+1][columna+1] && matriz[fila+1][columna+1] == matriz[fila+2][columna+2] && matriz[fila+2][columna+2] == matriz[fila+3][columna+3]) {
                        System.out.println("Diagonal_2");
                        return true;
                    }
                }
            }
            return false;
        }

        //Funciones de IA CPU
        public static int[][] insertarFicha_CPU(int[][]matriz,int jugador,int columna_CPU) {
            char[] lista_letras ={'A','B','C','D','E','F','G'};
            System.out.println("===>JUGADOR " + jugador);
            System.out.println("Introduce una columna: ");
            for (int j = matriz.length-1; j>=0; j--) {
                if(matriz[j][columna_CPU]==0){
                    matriz[j][columna_CPU]=2;
                    break;
                }else{
                    continue;
                }
            }
            pintarTablero(matriz,lista_letras);
            return matriz;
        }

        public static int[][]clonarTablero(int[][]matriz){
            int[][]matriz_clone=new int[6][7];
            for(int i=0;i< matriz.length;i++){
                for(int j=0;j<matriz[0].length;j++){
                    matriz_clone[i][j]=matriz[i][j];
                }
            }
            return matriz_clone;
        }

///ALGORITMOS DE ATAQUE
///Algoritmo para MATAR,en horizontal,vertical y diagonales, si tenemos 3 fichas seguidas de la ficha 2, devuelve la columna donde tiene que meter la ficha.
    public static int ataqueMatar(int[][]matriz){
        int colum_matar=-1;
        for(int fila=0;fila< matriz.length;fila++) {
            for (int columna = 0; columna < matriz[0].length; columna++) {
                if (columna < 4) {
                    int num = matriz[fila][columna];

                    int num1 = matriz[fila][columna + 1];

                    int num2 = matriz[fila][columna + 2];

                    int num3 = matriz[fila][columna + 3];

                    if(fila==5){

                        if (num == 2 && num == num1 && num == num2 && num3 == 0) {
                            colum_matar = columna + 3;
                            return colum_matar;
                        } else if (num == 2 && num1==2 && num2 == 0 && num3 == 2) {
                            colum_matar = columna + 2;
                            return colum_matar;
                        } else if (num == 2 && num1==0 && num2 == 2 && num3 == 2) {
                            colum_matar = columna + 1;
                            return colum_matar;
                        } else if (num == 0 && num1==2 && num2 == 2 && num3 == 2) {
                            colum_matar = columna;
                            return colum_matar;
                        }
                    }else{

                        int num_down=matriz[fila+1][columna];

                        int num1_down=matriz[fila+1][columna+1];

                        int num2_down=matriz[fila+1][columna+2];

                        int num3_down=matriz[fila+1][columna+3];
                        if (num == 2 && num == num1 && num == num2 && num3 == 0&&num3_down!=0) {
                            colum_matar = columna + 3;
                            return colum_matar;
                        } else if (num == 2 && num1==2 && num2 == 0 && num2_down!=0 && num3 == 2) {
                            colum_matar = columna + 2;
                            return colum_matar;
                        } else if (num == 2 && num1==0 && num1_down!=0 && num2 == 2 && num3 == 2) {
                            colum_matar = columna + 1;
                            return colum_matar;
                        } else if (num == 0 && num_down!=0 && num1==2 && num2 == 2 && num3 == 2) {
                            colum_matar = columna;
                            return colum_matar;
                        }
                    }
                }
                if(fila<3){
                    int num_v=matriz[fila][columna];
                    int num1_v=matriz[fila+1][columna];
                    int num2_v=matriz[fila+2][columna];
                    int num3_v=matriz[fila+3][columna];
                    if(num_v==0&&2==num1_v&&2==num2_v&&2==num3_v){
                        colum_matar=columna;
                        return colum_matar;
                    }

                }
            }
        }
        //Diagonal 1
        for(int fila = 3; fila < matriz.length; fila++){
            for(int columnas = 0; columnas < matriz[0].length - 3; columnas++){
                int pos0_diag=matriz[fila][columnas];
                int pos1_diag=matriz[fila-1][columnas+1];
                int pos1_down=matriz[fila][columnas+1];
                int pos2_diag=matriz[fila-2][columnas+2];
                int pos2_down=matriz[fila-1][columnas+1];
                int pos3_diag=matriz[fila-3][columnas+3];
                int pos3_down=matriz[fila-2][columnas+3];

                if(fila==5){
                    if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 2 && pos3_diag==0&&pos3_down!=0 ) {
                        colum_matar=columnas+3;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==2) {
                        colum_matar=columnas+2;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columnas+1;
                        return colum_matar;
                    } else if (pos0_diag == 0 && pos1_diag == 2 && pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columnas;
                        return colum_matar;
                    }
                }else{
                    int pos0_down=matriz[fila+1][columnas];
                    if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 2 && pos3_diag==0&&pos3_down!=0 ) {
                        colum_matar=columnas+3;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==2) {
                        colum_matar=columnas+2;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columnas+1;
                        return colum_matar;
                    } else if (pos0_diag == 0 &&pos0_down!=0&& pos1_diag == 2 && pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columnas;
                        return colum_matar;
                    }
                }
            }
        }
        //Diagonal 2
        for(int fila = 0; fila < matriz.length - 3; fila++){
            for(int columna = 0; columna < matriz[0].length - 3; columna++){
                int pos0_diag=matriz[fila][columna];
                int pos0_down=matriz[fila+1][columna];
                int pos1_diag=matriz[fila+1][columna+1];
                int pos1_down=matriz[fila+2][columna+1];
                int pos2_diag=matriz[fila+2][columna+2];
                int pos2_down=matriz[fila+3][columna+2];
                int pos3_diag=matriz[fila+3][columna+3];

                //Comprobamos que la ultima posicion sea un 0 y que justo debajo de ella tengamos ficha.
                if(fila==2){
                    if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 2 && pos3_diag==0 ) {
                        colum_matar=columna+3;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==2) {
                        colum_matar=columna+2;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columna+1;
                        return colum_matar;
                    } else if (pos0_diag == 0 && pos0_down!=0 && pos1_diag == 2 && pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columna;
                        return colum_matar;
                    }
                }else{
                    int pos3_down=matriz[fila+4][columna+3];
                    if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 2 && pos3_diag==0&&pos3_down!=0 ) {
                        colum_matar=columna+3;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==2) {
                        colum_matar=columna+2;
                        return colum_matar;
                    } else if (pos0_diag == 2 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columna+1;
                        return colum_matar;
                    } else if (pos0_diag == 0 &&pos0_down!=0&& pos1_diag == 2 && pos2_diag == 2 && pos3_diag==2) {
                        colum_matar=columna;
                        return colum_matar;
                    }
                }
            }
        }
        return colum_matar;
    }

//Algoritmo de ataque2
    public static int ataque2(int[][]matriz){
        int colum_ataque=-1;

        for(int fila=0;fila< matriz.length;fila++) {
            for (int columna = 0; columna < matriz[0].length; columna++) {
                if (columna < 5) {
                    int num = matriz[fila][columna];

                    int num1 = matriz[fila][columna + 1];

                    int num2 = matriz[fila][columna + 2];

                    if(fila==5) {
                        if (num == 2 && num1 == 2 && num2 == 0) {
                            colum_ataque = columna + 2;
                            return colum_ataque;
                        } else if (num == 2 && num1 == 0 && num2 == 2) {
                            colum_ataque = columna + 1;
                            return colum_ataque;
                        } else if (num == 0 && num1 == 2 && num2 == 2) {
                            colum_ataque = columna;
                            return colum_ataque;
                        }
                    }else{
                        int num_down = matriz[fila + 1][columna];
                        int num1_down = matriz[fila + 1][columna + 1];
                        int num2_down = matriz[fila + 1][columna + 2];
                        if (num == 2 && num1 == 2 && num2 == 0 && num2_down != 0) {
                            colum_ataque = columna + 2;
                            return colum_ataque;
                        } else if (num == 2 && num1 == 0 && num1_down != 0 && num2 == 2) {
                            colum_ataque = columna + 1;
                            return colum_ataque;
                        } else if (num == 0 && num_down != 0 && num1 == 2 && num2 == 2) {
                            colum_ataque = columna;
                            return colum_ataque;
                        }
                    }
                }
                if(fila<4){
                    int num_v=matriz[fila][columna];
                    int num1_v=matriz[fila+1][columna];
                    int num2_v=matriz[fila+2][columna];
                    //int num3_v=matriz[fila+3][columna];
                    if(num_v==0&&2==num1_v&&2==num2_v){
                        colum_ataque=columna;
                        return colum_ataque;
                    }

                }
            }
        }

        //Comprobacion Diagonales

        for(int fila = 3; fila < matriz.length; fila++){
            for(int columnas = 0; columnas < matriz[0].length - 2; columnas++){
                int pos0_diag=matriz[fila][columnas];
                int pos1_diag=matriz[fila-1][columnas+1];
                int pos1_down=matriz[fila][columnas+1];
                int pos2_diag=matriz[fila-2][columnas+2];
                int pos2_down=matriz[fila-1][columnas+1];;

                if(fila==5){
                    if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag==0&&pos2_down!=0 ) {
                        colum_ataque=columnas+2;
                        return colum_ataque;
                    } else if (pos0_diag == 2 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag==2) {
                        colum_ataque=columnas+1;
                        return colum_ataque;
                    } else if (pos0_diag ==0&& pos1_diag == 2 && pos2_diag==2) {
                        colum_ataque=columnas;
                        return colum_ataque;
                    }
                }else{
                    int pos0_down=matriz[fila][columnas];
                    if (pos0_diag == 2 && pos1_diag == 2 &&pos2_diag==0&&pos2_down!=0 ) {
                        colum_ataque=columnas+2;
                        return colum_ataque;
                    } else if (pos0_diag == 2 &&pos1_diag == 0 &&pos1_down!=0&& pos2_diag==2) {
                        colum_ataque=columnas+1;
                        return colum_ataque;
                    } else if (pos0_diag == 0&&pos0_down!=0 && pos1_diag == 2 && pos2_diag == 2) {
                        colum_ataque=columnas;
                        return colum_ataque;
                    }
                }
            }
        }
        //Diagonal 2
        for(int fila = 0; fila < matriz.length - 2; fila++){
            for(int columna = 0; columna < matriz[0].length - 3; columna++){
                int pos0_diag=matriz[fila][columna];
                int pos0_down=matriz[fila+1][columna];
                int pos1_diag=matriz[fila+1][columna+1];
                int pos1_down=matriz[fila+2][columna+1];
                int pos2_diag=matriz[fila+2][columna+2];



                //Comprobamos que la ultima posicion sea un 0 y que justo debajo de ella tengamos ficha.
                if(fila==3){
                    if (pos0_diag == 2 &&  pos1_diag == 2 && pos2_diag==0 ) {
                        colum_ataque=columna+2;
                        return colum_ataque;
                    } else if (pos0_diag == 2  && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 2) {
                        colum_ataque=columna+1;
                        return colum_ataque;
                    } else if (pos0_diag == 0 && pos1_down!=0 && pos1_diag == 2 && pos2_diag == 2) {
                        colum_ataque=columna;
                        return colum_ataque;
                    }
                }else{
                    int pos2_down=matriz[fila+3][columna+2];
                    if (pos0_diag == 2 && pos1_diag == 2 && pos2_diag==0&&pos2_down!=0 ) {
                        colum_ataque=columna+2;
                        return colum_ataque;
                    } else if (pos0_diag == 2 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag==2) {
                        colum_ataque=columna+1;
                        return colum_ataque;
                    } else if (pos0_diag == 0 &&pos0_down!=0&& pos1_diag == 2 && pos2_diag==2) {
                        colum_ataque=columna;
                        return colum_ataque;
                    }
                }
            }
        }

        return colum_ataque;
    }

//ALGORITMOS DE DEFENSA
    public static int defensaMuerte(int[][]matriz){
        int colum_defensa=-1;

        for(int fila=0;fila< matriz.length;fila++) {
            for (int columna = 0; columna < matriz[0].length; columna++) {
                if (columna < 4) {
                    int num = matriz[fila][columna];
                    int num1 = matriz[fila][columna + 1];
                    int num2 = matriz[fila][columna + 2];
                    int num3 = matriz[fila][columna + 3];
                    if(fila==5){
                        if (num == 1 && num == num1 && num == num2 && num3 == 0) {
                            colum_defensa = columna + 3;
                            return colum_defensa;
                        } else if (num == 1 && num1==1 && num2 == 0 && num3 == 1) {
                            colum_defensa = columna + 2;
                            return colum_defensa;
                        } else if (num == 1 && num1==0 && num2 == 1 && num3 == 1) {
                            colum_defensa = columna + 1;
                            return colum_defensa;
                        } else if (num == 0 && num1==1 && num2 == 1 && num3 == 1) {
                            colum_defensa = columna;
                            return colum_defensa;
                        }
                    }else{
                        int num_down=matriz[fila+1][columna];
                        int num1_down=matriz[fila+1][columna+1];
                        int num2_down=matriz[fila+1][columna+2];
                        int num3_down=matriz[fila+1][columna+3];

                        if (num == 1 && num == num1 && num == num2 && num3 == 0&&num3_down!=0) {
                            colum_defensa = columna + 3;
                            return colum_defensa;
                        } else if (num == 1 && num1==1 && num2 == 0 && num2_down!=0 && num3 == 1) {
                            colum_defensa = columna + 2;
                            return colum_defensa;
                        } else if (num == 1 && num1==0 && num1_down!=0 && num2 == 1 && num3 == 1) {
                            colum_defensa = columna + 1;
                            return colum_defensa;
                        } else if (num == 0 && num_down!=0 && num1==1 && num2 == 1 && num3 == 1) {
                            colum_defensa = columna;
                            return colum_defensa;
                        }
                    }
                }
                if(fila<3){
                    int num_v=matriz[fila][columna];
                    int num1_v=matriz[fila+1][columna];
                    int num2_v=matriz[fila+2][columna];
                    int num3_v=matriz[fila+3][columna];
                    if(num_v==0&&1==num1_v&&1==num2_v&&1==num3_v){
                        colum_defensa=columna;
                        return colum_defensa;
                    }

                }
            }
        }

        //Comprobacion Diagonales DEFENSA-MUERTE

        for(int fila = 3; fila < matriz.length; fila++){
            for(int columnas = 0; columnas < matriz[0].length - 3; columnas++){
                int pos0_diag=matriz[fila][columnas];
                int pos1_diag=matriz[fila-1][columnas+1];
                int pos1_down=matriz[fila][columnas+1];
                int pos2_diag=matriz[fila-2][columnas+2];
                int pos2_down=matriz[fila-1][columnas+1];
                int pos3_diag=matriz[fila-3][columnas+3];
                int pos3_down=matriz[fila-2][columnas+3];

                if(fila==5){
                    if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 1 && pos3_diag==0&&pos3_down!=0 ) {
                        colum_defensa=columnas+3;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==1) {
                        colum_defensa=columnas+2;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columnas+1;
                        return colum_defensa;
                    } else if (pos0_diag == 0 && pos1_diag == 1 && pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columnas;
                        return colum_defensa;
                    }
                }else{
                    int pos0_down=matriz[fila][columnas];
                    if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 1 && pos3_diag==0&&pos3_down!=0 ) {
                        colum_defensa=columnas+3;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==1) {
                        colum_defensa=columnas+2;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columnas+1;
                        return colum_defensa;
                    } else if (pos0_diag == 0 &&pos0_down!=0&& pos1_diag == 1 && pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columnas;
                        return colum_defensa;
                    }
                }
            }
        }
        //Diagonal 2
        for(int fila = 0; fila < matriz.length - 3; fila++){
            for(int columna = 0; columna < matriz[0].length - 3; columna++){
                int pos0_diag=matriz[fila][columna];
                int pos0_down=matriz[fila+1][columna];
                int pos1_diag=matriz[fila+1][columna+1];
                int pos1_down=matriz[fila+2][columna+1];
                int pos2_diag=matriz[fila+2][columna+2];
                int pos2_down=matriz[fila+3][columna+2];
                int pos3_diag=matriz[fila+3][columna+3];

                //Comprobamos que la ultima posicion sea un 0 y que justo debajo de ella tengamos ficha.
                if(fila==2){
                    if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 1 && pos3_diag==0 ) {
                        colum_defensa=columna+3;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==1) {
                        colum_defensa=columna+2;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columna+1;
                        return colum_defensa;
                    } else if (pos0_diag == 0 && pos0_down!=0 && pos1_diag == 1 && pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columna;
                        return colum_defensa;
                    }
                }else{
                    int pos3_down=matriz[fila+4][columna+3];
                    if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 1 && pos3_diag==0&&pos3_down!=0 ) {
                        colum_defensa=columna+3;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag == 0 &&pos2_down!=0&& pos3_diag==1) {
                        colum_defensa=columna+2;
                        return colum_defensa;
                    } else if (pos0_diag == 1 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columna+1;
                        return colum_defensa;
                    } else if (pos0_diag == 0 &&pos0_down!=0&& pos1_diag == 1 && pos2_diag == 1 && pos3_diag==1) {
                        colum_defensa=columna;
                        return colum_defensa;
                    }
                }
            }
        }
        return colum_defensa;
    }

    public static int defensa2(int[][]matriz){
        int colum_defensa2=-1;

        for(int fila=0;fila< matriz.length;fila++) {
            for (int columna = 0; columna < matriz[0].length; columna++) {
                if (columna < 5) {
                    int num = matriz[fila][columna];

                    int num1 = matriz[fila][columna + 1];

                    int num2 = matriz[fila][columna + 2];

                    if(fila==5) {
                        if (num == 1 && num1 == 1 && num2 == 0) {
                            colum_defensa2 = columna + 2;
                            return colum_defensa2;
                        } else if (num == 1 && num1 == 0 && num2 == 1) {
                            colum_defensa2 = columna + 1;
                            return colum_defensa2;
                        } else if (num == 0 && num1 == 1 && num2 == 1) {
                            colum_defensa2 = columna;
                            return colum_defensa2;
                        }
                    }else{
                        int num_down = matriz[fila + 1][columna];
                        int num1_down = matriz[fila + 1][columna + 1];
                        int num2_down = matriz[fila + 1][columna + 2];
                        if (num == 1 && num1 == 1 && num2 == 0 && num2_down != 0) {
                            colum_defensa2 = columna + 2;
                            return colum_defensa2;
                        } else if (num == 1 && num1 == 0 && num1_down != 0 && num2 == 1) {
                            colum_defensa2 = columna + 1;
                            return colum_defensa2;
                        } else if (num == 0 && num_down != 0 && num1 == 1 && num2 == 1) {
                            colum_defensa2 = columna;
                            return colum_defensa2;
                        }
                    }
                }
                if(fila<4){
                    int num_v=matriz[fila][columna];
                    int num1_v=matriz[fila+1][columna];
                    int num2_v=matriz[fila+2][columna];
                    //int num3_v=matriz[fila+3][columna];
                    if(num_v==0&&1==num1_v&&1==num2_v){
                        colum_defensa2=columna;
                        return colum_defensa2;
                    }

                }
            }
        }

        //Comprobacion Diagonales DEFENSA2

        for(int fila = 3; fila < matriz.length; fila++){
            for(int columnas = 0; columnas < matriz[0].length - 2; columnas++){
                int pos0_diag=matriz[fila][columnas];
                int pos1_diag=matriz[fila-1][columnas+1];
                int pos1_down=matriz[fila][columnas+1];
                int pos2_diag=matriz[fila-2][columnas+2];
                int pos2_down=matriz[fila-1][columnas+1];;

                if(fila==5){
                    if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag==0&&pos2_down!=0 ) {
                        colum_defensa2=columnas+2;
                        return colum_defensa2;
                    } else if (pos0_diag == 1 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag==1) {
                        colum_defensa2=columnas+1;
                        return colum_defensa2;
                    } else if (pos0_diag ==0&& pos1_diag == 1 && pos2_diag==1) {
                        colum_defensa2=columnas;
                        return colum_defensa2;
                    }
                }else{
                    int pos0_down=matriz[fila][columnas];
                    if (pos0_diag == 1 && pos1_diag == 1 &&pos2_diag==0&&pos2_down!=0 ) {
                        colum_defensa2=columnas+2;
                        return colum_defensa2;
                    } else if (pos0_diag == 1 &&pos1_diag == 0 &&pos1_down!=0&& pos2_diag==1) {
                        colum_defensa2=columnas+1;
                        return colum_defensa2;
                    } else if (pos0_diag == 0&&pos0_down!=0 && pos1_diag == 1 && pos2_diag == 1) {
                        colum_defensa2=columnas;
                        return colum_defensa2;
                    }
                }
            }
        }
        //Diagonal 2
        for(int fila = 0; fila < matriz.length - 2; fila++){
            for(int columna = 0; columna < matriz[0].length - 3; columna++){
                int pos0_diag=matriz[fila][columna];
                int pos0_down=matriz[fila+1][columna];
                int pos1_diag=matriz[fila+1][columna+1];
                int pos1_down=matriz[fila+2][columna+1];
                int pos2_diag=matriz[fila+2][columna+2];



                //Comprobamos que la ultima posicion sea un 0 y que justo debajo de ella tengamos ficha.
                if(fila==3){
                    if (pos0_diag == 1 &&  pos1_diag == 1 && pos2_diag==0 ) {
                        colum_defensa2=columna+2;
                        return colum_defensa2;
                    } else if (pos0_diag == 1  && pos1_diag == 0 &&pos1_down!=0&& pos2_diag == 1) {
                        colum_defensa2=columna+1;
                        return colum_defensa2;
                    } else if (pos0_diag == 0 && pos1_down!=0 && pos1_diag == 1 && pos2_diag == 1) {
                        colum_defensa2=columna;
                        return colum_defensa2;
                    }
                }else{
                    int pos2_down=matriz[fila+3][columna+2];
                    if (pos0_diag == 1 && pos1_diag == 1 && pos2_diag==0&&pos2_down!=0 ) {
                        colum_defensa2=columna+2;
                        return colum_defensa2;
                    } else if (pos0_diag == 1 && pos1_diag == 0 &&pos1_down!=0&& pos2_diag==1) {
                        colum_defensa2=columna+1;
                        return colum_defensa2;
                    } else if (pos0_diag == 0 &&pos0_down!=0&& pos1_diag == 1 && pos2_diag==1) {
                        colum_defensa2=columna;
                        return colum_defensa2;
                    }
                }
            }
        }
        return colum_defensa2;
    }



//    public static boolean columnaBase(int fila,int columna){
//        if(fila==3){
//            for(int i=0;i<6;i++){
//                for(int j=0;j<7;i++){
//
//                }
//            }
//        }
//
//    }

    public static void main (String[]args){
            Random rand=new Random();

            char[] lista_letras ={'A','B','C','D','E','F','G'};
            String continuar="";
            int columna_matar;
            int columna_defensa;
            int columna_ataque;
            int columna_defensa2;
            while(true){
                int[][] matriz = crearTablero();//new int[][]{{1,1,1,1,1,1,1},{2,2,2,2,2,2,2},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{2,2,2,2,2,2,2},{0,0,0,0,0,0,0}};
                System.out.println("========CONECTA4=======");
                System.out.println("\n");

                System.out.println("Selecciona modo de Juego: ");
                System.out.println("1 ==> Player1 VS Player2");
                System.out.println("2 ==> Player1 VS CPU");
                System.out.print("Selección=> ");

                char modo_juego=teclado.nextLine().charAt(0);

                while(modo_juego<49||modo_juego>50){
                    System.out.println("Selecciona modo de Juego: ");
                    System.out.println("1 ==> Player1 VS Player2");
                    System.out.println("2 ==> Player1 VS CPU");
                    System.out.print("Selección=> ");
                    modo_juego=teclado.nextLine().charAt(0);
                }
                //Pintamos el tablero
                pintarTablero(matriz,lista_letras);

                if(modo_juego==1) {
                    while (true) {
                        int jugador = 1;
                        insertarFicha(matriz, jugador, lista_letras);
                        if (comprobrarHorizontalVertical(matriz) || comprobarDiagonales(matriz)) {
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"Gana el jugador " + jugador+ANSI_RESET);
                            break;
                        }
                        jugador = 2;
                        insertarFicha(matriz, jugador, lista_letras);
                        if (comprobrarHorizontalVertical(matriz) || comprobarDiagonales(matriz)) {
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"Gana el jugador " + jugador+ANSI_RESET);
                            break;
                        }
                    }
                }else{
                    while (true) {

                        int jugador = 1;
                        insertarFicha(matriz, jugador, lista_letras);
                        if (comprobrarHorizontalVertical(matriz) || comprobarDiagonales(matriz)) {
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"Gana el jugador " + jugador+ANSI_RESET);
                            break;
                        }
                        if(tableroCompleto(matriz)){
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"!!!EMPATE!!!"+ANSI_RESET);
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"SE ACABO EL TABLERO"+ANSI_RESET);
                            break;
                        }
                        jugador = 2;
                        columna_matar=ataqueMatar(matriz);
                        if(columnaCompleta(matriz,columna_matar)){
                            columna_matar=-1;
                        }
                        columna_defensa=defensaMuerte(matriz);
                        if(columnaCompleta(matriz,columna_defensa)){
                            columna_defensa=-1;
                        }
                        columna_ataque=ataque2(matriz);
                        if(columnaCompleta(matriz,columna_ataque)){
                            columna_ataque=-1;
                        }
                        columna_defensa2=defensa2(matriz);
                        if(columnaCompleta(matriz,columna_defensa2)){
                            columna_defensa2=-1;
                        }
                        if (columna_matar!=-1){
                            insertarFicha_CPU(matriz,2,columna_matar);
                            if (comprobrarHorizontalVertical(matriz) || comprobarDiagonales(matriz)) {
                                System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"Gana el jugador " + jugador+ANSI_RESET);
                                break;
                            }

                        } else if (columna_matar==-1&&columna_defensa!=-1) {
                            insertarFicha_CPU(matriz,2,columna_defensa);
                            System.out.println(ANSI_WHITE_BACKGROUND+ANSI_BLACK+"CPU DEFENSA MUERTE!"+ANSI_RESET);
                        } else if (columna_matar==-1&&columna_defensa==-1&&columna_ataque!=-1) {
                            insertarFicha_CPU(matriz,2,columna_ataque);
                            System.out.println(ANSI_WHITE_BACKGROUND+ANSI_BLACK+"CPU ATAQUE NIVEL 1!"+ANSI_RESET);
                        } else if (columna_matar==-1&&columna_defensa==-1&&columna_ataque==-1&&columna_defensa2!=-1) {
                            insertarFicha_CPU(matriz,2,columna_defensa2);
                            System.out.println(ANSI_WHITE_BACKGROUND+ANSI_BLACK+"CPU DEFENSA NIVEL 1!"+ANSI_RESET);
                        }else if(columna_matar==-1&&columna_defensa==-1&&columna_ataque==-1&&columna_defensa2==-1&&!columnaCompleta(matriz,3)){
                            insertarFicha_CPU(matriz,2,3);
                            System.out.println(ANSI_WHITE_BACKGROUND+ANSI_BLACK+"CPU FICHA CENTRAL!"+ANSI_RESET);
                        }else{
                            int columna_rand= rand.nextInt(7);
                            while(columnaCompleta(matriz,columna_rand)){
                                columna_rand=rand.nextInt(7);
                                System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"CPU INTRODUCE FICHA RANDOM"+ANSI_RESET);
                            }
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"CPU INTRODUCE FICHA RANDOM"+ANSI_RESET);
                            insertarFicha_CPU(matriz,2,columna_rand);
                            if (comprobrarHorizontalVertical(matriz) || comprobarDiagonales(matriz)) {
                                System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"Gana el jugador " + jugador+ANSI_RESET);
                                break;
                            }
                        }
                        if(tableroCompleto(matriz)){
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"!!!EMPATE!!!"+ANSI_RESET);
                            System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"SE ACABO EL TABLERO"+ANSI_RESET);
                            break;
                        }
                    }
                }
                System.out.println("Pulse ENTER para continuar: ");
                teclado.nextLine();
                String continuar1= teclado.nextLine();
            }
    }
}