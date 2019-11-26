package com.shamweel.jsontoforms.viewholder;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shamweel.jsontoforms.FormConstants;
import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.models.JSONModel;
import com.shamweel.jsontoforms.sigleton.DataValueHashMap;

import java.util.List;


public class RadioViewHolder extends RecyclerView.ViewHolder {

    public TextView txtRadio;
    public RadioGroup rGroup;


    public RadioViewHolder(@NonNull View itemView, List<JSONModel> jsonModelList) {
        super(itemView);
        txtRadio = itemView.findViewById(R.id.txt_radio);
        rGroup = itemView.findViewById(R.id.rGroup);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (getAdapterPosition() == -1) {
                    return;
                }
                DataValueHashMap.put(
                        jsonModelList.get(getAdapterPosition()).getId(),
                        rGroup.getCheckedRadioButtonId() == -1 ? FormConstants.EMPTY_STRING : ((RadioButton) itemView.findViewById(rGroup.getCheckedRadioButtonId())).getText().toString());

                if (rGroup.getCheckedRadioButtonId() != -1){
                    for (int j=0; j<radioGroup.getChildCount(); j++){
                        ((RadioButton)rGroup.getChildAt(j)).setError(null);
                    }
                }

                if (itemView.getRootView().findFocus() != null) {
                    itemView.getRootView().findFocus().clearFocus();
                }
            }
        });



    }
}

