package com.example.registrationform;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class updateData extends AppCompatActivity {

    TextView name, phonenumber, alamat, infoSeek;
    Button update, delete;
    SeekBar day;
    CheckBox renangchk, basketchk, vollychk;
    RadioGroup groupGender;
    RadioButton maleGdr, femaleGdr;

    String inputnama, inputnomor, inputalamat, gender, olahraga, time, id, jk, sport;
    int  pval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_data);


        name = findViewById(R.id.inputnama2);
        phonenumber = findViewById(R.id.inputnomor2);
        alamat = findViewById(R.id.inputalamat2);
        renangchk = (CheckBox) findViewById(R.id.renangCheckbox2);
        basketchk = (CheckBox) findViewById(R.id.basketCheckbox2);
        vollychk = (CheckBox) findViewById(R.id.vollyCheckbox2);

        update = (Button) findViewById(R.id.update);
        delete  = findViewById(R.id.delete);

        groupGender = (RadioGroup) findViewById(R.id.gender_group2);
        femaleGdr = (RadioButton) findViewById(R.id.femaleGender2);
        maleGdr = (RadioButton) findViewById(R.id.maleGender2);

        day = (SeekBar) findViewById(R.id.seekdDay2);
        day.setProgress(0);
        infoSeek = findViewById(R.id.day2);
        time = "1 jam";
        pval = 1;
        day.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                time = (String.valueOf(pval));
                time = time.concat(" jam");
                infoSeek.setText(time);
            }
        });
        getandSetIntentData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jk = ((RadioButton) findViewById(groupGender.getCheckedRadioButtonId())).getText().toString();
                sport="";
                if (renangchk.isChecked()) {
                    sport = sport + "Renang";
                }
                if (basketchk.isChecked()) {
                    sport = sport + "Basket ";
                }
                if (vollychk.isChecked()) {
                    sport = sport + "Volly ";
                }
                databasehelper db = new databasehelper(updateData.this);
                inputnama = name.getText().toString().trim();
                inputnomor = phonenumber.getText().toString().trim();
                inputalamat = alamat.getText().toString().trim();
                gender = jk.trim();
                olahraga = sport.trim();
                time = infoSeek.getText().toString().trim();
                db.updateData(id, inputnama, inputnomor, inputalamat, gender, olahraga, time);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }


    void getandSetIntentData(){
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("nama")&&getIntent().hasExtra("no_telp")&&getIntent().hasExtra("alamat")&&getIntent().hasExtra("gender")&&getIntent().hasExtra("olahraga")&&getIntent().hasExtra("waktu")){
            id = getIntent().getStringExtra("id");
            inputnama = getIntent().getStringExtra("nama");
            inputnomor = getIntent().getStringExtra("no_telp");
            inputalamat = getIntent().getStringExtra("alamat");
            gender = getIntent().getStringExtra("gender");
            olahraga = getIntent().getStringExtra("olahraga");
            time = getIntent().getStringExtra("waktu");

            name.setText(inputnama);
            phonenumber.setText(inputnomor);
            alamat.setText(inputalamat);
            infoSeek.setText(time);

            String male = "Laki - Laki";
            String female = "Perempuan";
            String renang = "Renang";
            String basket = "Basket";
            String volly = "Volly";
            String renangbasket = "Renang Basket";
            String renangvolly = "Renang Volly";
            String basketvolly = "Basket Volly";
            String renangbasketvolly = "Renang Basket Volly";

            if(gender.equals(male)) {
                groupGender.check(R.id.maleGender2);
            } else if(gender.equals(female)) {
                groupGender.check(R.id.femaleGender2);
            }
            if(olahraga.equals(renang)) {
               renangchk.setChecked(true);
            }else if(olahraga.equals(basket)) {
                basketchk.setChecked(true);
            }else if(olahraga.equals(volly)) {
                vollychk.setChecked(true);
            }else if(olahraga.equals(renangvolly)) {
                vollychk.setChecked(true);
                renangchk.setChecked(true);
            }else if(olahraga.equals(renangbasket)) {
                renangchk.setChecked(true);
                basketchk.setChecked(true);
            }else if(olahraga.equals(basketvolly)) {
                vollychk.setChecked(true);
                basketchk.setChecked(true);
            }else if(olahraga.equals(renangbasketvolly)) {
                renangchk.setChecked(true);
                basketchk.setChecked(true);
                vollychk.setChecked(true);
            }

        }else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus" + inputnama + " ?");
        builder.setMessage("Yakin mau akan menghapus "+ inputnama + " ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databasehelper db = new databasehelper(updateData.this);
                db.deleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}