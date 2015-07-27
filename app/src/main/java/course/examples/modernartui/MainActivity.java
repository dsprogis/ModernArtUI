package course.examples.modernartui;

import android.net.Uri;
import android.content.Intent;
//import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.Gravity;
//import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static final int alpha = 0xFF000000;
    public static final int red = 0xFF0000;
//    public static final int orange = 0xFFF800;
//    public static final int yellow = 0xFFFF00;
    public static final int green = 0x00FF00;
    public static final int blue = 0x0000FF;
//    public static final int purple = 0x800080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            float progress = 0;
            float regress = 0;
            int mixOrange = 0;
            int mixYellow = 0;
            int mixGreen = 0;
            int mixBlue = 0;
            int mixPurple = 0;

            SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
            //           FrameLayout stripe01 = (FrameLayout) findViewById(R.id.stripe01);
            FrameLayout stripe03 = (FrameLayout) findViewById(R.id.stripe03);
            FrameLayout stripe05 = (FrameLayout) findViewById(R.id.stripe05);
            FrameLayout stripe07 = (FrameLayout) findViewById(R.id.stripe07);
            FrameLayout stripe09 = (FrameLayout) findViewById(R.id.stripe09);
            FrameLayout stripe11 = (FrameLayout) findViewById(R.id.stripe11);

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = (float) progresValue / 100;
                regress = (float) (100 - progresValue) / 100;

                mixOrange = ((int) (progress * red) & red) + ((int) (progress * green * 0.5) & green) + ((int) (regress * red) & red);
                mixYellow = ((int) (progress * red) & red) + ((int) (progress * green) & green) + ((int) (regress * red) & red);
                mixGreen = ((int) (progress * green * 0.5) & green) + ((int) (regress * red) & red);
                mixBlue = ((int) (progress * blue) & blue) + ((int) (regress * red) & red);
                mixPurple = ((int) (progress * red * 0.5) & red) + ((int) (progress * blue * 0.5) & blue) + ((int) (regress * red) & red);

                stripe03.setBackgroundColor(mixOrange + alpha); // Orange
                stripe05.setBackgroundColor(mixYellow + alpha); // Yellow
                stripe07.setBackgroundColor(mixGreen + alpha); // Green
                stripe09.setBackgroundColor(mixBlue + alpha); // Blue
                stripe11.setBackgroundColor(mixPurple + alpha); // Purple

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            AlertDialog.Builder popup = new AlertDialog.Builder(this)
                    .setPositiveButton(R.string.visit_met, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // Intent baseIntent = null;
                            Uri uriWebpage = Uri.parse( "http://www.metmuseum.org/toah/works-of-art/69.701.2" );
                            Intent baseIntent = new Intent(Intent.ACTION_VIEW, uriWebpage);
                            Intent chooserIntent = Intent.createChooser( baseIntent, "CHOOSER" );
                            startActivity( chooserIntent );

                        }
                    })
                    .setNegativeButton(R.string.not_now, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    });

            TextView myMsg = new TextView(this);
            myMsg.setText(R.string.msg_more_art);
            myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
            popup.setView(myMsg);
            popup.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
