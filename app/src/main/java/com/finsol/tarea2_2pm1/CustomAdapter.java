package com.finsol.tarea2_2pm1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Datos> implements View.OnClickListener
{
    private List<Datos> dataset;
    private Context mContext;

    public static class ViewHolder{
        TextView userId, id, title;
    }

    public CustomAdapter(List<Datos> data, Context context) {
        super(context, R.layout.mylist, data);

        this.dataset = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        Object object = getItem(position);

        Datos datModel = (Datos) object;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Datos datosModel = getItem(position);
        ViewHolder viewHolder = null;
        final View Result;

        if(convertView == null){
            viewHolder = new ViewHolder();


            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.mylist, parent, false);

            viewHolder.userId = convertView.findViewById(R.id.txtUserID);
            viewHolder.id = convertView.findViewById(R.id.txtID);
            viewHolder.title = convertView.findViewById(R.id.txtTitle);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();
            convertView.setTag(viewHolder);
        }

        viewHolder.userId.setText("User ID: "+String.valueOf(datosModel.getUserId()));
        viewHolder.id.setText("ID: "+String.valueOf(datosModel.getId()));
        viewHolder.title.setText("Title: "+datosModel.getTitle());

        return convertView;
    }
}
