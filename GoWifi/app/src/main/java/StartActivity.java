import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wifidirectchat.MainActivity;
import com.example.wifidirectchat.R;


public class StartActivity extends Activity{


    private ImageView imgblue;
    private ImageView imgwifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        imgblue = (ImageView) findViewById(R.id.imgbluetooth);
        imgwifi = (ImageView) findViewById(R.id.imgwifidirect);


        imgblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        imgblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.wifidirectchat.MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
