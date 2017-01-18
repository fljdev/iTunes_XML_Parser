package util;

import model.Playlist;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.Song;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XML_Parser {

    public XML_Parser(){
    }


    public ArrayList<Song> parseSongs(String xml){

        ArrayList<Song> songArrayList = new ArrayList<Song>();

        try{

//            File xmlFile = new File("src/xml_files/anotherPlaylist");
            File xmlFile = new File(xml);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();


            XPath xp = XPathFactory.newInstance().newXPath();
            String expression = "//dict/key[.='Tracks']/following-sibling::*[1]/child::*";
            NodeList nodeList = (NodeList)xp.compile(expression).evaluate(doc, XPathConstants.NODESET);

            NodeList eachTrack = null;
            System.out.println("node list.lenth = "+nodeList.getLength());

            for(int i  = 0;i<nodeList.getLength();i++){


                Song aSong = new Song();

                if(nodeList.item(i).getNodeName().equalsIgnoreCase("key")){

                    eachTrack=(NodeList) nodeList.item(i).getNextSibling().getNextSibling().getChildNodes();

                    String name="";
                    String artist="";
                    String album="";
                    String genre="";
                    String year="";
                    String persistentID="";
                    String trackID="";
                    for(int j = 0; j < eachTrack.getLength();j++){
                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Name")){
                            name = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Track Name "+name);
                            aSong.setName(name);
                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Track ID")){
                            trackID = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Track ID "+name);
                            aSong.setTrackID(Integer.parseInt(trackID));
                        }


                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Artist")){
                            artist = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Artist "+eachTrack.item(j).getNextSibling().getTextContent());
                            aSong.setArtist(artist);
                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Album")){
                            album= eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Album "+eachTrack.item(j).getNextSibling().getTextContent());
                            aSong.setAlbum(album);

                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Genre")){
                            genre = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Genre "+eachTrack.item(j).getNextSibling().getTextContent());
                            aSong.setGenre(genre);

                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Year")){
                            year=eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Year "+eachTrack.item(j).getNextSibling().getTextContent());
                            aSong.setYear(year);

                        }
                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Persistent Id")){
                            persistentID=eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Persistent Id "+eachTrack.item(j).getNextSibling().getTextContent());
                            aSong.setPersistentId(persistentID);
                        }
                    }
                }
                if(aSong.getName()!=null){
                    songArrayList.add(aSong);
                    System.out.println("\n----------------------\n");

                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i = 0; i <songArrayList.size();i++){
            System.out.println(songArrayList.get(i).toString());
        }//end for

        return songArrayList;
    }//end parse




    public ArrayList<Playlist> getAllPlaylist(List<Song> trackSet, String xml) throws Exception{
        File xmlFile = new File(xml);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();


        XPath xp = XPathFactory.newInstance().newXPath();
        String nameExpression = "//dict/key[. = 'Playlists']/following-sibling::*[1]/child::*";
        NodeList nodeList = (NodeList) xp.compile(nameExpression).evaluate(doc, XPathConstants.NODESET);
        NodeList playListNodeList = null;
        ArrayList<Playlist> playlists = new ArrayList<>();


        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList newNodeList = nodeList.item(i).getChildNodes();
            Playlist playlist = new Playlist();
            ArrayList<Song> trackIdList = new ArrayList<>();
            for (int j = 0; j < newNodeList.getLength(); j++) {
                if (newNodeList.item(j).getTextContent().equals("Name"))
                    playlist.setPlaylistName(newNodeList.item(j).getNextSibling().getTextContent());
                if (newNodeList.item(j).getTextContent().equals("Playlist ID"))
                    playlist.setPlaylistId(newNodeList.item(j).getNextSibling().getTextContent());
                if (newNodeList.item(j).getTextContent().equals("Playlist Persistent ID"))
                    playlist.setPersistentId(newNodeList.item(j).getNextSibling().getTextContent());
                if (newNodeList.item(j).getTextContent().equals("Playlist Items")) {
                    playListNodeList = newNodeList.item(j).getNextSibling().getNextSibling().getChildNodes();
                    for (int x = 0; x < playListNodeList.getLength(); x++) {
                        if (playListNodeList.item(x).getNodeName().equals("dict")){
                            System.out.println(Integer.parseInt(playListNodeList.item(x).getChildNodes().item(0).getNextSibling().getNextSibling().getTextContent()));
                            int playlistTrackId = Integer.parseInt(playListNodeList.item(x).getChildNodes().item(0).getNextSibling().getNextSibling().getTextContent());
                            for(Song s: trackSet) {
                                if (s.getTrackID() == playlistTrackId) {
                                    trackIdList.add(s);
                                    System.out.println(s.toString()+"From XMLParser");
                                }
                            }
                        }

                    }
                    playlist.setSonglist(trackIdList);
                }
            }
            playlists.add(playlist);
        }
        return playlists;
    }



//    public List<Playlist> parsePlaylist(String xmlString) throws Exception{
//
//        XML_Parser xml_parser = new XML_Parser();
//
//        List<Song> trackslist = xml_parser.parseSongs("");
//
//        ArrayList<Playlist> playlists = new ArrayList<>();
//
//        Playlist playlist;
//
//        ArrayList<Song> songList = new ArrayList<>();
//
//        File fXmlFile = new File(xmlString);
//
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//
//        DocumentBuilder dBuilder;
//
//        try {
//            dBuilder = dbFactory.newDocumentBuilder();
//
//            Document doc = dBuilder.parse(fXmlFile);
//            doc.getDocumentElement().normalize();
//            String nameExpression = "//dict/key[. = 'Playlists']/following-sibling::*[1]/child::*";
//            XPath xPath = XPathFactory.newInstance().newXPath();
//            NodeList nodeList = (NodeList) xPath.compile(nameExpression).evaluate(doc, XPathConstants.NODESET);
//
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                playlist = new Playlist();
//                Node playlistNode = nodeList.item(i);
//
//                if (playlistNode.getNodeName().equals("dict")) {
//
//                    NodeList playlistsNode = playlistNode.getChildNodes();
//                    for (int a = 0; a < playlistsNode.getLength(); a++) {
//                        Node another = playlistsNode.item(a);
//
//                        if (another.getTextContent().equals("Name")) {
//
//                            playlist.setPlaylistName(another.getNextSibling().getTextContent());
//                        } else if (another.getTextContent().equals("Playlist ID")) {
//
//                            playlist.setId(Integer.parseInt(another.getNextSibling().getTextContent()));
//                        } else if (another.getTextContent().equals("Playlist Persistent ID")) {
//
//                            playlist.setPersistentId(another.getNextSibling().getTextContent());
//
//                        } else if (another.getTextContent().equals("Playlist Items")) {
//
//                            NodeList items = another.getNextSibling().getNextSibling().getChildNodes();
//
//                            for (int x = 0; x < items.getLength(); x++) {
//
//
//                                Node xitem = items.item(x);
//                                NodeList mostItems = xitem.getChildNodes();
//
//                                for (int h = 0; h < mostItems.getLength(); h++) {
//
//                                    Node something = mostItems.item(h);
//                                    if (something.getTextContent().equals("Track ID")) {
//                                        String current = something.getNextSibling().getTextContent();
//
//                                        for (Song s : trackslist) {
//
//                                            if (Integer.parseInt(current) == s.getId()) {
//                                                songList.add(s);
//                                                playlist.setSonglist(songList);
//                                            }
//                                        }
//                                    }
//
//                                }
//                            }//System.out.println(trackList);
//                            playlists.add(playlist);
//                        }
//                    }
//                }
//            }
//            System.out.println("Count is: " + playlists.size());
//
//            for (Playlist p : playlists) {
//                System.out.println(p.getPlaylistName());
//                System.out.println(p.getPersistentId());
//                System.out.println(p.getSonglist());
//                System.out.println("");
//            }
//        } catch (ParserConfigurationException | IOException e) {
//            e.printStackTrace();
//        } catch (XPathExpressionException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//
//
//        return playlists;
//    }

}//end class