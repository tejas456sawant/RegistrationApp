package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,college,phone,gmail;
    String str_name,str_college,str_phone,str_gmail;
    String[] Events = {"Select Event","Clash Of Cods","Webber"};
    TextView changeAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.nameInput);
        college = (EditText)findViewById(R.id.collegeInput);
        phone = (EditText)findViewById(R.id.phoneInput);
        gmail = (EditText)findViewById(R.id.gmailInput);

        changeAmount = (TextView)findViewById(R.id.amountText);

        final Spinner eSpinner = (Spinner) findViewById(R.id.eventDropdown);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,Events);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eSpinner.setAdapter(mAdapter);
        eSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int sid = eSpinner.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),Events[sid],Toast.LENGTH_SHORT).show();
                if ("Clash Of Cods".equals(Events[sid])) {
                    changeAmount.setText("50 Rs");
                } else if ("Webber".equals(Events[sid])) {
                    changeAmount.setText("10 Rs");
                } else {
                    changeAmount.setText("0 Rs");
                }
            }

            //String areaselected = eSpinner.getSelectedItem().toString();
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getBaseContext(),"   "  ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onReg(View view) {
        str_name = name.getText().toString();
        str_college = college.getText().toString();
        str_phone = phone.getText().toString();
        str_gmail = gmail.getText().toString();
        String type = "Register";

        backgroundWorker Backwork = new backgroundWorker(this);
        Backwork.execute(type,str_name,str_college,str_phone,str_gmail);
    }
}
