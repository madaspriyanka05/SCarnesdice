package shubh.scarnesdice;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtuser_score, txtcomp_score;
    ImageView dice_img;
    Button btnroll, btnhold, btnreset;
    int user_score, comp_score;

    public static int cal_val = 0, total_user = 0, total_comp = 0;
    boolean con = true;
    Random r = new Random();

    private int diceIcons[] = {
            R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtuser_score = findViewById(R.id.user_score);
        txtcomp_score = findViewById(R.id.comp_score);
        dice_img = findViewById(R.id.dice_img);
        btnroll = findViewById(R.id.diceroll);
        btnhold = findViewById(R.id.dicehold);
        btnreset = findViewById(R.id.dicereset);
        btnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rolldice();
            }
        });
        btnhold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computer_mode();
            }
        });
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetdice();
            }
        });

    }

    private void rolldice() {

        int i = r.nextInt(7);
        switch (i) {
            case 1:
                cal_val = 0;
                dice_img.setImageResource(R.drawable.dice1);
                user_mode();
                break;
            case 2:
                dice_img.setImageResource(R.drawable.dice2);
                cal_val += 2;
                break;
            case 3:
                dice_img.setImageResource(R.drawable.dice3);
                cal_val += 3;
                break;
            case 4:
                dice_img.setImageResource(R.drawable.dice4);
                cal_val += 4;
                break;
            case 5:
                dice_img.setImageResource(R.drawable.dice5);
                cal_val += 5;
                break;
            case 6:
                dice_img.setImageResource(R.drawable.dice6);
                cal_val += 6;
                break;
        }
    }

    public void user_mode() {
        total_user += cal_val;
        cal_val = 0;
        txtuser_score.setText(total_user + "");
        if (total_user >= 100) {
            Toast.makeText(MainActivity.this, "You Are Winner", Toast.LENGTH_LONG).show();
            onReset(btnreset);
        }
        com_cal();
    }

    public void onHold(View v) {
        btnroll.setEnabled(false);
        btnhold.setEnabled(false);
        user_mode();
    }

    public void onReset(View v) {
        total_comp = 0;
        total_user = 0;
        txtuser_score.setText("0");
        txtcomp_score.setText("0");
        btnhold.setEnabled(true);
        btnroll.setEnabled(true);
        dice_img.setImageResource(R.drawable.dice6);
    }

    Handler h = new Handler();
    Runnable Run = new Runnable() {
        @Override
        public void run() {
            con = true;
            int i = r.nextInt(7);
            switch (i) {
                case 1:
                    cal_val = 0;
                    dice_img.setImageResource(R.drawable.dice1);
                    con = false;
                    break;
                case 2:
                    dice_img.setImageResource(R.drawable.dice2);
                    cal_val += 2;
                    break;
                case 3:
                    dice_img.setImageResource(R.drawable.dice3);
                    cal_val += 3;
                    break;
                case 4:
                    dice_img.setImageResource(R.drawable.dice4);
                    cal_val += 4;
                    break;
                case 5:
                    dice_img.setImageResource(R.drawable.dice5);
                    cal_val += 5;
                    break;
                case 6:
                    dice_img.setImageResource(R.drawable.dice6);
                    cal_val += 6;
                    break;
            }
            if (cal_val < 10 && con)
                h.postDelayed(this, 500);
            else computer_mode();
        }
    };

    public void com_cal() {
        h.postDelayed(Run, 500);
    }

    private void resetdice(){
        user_score=0;
        comp_score=0;
        txtuser_score.setText("0");
        txtcomp_score.setText("0");

    }

    public void computer_mode() {
        total_comp += cal_val;
        cal_val = 0;
        txtcomp_score.setText(total_comp + "");
        if (total_comp >= 100) {
            Toast.makeText(MainActivity.this, "Computer is a Winner", Toast.LENGTH_LONG).show();
            onReset(btnreset);
        }
        btnhold.setEnabled(true);
        btnroll.setEnabled(true);
    }
}