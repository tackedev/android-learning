package com.tackedev.coffee_shop.activity.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tackedev.coffee_shop.R;
import com.tackedev.coffee_shop.activity.user.UsersActivity;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private List<String> productNames;
    private List<Integer> productImages;
    private List<String> productDescriptions;

    private RecyclerView layoutProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        layoutProducts = findViewById(R.id.layoutProducts);

        productNames = List.of(getResources().getStringArray(R.array.coffeeNames));
        productDescriptions = List.of(getResources().getStringArray(R.array.coffeeDescriptions));
        productImages = List.of(
                R.drawable.coffee_01,
                R.drawable.coffee_02,
                R.drawable.coffee_03,
                R.drawable.coffee_04,
                R.drawable.coffee_05,
                R.drawable.coffee_06,
                R.drawable.coffee_07,
                R.drawable.coffee_08,
                R.drawable.coffee_09
        );

        CoffeeAdapter coffeeAdapter = new CoffeeAdapter(this, productNames, productImages, productDescriptions);
        layoutProducts.setAdapter(coffeeAdapter);
        layoutProducts.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_settings:
                showUser();
                return true;
            case R.id.option_favorites:
                showFavorites();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFavorites() {
        Toast.makeText(ProductListActivity.this, "Do nothing", Toast.LENGTH_LONG).show();
    }

    private void showUser() {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
    }

}