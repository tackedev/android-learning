package com.tackedev.coffee_shop.activitity.product;

import static com.tackedev.coffee_shop.activitity.product.CoffeeDetailActivity.PRODUCT_DESCRIPTION_KEY;
import static com.tackedev.coffee_shop.activitity.product.CoffeeDetailActivity.PRODUCT_IMAGE_KEY;
import static com.tackedev.coffee_shop.activitity.product.CoffeeDetailActivity.PRODUCT_NAME_KEY;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tackedev.coffee_shop.R;
import com.tackedev.coffee_shop.activitity.cart.CartActivity;

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
        private final Button btnAddToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            imgProducts = itemView.findViewById(R.id.imgProduct);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(ctx, CoffeeDetailActivity.class);
                int position = getAdapterPosition();
                intent.putExtra(PRODUCT_IMAGE_KEY, productImages.get(position));
                intent.putExtra(PRODUCT_NAME_KEY, productNames.get(position));
                intent.putExtra(PRODUCT_DESCRIPTION_KEY, productDescription.get(position));
                ctx.startActivity(intent);
            });

            btnAddToCart.setOnClickListener(view -> {
                Intent cartIntent = new Intent(ctx, CartActivity.class);
                PendingIntent intent = PendingIntent.getActivity(ctx, 0, cartIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                String channelId = "Add to cart channel";
                String channelName = "Add to cart channel";

                Notification notification = new NotificationCompat.Builder(ctx, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Add new product to cart")
                        .setContentText("Add a " + tvProductName.getText().toString() + " to cart")
                        .setContentIntent(intent)
                        .build();

                NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);

                notificationManager.notify(0, notification);
            });
        }
    }
}
