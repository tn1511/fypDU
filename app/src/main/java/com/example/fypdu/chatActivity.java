package com.example.fypdu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class chatActivity extends AppCompatActivity {

    private Button sendMessageBtn;
    private ScrollView mScrollView;
    private EditText userMessage;
    private TextView displayMessages, gcName;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef, groupChatRef, gMessageKeyRef;

    private String currentGcName, currentUserID, currentUserName, currentDate, currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        currentGcName = getIntent().getExtras().get("groupName").toString();
        Toast.makeText(chatActivity.this, currentGcName, Toast.LENGTH_LONG).show();
        //gets current user and there ID from firebase
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");
        groupChatRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGcName);



        
        initalizeFields();

        userInfo();

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override//send button when buttons clicked
            public void onClick(View view) {
                saveMessageToDb();

                userMessage.setText("");

            }
        });

        
    }

    private void saveMessageToDb() {
        ///sends messages to the database
        String message = userMessage.getText().toString();

        String messageKey = groupChatRef.push().getKey();

        if (TextUtils.isEmpty(message)){
            Toast.makeText(chatActivity.this, "Please Enter A Message Before Sending", Toast.LENGTH_LONG).show();
        } else {
        //gets date for the messages
            Calendar date = Calendar.getInstance();
            //sets the format of the date to day/month/year
            SimpleDateFormat currentDateF = new SimpleDateFormat("dd MMM, yyyy");
            currentDate = currentDateF.format(date.getTime());

            //for the time message was sent
            Calendar time = Calendar.getInstance();
            SimpleDateFormat currentTimeF = new SimpleDateFormat("hh:mm a");
            currentTime = currentTimeF.format(time.getTime());


            HashMap<String, Object> gcKey = new HashMap<>();
            groupChatRef.updateChildren(gcKey);
            gMessageKeyRef = groupChatRef.child(messageKey);

            HashMap<String, Object> messageInfo = new HashMap<>();
            messageInfo.put("name", currentUserName);
            messageInfo.put("message", message);
            messageInfo.put("date", currentDate);
            messageInfo.put("time", currentTime);


            gMessageKeyRef.updateChildren(messageInfo);




        }

    }

    private void userInfo() {
        //this function gets the users information
        userRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                //checks usewr exists or not
                    currentUserName = snapshot.child("name").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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