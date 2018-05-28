package com.c4k.emnumbers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ENumbersAdapter extends ArrayAdapter <ENumbersTable>{

    private Context context;
    CallPhoneClass callPhoneClass;
    String phoneNumber;

    ENumbersAdapter(Context context, int resource,List<ENumbersTable> eNumbersTableList) {
        super(context, resource,eNumbersTableList);

        this.context = context;
        callPhoneClass = new CallPhoneClass(this.context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view =LayoutInflater.from(context).inflate(R.layout.listitem, null);
        }

        final ENumbersTable eNumbersTableItem = getItem(position);

        //if (item!=null){
        TextView description = view.findViewById(R.id.descTextView);
        Button callButton = view.findViewById(R.id.callButton);
        description.setText(eNumbersTableItem.getDescription()+" "+eNumbersTableItem.getPhoneNumber());

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "JF-Flat-regular.otf");
        description.setTypeface(tf);
        callButton.setTypeface(tf);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNumber=Long.toString(eNumbersTableItem.getPhoneNumber());
                callPhoneClass.MakePhoneCall(phoneNumber);
            }
        });
        return view;
    }
}