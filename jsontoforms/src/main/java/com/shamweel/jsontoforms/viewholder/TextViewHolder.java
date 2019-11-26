package com.shamweel.jsontoforms.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.models.JSONModel;

import java.util.List;


public class TextViewHolder extends RecyclerView.ViewHolder {

    public TextView txtHead;


    public TextViewHolder(@NonNull View itemView, List<JSONModel> jsonModelList) {
        super(itemView);
        txtHead = itemView.findViewById(R.id.txtHead);

    }
}

