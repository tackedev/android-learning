package com.tackedev.coffee_shop.activitity.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tackedev.coffee_shop.R;

public class CoffeeDetailActivity extends AppCompatActivity {
    public static final String PRODUCT_IMAGE_KEY = "PRODUCT_IMAGE_KEY";
    public static final String PRODUCT_NAME_KEY = "PRODUCT_NAME_KEY";
    public static final String PRODUCT_DESCRIPTION_KEY = "PRODUCT_DESCRIPTION_KEY";

    private ImageView imgProduct;
    private TextView tvProductName;
    private TextView tvContactPhone;
    private TextView tvProductDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_detail);

        imgProduct = findViewById(R.id.imgProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvContactPhone = findViewById(R.id.tvContactPhone);
        tvProductDescription = findViewById(R.id.tvProductDescription);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if (intent != null) {
            int imgProductId = intent.getIntExtra(PRODUCT_IMAGE_KEY, R.mipmap.ic_launcher);
            String productName = intent.getStringExtra(PRODUCT_NAME_KEY);
            String productDescription = intent.getStringExtra(PRODUCT_DESCRIPTION_KEY);

            imgProduct.setImageResource(imgProductId);
            tvProductName.setText(productName);
            String phoneNumber = "+79532338";
            tvContactPhone.setText("Phone number: " + phoneNumber);
            tvProductDescription.setText(productDescription);

            tvContactPhone.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                startActivity(callIntent);
            });
        }


    }
}