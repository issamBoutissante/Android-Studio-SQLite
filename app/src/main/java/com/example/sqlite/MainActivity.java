package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText modelTXT,colorTXT;
    Button saveDataBtn,showRowCountBtn,showAllBtn;
    private ListView carsListView;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelTXT=findViewById(R.id.model);
        colorTXT=findViewById(R.id.color);
        saveDataBtn=findViewById(R.id.saveData);
        showRowCountBtn=findViewById(R.id.showCount);
        showAllBtn=findViewById(R.id.showAll);
        carsListView=findViewById(R.id.carsList);
        db=new DataBase(this);
        saveDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(modelTXT.getText().toString()) || TextUtils.isEmpty(colorTXT.getText().toString())) {
                    Toast.makeText(getBaseContext(),"you have to fill the fields",Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isInserted = db.insert(new Car(modelTXT.getText().toString(), colorTXT.getText().toString()));
                if (isInserted) {
                    Toast.makeText(getBaseContext(), "insertion Succesed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(),"Error Occured", Toast.LENGTH_LONG).show();
                }
            }
        });
        showRowCountBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                long rowCount=db.getCarCount();
                Toast.makeText(getBaseContext(),"Count = "+rowCount,Toast.LENGTH_LONG).show();
            }
        });
        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarsListAdapter adapter=new CarsListAdapter(getBaseContext(),db.getAllCars());
                carsListView.setAdapter(adapter);
            }
        });
    }
}