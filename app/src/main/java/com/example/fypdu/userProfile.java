package com.example.fypdu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        logout = (Button) findViewById(R.id.logoutBtn);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(userProfile.this, MainActivity.class));
                }
            });

            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("users");
            userID = user.getUid();

            final TextView introMsgTextView = (TextView) findViewById(R.id.introMsg);
        final TextView fNameTextView = (TextView) findViewById(R.id.fName);
        final TextView emailTextView = (TextView) findViewById(R.id.email);
        final TextView ageTextView = (TextView) findViewById(R.id.age);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);
                if (userProfile != null){
                    String fname = userProfile.name;
                    String email = userProfile.email;
                    String age = userProfile.age;

                    introMsgTextView.setText("welcome " + fname);
                    fNameTextView.setText(fname);
                    ageTextView.setText(age);
                    emailTextView.setText(email);





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(userProfile.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}