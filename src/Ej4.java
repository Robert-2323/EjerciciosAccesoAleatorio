import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej4 {

    private static final String RUTA_DIR="archivos/";
    private static final String RUTA_FILE="juegos.dat";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try(RandomAccessFile puntero=new RandomAccessFile(RUTA_DIR+RUTA_FILE,"r")){
            puntero.seek(0);
            System.out.print("Introduzca una edad minima para buscar juegos disponibles: ");
            int filtroEdad=sc.nextInt();
            while(puntero.getFilePointer()<puntero.length()){
                if(puntero.readInt()==filtroEdad){
                    puntero.seek(puntero.getFilePointer()-28);//-28 porque son los bytes que hay hasta la edad minima que es por la que estamos filtrando
                    System.out.println("ID: "+puntero.readInt());
                    System.out.print("Nombre: ");
                    for (int i=0;i<10;i++){
                        System.out.print(puntero.readChar());
                    }
                    System.out.println();
                    System.out.println("Edad Minima: "+puntero.readInt());
                    System.out.print("Clasificacion: ");
                    for (int i=0;i<10;i++){
                        System.out.print(puntero.readChar());
                    }
                    System.out.println();
                }
            }
        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }
}
