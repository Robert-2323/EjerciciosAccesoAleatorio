import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EJ1 {

    private static final String RUTA_DIR="archivos/";
    private static final String RUTA_FILE="enteros.dat";
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        /*Escribe un programa en java que escriba 6 veces el valor 1 en un archivo llamado
            “enteros.dat”, y después 10 veces el valor 2. Muestre por pantalla el contenido del
            archivo, solicite un número entre 0 y 9, sustituya todos los 2 por el valor obtenido por
            teclado y muestre el resultado.*/
        try(RandomAccessFile puntero=new RandomAccessFile(RUTA_DIR+RUTA_FILE,"rw")){
            int contador=0;
            for(int i=0;i<15;i++){
                if(contador<6){
                    puntero.writeInt(1);
                    contador++;
                }
                if (contador>5){
                    puntero.writeInt(2);
                    contador++;
                }
            }
            puntero.seek(0);
            while(puntero.getFilePointer()<puntero.length()){
                System.out.println(puntero.readInt());
            }
            System.out.println("Selecciona un numero entre el 0 y el 9");
            int num=sc.nextInt();
            puntero.seek(0);
            while(puntero.getFilePointer()<puntero.length()){
                if(puntero.readInt()==2){
                    puntero.seek(puntero.getFilePointer()-4);
                    puntero.writeInt(num);
                }
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
