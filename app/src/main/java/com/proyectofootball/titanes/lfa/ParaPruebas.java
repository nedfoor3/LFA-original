package com.proyectofootball.titanes.lfa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ParaPruebas extends AppCompatActivity {
    private EditText etTestValue, etTestKey, etID;
    private Button btnTest;
    private Firebase mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_pruebas);
        mRootRef = new Firebase("https://lfa-desarrollo.firebaseio.com/Test");
        etTestKey = (EditText)findViewById(R.id.eTKeyForTest);
        etTestValue = (EditText)findViewById(R.id.etTPruebas);
        etID = (EditText)findViewById(R.id.eT_para_id);
        btnTest = (Button)findViewById(R.id.btn_pruebas);


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = etTestValue.getText().toString();
                String keyValue = etTestKey.getText().toString();
                String idValue = etID.getText().toString();
                Firebase mRefChild = mRootRef.child(keyValue);
                //Firebase mRefChild = mRootRef.child("Estatura");
                //mRefChild.setValue(value);
                mRootRef.setValue(value);
            }
        });
/*
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valueRead = dataSnapshot.getValue(String.class);
                tvFire.setText(valueRead);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
    }
}
