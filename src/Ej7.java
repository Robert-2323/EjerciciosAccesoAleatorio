import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej7 {
    /*Crea un programa en java que lea el fichero “precio.dat” creado en el ejercicio anterior,
    y realice un descuento en el precio a pagar. La cantidad resultante se guardará en un
    fichero aleatorio llamado “descuento.dat” que contendrá el DNI, la cantidad anterior y
    la cantidad con el descuento realizado. El % de descuento aplicado, se solicitará por
    teclado. Después, el fichero se mostrará por pantalla (cada alumno en una línea).*/

    private static final String RUTA_DIR="archivos/";
    private static final String RUTA_FILE1="precio.dat";
    private static final String RUTA_FILE2="descuento.dat";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        try(RandomAccessFile lector=new RandomAccessFile(RUTA_DIR+RUTA_FILE1,"r");
            RandomAccessFile escritor=new RandomAccessFile(RUTA_DIR+RUTA_FILE2,"rw")){


            char[] DNI=new char[9];
            System.out.println("¿Que descuento desea realizar?");
            int des=sc.nextInt();
            double descuento= (double) des /100;
            lector.seek(0);
            while(lector.getFilePointer()<lector.length()){
                lector.seek(lector.getFilePointer()+20);//nombre
                for (int i=0;i<9;i++){
                    DNI[i]=lector.readChar();
                }
                String dni=new String(DNI);
                escritor.writeChars(dni);

                lector.seek(lector.getFilePointer()+4);//horas

                double precio=lector.readDouble();
                escritor.writeDouble(precio);;
                double rebaja=precio-(precio*descuento);
                escritor.writeDouble(rebaja);
            }

            escritor.seek(0);
            while(escritor.getFilePointer()<escritor.length()){
                System.out.print("DNI: ");
                for (int i=0;i<9;i++){
                    System.out.print(escritor.readChar());
                }
                System.out.println();
                System.out.println("Precio: "+escritor.readDouble());
                System.out.println("Descuento: "+escritor.readDouble());

            }

        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }

    }
}
