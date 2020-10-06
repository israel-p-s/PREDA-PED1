import java.util.Arrays;

public class mayoritario {

    public String traza = "";                       // String para ir guardando la traza
    
    public mayoritario() {
    }
    
    /*
     * Método que hace la llamada inicial para partir el problema inicial en subproblemas
     */
    public int mayoritario(int i, int j, Integer[] v){
        int m, s1, s2;
        if(i == j){
            return v[i];
        } else {
            m = (i + j) / 2;
            s1 = mayoritario(i, m, v);
            s2 = mayoritario(m + 1, j, v);
            traza += "\nDividimos el problema de la posición " + (i) + " a " + (m) + " y de la posición " + (m+1) + " a " + (j) + ".\n";
            return combinar(s1, s2, v);
        }
    }
    
    /*
     * Método que combina todas las soluciones parciales
     */
    public int combinar(int a, int b, Integer[] v) {
        if(a == -1 && b == -1) {
            return -1;
        } else if(a == -1 && b != -1) {
            return comprobarMayoritario(b,v);
        } else if(a != -1 && b == -1) {
            return comprobarMayoritario(a,v);
        } else if(a != -1 && b != -1) {
            if(comprobarMayoritario(a, v) == a) {
                return a;
            } else if(comprobarMayoritario(b, v) == b) {
                return b;
            }
        }
        return -1;
    }
    
    /*
     * Método que comprueba si para un entero este es mayoritario en un vector
     */
    public int comprobarMayoritario(int x, Integer[] v){
        int c = 0;
        for (int i = 0; i < v.length; i++){
            if(v[i] == x) {
                c++;
            }
        }
        if(c > v.length/2){
            traza += "\nTras comprobar si " + x + " es mayoritario, vemos que aparece " + c + " veces (mayor que " + v.length/2 + "). ÉS SOLUCIÓN \n";
            return x;
        } else {
            traza += "\nTras comprobar si " + x + " es mayoritario, vemos que aparece " + c + " veces (menor o igual que " + v.length/2 + "). NO ÉS SOLUCIÓN \n";
            return -1;
        }
    }
}
