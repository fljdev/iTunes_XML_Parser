package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by admin on 05/12/2016.
 */

@Entity
@XmlRootElement
public class Playlist {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Song> songlist;

    @ManyToOne
    private Library library;


    private String playlistName;


    private String playlistId;

    private String persistentId;

    public Playlist() {
    }


    public Playlist(List<Song> songlist, String playlistName, Library library, String persistentId) {
        this.songlist = songlist;
        this.playlistName = playlistName;
        this.library = library;
        this.persistentId = persistentId;
    }

    @XmlElement
    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public List<Song> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<Song> songlist) {
        this.songlist = songlist;
    }

    @XmlElement
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    @XmlElement
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }


    public String getPersistentId() {
        return persistentId;
    }

    public void setPersistentId(String persistentId) {
        this.persistentId = persistentId;
    }
}
