package stephania.com.reddit.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import stephania.com.reddit.R;
import stephania.com.reddit.models.Category;
import stephania.com.reddit.services.IRedditService;
import stephania.com.reddit.utils.StringUtil;

/**
 * Adapter de las categorias
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class CategoriesAdapter extends BaseAdapter {

    /** Contexto de la actividad **/
    private Context mContext;

    /** Variable tipo inflater para la vistas **/
    private LayoutInflater mInflater;

    /** Categorias **/
    private List<Category> mListCategory;

    /**
     * Servicio Reddit
     **/
    @Inject
    IRedditService mRedditService;

    /**
     * Constructor del adapter
     * @param context
     *          Contexto de ña actividad
     * @param category
     *          Lista de categorias
     * @param redditService
     *          Interfaces del servicio
     */
    public CategoriesAdapter(Context context, List<Category> category,
                             IRedditService redditService){
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mListCategory = category;
        this.mRedditService = redditService;
    }

    @Override
    public int getCount() {
        return this.mListCategory.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Content para creación de los controles usados en la actividad
     **/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHandler handler;

        if(convertView == null){
            handler = new DataHandler();
            convertView = mInflater.inflate(R.layout.view_item_category, parent, false);

            handler.mNameApp = (TextView)convertView.findViewById(R.id.title_tv);
            handler.mNameCategoryTv = (TextView)convertView.findViewById(R.id.name_tv);
            handler.mImageCategoryIv = (ImageView)convertView.findViewById(R.id.category_iv);

            /** Guarda los items que debe cargar en la vista **/
            convertView.setTag(handler);
        }else{
            handler = (DataHandler) convertView.getTag();
        }
        Category category = mListCategory.get(position);
        if(category.getDisplayName() == null){
            handler.mNameApp.setText("Sin información");
        }else{
            handler.mNameApp.setText(StringUtil.capitalize(category.getDisplayName()));
        }
        if(category.getAdvertiserCategory() == null){
            handler.mNameCategoryTv.setText("Otros");
        }else {
            handler.mNameCategoryTv.setText(category.getAdvertiserCategory());
        }
        if(category.getDisplayName() == null){
            handler.mImageCategoryIv.setImageDrawable
                    (mContext.getResources().getDrawable(R.drawable.ic_not));
        }else{
            Picasso.with(mContext).load(category.getHeaderImg()).resize(100,100)
                    .into(handler.mImageCategoryIv);
        }

        return convertView;
    }

    /**
     * Hendler para creación de los controles usados en la actividad
     **/
    static class DataHandler {
        private TextView mNameCategoryTv;
        private TextView mNameApp;
        private ImageView mImageCategoryIv;

    }

}
