package com.shamweel.jsontoforms.viewholder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.textfield.TextInputLayout;
import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.models.JSONModel;
import com.shamweel.jsontoforms.sigleton.DataValueHashMap;

import java.util.List;


public class EditTextViewHolder extends RecyclerView.ViewHolder {

    public TextInputLayout layoutEdittext;


    public EditTextViewHolder(@NonNull View itemView, List<JSONModel> jsonModelList) {
        super(itemView);
        layoutEdittext = itemView.findViewById(R.id.layout_edittext);


        layoutEdittext.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (getAdapterPosition() == -1) {
                    return;
                }

                DataValueHashMap.put(
                        jsonModelList.get(getAdapterPosition()).getId(),
                        editable.toString());

                if (editable.length() > 0 && layoutEdittext.isErrorEnabled()){
                    layoutEdittext.setErrorEnabled(false);
                }

            }
        });

    }

}
