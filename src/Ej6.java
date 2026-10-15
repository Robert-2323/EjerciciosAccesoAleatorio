import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Ej6 {

    /*Escribe un programa en java que, utilizando los datos del fichero creado en el ejercicio
    anterior, cree un fichero de acceso aleatorio llamado “precio.dat” en el que calcule la
    cantidad que debe pagar cada alumno por las clases de prácticas recibidas. En este
    fichero se almacenará el nombre del alumno, su DNI, el número de horas (leídos en
    “practicas.dat”) y la cantidad a pagar, teniendo en cuenta que el precio por clase de
    prácticas es de 45`7€.*/

    private static final String RUTA_DIR="archivos/";
    private static final String RUTA_FILE1="practicas.dat";
    private static final String RUTA_FILE2="precio.dat";

    public static void main(String[] args) {
        File precio=new File("archivos/precio.dat");

        try(RandomAccessFile lector=new RandomAccessFile(RUTA_DIR+RUTA_FILE1,"r");
            RandomAccessFile escritor=new RandomAccessFile(RUTA_DIR+RUTA_FILE2,"rw")){

            if(precio.createNewFile()){
                System.out.println("El archivo se creo correctamente");
            }
            else{
                System.out.println("El archivo ya exsiste");
            }

            //Recuperamos los valores del archivo practicas.dat y los escribimos a la vez en el nuevo fichero
            char[] nombre=new char[10];
            char[] DNI=new char[9];

            lector.seek(0);
            while(lector.getFilePointer()<lector.length()){
                for (int i=0;i<10;i++){
                    nombre[i]=lector.readChar();
                }
                String nom=new String(nombre);
                escritor.writeChars(nom);

                lector.seek(lector.getFilePointer()+70);
                for (int i=0;i<9;i++){
                    DNI[i]=lector.readChar();
                }
               String dni=new String(DNI);
                escritor.writeChars(dni);

                lector.seek(lector.getFilePointer()+4);
                int horas=lector.readInt();
                double price=horas*45.7;
                escritor.writeInt(horas);
                escritor.writeDouble(price);
            }

            //leemos el nuevo archivo

            escritor.seek(0);
            while(escritor.getFilePointer()<escritor.length()){
                System.out.print("Nombre: ");
                for (int i=0;i<10;i++){
                    System.out.print(escritor.readChar());
                }
                System.out.println();
                System.out.print("DNI: ");
                for (int i=0;i<9;i++){
                    System.out.print(escritor.readChar());
                }
                System.out.println();
                System.out.println("Numero de horas: "+escritor.readInt());
                System.out.println("Precio: "+escritor.readDouble());

            }


        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }
}
