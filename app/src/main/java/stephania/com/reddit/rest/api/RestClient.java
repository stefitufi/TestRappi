package stephania.com.reddit.rest.api;

import android.util.Log;

import com.google.inject.Singleton;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import stephania.com.reddit.rest.IRestClient;
import stephania.com.reddit.utils.ConstantUtil;

/**
 * Clase que realiza peticion al servidor
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@Singleton
public class RestClient implements IRestClient {

    /** Tag for logs **/
    private static final String TAG_LOG = RestClient.class.getName();

    /** Constuctor de la clase **/
    public RestClient() {}

    /** Cliente retrofit **/
    private Retrofit mRetrofit;

    @Override
    public <T> T getApi(Class<T> clazz) {
        synchronized (RestClient.class) {
            if (mRetrofit == null) {

                OkHttpClient okClient = new OkHttpClient();
                okClient.interceptors().add(new Interceptor() {
                    @Override
                    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                        com.squareup.okhttp.Response response = chain.proceed(chain.request());
                        return response;
                    }
                });

                mRetrofit = new Retrofit.Builder()
                        .baseUrl(ConstantUtil.URLBASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return mRetrofit.create(clazz);
        }
    }

    @Override
    public <T> Response<T> execute(Call<T> call) {
        Response<T> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            Log.e(TAG_LOG, "An error occurs talking to the server", e);
        } catch (RuntimeException e) {
            Log.e(TAG_LOG, "unexpected error occurs creating the request or decoding the response",
                    e);
        }
        return response;
    }

}
