package com.shamweel.jsontoforms.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.interfaces.JsonToFormClickListener;
import com.shamweel.jsontoforms.models.JSONModel;

import java.util.List;


public class AddAgainButtonHolder extends RecyclerView.ViewHolder {

    public ExtendedFloatingActionButton btnAddAgain;


    public AddAgainButtonHolder(@NonNull View itemView, List<JSONModel> jsonModelList, JsonToFormClickListener jsonToFormClickListener) {
        super(itemView);
        btnAddAgain = itemView.findViewById(R.id.btn_add_again);

        btnAddAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getAdapterPosition() == -1) {
                    return;
                }
                jsonToFormClickListener.onAddAgainButtonClick();
            }
        });

    }
}

