package stephania.com.reddit.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.sql.SQLException;

import stephania.com.reddit.persistence.CrudManager;
import stephania.com.reddit.models.Category;

/**
 * Clase para la implementacion de los query de Category
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@Singleton
public class CategoryManager extends CrudManager<Category, Integer> implements ICategoryManager {
    /**
     * This is the main constructor of the CrudManager
     *
     * @param helper
     *         The DBHelper
     *
     * @throws SQLException
     *         If there's an error creating the Entity's DAO
     */
    @Inject
    public CategoryManager(DatabaseHelper helper) throws SQLException {
        super(helper, Category.class);
    }
}
