package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 05/12/2016.
 */

@Entity
@XmlRootElement
public class Library {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;


    @ManyToOne
    private User user;

    private String persistentId;

    public Library() {
    }

    public Library(User user, String persistentId) {
        this.user = user;
        this.persistentId = persistentId;
    }

    @XmlElement
    public int getId() {
        return id;
    }


    @XmlElement
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlElement
    public String getPersistentId() {
        return persistentId;
    }

    public void setPersistentId(String persistentId) {
        this.persistentId = persistentId;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", user=" + user +
                ", persistentId='" + persistentId + '\'' +
                '}';
    }
}
