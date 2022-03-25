package sg.edu.tp.musicstream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sg.edu.tp.musicstream.util.AppUtil;

public class MainActivity extends AppCompatActivity
{
    private SongCollection songCollection = new SongCollection();;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleSelection(View view)
    {
        String resourceId = AppUtil.getResourceId(this, view);
        Song selectedSong = songCollection.searchById(resourceId);
        AppUtil.popMessage(this, "Streaming song: " + selectedSong.getTitle());
        sendDataToActivity(selectedSong);
    }

    public void sendDataToActivity(Song song)
    {
        Intent intent = new Intent(this, PlaySongActivity.class);

        intent.putExtra("id", song.getId());
        intent.putExtra("title", song.getTitle());
        intent.putExtra("artist", song.getArtist());
        intent.putExtra("fileLink", song.getFileLink());
        intent.putExtra("coverArt", song.getCoverArt());

        startActivity(intent);
    }
}
