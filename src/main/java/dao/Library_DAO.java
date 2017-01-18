package dao;

import model.Library;
import persistence.PersistenceUtil;

import java.util.ArrayList;

/**
 * Created by admin on 05/12/2016.
 */
public class Library_DAO {

    public static ArrayList<Library> libraryArrayList = new ArrayList<>();

    public void createLibrary(Library library){
        PersistenceUtil.persist(library);
    }

}
