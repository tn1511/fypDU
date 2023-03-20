package com.example.fypdu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class chatActivity extends AppCompatActivity {

    private Button sendMessageBtn;
    private ScrollView mScrollView;
    private EditText userMessage;
    private TextView displayMessages, gcName;

    private String currentGcName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        currentGcName = getIntent().getExtras().get("groupName").toString();
        Toast.makeText(chatActivity.this, currentGcName, Toast.LENGTH_LONG).show();
        
        initalizeFields();
        
    }

    private void initalizeFields() {
        sendMessageBtn = (Button) findViewById(R.id.sendMessageBtn);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        userMessage = (EditText) findViewById(R.id.inputMessage);
        displayMessages = (TextView) findViewById(R.id.textDisplay);
        gcName = (TextView) findViewById(R.id.groupName);
        gcName.setText(currentGcName);

    }
}