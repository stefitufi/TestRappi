package stephania.com.reddit.services;

import android.content.Context;

import com.github.johnpersano.supertoasts.SuperToast;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import stephania.com.reddit.R;
import stephania.com.reddit.managers.IAppManager;
import stephania.com.reddit.managers.ICategoryManager;
import stephania.com.reddit.models.App;
import stephania.com.reddit.models.Category;
import stephania.com.reddit.models.Data;
import stephania.com.reddit.models.RespuestaBasica;
import stephania.com.reddit.rest.IRestClient;
import stephania.com.reddit.rest.IRestClientApi;
import stephania.com.reddit.rest.api.RestClient;
import stephania.com.reddit.utils.ConstantUtil;
import stephania.com.reddit.utils.ToastUtil;

/**
 * Clase que expone los servicios Reddit
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@Singleton
public class RedditService implements IRedditService{

    /** Variable de tipo {@link Category} **/
    private Category mCategorybd;

    /** Variable de tipo {@link App} **/
    private App mAppbd;

    /** Lista de tipo Categoria **/
    private List<Category> categories;

    /** Lista de tipo App **/
    private List <App> mAppList;

    /** Cliente rest reddit **/
    @Inject
    private IRestClient mCliente = new RestClient();

    /** Variable de tipo {@link IRestClientApi} **/
    private IRestClientApi mIRestClientApi;

    /** Context de la actividad **/
    private Context mContext;

    /** Variable de tipo Categoria manager **/
    @Inject
    private ICategoryManager mCategoryManager;

    /** Variable de tipo App manager **/
    @Inject
    private IAppManager mAppManager;

    /**
     * Constructor del servicio {@link RedditService}
     * @param context
     * @param categoriesManager
     * @param appsManager
     */
    @Inject
    public RedditService(Context context, ICategoryManager categoriesManager,
                         IAppManager appsManager) {
        this.mContext = context;
        this.mCategoryManager = categoriesManager;
        this.mAppManager = appsManager;
    }

    @Override
    public App getAppSaved() {
        return mAppbd;
    }

    @Override
    public Category getCategories() {
        return mCategorybd;
    }

    /**
     * This method gets all the stored categories
     *
     * @return All stored categories
     */
    @Override
    public List<Category> getAllCategories() {
        return mCategoryManager.all();
    }

    /**
     * This method gets the stored apps by id
     *
     * @return  stored apps
     */
    @Override
    public List<App> getAppsByCatId(Object value) {
        return mAppManager.findByAttr("idCategory", value);
    }

    /**
     * This method gets all the stored apps
     *
     * @return All stored apps
     */
    @Override
    public List<App> getAllApps() {

        return mAppManager.all();
    }

    @Override
    public void saveCategory(Category categorySaved) {
        this.mCategorybd = categorySaved;
    }

    /**
     * Este mtodo la infromacion  de las categorias
     *
     * @return categorias
     */
    @Override
    public List<Category> obtenerCategory() {
        mIRestClientApi = getRestClientApi();
        Call<RespuestaBasica<Data<Category>>> call = mIRestClientApi.getReddits();
        Response<RespuestaBasica<Data<Category>>> response = mCliente.execute(call);

        if (!isSuccessful(response)) {
            List<Category> categoriesPersisted = getAllCategories();
            return categoriesPersisted;
        }
        if (response.body() == null) {
            ToastUtil.crearToast(mContext, mContext.getString(R.string.not_connection),
                    SuperToast.Duration.MEDIUM, ToastUtil.TipoNotificacion.ALERTA).show();
        }else {
            categories = new ArrayList<>();
            List<RespuestaBasica<Category>> data = response.body().getData().getElementos();

            for (RespuestaBasica<Category> rc : data) {
                categories.add(rc.getData());
            }
        }
        if (getAllCategories().isEmpty()) {
            for (Category rc : categories) {
                mCategoryManager.deleteAll();
                mAppManager.deleteAll();
                createOrUpdateCategory(rc);
            }
        }
        return categories;
    }

    /**
     * Este metodo carga infromacion de las apps
     *
     * @param url informacion del app segun la catgoria pre-seleccionada
     * @return apps
     */
    @Override
    public List<App> obtenerApp(String url) {
        mIRestClientApi = getRestClientApi();
        Call<RespuestaBasica<Data<App>>> call = mIRestClientApi.getApp(url+ConstantUtil.JSON);
        Response<RespuestaBasica<Data<App>>> response = mCliente.execute(call);

        if (!isSuccessful(response)) {
            List<App> appsPersisted= getAllApps();
            return appsPersisted;
        }

        if(response.body() == null){
            ToastUtil.crearToast(mContext, mContext.getString(R.string.not_connection),
                    SuperToast.Duration.MEDIUM, ToastUtil.TipoNotificacion.ALERTA).show();
        }else{
            mAppList = new ArrayList<>();
            List<RespuestaBasica<App>> data = response.body().getData().getElementos();
            for (RespuestaBasica<App> rc : data) {
                mAppList.add(rc.getData());
            }
        }

        if (getAllApps().isEmpty()) {
            for (App rc : mAppList) {
                createOrUpdateApp(rc);
            }
        }


        return mAppList;
    }

    /**
     * Obtiene el API de las visitas del cliente
     *
     * @return La interfaz del API
     */
    private IRestClientApi getRestClientApi() {
        if (mIRestClientApi == null) {
            mIRestClientApi = mCliente.getApi(IRestClientApi.class);
        }
        return mIRestClientApi;
    }

    @Override
    public void saveApp(App appSave) {
        this.mAppbd = appSave;
    }

    /**
     * Metodo que crea o actualiza la informacion de las categorias
     * @param category
     */
    @Override
    public void createOrUpdateCategory(Category category) {
        mCategoryManager.createOrUpdate(category);
    }

    /**
     * Metodo que crea o actuliza la informacion de una aplicacion
     * @param redditApp
     */
    @Override
    public void createOrUpdateApp(App redditApp) {
        mAppManager.createOrUpdate(redditApp);
    }

    /**
     * Este metodo verifica si la respuesta fue exitosa o no
     *
     * @param response La respuesta para ser verificada
     * @return True si fue exitoso. False de lo contrario.
     */
    public static boolean isSuccessful(Response response) {
        return response != null && response.isSuccessful();
    }
}
