package ly.de.drthinkingpatient;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import ly.de.drthinkingpatient.web.ClientHandler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenView extends AppCompatActivity {



    private WebView web;
    private WebChromeClient cc;
    private ClientHandler handler;
    private boolean doubleBackToExitPressedOnce = false;



    private final static String TAG = "FullscreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen_view);



        web = (WebView) (findViewById(R.id.web_view));
        cc = new WebChromeClient();
        setupWebView(web);
        handler = new ClientHandler(this);
        handler.loadURL();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void setupWebView(WebView web){
        web.getSettings().setBuiltInZoomControls(false);
        web.setNestedScrollingEnabled(true);
        web.getSettings().setDisplayZoomControls(false);
    }


    public WebView getWeb() {
        return web;
    }

}
