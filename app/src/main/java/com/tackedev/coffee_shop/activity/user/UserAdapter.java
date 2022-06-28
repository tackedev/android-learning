package com.tackedev.coffee_shop.activity.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tackedev.coffee_shop.R;
import com.tackedev.coffee_shop.dao.UserDao;
import com.tackedev.coffee_shop.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context ctx;
    private final UserDao userDao;
    private final List<User> users;

    public UserAdapter(Context ctx, UserDao userDao) {
        this.ctx = ctx;
        this.userDao = userDao;
        this.users = userDao.getAll();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvEmail.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvEmail;
        private final Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEmail = itemView.findViewById(R.id.tvEmail);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btnDelete.setOnClickListener(view -> {
                userDao.delete(tvEmail.getText().toString());
                ((AppCompatActivity) ctx).recreate();
            });
        }
    }

}
