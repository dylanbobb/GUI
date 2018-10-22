package spotifyparser;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Album {
    private String artistName;
    private String albumName;
    private String imageURL;
    private ArrayList<String> tracks;
    private ArrayList<Integer> trackLengthsInSeconds;
    
    public Album(String artistName, String albumName, String imageURL, ArrayList<String> tracks, ArrayList<Integer> trackLengthsInSeconds)
    {
        this.artistName = artistName;
        this.albumName = albumName;
        this.imageURL = imageURL;
        this.tracks = tracks;
        this.trackLengthsInSeconds = trackLengthsInSeconds;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ArrayList<String> getTracks() {
        return tracks;
    }
    
    public ArrayList<Integer> getTrackLengthsInSeconds() {
        return trackLengthsInSeconds;
    }
    
    public String toString()
    {
        String output = "Album from: "+ artistName+" \nTitle: "+ albumName + "\n\nTracks:\n";
        
        if (tracks != null && trackLengthsInSeconds != null && 
            tracks.size() > 0 && tracks.size() == trackLengthsInSeconds.size())
        {
            for (int i=0; i<tracks.size(); ++i)
            {
                output += tracks.get(i) + " [" + (trackLengthsInSeconds.get(i) / 60) + ":" + (trackLengthsInSeconds.get(i) % 60) + "]\n";
            }            
        }
        
        return output;
    }
}
