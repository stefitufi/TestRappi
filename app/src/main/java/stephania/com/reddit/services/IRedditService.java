package stephania.com.reddit.services;

import java.util.List;

import stephania.com.reddit.models.App;
import stephania.com.reddit.models.Category;

/**
 * Interfaz del servicio {@link RedditService}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public interface IRedditService {

    App getAppSaved();

    /**
     * Meotdo que obtiene las categorias
     * @return
     *      Categorias
     */
    Category getCategories();

    /**
     * Guarda la app seleccionada
     *
     * @param appSave app
     */
    void saveApp(App appSave);

    /**
     * Save the selected category
     *
     * @param categorySaved category
     */
    void saveCategory(Category categorySaved);

    /** S
     * Crea o modifica una categoria
     *
     * @param category
     */
    void createOrUpdateCategory(Category category);

    /** S
     * Crea o modifica una apps
     *
     * @param redditApp
     */
    void createOrUpdateApp(App redditApp);

    /** S
     * Obtiene todas las categorias
     *
     * @return All categories
     */
    List<Category> getAllCategories();

    /** S
     * Obtiene la informacion de categorias de reddit
     *
     * @return Respuesta con Reddit
     */
    List<Category> obtenerCategory();

    /** S
     * Obtiene las apps reddit
     *
     * @return Respuesta apps
     */
    List<App> obtenerApp(String url);

    /** S
     * Metodo que obtiene las app según la categoria
     * @param value
     * @return **
     */
    List<App> getAppsByCatId(Object value);

    /** Obtengo la lista de Apps segun la categoria seleccionada **/
    List<App> getAllApps();







}
