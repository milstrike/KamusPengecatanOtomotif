package system.mil.kamuspengecatanotomotif;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;


/**
 * Created by milstrike on 18/03/2017.
 */

public class DetailActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener{


    private ImageView gambarKamus;
    private LinearLayout LayoutGambar;
    private TextView kataKamus, detailKamus;
    private String gambar, kata, detail;
    private TextToSpeech tts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_detail);

        gambarKamus = (ImageView) findViewById(R.id.gambarIndex);
        kataKamus = (TextView) findViewById(R.id.judulIndex);
        detailKamus = (TextView) findViewById(R.id.detailIndex);
        LayoutGambar = (LinearLayout) findViewById(R.id.layout_gambar);

        tts = new TextToSpeech(this, this);

        SharedPreferences sharedpreferences = getSharedPreferences("datakamus", Context.MODE_PRIVATE);
        gambar = sharedpreferences.getString("gambar", "null");
        kata = sharedpreferences.getString("indexkata", "null");
        detail = sharedpreferences.getString("artikata", "null");


        if(gambar.equalsIgnoreCase("null")){
            LayoutGambar.setVisibility(View.GONE);
        }

        kataKamus.setText(kata);
        detailKamus.setText(detail);
        Picasso.with(getApplicationContext()).load("file:///android_asset/img/"+gambar).into(gambarKamus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;

            case R.id.berbagi:
                berbagi();
                return true;

            case R.id.tts:
                speakOut();
                return true;



        }
        return super.onOptionsItemSelected(item);
    }

    public void goToPenampilGambar(View v){
        Intent i = new Intent(DetailActivity.this, ImageViewerActivity.class);
        startActivity(i);
    }

    public void berbagi(){
        String shareBody = "\n" + kata + "\n\n" + detail + "\n\n[Kamus Pengecatan Otomotif]";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kamus Pengecatan Otomotif [ " + kata + " ]");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Bagikan melalui"));
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {
        String text = kata;
        showSucess();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void showSucess(){
        Snackbar.make(findViewById(android.R.id.content), "SPEAKING...", Snackbar.LENGTH_LONG).show();
    }
}
