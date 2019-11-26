package com.shamweel.jsontoforms.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.interfaces.JsonToFormClickListener;
import com.shamweel.jsontoforms.models.JSONModel;

import java.util.List;



public class SubmitButtonHolder extends RecyclerView.ViewHolder {

    public ExtendedFloatingActionButton btnSubmit;


    public SubmitButtonHolder(@NonNull View itemView, List<JSONModel> jsonModelList, JsonToFormClickListener mSubmitBtnListener) {
        super(itemView);
        btnSubmit = itemView.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getAdapterPosition() == -1) {
                    return;
                }

                if (itemView.getRootView().findFocus() != null) {
                    itemView.getRootView().findFocus().clearFocus();
                }
                mSubmitBtnListener.onSubmitButtonClick();
            }
        });

    }
}

