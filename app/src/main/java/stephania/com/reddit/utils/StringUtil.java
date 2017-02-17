package stephania.com.reddit.utils;

/**
 * Utilidades String
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class StringUtil {

    /**
     * Metodo que convierte el primer caracter en mayuscula
     * @param word
     *          Palabra a acfectar
     * @return
     *          Palabra con primera letra en mayuscula
     */
    public static String capitalize(String word){
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }
}
