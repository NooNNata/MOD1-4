package com.example.registrationform;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class tambahData extends AppCompatActivity {
    TextView name, phonenumber, alamat, infoSeek;
    Button save;
    SeekBar day;
    CheckBox renangchk, basketchk, vollychk;
    RadioGroup groupGender;
    RadioButton maleGdr, femaleGdr;
    Button cancelDialog, submitDialog;

    TextView showname, shownumber, showalamat, showgender, showsolahraga, showtime;

    String inputnama, inputnomor, inputalamat, gender, olahraga, time;
    int statusForm, pval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tambah);


        name = (TextView) findViewById(R.id.inputnama);
        phonenumber = (TextView) findViewById(R.id.inputnomor);
        alamat = (TextView) findViewById(R.id.inputalamat);
        renangchk = (CheckBox) findViewById(R.id.renangCheckbox);
        basketchk = (CheckBox) findViewById(R.id.basketCheckbox);
        vollychk = (CheckBox) findViewById(R.id.vollyCheckbox);

        save = (Button) findViewById(R.id.submit);

        groupGender = (RadioGroup) findViewById(R.id.gender_group);
        femaleGdr = (RadioButton) findViewById(R.id.femaleGender);
        maleGdr = (RadioButton) findViewById(R.id.maleGender);

        day = (SeekBar) findViewById(R.id.seekDay);
        day.setProgress(0);
        infoSeek = (TextView) findViewById(R.id.day);
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


        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                statusForm = 0;
                bacaNama();
                bacaNomor();
                bacaAlamat();
                bacaGender();
                bacaOlahraga();

                if (statusForm == 5) {
                    DialogForm();
                } else {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast informasikurang = Toast.makeText(context, "Data Tidak valid.", duration);
                    informasikurang.show();
                }
            }
        });
    }

    //Baca Nama
    public void bacaNama() {
        inputnama = name.getText().toString();
        if (TextUtils.isEmpty(inputnama)) {
            name.setError("Tolong Masukkan Nama!");
        } else {
            name.setError(null);
            statusForm = statusForm + 1;
        }
    }

    public void bacaNomor() {
        inputnomor = phonenumber.getText().toString();
        if (TextUtils.isEmpty(inputnomor)) {
            phonenumber.setError("Tolong Masukkan Nomor Telepon!");
        } else {
            phonenumber.setError(null);
            statusForm = statusForm + 1;
        }
    }

    //Baca Username
    public void bacaAlamat() {
        inputalamat = alamat.getText().toString();
        if (TextUtils.isEmpty(inputalamat)) {
            alamat.setError("Tolong Masukkan Alamat!");

        } else {
            statusForm = statusForm + 1;
            alamat.setError(null);
        }
    }

    //Baca Radio Button Gender
    public void bacaGender() {
        if (groupGender.getCheckedRadioButtonId() != -1) {
            femaleGdr.setError(null);
            statusForm = statusForm + 1;
            gender = ((RadioButton) findViewById(groupGender.getCheckedRadioButtonId())).getText().toString();
        } else {
            femaleGdr.setError("Pilih Jenis Kelamin!");
        }
    }

    //Baca Checkbox
    public void bacaOlahraga() {
        olahraga = "";
        if (renangchk.isChecked() || basketchk.isChecked() || vollychk.isChecked()) {
            vollychk.setError(null);
            statusForm = statusForm + 1;
        } else {
            vollychk.setError("Pilih Kendaraan yang ingin disewa!");
        }
        if (renangchk.isChecked()) {
            olahraga = olahraga + "Renang ";
        }
        if (basketchk.isChecked()) {
            olahraga = olahraga + "Basket ";
        }
        if (vollychk.isChecked()) {
            olahraga = olahraga + "Volly ";
        }
    }

    private void DialogForm() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialaog_regiter, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        showname = (TextView) dialogView.findViewById(R.id.isinama);
        shownumber = (TextView) dialogView.findViewById(R.id.isinomor);
        showalamat = (TextView) dialogView.findViewById(R.id.isialamat);
        showgender = (TextView) dialogView.findViewById(R.id.isigender);
        showsolahraga = (TextView) dialogView.findViewById(R.id.isiolahraga);
        showtime = (TextView) dialogView.findViewById(R.id.isiwaktu);
        showname.setText(inputnama);
        shownumber.setText(inputnomor);
        showalamat.setText(inputalamat);
        showgender.setText(gender);
        showsolahraga.setText(olahraga);
        showtime.setText(time);

        cancelDialog = (Button) dialogView.findViewById(R.id.batal);
        submitDialog = (Button) dialogView.findViewById(R.id.kirim);

        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        submitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databasehelper db = new databasehelper(tambahData.this);
                db.addBook(inputnama.trim(), inputnomor.trim(), inputalamat.trim(), gender.trim(), olahraga.trim(), time.trim());
                Intent intent = new Intent(tambahData.this, MainActivity.class);
                startActivity(intent);

            }
        });

        dialog.show();

    }

}