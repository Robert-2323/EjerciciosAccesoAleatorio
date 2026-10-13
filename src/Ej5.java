/*Escribe un programa en java para guardar en un fichero de acceso aleatorio llamado
“practicas.dat” los datos de los alumnos de una autoescuela. La cantidad de alumnos de
lo que se desea guardar los datos, se solicita por teclado. De cada alumno se guarda:
nombre (10 caracteres), apellidos (35 caracteres), DNI (8 dígitos y una letra), vehículo
de prácticas (entero de dos dígitos), número de clases de prácticas (entero de dos
dígitos). Una vez añadido los datos de los alumnos, que muestre por pantalla el
contenido del fichero (una línea en por alumno).*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Ej5 {
    private static final String RUTA_DIR="archivos/";
    private static final String RUTA_FILE="practicas.dat";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //Añadir alumnos
        ArrayList<String> nombres=new ArrayList<>();//10 char
        ArrayList<String> apellidos=new ArrayList<>();//35 char
        ArrayList<String> DNI=new ArrayList<>();//9 char
        ArrayList<Integer> vehiculoPracticas=new ArrayList<>();//dos digitos
        ArrayList<Integer> numClases=new ArrayList<>();//dos digitos
        //numero de bytes=107

        System.out.print("Cuantos alumnos quiere añadir??: ");
        int numAlumnos=sc.nextInt();
        for(int i=0;i<numAlumnos;i++){
            System.out.print("Introduzca el nombre: ");
            String nom=sc.next();
            System.out.print("Introduzca los apellidos: ");
            String ape=sc.nextLine();
            System.out.print("Introduzca su DNI: ");
            String dni=sc.next();
            int numV=sc.nextInt();
            int numC=sc.nextInt();
            nombres.add(nom);
            apellidos.add(ape);
            DNI.add(dni);
            vehiculoPracticas.add(numV);
            numClases.add(numC);
        }
        
        try(RandomAccessFile puntero=new RandomAccessFile(RUTA_DIR+RUTA_FILE,"rw")){
            for(int i=0;i< nombres.size();i++){
                puntero.writeChars(nombres.get(i)+" ".repeat(10-nombres.get(i).length()));
                puntero.writeChars(apellidos.get(i)+" ".repeat(35-apellidos.get(i).length()));
                puntero.writeChars(DNI.get(i));
                puntero.writeInt(vehiculoPracticas.get(i));
                puntero.writeInt(numClases.get(i));
            }
            puntero.seek(0);
            while(puntero.getFilePointer()<puntero.length()){
                System.out.print("Nombre: ");
                for (int i=0;i<10;i++){
                    System.out.print(puntero.readChar());
                }
                System.out.println();
                System.out.print("Apellidos: ");
                for (int i=0;i<35;i++){
                    System.out.print(puntero.readChar());
                }
                System.out.println();
                System.out.print("DNI: ");
                for (int i=0;i<9;i++){
                    System.out.print(puntero.readChar());
                }
                System.out.println("Numero de vehiculo: "+puntero.readInt());
                System.out.println("Numero de clases: "+puntero.readInt());


            }
        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }
}
