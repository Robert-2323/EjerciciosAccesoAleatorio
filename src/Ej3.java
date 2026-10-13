import java.io.IOException;
import java.io.RandomAccessFile;

public class Ej3 {
    private static final String RUTA_DIR="archivos/";
    private static final String RUTA_FILE="juegos.dat";

    public static void main(String[] args) {
        /*Escribe un programa en java que añada en un fichero aleatorio llamado “juegos.dat”,
        una serie de registros formados por un número de orden (entero), el nombre de un
        juego (10 caracteres), la edad mínima recomendada (entero) y la clasificación del juego
        según su temática (10 caracteres). Después que muestre el fichero (cada registro en una
        línea).*/

        int[] id={1,2,3,4};
        String [] nombre={"Doom","Undertale","GTA","Terraria"};
        int[] edadMin={18,16,18,12};
        String[] clasificacion={"Shooter","Historia","AAA","Explorar"};
        int registro=48;

        try(RandomAccessFile puntero=new RandomAccessFile(RUTA_DIR+RUTA_FILE,"rw")){
            for(int i=0;i<id.length;i++){
                puntero.writeInt(id[i]);
                puntero.writeChars(nombre[i]+" ".repeat(10- nombre[i].length()));
                puntero.writeInt(edadMin[i]);
                puntero.writeChars(clasificacion[i]+" ".repeat(10-clasificacion[i].length()));
            }
            puntero.seek(0);
            while(puntero.getFilePointer()<puntero.length()){
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
        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }
}
