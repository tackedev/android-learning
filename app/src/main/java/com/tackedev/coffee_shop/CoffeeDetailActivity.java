package com.tackedev.coffee_shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CoffeeDetailActivity extends AppCompatActivity {
    public static final String PRODUCT_IMAGE_KEY = "PRODUCT_IMAGE_KEY";
    public static final String PRODUCT_NAME_KEY = "PRODUCT_NAME_KEY";
    public static final String PRODUCT_DESCRIPTION_KEY = "PRODUCT_DESCRIPTION_KEY";

    private ImageView imgProduct;
    private TextView tvProductName;
    private TextView tvProductDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_detail);

        imgProduct = findViewById(R.id.imgProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductDescription = findViewById(R.id.tvProductDescription);
    }

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
            tvProductDescription.setText(productDescription);
        }
    }
}