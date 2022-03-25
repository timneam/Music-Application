package sg.edu.tp.musicstream;

public class Song
{
    private String id;
    private String title;
    private String artist;
    private String fileLink;
    private double songLength;
    private String coverArt;

    public Song(String id, String title, String artist, String fileLink, double songLength, String coverArt)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.coverArt = coverArt;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()

    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public double getSongLength()
    {
        return songLength;
    }

    public void setSongLength(double songLength)
    {
        this.songLength = songLength;
    }

    public String getFileLink()    {        return fileLink;    }

    public void setFileLink(String fileLink)
    {
        this.fileLink = fileLink;
    }

    public String getCoverArt()
    {
        return coverArt;
    }

    public void setCoverArt(String coverArt)
    {
        this.coverArt = coverArt;
    }
}
//end of Song
