package sg.edu.tp.musicstream;

import android.content.Intent;
import android.media.*;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.IOException;

import sg.edu.tp.musicstream.util.AppUtil;

public class PlaySongActivity extends AppCompatActivity
{
    private static final String BASE_URL = "https://p.scdn.co/mp3-preview/";

    private String songId = "";
    private String title = "";
    private String artist ="";
    private String fileLink = "";
    private String coverArt = "";
    private String url = "";

    private MediaPlayer player = null;
    private int musicPosition = 0;
    private Button btnPlayPause = null;

    private SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        btnPlayPause = (Button) findViewById(R.id.btnPlayPause);

        retrieveData();

        displaySong(title, artist, coverArt);
    }

    private void retrieveData()
    {
        Bundle songData = this.getIntent().getExtras();

        songId = songData.getString("id");
        title = songData.getString("title");
        artist = songData.getString("artist");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");

        url = BASE_URL + fileLink;
    }

    private void displaySong(String title, String artist, String coverArt)
    {
        TextView txtTitle = (TextView) findViewById(R.id.txtSongTitle);

        txtTitle.setText(title);

        TextView txtArtist = (TextView) findViewById(R.id.txtArtist);

        txtArtist.setText(artist);

        int imageId = AppUtil.getImageIdFromDrawable(this, coverArt);

        ImageView ivCoverArt = (ImageView) findViewById(R.id.imgCoverArt);

        ivCoverArt.setImageResource(imageId);
    }

    private void preparePlayer()
    {
        player = new MediaPlayer();

        try
        {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepare();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view)
    {
        if (player == null)
            preparePlayer();

        if (!player.isPlaying())
        {
            if (musicPosition > 0)
            {
                player.seekTo(musicPosition);
            }
            player.start();
            btnPlayPause.setText("PAUSE");
            setTitle("Now Playing: " + title + " - " + artist);

            gracefullyStopWhenMusicEnds();

        }
        else
        {
            pauseMusic();
        }
    }

    private void pauseMusic()
    {
        player.pause();
        musicPosition = player.getCurrentPosition();
        btnPlayPause.setText("PLAY");
    }

    private void gracefullyStopWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                stopActivities();
            }
        });
    }

    private void stopActivities()
    {
        if (player != null)
        {
            btnPlayPause.setText("PLAY");
            musicPosition = 0;
            setTitle("");
            player.stop();
            player.release();
            player = null;
        }
    }

    public void playPrevious(View view)
    {
        Song previousSong = songCollection.getPreviousSong(songId);

        if (previousSong != null)
        {
            songId = previousSong.getId();
            title = previousSong.getTitle();
            artist = previousSong.getArtist();
            fileLink = previousSong.getFileLink();
            coverArt = previousSong.getCoverArt();

            url = BASE_URL + fileLink;

            displaySong(title, artist, coverArt);

            stopActivities();

            playOrPauseMusic(view);
        }
    }

    public void playNext(View view)
    {
        Song nextSong = songCollection.getNextSong(songId);

        if (nextSong != null)
        {
            songId = nextSong.getId();
            title = nextSong.getTitle();
            artist = nextSong.getArtist();
            fileLink = nextSong.getFileLink();
            coverArt = nextSong.getCoverArt();

            url = BASE_URL + fileLink;

            displaySong(title, artist, coverArt);

            stopActivities();

            playOrPauseMusic(view);
        }
    }
}