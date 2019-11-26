package com.shamweel.jsontoforms.viewholder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.models.JSONModel;
import com.shamweel.jsontoforms.sigleton.DataValueHashMap;

import java.util.List;


public class SpinnerViewHolder extends RecyclerView.ViewHolder {

    public TextView txtSpinner;
    public Spinner spinner;


    public SpinnerViewHolder(@NonNull View itemView, List<JSONModel> jsonModelList) {
        super(itemView);
        txtSpinner = itemView.findViewById(R.id.txt_spinner);
        spinner = itemView.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (getAdapterPosition() == -1) {
                    return;
                }

                DataValueHashMap.put(
                        jsonModelList.get(getAdapterPosition()).getId(),
                        spinner.getSelectedItem().toString());

                if (itemView.getRootView().findFocus() != null) {
                    itemView.getRootView().findFocus().clearFocus();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

