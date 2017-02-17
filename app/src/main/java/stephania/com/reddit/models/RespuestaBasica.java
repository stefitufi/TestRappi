package stephania.com.reddit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Esta es la clase utilizada para recibir las respuestas de las apps
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class RespuestaBasica<T> implements Serializable {

    /** Elemento kind **/
    @SerializedName("kind")
    private String kind;

    /** List Data **/
    @SerializedName("data")
    private T data;

    /**
     *
     * @return
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }
}
