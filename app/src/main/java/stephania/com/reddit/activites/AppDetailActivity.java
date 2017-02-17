package stephania.com.reddit.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import stephania.com.reddit.R;
import stephania.com.reddit.models.App;
import stephania.com.reddit.services.IRedditService;
import stephania.com.reddit.utils.ConnectionUtil;
import stephania.com.reddit.utils.StringUtil;

/**
 * Actividad que muestra detalle de la aplicacion
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@ContentView(R.layout.activity_app_detail)
public class AppDetailActivity extends RoboActionBarActivity {

    /** Variable de tipo Toolbar **/
    @Nullable
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /** TextView que contiene el titulo de la actividad **/
    @InjectView(R.id.toolbar_title_tv)
    private TextView mToolbarTitleTv;

    /** TextView que contiene el titulo **/
    @InjectView(R.id.title_tv)
    private TextView mTitle;

    /** TextView que contiene el titulo largo  **/
    @InjectView(R.id.description_tv)
    private TextView mDescriptionTv;

    /** TextView que contiene el puntaje **/
    @InjectView(R.id.score_tv)
    private TextView mScoreTv;

    /** TextView que contiene nombre del author titulo **/
    @InjectView(R.id.author_tv)
    private TextView mAuthorTv;

    /** TextView que contiene cantidad de comentarios **/
    @InjectView(R.id.num_comments_tv)
    private TextView mCommnetTv;

    /** TextView que contiene la imagen **/
    @InjectView(R.id.image_iv)
    private ImageView mImageIv;

    /** Variable de tipo {@link App} **/
    private App mApp;

    /**
     * Variable de tipo {@link IRedditService}
     **/
    @Inject
    private IRedditService mRedditService;

    /** Llave que carga la informacion del item seleccioando **/
    public static final String ITEM = "ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(this);

        //Recoje putExtra del objecto enviado Category
        mApp = (App) getIntent().getSerializableExtra(ITEM);
        setSupportActionBar(mToolbar);

        //Llame el metodo que carga la informacion
        loadInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectionUtil.getConnectivityStatusString(this);
    }

    /**
     * Metodo que carga detalle de la informacion
     */
    private void loadInfo(){
        mToolbarTitleTv=(TextView)findViewById(R.id.toolbar_title_tv);
        mToolbarTitleTv.setText(StringUtil.capitalize(mRedditService.getAppSaved().getSubreddit()));
        mTitle=(TextView)findViewById(R.id.title_tv) ;
        mTitle.setText(StringUtil.capitalize(mApp.getSubreddit()));
        mDescriptionTv=(TextView)findViewById(R.id.description_tv);
        mDescriptionTv.setText(StringUtil.capitalize(mApp.getTitle()));
        mScoreTv=(TextView)findViewById(R.id.score_tv);
        mScoreTv.setText(mApp.getScore());
        mAuthorTv=(TextView)findViewById(R.id.author_tv);
        mAuthorTv.setText(StringUtil.capitalize(mApp.getAuthor()));
        mCommnetTv=(TextView)findViewById(R.id.num_comments_tv);
        mCommnetTv.setText(mApp.getNumComments());
        mImageIv=(ImageView)findViewById(R.id.image_iv);
        if(mApp.getThumbnail().isEmpty()){
            mImageIv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        }else {
            Picasso.with(this).load(mApp.getThumbnail()).resize(100, 100).into(mImageIv);
        }
    }
}
