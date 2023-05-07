package com.example.notbasictodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MyAdapter extends ArrayAdapter<DataModel> {

    private List<DataModel> dataModelList;
    private final Context context;

    public MyAdapter(Context context, List<DataModel> dataModelList) {
        super(context, R.layout.itemlist, dataModelList);
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.itemlist, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.Name);
        CheckBox checkBox=convertView.findViewById(R.id.checkBox);
        DBHandler DBHandler = new DBHandler(getContext());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(b==true){
                   DBHandler.updateIsDone(dataModelList.get(position).getId(),"1");
               }else{
                   DBHandler.updateIsDone(dataModelList.get(position).getId(),"0");
               }
            }
        });



        System.out.println(dataModelList.get(position).getisDone());

        // Set the values of the TextViews from the DataModel object
        nameTextView.setText(dataModelList.get(position).getTask());
        if (Integer.parseInt(dataModelList.get(position).getisDone()) == 0) {
            checkBox.setChecked(false);
        }else {
            checkBox.setChecked(true);
        }

        return convertView;
    }
}