import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class operacionesFichero {
    public static int parametroN;                       // Entero para guardar el parámetro N
    public static Integer[] vector = null;              // Vector de enteros para guardar el vector
    public static int error;                            // Entero para guardar el problema que se produce
    public static final int FILA1 = 1;                  // Constante para indicar que hay un error en la fila 1
    public static final int FILA2 = 2;                  // Constante para indicar que hay un error en la fila 2
    public static final int NO_COINCIDEN = 3;           // Constante para indicar que el parámetro N y la talla del vector no coinciden 
    
    /*
     * Método que comprueba si el string que se le pasa es un número
     */
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    /*
     * Método que comprueba si el fichero de entrada es correcto
     */
    public Boolean comprobarFicheroEntrada(File ficheroEntrada){
        FileReader fr = null;
        BufferedReader br = null;
        File archivo = null;
        int numeroLinea = 1;
        
        try{
            archivo = ficheroEntrada;
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
        
            String linea;
            // While que va fila por fila comprobando si es correcto lo que estamos encontrando
            while((linea=br.readLine())!=null){   
                if(numeroLinea==1){
                    if (!isNumeric(linea)){
                        error = FILA1;
                        return false;
                    } else {
                        parametroN  =  Integer.parseInt(linea);
                    }
                }
                
                if(numeroLinea==2){
                    String[] aux = linea.split(" ");
                    vector = new Integer[aux.length];
                    if(parametroN != vector.length) {
                        error = NO_COINCIDEN;
                        return false;
                    } else {
                        for(int i = 0; i < aux.length; i++){
                            if(isNumeric(aux[i])){
                                vector[i] = Integer.parseInt(aux[i]);
                            } else {
                                error = FILA2;
                                return false;
                            }                  
                        } 
                    }           
                }
            
                numeroLinea++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null != fr){
                    fr.close();
                }
            }catch (Exception e2){
                    e2.printStackTrace();
            }  
        }
        return true;
    }
}
