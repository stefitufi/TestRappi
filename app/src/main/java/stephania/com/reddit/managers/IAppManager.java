package stephania.com.reddit.managers;

import stephania.com.reddit.persistence.ICrudManager;
import stephania.com.reddit.models.App;

/**
 * Exposed queries for Apps' manager
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public interface IAppManager extends ICrudManager<App, Integer> {}
