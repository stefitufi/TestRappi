package stephania.com.reddit.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.sql.SQLException;

import stephania.com.reddit.models.App;
import stephania.com.reddit.persistence.CrudManager;

/**
 * Clase para la implementacion de los query de App
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@Singleton
public class AppManager extends CrudManager<App, Integer> implements IAppManager {
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
    public AppManager(DatabaseHelper helper) throws SQLException {
        super(helper, App.class);
    }
}
