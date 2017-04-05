package system.mil.kamuspengecatanotomotif;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by milstrike on 18/03/2017.
 */

public class HelpActivity extends AppCompatActivity {

    private ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_help);

        webviewer();

    }

    public void webviewer(){
        progress = new ProgressDialog(this);
        progress.setMessage("Memuat Help...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);

        WebChromeClient webChromeClient = new WebChromeClient();
        WebView penampilBantuan = (WebView) findViewById(R.id.help_viewer);
        penampilBantuan.getSettings().setJavaScriptEnabled(true);
        penampilBantuan.setWebChromeClient(webChromeClient);
        penampilBantuan.setWebViewClient(new loaderProgress());
        penampilBantuan.setBackgroundColor(0x00000000);
        penampilBantuan.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        penampilBantuan.loadUrl("file:///android_asset/file_html/help.html");
        progress.show();
    }

    private class loaderProgress extends WebViewClient {
        public void onPageFinished (WebView view, String url) {
            progress.dismiss();
        }
    }

}
