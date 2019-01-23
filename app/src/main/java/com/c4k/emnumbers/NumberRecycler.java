package com.c4k.emnumbers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class NumberRecycler extends RecyclerView.Adapter {
    CallPhoneClass callPhoneClass;
    String phoneNumber;
    Context context;
    private int lastPosition = -1;
    private List<ENumbersTable> eNumbersTableList;

    NumberRecycler(Context context, List<ENumbersTable> eNumbersTableList) {
        this.context = context;
        this.callPhoneClass = new CallPhoneClass(this.context);
        this.eNumbersTableList = eNumbersTableList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.numbers_item, viewGroup, false);
        return new NumbersHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        NumbersHolder holder = (NumbersHolder) viewHolder;
        holder.description.setText(eNumbersTableList.get(i).getDescription() + " " + eNumbersTableList.get(i).getPhoneNumber());
        //setAnimation(holder.itemView, i);
        if (i > lastPosition) {
            AnimationClass.Animate(holder, true);
        } else {
            AnimationClass.Animate(holder, false);
        }
        lastPosition = i;
    }

    @Override
    public int getItemCount() {
        return eNumbersTableList.size();
    }

    public class NumbersHolder extends RecyclerView.ViewHolder {

        public TextView description;
        public Button callButton;

        NumbersHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.descTextView);
            callButton = itemView.findViewById(R.id.callButton);
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phoneNumber = Long.toString(eNumbersTableList.get(getAdapterPosition()).getPhoneNumber());
                    callPhoneClass.MakePhoneCall(phoneNumber);
                }
            });
        }
    }
}