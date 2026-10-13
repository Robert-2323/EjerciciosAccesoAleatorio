import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej2 {
    private static final String RUTA_DIR="archivos/";
    private static final String RUTA_FILE="enteros.dat";
    public static void main(String[] args) {
        /*Escribe un programa en java que solicite un número entre 0 y 9 y lo añada una cantidad
            de veces determinada (por teclado) al final del archivo del ejercicio 1.*/

        Scanner sc=new Scanner(System.in);
        System.out.print("Introduce un numero del 0 al 9: ");
        int num=sc.nextInt();
        System.out.println("Introduce las veces que quieres añadir el numero");
        int veces=sc.nextInt();
        try(RandomAccessFile puntero=new RandomAccessFile(RUTA_DIR+RUTA_FILE,"rw")){
            puntero.seek(puntero.length());
            for(int i=0;i<veces;i++){
                puntero.writeInt(num);
            }
            puntero.seek(0);
            while(puntero.getFilePointer()<puntero.length()){
                System.out.println(puntero.readInt());
            }
        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }
}
