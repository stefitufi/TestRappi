package stephania.com.reddit.rest;

import retrofit2.Call;

/**
 * Interface ofrece los métodos básicos utilizados para la comunicación con el Web Service
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public interface IRestClient {

    /**
     * This method returns the API of the selected class
     *
     * @param clazz Class of the desired API
     * @param <T>   API class
     * @return the instance API
     */
    <T> T getApi(Class<T> clazz);

    /**
     * This method executes a call to the server and returns the result. If an error occurs, then
     * null is returned
     *
     * @param <T>  Response type
     * @param call Call to be executed
     * @return The response. Returns null if an error occurs
     */
    <T> retrofit2.Response<T> execute(Call<T> call);
}
