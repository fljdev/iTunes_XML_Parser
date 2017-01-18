package dao;

import model.Library;
import model.Song;
import persistence.PersistenceUtil;
import util.XML_Parser;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 03/12/2016.
 */
public class Song_DAO {

    public static ArrayList<Song> songArrayList = new ArrayList<>();

//   poplateSongListArray



    public static List<Song> findAllSongs(){
        EntityManager em = PersistenceUtil.createEM();
        List<Song> songs = (List<Song>)
                em.createNamedQuery("Song.findAll").getResultList();
        em.close();

        return songs;

    }

    public static Song findSongBySongName(String name){

        EntityManager em = PersistenceUtil.createEM();
        List<Song> songs = (List<Song>)
                em.createNamedQuery("Song.findBySongname").
                        setParameter("name", name).getResultList();
        em.close();

        if (songs.size() == 0)
            return null;
        else
            return songs.get(0);
    }



    public void createSong(Song song){

        PersistenceUtil.persist(song);

    }

    public List<Song> findByLibrary(Library library){
        EntityManager em = PersistenceUtil.createEM();
        List<Song> songs = (List<Song>)
                em.createNamedQuery("Song.findByLibrary").setParameter("library", library).getResultList();
        em.close();
        return songs;
    }


}
