import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /* Crear un fichero de acceso aleatorio y almacena varios registros de empleados.
        Todos los registros ocupan 36 bytes, por lo que posteriormente podrán localizarse
        directamente mediante su posición.*/

        File fichero=new File("datosEmpleados.dat");

       try{
           if(fichero.createNewFile()){
               System.out.println("El fichero se ha creado correctamente");
           }else {
               System.out.println("El fichero ya existe o no se ha podido crear correctamente");
           }
       }catch (IOException e){
           System.out.println("ERROR: "+e);
       }
       int[] id={1,2,3,4};
       String[] apellidos={"Fernandez","Lopez","Garcia","Sanchez"};
       int [] dept={10,20,10,30};
       double[]salario={1800.50,2100.78,1950.55,2300.25};

       try(RandomAccessFile rFichero=new RandomAccessFile("datosEmpleados.dat","rw")){
           for(int i=0;i<id.length;i++){
                rFichero.writeInt(id[i]);
                rFichero.writeUTF(apellidos[i]);
                rFichero.writeInt(dept[i]);
                rFichero.writeDouble(salario[i]);
           }
           rFichero.seek(0);

           while(rFichero.getFilePointer()<rFichero.length()){
               System.out.println(rFichero.readInt());
               System.out.println(rFichero.readUTF());
               System.out.println(rFichero.readInt());
               System.out.println(rFichero.readDouble());
           }
       }catch (IOException e){
           System.out.println("ERROR: "+e);
       }

    }
}