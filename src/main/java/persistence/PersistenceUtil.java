package persistence;





import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by admin on 29/09/2016.
 */
public class PersistenceUtil {

    private static final long serialVersionUID = 1L;

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("songsDb");

    public static void persist(Object entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public static void remove(Object entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Object mergedEntity = em.merge(entity);
        em.remove(mergedEntity);
        em.getTransaction().commit();
        em.close();
    }

    public static Object merge(Object entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        entity = em.merge(entity);
        em.getTransaction().commit();
        em.close();


        return entity;
    }

    public static EntityManager createEM() {

        return emf.createEntityManager();
    }








//    public static void createSong(Song s){
//        PersistenceUtil.persist(s);
//    }
}
