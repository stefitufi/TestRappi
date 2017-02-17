package stephania.com.reddit.services;

import com.google.inject.AbstractModule;

import stephania.com.reddit.managers.AppManager;
import stephania.com.reddit.managers.CategoryManager;
import stephania.com.reddit.managers.IAppManager;
import stephania.com.reddit.managers.ICategoryManager;
import stephania.com.reddit.rest.IRestClient;
import stephania.com.reddit.rest.api.RestClient;

/**
 * Clase dependencia RoboGuice
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bindServices();
        bindManagers();
        bindOthers();
    }

    /**
     * This method binds all services
     */
    private void bindServices() {
        bind(IRedditService.class).to(RedditService.class);
    }

    /**
     * This method binds all managers
     */
    private void bindManagers() {
        bind(ICategoryManager.class).to(CategoryManager.class);
        bind(IAppManager.class).to(AppManager.class);
    }

    /**
     * This method binds other classes, such as clients and internal classes
     */
    private void bindOthers() {
        bind(IRestClient.class).to(RestClient.class);
    }
}
