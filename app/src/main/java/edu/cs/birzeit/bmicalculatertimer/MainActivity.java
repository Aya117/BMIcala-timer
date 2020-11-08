package edu.cs.birzeit.bmicalculatertimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String Name="Name";
    public static final String Height="Height";
    public static final String Weight="Weight";
    public static final String Gender="Gender";
    public static final String Flag="Flag";
    private EditText edtName;
    private EditText edtheight;
    private EditText edtWeight;
    private TextView edtBMI;
    private Spinner spinner;
    private CheckBox chk;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPrefs();
        checkprefs();

    }

    private void checkprefs(){
        boolean flag=prefs.getBoolean(Flag,false);
        if(flag){
            String name=prefs.getString(Name,"");
            String height=prefs.getString(Height,"");
            String weight=prefs.getString(Weight,"");
            String gender=prefs.getString(Gender,"");
            edtName.setText(name);
            edtheight.setText(height);
            edtWeight.setText(weight);
        //    spinner.setSelection(gender);
            chk.setChecked(true);
        }
    }

    private void setupSharedPrefs(){
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }

    private void setupViews(){
        edtName=findViewById(R.id.edtName);
        edtheight=findViewById(R.id.edtheight);
        edtWeight=findViewById(R.id.edtWeight);
       spinner=findViewById(R.id.spinner);
        edtBMI=findViewById(R.id.edtBMI);
        chk=findViewById(R.id.chk);
    }

    public void btnSaveOnClick(View view) {
        String name=edtName.getText().toString();
        String height=edtheight.getText().toString();
        String weight=edtWeight.getText().toString();
        String spinn=spinner.getSelectedItem().toString();

        float he=Float.parseFloat(weight);
        float we=Float.parseFloat(height)/100;
        float BMI=he/(we * we);
        String s;
        if(BMI<18.5) {
            s = "the Body mass index = "+BMI+",,ohh its underweight you must eat more ";
            edtBMI.setText(s);
        }
        else if (BMI<25 && BMI >18.5) {
            s = "the Body mass index =" + BMI + ",,ohh its normal";
            edtBMI.setText(s);
        }
        else if (BMI<30 && BMI >25) {
            s="the Body mass index = " + BMI + ",,ohh its overweight you must eat less";
            edtBMI.setText(s);
        }
        else {
            s="the Body mass index = " + BMI + ",,o" +
                    "ops its  obese you must do Diet tracking";
            edtBMI.setText(s);
        }
        if (chk.isChecked()) {
            editor.putString(Name, name);
            editor.putString(Height, height);
            editor.putString(Weight, weight);
            editor.putString(Gender,spinn);
            editor.putBoolean(Flag, true);
            editor.commit();
        }
    }

    public void btnTimer(View view) {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}