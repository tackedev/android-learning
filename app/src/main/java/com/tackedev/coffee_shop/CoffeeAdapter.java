package com.tackedev.coffee_shop;

import static com.tackedev.coffee_shop.CoffeeDetailActivity.PRODUCT_DESCRIPTION_KEY;
import static com.tackedev.coffee_shop.CoffeeDetailActivity.PRODUCT_IMAGE_KEY;
import static com.tackedev.coffee_shop.CoffeeDetailActivity.PRODUCT_NAME_KEY;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    private final Context ctx;
    private final List<String> productNames;
    private final List<Integer> productImages;
    private final List<String> productDescription;

    public CoffeeAdapter(Context ctx, List<String> productNames, List<Integer> productImages, List<String> productDescription) {
        this.ctx = ctx;
        this.productNames = productNames;
        this.productImages = productImages;
        this.productDescription = productDescription;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.coffee_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvProductName.setText(productNames.get(position));
        viewHolder.imgProducts.setImageResource(productImages.get(position));
    }

    @Override
    public int getItemCount() {
        return productNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProductName;
        private final ImageView imgProducts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            imgProducts = itemView.findViewById(R.id.imgProduct);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(ctx, CoffeeDetailActivity.class);
                int position = getAdapterPosition();
                intent.putExtra(PRODUCT_IMAGE_KEY, productImages.get(position));
                intent.putExtra(PRODUCT_NAME_KEY, productNames.get(position));
                intent.putExtra(PRODUCT_DESCRIPTION_KEY, productDescription.get(position));
                ctx.startActivity(intent);
            });
        }
    }
}
