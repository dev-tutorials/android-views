package com.example.hannabotar.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mNameInput;
    private EditText mEspressoInput;
    private EditText mCappuccinoInput;

    private Button mOrderButton;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_linear_layout);

//        setContentView(R.layout.activity_relative_layout);

//        setContentView(R.layout.activity_relative_to_others);

//        setContentView(R.layout.activity_nested);

        setContentView(R.layout.activity_user_input);
        setupUserInput();
    }

    private void setupUserInput() {
        setTitle("Coffee Shop");

        mNameInput = (EditText) findViewById(R.id.name_input);
        mEspressoInput = (EditText) findViewById(R.id.espresso_input);
        mCappuccinoInput = (EditText) findViewById(R.id.cappuccino_input);

        mEspressoInput.setText("0");
        mCappuccinoInput.setText("0");

        mOrderButton = findViewById(R.id.order_button);

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(LOG_TAG, "Order button pressed");

                String name = mNameInput.getText().toString();
                String espresso = mEspressoInput.getText().toString();
                String cappucino = mCappuccinoInput.getText().toString();

                // show toast message
                String message = "Thank you, " + name + "!\n" +
                        "You have ordered " + espresso + " espresso(s) and " + cappucino + " cappuccino(s).";

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                mNameInput.setText("");
                mEspressoInput.setText("0");
                mCappuccinoInput.setText("0");


                // send mail
                /*String orderSummary = "- " + espresso + " espresso(s)\n - " + cappucino + " cappucino(s)";
                sendMail(orderSummary, name);*/
            }
        });
    }

    private void sendMail(String orderSummary, String name) {
        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        // Verify that the intent will resolve properly
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
