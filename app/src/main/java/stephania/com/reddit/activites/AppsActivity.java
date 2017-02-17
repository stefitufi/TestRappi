package stephania.com.reddit.activites;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import stephania.com.reddit.R;
import stephania.com.reddit.models.App;
import stephania.com.reddit.models.Category;
import stephania.com.reddit.services.IRedditService;
import stephania.com.reddit.utils.AsyncTaskUtil;
import stephania.com.reddit.utils.ConnectionUtil;
import stephania.com.reddit.utils.StringUtil;
import stephania.com.reddit.utils.ToastUtil;
import stephania.com.reddit.views.adapter.AppsAdapter;

/**
 * Actividad que lista las aplicaciones
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@ContentView(R.layout.activity_apps)
public class AppsActivity extends RoboActionBarActivity implements
        SwipeRefreshLayout.OnRefreshListener {

    /** Variable de tipo Toolbar **/
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /** TextView que contiene el titulo de la actividad **/
    @InjectView(R.id.toolbar_title_tv)
    private TextView mToolbarTitleTv;

    /** TextView que contiene titulo del app**/
    @InjectView(R.id.title_tv)
    private TextView mTitleAppTv;

    /** TextView que contiene el nombre de la categoria**/
    @InjectView(R.id.name_tv)
    private TextView mNmeCategoryTv;

    /** ImagenView que contiene la imagen**/
    @InjectView(R.id.category_iv)
    private ImageView mImagenAppIv;

    /** Variable tipo SwipeRefreshLayout **/
    @InjectView(R.id.refresh_layout)
    private SwipeRefreshLayout mRefreshL;

    /** RecyclerView **/
    @InjectView(R.id.content_app_rv)
    private RecyclerView mListRv;

    /** Lista de tipo {@link App} **/
    private List<App> mListApp;

    /** Variable de tipo {@link App} **/
    private App mApp;

    /** Variable de tipo {@link AppsAdapter} **/
    private AppsAdapter mAdapter;

    /**
     * Variable de tipo {@link IRedditService}
     **/
    @Inject
    private IRedditService mRedditService;

    /**
     * Variable de tipo {@link Category}
     */
    public static Category mCategory;

    /** Llave que carga la informacion del item seleccioando **/
    public static final String ITEM = "ITEM";

    /** Tag para logs **/
    private static final String TAG_LOG = AppsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(this);

        //Recoje putExtra del objecto enviado Category
        mCategory = (Category) getIntent().getSerializableExtra(ITEM);

        //LLamada al metodo loadInfo
        loadInfo();

        setSupportActionBar(mToolbar);
        mToolbarTitleTv.setText(StringUtil.capitalize(mCategory.getDisplayName()));

        mListApp = new ArrayList<App>();

        mApp = new App();

        mRefreshL.setOnRefreshListener(this);

        // Configure the refreshing colors
        mRefreshL.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //LLamada la clase del AsynTasck
        new LoadAsyncTask().execute();
    }

    /**
     * Metodo que carga informacion de cabecera
     */
    private void loadInfo(){
        mTitleAppTv.setText(StringUtil.capitalize(mCategory.getDisplayName()));
        mNmeCategoryTv.setText(mCategory.getAdvertiserCategory());
        Picasso.with(this).load(mCategory.getHeaderImg()).resize(100,100).into(mImagenAppIv);
    }

    /**
     * Método que refresca la lista de categorías
     */
    @Override
    public void onRefresh() {
        ConnectionUtil.getConnectivityStatusString(this);
        new LoadAsyncTask().execute();
    }

    /**
     * Esta clase realiza la carga inicial de manera asíncrona
     */
    private class LoadAsyncTask extends AsyncTaskUtil<Void, Void, Void> {

        /**
         * Constructor para el progress async task
         */
        public LoadAsyncTask() {
            super(AppsActivity.this);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mListApp = mRedditService.obtenerApp(mCategory.getUrl());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (mApp == null) {
                ToastUtil.crearToast(AppsActivity.this,
                        getString(R.string.not_connection),
                        SuperToast.Duration.MEDIUM,
                        ToastUtil.TipoNotificacion.ALERTA).show();
                super.onPostExecute(aVoid);
                return;
            }
            mAdapter =  new AppsAdapter(AppsActivity.this, mListApp, mRedditService);
            LinearLayoutManager llm = new LinearLayoutManager(AppsActivity.this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            mListRv.setLayoutManager(llm);
            mListRv.setAdapter(mAdapter);
            mRefreshL.setRefreshing(false);

            super.onPostExecute(aVoid);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectionUtil.getConnectivityStatusString(this);
    }
}
