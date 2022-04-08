package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"potekhina15092003@gmail.com"};
    String subject = "Order from Music Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsname = receivedOrderIntent.getStringExtra("goodsname");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);

        emailText = "Customer name:" + userName + "\n" +
                "Goods name: " + goodsname + "\n" +
                "Quantity: " +quantity + "\n" +
                "Price: " +price + "\n" +
                "Order Price: " +orderPrice;

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_EMAIL, addresses);
        intent.putExtra(intent.EXTRA_SUBJECT, subject);
        intent.putExtra(intent.EXTRA_TEXT, emailText);
        Toast.makeText(this,
                "Email sending",
                Toast.LENGTH_SHORT).show();
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }
    }

}