package sg.edu.tp.musicstream;

public class SongCollection
{
    private Song[] songs = new Song[4];

    public SongCollection()
    {
        prepareSongs();
    }

    private void prepareSongs()
    {
        Song photograph = new Song("S1001", "Photograph", "Ed Sheeran","097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=null",4.32,"photograph");
        Song thisChristmas = new Song("S1002", "This Christmas", "Taeyeon","2c565d62f5b5b78ff81ed666eb45061270fec6ec?cid=2afe87a64b0042dabf51f37318616965",4.45,"thischristmas");
        Song dduDuDduDu = new Song ("S1003", "Ddu-Du Ddu-Du", "BLACKPINK","4045a51dbea3faa5d1e21adb8d5ee293f8ac412b?cid=2afe87a64b0042dabf51f37318616965",3.49,"ddududdudu");
        Song stay = new Song ("S1004", "Stay", "Taeyeon", "df938e9004dbffc882af93204f2a93b3e500c27c?cid=2afe87a64b0042dabf51f37318616965", 4.22, "stay");

        songs[0] = photograph;
        songs[1] = thisChristmas;
        songs[2] = dduDuDduDu;
        songs[3] = stay;

    }

    public Song searchById(String id)
    {
        Song song = null;

        for (int index = 0; index < songs.length; index++)
        {
            song = songs[index];
            if (song.getId().equals(id))
            {
                return song;
            }
        }
        return song;
    }

    public Song getNextSong(String currentSongId)
    {
        Song song = null;

        for (int index = 0; index < songs.length; index++)
        {
            if (songs[index].getId().equals(currentSongId) && (index < songs.length - 1))
            {
                song = songs[index + 1];

                break;
            }
        }

        return song;
    }

    public Song getPreviousSong(String currentSongId)
    {
        Song song = null;

        for (int index = 0; index < songs.length; index++)
        {
            if (songs[index].getId().equals(currentSongId) && (index > 0))
            {
                song = songs[index - 1];
                break;
            }
        }

        return song;
    }
}