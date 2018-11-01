package ly.de.drthinkingpatient.web;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ly.de.drthinkingpatient.FullscreenView;
import ly.de.drthinkingpatient.utils.Constants;


/**
 * Created by Ly on 27.09.2017.
 */

public class ClientHandler {

    private FullscreenView fa;
    private WebView web;


    private TextGrabberInterface textGrabberInterface;

    private final static String TAG = "Client";
    private static String cache= "";

    public ClientHandler(FullscreenView fa){
        this.fa = fa;
        web = fa.getWeb();
        textGrabberInterface = new TextGrabberInterface(fa.getApplicationContext());
        setupWebView(web);
    }



    private void setupWebView(WebView web){
        web.addJavascriptInterface(textGrabberInterface, "DrThinking");
    }


    public void loadURL(){
        if(web != null){
            web.getSettings().setJavaScriptEnabled(true);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl(Constants.URL);
        }
    }

   public class TextGrabberInterface{
        private Context context;

        public TextGrabberInterface(Context context){
            this.context = context;
        }

        @JavascriptInterface
       public void getText(String text){
            cache = text;
            Log.d(TAG, "getText: "+text+"; "+cache);
       }

   }
}
