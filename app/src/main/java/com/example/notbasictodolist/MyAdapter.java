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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

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
TextView DateText=convertView.findViewById(R.id.DateText);
        TextView nameTextView = convertView.findViewById(R.id.Name);
        CheckBox checkBox=convertView.findViewById(R.id.checkBox);
        DBHandler DBHandler = new DBHandler(getContext());

NotificationHandler notificationHandler=new NotificationHandler();

DateUtils dateUtils=new DateUtils();

        System.out.println();
        String date = null;
        date = dateUtils.getDateOneDayBefore(dataModelList.get(position).getisDate());
        NotificationScheduler.scheduleNotification(context, "", dataModelList.get(position).getTask(), date);



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
        DateText.setText("End Date:"+dataModelList.get(position).getisDate());
        if (Integer.parseInt(dataModelList.get(position).getisDone()) == 0) {
            checkBox.setChecked(false);
        }else {
            checkBox.setChecked(true);
        }

        return convertView;
    }
}