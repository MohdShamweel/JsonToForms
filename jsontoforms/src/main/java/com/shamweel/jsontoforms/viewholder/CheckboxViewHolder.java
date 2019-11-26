package com.shamweel.jsontoforms.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.models.JSONModel;
import com.shamweel.jsontoforms.sigleton.DataValueHashMap;

import java.util.List;


public class CheckboxViewHolder extends RecyclerView.ViewHolder {

    public CheckBox checkBox;


    public CheckboxViewHolder(@NonNull View itemView, List<JSONModel> jsonModelList) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (getAdapterPosition() == -1) {
                    return;
                }

                if (b){
                    DataValueHashMap.put(
                            jsonModelList.get(getAdapterPosition()).getId(),
                           "1");

                    checkBox.setError(null);


                }else{
                    DataValueHashMap.put(
                            jsonModelList.get(getAdapterPosition()).getId(),
                            "0");

                }
                if (itemView.getRootView().findFocus() != null) {
                    itemView.getRootView().findFocus().clearFocus();
                }
            }
        });
    }
}

