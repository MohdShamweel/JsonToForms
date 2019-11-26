package com.shamweel.jsontoforms.viewholder;

import android.app.DatePickerDialog;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.models.JSONModel;
import com.shamweel.jsontoforms.sigleton.DataValueHashMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class DateViewHolder extends RecyclerView.ViewHolder {

    public TextInputLayout layoutDate;
    public TextInputEditText etdate;


    public DateViewHolder(@NonNull final View itemView, List<JSONModel> jsonModelList) {
        super(itemView);
        layoutDate = (TextInputLayout)itemView.findViewById(R.id.layout_date);
        etdate = (TextInputEditText)itemView.findViewById(R.id.et_date);

        layoutDate.setOnClickListener(view ->
                onDateViewClick(jsonModelList, getAdapterPosition()));
        etdate.setOnClickListener(view -> onDateViewClick(jsonModelList, getAdapterPosition()));

    }



    void onDateViewClick(List<JSONModel> jsonModelList, int position) {

        if (getAdapterPosition() == -1) {
            return;
        }

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yyyy"; // your format
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

            layoutDate.getEditText().setText(sdf.format(myCalendar.getTime()));
            DataValueHashMap.put(jsonModelList.get(position).getId(), sdf.format(myCalendar.getTime()));

        };
        new DatePickerDialog(itemView.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

}
