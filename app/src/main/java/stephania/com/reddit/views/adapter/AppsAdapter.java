package stephania.com.reddit.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import stephania.com.reddit.R;
import stephania.com.reddit.activites.AppDetailActivity;
import stephania.com.reddit.models.App;
import stephania.com.reddit.services.IRedditService;
import stephania.com.reddit.utils.AnimacionUtil;
import stephania.com.reddit.utils.StringUtil;

/**
 * Adapter de las apps
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class AppsAdapter extends RecyclerView.Adapter  {

    /** Contexto de la actividad **/
    private Context mContext;

    /** Variable tipo inflater para la vistas **/
    private LayoutInflater mInflater;

    /** Categorias **/
    private List<App> mListApp;

    /**
     * Servicio Reddit
     **/
    @Inject
    IRedditService mRedditService;

    /** Llave para enviar informacion del item seleccioando **/
    public static final String ITEM = "ITEM";

    /**
     * Constructor del adapter
     * @param context
     *          Contexto de la actividad
     * @param app
     *          Lista de apps
     * @param redditService
     *          Interfaces del servicio
     */
    public AppsAdapter(Context context, List<App> app,
                             IRedditService redditService){
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mListApp = app;
        this.mRedditService = redditService;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_card_app, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Clase que contiene los elementos de la vista
     */
    private static class ViewHolder extends RecyclerView.ViewHolder {

        /** TextView que contiene el titulo del app **/
        public TextView mTitleTv;

        /** TextView que contiene el nombre del autor **/
        public TextView mAuthorTv;

        /** TextView que contiene el puntaje **/
        public TextView mScoreTv;

        /** TextView que contiene la cantidad de comentarios **/
        public TextView mLikeTv;

        /** TextView que contiene la cantidad de likes **/
        public TextView mCommentTv;

        /** Imagen de la aplicacion **/
        public ImageView mImageIv;

        /** Vista del cardview **/
        public CardView mContentVc;

        /** TextView que direcciona a la actividad {@link AppDetailActivity} **/
        public TextView mVermasTv;

        /**
         * Meotodo que iniciliza los elementos de la vista
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView)itemView.findViewById(R.id.title_app_tv);
            mAuthorTv = (TextView)itemView.findViewById(R.id.author_app_tv);
            mScoreTv = (TextView)itemView.findViewById(R.id.score_app_tv);
            mLikeTv = (TextView)itemView.findViewById(R.id.like_app_tv);
            mCommentTv = (TextView)itemView.findViewById(R.id.comemment_app_tv);
            mImageIv = (ImageView)itemView.findViewById(R.id.image_app_iv);
            mContentVc = (CardView)itemView.findViewById(R.id.content_app_cv);
            mVermasTv = (TextView)itemView.findViewById(R.id.ver_mas_tv);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        App apps = mListApp.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.mTitleTv.setText(apps.getTitle());
        viewHolder.mAuthorTv.setText("Autor: " + StringUtil.capitalize(apps.getAuthor()));
        viewHolder.mScoreTv.setText("Score: " + apps.getScore());
        if(apps.getLikes()!=null) {
            viewHolder.mLikeTv.setText(apps.getLikes());
        }else {
            viewHolder.mLikeTv.setText("0");
        }
        viewHolder.mCommentTv.setText(apps.getNumComments());
        if(apps.getThumbnail() == null || apps.getThumbnail().isEmpty()){
            viewHolder.mImageIv.setImageResource(R.mipmap.ic_launcher);
        }else{
            Picasso.with(mContext).load(apps.getThumbnail()).resize(100,100)
                    .into(viewHolder.mImageIv);
        }
        //Evento Onclick que envia ala actividad Detalle
        viewHolder.mVermasTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App app = mListApp.get(position);
                mRedditService.saveApp(app);
                Intent intentActivity = new Intent(mContext, AppDetailActivity.class);
                intentActivity.putExtra(ITEM, app);
                AnimacionUtil.configurarAnimacion(mContext, viewHolder.mContentVc, false,
                        intentActivity);
            }
        });
        AnimacionUtil.setAnimationleft(viewHolder.mContentVc, position, mContext);
    }

    @Override
    public int getItemCount() {
        return this.mListApp.size();
    }
}
