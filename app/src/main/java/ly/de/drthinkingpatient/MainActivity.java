package ly.de.drthinkingpatient;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int TRIES = 5;
    private int counter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }

    public void check(){
        TextView tv = (TextView) findViewById(R.id.main_message);
        tv.setText("Checking network availability...");

        if(isNetworkConnected()){
            counter = 0;
            startFullScreenAct();
        }else{
            if(counter < TRIES){
                counter++;
                check();
            }else{
                tv.setTextColor(Color.RED);
                tv.setText("Please check your network connection!");
            }
        }
    }

    private void startFullScreenAct(){
        Intent intent = new Intent(this, FullscreenView.class);
        startActivity(intent);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
