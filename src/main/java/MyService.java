

import dao.Library_DAO;
import dao.Playlist_DAO;
import dao.Song_DAO;
import dao.User_DAO;
import model.Library;
import model.Playlist;
import model.Song;
import model.User;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import persistence.PersistenceUtil;
import util.XML_Parser;

import javax.swing.text.View;
import javax.ws.rs.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static dao.Song_DAO.songArrayList;

/**
 * Created by admin on 04/12/2016.
 */

@Path("/api")
public class MyService {

    private User currentUser = null;

    private Song_DAO song_dao = new Song_DAO();
    private Playlist_DAO playlist_dao = new Playlist_DAO();


    @POST
    @Path("/login")
    public String login(@FormParam("username")String username,
                           @FormParam("password") String password) {
        boolean found = false;

        List<User> userArrayList = new ArrayList<>();
        User_DAO user_dao = new User_DAO();
        userArrayList = user_dao.findAllUser();

        for(User u : userArrayList){
            if(username.equalsIgnoreCase(u.getUsername()) && password.equalsIgnoreCase(u.getPassword())){
                found = true;
                currentUser=u;
            }
        }

        if(found){
            return currentUser.getUsername()+" is now logged in";


        }
        return "Those credentials do not match anything in our DB";
    }

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public String uploadFile(MultipartFormDataInput input) {

        return "You just uploaded "+input;
    }

    @POST
    @Path("/createUser")
    public String createUser(@FormParam("username")String username,
                           @FormParam("email")String email,
                           @FormParam("password") String password,
                           @FormParam("confirm-password")String confirmPass) {



        String result = "User: "+ username+ " Email "+ email+ " P1 "+password + "P2 "+ confirmPass;

        User newUser = new User();

        if(!password.equalsIgnoreCase(confirmPass)){
            return "Passwords don't match ya muppet!!!";
        }else{
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setUsername(username);

            List<User> userArrayList = new ArrayList<>();

            boolean found = false;

            User_DAO user_dao = new User_DAO();
            userArrayList = user_dao.findAllUser();

            for(User u : userArrayList){
                if(username.equalsIgnoreCase(u.getUsername()) || email.equalsIgnoreCase(u.getEmail())){
                    found = true;
                }
            }

            if(!found){
                user_dao.createUser(newUser);
            }else{
                return "Sorry, all emails and usernames must be unique, retry!!";
            }
            return newUser.getUsername()+ "has been added to the database";
        }


    }





    @GET
    @Path("/{param}")
    public String sayHello(@PathParam("param") String msg) {

        String result = "Good Morning : " + msg+"!";

        return result;
    }//end sayHello




    @GET
    @Path("/songs")
    public String seeSongs() throws Exception{

        XML_Parser mp = new XML_Parser();

        User aUser = new User("johnny","johnny@gmail.com","1234pass");
        User_DAO user_dao = new User_DAO();
        user_dao.createUser(aUser);


        Library library = new Library(aUser,"123445");
        Library_DAO library_dao = new Library_DAO();
        library_dao.createLibrary(library);

        ArrayList<Song> songs= mp.parseSongs("/Users/admin/Documents/College/4th_Year/Semester_1/Distributed_Sys/bits and pieces/Final_DS/Rest_JPA_XML_Parser/src/main/java/xml_files/anotherPlaylist.xml");
        for(Song s : songs){

            s.setLibrary(library);
            song_dao.createSong(s);
        }

        List<Song> songList = song_dao.findByLibrary(library);
        List<Playlist>playlists;

        playlists = mp.getAllPlaylist(songList,   "/Users/admin/Documents/College/4th_Year/Semester_1/Distributed_Sys/bits and pieces/Final_DS/Rest_JPA_XML_Parser/src/main/java/xml_files/anotherPlaylist.xml"
        );
        for(Playlist pl : playlists){
            pl.setLibrary(library);
            playlist_dao.createPlaylist(pl);
        }

        String result ="Boxed off!!";


        return result;
    }//end seeSongs
}
