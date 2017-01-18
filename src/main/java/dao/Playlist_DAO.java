package dao;

import model.Playlist;
import persistence.PersistenceUtil;

import java.util.ArrayList;

/**
 * Created by admin on 05/12/2016.
 */
public class Playlist_DAO {

    public static ArrayList<Playlist> playlistArrayList = new ArrayList<>();

    public void  createPlaylist(Playlist playlist){
        PersistenceUtil.merge(playlist);
    }

}
