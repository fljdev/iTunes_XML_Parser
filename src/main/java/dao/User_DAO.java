package dao;

import model.Song;
import model.User;
import persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 05/12/2016.
 */
public class User_DAO {

    public static ArrayList<User> userArrayList = new ArrayList<>();

    public void createUser(User aUser){
        PersistenceUtil.persist(aUser);
    }

    public static List<User> findAllUser(){
        EntityManager em = PersistenceUtil.createEM();
        List<User> users = (List<User>)
                em.createNamedQuery("User.findAll").getResultList();
        em.close();

        return users;

    }

//    public static User findUserByUsername(String username){
//
//        EntityManager em = PersistenceUtil.createEM();
//        List<User> users = (List<User>)
//                em.createNamedQuery("User.findByUsername").
//                        setParameter("username", username).getResultList();
//        em.close();
//
//        if (users.size() == 0)
//            return null;
//        else
//            return users.get(0);
//    }



}
