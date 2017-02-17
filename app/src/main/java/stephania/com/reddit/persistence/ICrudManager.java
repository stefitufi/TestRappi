package stephania.com.reddit.persistence;

import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * Interface offers the CRUD methods to manage the database entities.
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public interface ICrudManager<Entity, Id> {

    /**
     * Creates or Updates the given entity. If the given entity is using auto-generated Id, then
     * this will be loaded into the instance
     *
     * @param entity
     *         Entity instance to be created or updated into the DB
     *
     * @return True if the entity was created/updated. False otherwise
     */
    boolean createOrUpdate(Entity entity);

    /**
     * Finds an element of the Entity given its Id
     *
     * @param id
     *         Entity's Id
     *
     * @return If there's a match, the Entity element is returned. Otherwise returns null
     */
    Entity findById(Id id);

    /**
     * Find by field name and attribute
     *
     * @param field field name
     * @param value value
     * @return List of entities
     */
    List<Entity> findByAttr(String field, Object value);

    /**
     * Returns a list of all stored elements of the Entity in the DB
     *
     * @return List of all stored elements of the Entity in the DB
     */
    List<Entity> all();

    /**
     * This method deletes all Table content
     */
    void deleteAll();

    /**
     * Returns the Entity DAO. This should be used to add custom queries
     *
     * @return Entity's DAO
     */
    Dao<Entity, Id> getDao();

}
