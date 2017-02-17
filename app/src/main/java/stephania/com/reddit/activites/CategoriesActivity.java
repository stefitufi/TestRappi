package stephania.com.reddit.activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import stephania.com.reddit.R;
import stephania.com.reddit.models.Category;
import stephania.com.reddit.services.IRedditService;
import stephania.com.reddit.utils.AnimacionUtil;
import stephania.com.reddit.utils.AsyncTaskUtil;
import stephania.com.reddit.utils.ConnectionUtil;
import stephania.com.reddit.utils.ToastUtil;
import stephania.com.reddit.views.adapter.CategoriesAdapter;

/**
 * Actividad Categorias
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@ContentView(R.layout.activity_categories)
public class CategoriesActivity extends RoboActionBarActivity implements
        SwipeRefreshLayout.OnRefreshListener {

    /** Variable de tipo Toolbar **/
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /** Layout que contiene el toolbar **/
    @InjectView(R.id.toolbar)
    private View mContentLl;

    /** TextView que contiene el titulo de la actividad **/
    @InjectView(R.id.toolbar_title_tv)
    private TextView mToolbarTitleTv;

    /** ImagenView que contiene el logo del toolbar **/
    @InjectView(R.id.logo_toolbar_iv)
    private ImageView mLogoIv;

    /** **/
    @InjectView(R.id.swipe_srl)
    private SwipeRefreshLayout mRefreshL;

    @InjectView(R.id.category_dlv)
    private DynamicListView mCategoryDlv;

    /** **/
    @InjectView(R.id.category_rv)
    private RecyclerView mCategoryRv;

    /** Variable de tipo {@link Category} **/
    private List<Category> mCategories;

    /** Variable de tipo {@link Category} **/
    private Category mCategory;

    /** Variable de tipo {@link CategoriesAdapter} **/
    private CategoriesAdapter mAdapter;

    /**
     * Variable de tipo {@link IRedditService}
     **/
    @Inject
    private IRedditService mRedditService;

    /** Entero que indica codigo respuesta */
    private static final int COD_RESPUESTA = 1;

    /** Llave para enviar informacion del item seleccioando **/
    public static final String ITEM = "ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbarTitleTv.setText(R.string.title_activity_categories);

        mCategories = new ArrayList<Category>();

        mCategory = new Category();

        // Metodo que realiza la animacion de la vista
        AnimacionUtil.ajustarTransiciones(CategoriesActivity.this,mLogoIv, mContentLl);

        mRefreshL.setOnRefreshListener(this);

        // Configure the refreshing colors
        mRefreshL.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //Metodo que invoca el AsyncTask
        new LoadAsyncTask().execute();

        //Evento de la lista de funcionarios
        mCategoryDlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentActivity = new Intent(CategoriesActivity.this, AppsActivity.class);
                mCategory = mCategories.get(position);
                intentActivity.putExtra(ITEM, mCategory);

                //Implementacion de transicion al pasar al actividad de registro
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String>[] t = new Pair[2];
                    t[0] = Pair.create(view, getString(R.string.transition_background));
                    t[1] = Pair.create(findViewById(R.id.content_tiem), getString(R.string.toolbar));

                    Bundle b = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(CategoriesActivity.this, t).toBundle();
                    startActivityForResult(intentActivity, COD_RESPUESTA, b);
                } else {
                    startActivity(intentActivity);
                }
            }
        });
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
            super(CategoriesActivity.this);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mCategories = mRedditService.obtenerCategory();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (mCategories == null) {
                ToastUtil.crearToast(CategoriesActivity.this,
                        getString(R.string.not_connection), SuperToast.Duration.MEDIUM,
                        ToastUtil.TipoNotificacion.ALERTA).show();
                super.onPostExecute(aVoid);
                return;
            }
            mAdapter =  new CategoriesAdapter(CategoriesActivity.this, mCategories, mRedditService);
            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(mAdapter);
            animationAdapter.setAbsListView(mCategoryDlv);
            mCategoryDlv.setAdapter(animationAdapter);
            mCategoryDlv.getListPaddingLeft();
            mRefreshL.setRefreshing(false);

            super.onPostExecute(aVoid);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectionUtil.getConnectivityStatusString(this);
    }


    /** Metodo que devuelve a la actividad {@link CategoriesActivity} **/
    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }
}
