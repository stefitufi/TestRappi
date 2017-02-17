package stephania.com.reddit.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import stephania.com.reddit.models.App;
import stephania.com.reddit.models.Category;
import stephania.com.reddit.models.Data;
import stephania.com.reddit.models.RespuestaBasica;

/**
 * Interfaz del Api Client
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public interface IRestClientApi {

    /**
     * Obtiene los elemntos reddits
     * @return
     *      Respuesta de las categorias
     */
    @GET("/reddits.json")
    Call<RespuestaBasica<Data<Category>>> getReddits();

    /**
     * Obtiene las app segun la categoria a la que pertence
     * @param mUrl
     *          Categoria de la App
     * @return
     *      App
     */
    @GET
    Call<RespuestaBasica<Data<App>>> getApp(@Url String mUrl);
}
