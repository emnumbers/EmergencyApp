package com.c4k.emnumbers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GoverRecyclers extends RecyclerView.Adapter {
    private Context context;
    private String[] govers;
    private int lastPosition=-1;

    public GoverRecyclers(Context context, String[] govers) {
        this.context = context;
        this.govers = govers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.govers_item, null);
        return new GoverHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GoverHolder goverHolder = (GoverHolder) viewHolder;
        goverHolder.goverText.setText(govers[i]);
        if (goverHolder.getAdapterPosition() > lastPosition) {
            AnimationClass.Animate(goverHolder, true);
        } else {
            AnimationClass.Animate(goverHolder, false);
        }
        lastPosition = goverHolder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return govers.length;
    }
    boolean LoadFragment(Fragment fragment) {
        if (fragment != null) {
            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
            return true;
        }
        return false;
    }
    class GoverHolder extends RecyclerView.ViewHolder {

        TextView goverText;

        GoverHolder(@NonNull View itemView) {
            super(itemView);
            goverText = itemView.findViewById(R.id.goverTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // start sections activity
                    Fragment fragment=new NumbersFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("gover_name",goverText.getText().toString());
                    fragment.setArguments(bundle);
                    LoadFragment(fragment);
                }
            });
        }
    }
}