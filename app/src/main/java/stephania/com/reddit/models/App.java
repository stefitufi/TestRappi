package stephania.com.reddit.models;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Clase que representa la descipcion de elemento
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class App implements Serializable {

    /** Local app ID **/
    @DatabaseField(generatedId = true)
    private Integer idLocal;

    /** Id Categoria **/
    @DatabaseField(canBeNull = true)
    private String idCategory;

    /** Id App **/
    @SerializedName("id")
    @DatabaseField(canBeNull = false)
    private String id;

    /** Nombre de la categoria **/
    @SerializedName("subreddit")
    @DatabaseField(canBeNull = true)
    private String subreddit;

    @SerializedName("selftext")
    @DatabaseField(canBeNull = true)
    private String selftext;

    /** String de la url  **/
    @SerializedName("url")
    @DatabaseField(canBeNull = true)
    private String url;

    /** String que cotiene cantidad de like **/
    @SerializedName("likes")
    @DatabaseField(columnName = "likes")
    private String likes;

    /** String contiene el autor **/
    @SerializedName("author")
    @DatabaseField(canBeNull = true)
    private String author;

    /** String contiene num de puntaje **/
    @SerializedName("score")
    @DatabaseField(canBeNull = true)
    private String score;

    /** String contiene el titulo **/
    @SerializedName("title")
    @DatabaseField(canBeNull = true)
    private String title;

    /** String contiene la imagen **/
    @SerializedName("thumbnail")
    @DatabaseField(canBeNull = true)
    private String thumbnail;

    /** String contiene cantidad de commentarios **/
    @SerializedName("num_comments")
    @DatabaseField(canBeNull = true)
    private String numComments;

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNumComments() {
        return numComments;
    }

    public void setNumComments(String numComments) {
        this.numComments = numComments;
    }
}
