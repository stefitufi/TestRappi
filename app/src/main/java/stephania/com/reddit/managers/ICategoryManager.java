package stephania.com.reddit.managers;

import stephania.com.reddit.persistence.ICrudManager;
import stephania.com.reddit.models.Category;

/**
 * Exposed queries for Category manager
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public interface ICategoryManager extends ICrudManager<Category, Integer> {}
