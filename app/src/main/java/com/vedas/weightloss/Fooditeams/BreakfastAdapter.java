package com.vedas.weightloss.Fooditeams;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vedas.weightloss.R;

import java.util.ArrayList;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.ViewHolder> {
    Context context;
    ArrayList<String> itemsList = new ArrayList<>();
    static int selectedPosition = -1;
    public static String selectedLanguage, calories;

    BreakfastAdapter(Context context, ArrayList<String> itemsList )
    {
        this.context = context;
        this.itemsList = itemsList;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breakfast, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemName.setText(itemsList.get(position).toString());







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                Log.e("pos", "" + selectedPosition);

                selectedLanguage = holder.itemName.getText().toString();
                Log.e("text", "" + selectedLanguage);

            //    loadWeightMeasuresSpinner();

                //notifyDataSetChanged();

            }
        });

    }

     /*delete_item_icon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (context instanceof CategoriesActivity) {
                ((CategoriesActivity) context).deleteItemActivity(v, groupPosition, childPosition);
            }

        }
    });*/



    @Override
    public int getItemCount() {
        return itemsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);

        }
    }
    public void getFilter(ArrayList<String> filterList)
    {
        itemsList = new ArrayList<>();
        itemsList.addAll(filterList);
        notifyDataSetChanged();
    }


}
