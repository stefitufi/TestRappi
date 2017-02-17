package stephania.com.reddit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Clase de los datos recibidos
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class Data<T> {

    /** String modhash **/
    @SerializedName("modhash")
    private String modhash;

    /** Lista de los elementos **/
    @SerializedName("children")
    private List<RespuestaBasica<T>> elementos;

    /** String after **/
    @SerializedName("after")
    private String after;

    /** String before **/
    @SerializedName("before")
    private String before;

    /**
     *
     * @return
     */
    public String getModhash() {
        return modhash;
    }

    /**
     *
     * @param modhash
     */
    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    /**
     *
     * @return
     */
    public List<RespuestaBasica<T>> getElementos() {
        return elementos;
    }

    /**
     *
     * @param elementos
     */
    public void setElementos(List<RespuestaBasica<T>> elementos) {
        this.elementos = elementos;
    }

    /**
     *
     * @return
     */
    public String getAfter() {
        return after;
    }

    /**
     *
     * @param after
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     *
     * @return
     */
    public String getBefore() {
        return before;
    }

    /**
     *
     * @param before
     */
    public void setBefore(String before) {
        this.before = before;
    }
}
