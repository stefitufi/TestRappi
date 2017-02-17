package stephania.com.reddit.models;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Clase que representa las categorias
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class Category implements Serializable{

    /** Local Category ID **/
    @DatabaseField(generatedId = true)
    private Integer idLocal;

    @SerializedName("id")
    @DatabaseField(canBeNull = false)
    private String id;

    /** String que contiene url de la imagen **/
    @SerializedName("header_img")
    @DatabaseField(canBeNull = true)
    private String headerImg;

    /** String que contiene nombre de la categoria **/
    @SerializedName("advertiser_category")
    @DatabaseField(canBeNull = true)
    private String advertiserCategory;

    /** String que contiene nombre de app **/
    @SerializedName("display_name")
    @DatabaseField(canBeNull = true)
    private String displayName;

    /** String que contiene el titulo del app **/
    @SerializedName("title")
    @DatabaseField(canBeNull = true)
    private String title;

    /** String que contiene el titulo del app **/
    @SerializedName("url")
    @DatabaseField(canBeNull = true)
    private String url;

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getAdvertiserCategory() {
        return advertiserCategory;
    }

    public void setAdvertiserCategory(String advertiserCategory) {
        this.advertiserCategory = advertiserCategory;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
