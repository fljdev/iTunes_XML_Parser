package model;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 03/12/2016.
 */

@NamedQueries(value = {
        @NamedQuery(name = "Song.findAll", query = "select s from Song s"),
        @NamedQuery(name = "Song.findBySongname", query = "select s from Song s where s.name=:name"),
        @NamedQuery(name = "Song.findByLibrary", query = "select o from Song o where o.library=:library")

})

@Entity
@XmlRootElement
public class Song {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private int trackID;
    private String name;
    private String artist;
    private String album;
    private  String year;
    private String genre;
    private String persistentId;


    @ManyToOne
    private Library library;

//    private int libraryId;

    public Song(){

    }

    public Song(String name) {
        this.name = name;
    }

    public Song(String name, String artist, String album, String year, String genre, String persistentId, Library library) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.persistentId = persistentId;
        this.library = library;
    }

    @XmlElement
    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @XmlElement
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement
    public String getPersistentId() {
        return persistentId;
    }

    public void setPersistentId(String persistentId) {
        this.persistentId = persistentId;
    }

    @XmlElement
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", persistentId='" + persistentId + '\'' +
                ", libraryshow databases;" +
                "=" + library +
                '}';
    }
}
