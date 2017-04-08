package system.mil.kamuspengecatanotomotif;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by milstrike on 07/04/2017.
 */

public class ImageViewerActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_image_viewer);

        TouchImageView penampilGambar = (TouchImageView) findViewById(R.id.imageViewer1);

        SharedPreferences sharedpreferences = getSharedPreferences("datakamus", Context.MODE_PRIVATE);
        String gambar = sharedpreferences.getString("gambar", "null");

        Picasso.with(getApplicationContext()).load("file:///android_asset/img/"+gambar).into(penampilGambar);

        Snackbar.make(findViewById(android.R.id.content), "pinch in dan pinch out untuk memperbesar/memperkecil gambar", Snackbar.LENGTH_LONG).show();

    }


}
