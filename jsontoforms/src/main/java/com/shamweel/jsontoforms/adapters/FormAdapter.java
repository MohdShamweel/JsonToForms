package com.shamweel.jsontoforms.adapters;


import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shamweel.jsontoforms.FormConstants;
import com.shamweel.jsontoforms.R;
import com.shamweel.jsontoforms.interfaces.JsonToFormClickListener;
import com.shamweel.jsontoforms.models.JSONModel;
import com.shamweel.jsontoforms.sigleton.DataValueHashMap;
import com.shamweel.jsontoforms.viewholder.AddAgainButtonHolder;
import com.shamweel.jsontoforms.viewholder.CheckboxViewHolder;
import com.shamweel.jsontoforms.viewholder.DateViewHolder;
import com.shamweel.jsontoforms.viewholder.EditTextViewHolder;
import com.shamweel.jsontoforms.viewholder.RadioViewHolder;
import com.shamweel.jsontoforms.viewholder.SpaceViewHolder;
import com.shamweel.jsontoforms.viewholder.SpinnerViewHolder;
import com.shamweel.jsontoforms.viewholder.SubmitButtonHolder;
import com.shamweel.jsontoforms.viewholder.TextViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<JSONModel> jsonModelList;
    Context mContext;
    public static HashMap<String, String> keyValueHashMap = new HashMap<>();
    JsonToFormClickListener jsonToFormClickListener;


    public FormAdapter(List<JSONModel> jsonModelList,
                       Context context,
                       JsonToFormClickListener jsonToFormClickListener) {
        this.jsonModelList = jsonModelList;
        this.mContext = context;
        this.jsonToFormClickListener = jsonToFormClickListener;
    }

    @Override
    public void onViewAttachedToWindow(final RecyclerView.ViewHolder holder) {
        holder.setIsRecyclable(false);
        super.onViewAttachedToWindow(holder);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == FormConstants.TYPE_TEXT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_textview, viewGroup, false);
            return new TextViewHolder(view, jsonModelList);
        } else if (viewType == FormConstants.TYPE_EDITTEXT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_edittext, viewGroup, false);
            return new EditTextViewHolder(view, jsonModelList);
        } else if (viewType == FormConstants.TYPE_SPINNER) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, viewGroup, false);
            return new SpinnerViewHolder(view, jsonModelList);
        } else if (viewType == FormConstants.TYPE_RADIO) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_radio, viewGroup, false);
            return new RadioViewHolder(view, jsonModelList);
        } else if (viewType == FormConstants.TYPE_SPACE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_space, viewGroup, false);
            return new SpaceViewHolder(view, jsonModelList);
        } else if (viewType == FormConstants.TYPE_DATE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_date, viewGroup, false);
            return new DateViewHolder(view, jsonModelList);
        } else if (viewType == FormConstants.TYPE_SUBMIT_BUTTON) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_submit_button, viewGroup, false);
            return new SubmitButtonHolder(view, jsonModelList, jsonToFormClickListener);
        } else if (viewType == FormConstants.TYPE_CHECKBOX) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_checkbox, viewGroup, false);
            return new CheckboxViewHolder(view, jsonModelList);
        } else if (viewType == FormConstants.TYPE_ADD_AGAIN_BUTTON) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_add_again, viewGroup, false);
            return new AddAgainButtonHolder(view, jsonModelList, jsonToFormClickListener);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_space, viewGroup, false);
            return new SpaceViewHolder(view, jsonModelList);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        if (position == -1) {
            return;
        }

        if (holder instanceof TextViewHolder) {
            bindTextView((TextViewHolder) holder, position);
        } else if (holder instanceof EditTextViewHolder) {
            bindEditText((EditTextViewHolder) holder, position);
        } else if (holder instanceof SpinnerViewHolder) {
            bindSpinner((SpinnerViewHolder) holder, position);
        } else if (holder instanceof RadioViewHolder) {
            bindRadio((RadioViewHolder) holder, position);
        } else if (holder instanceof SpaceViewHolder) {
            bindSpace((SpaceViewHolder) holder, position);
        } else if (holder instanceof DateViewHolder) {
            bindDate((DateViewHolder) holder, position);
        } else if (holder instanceof SubmitButtonHolder) {
            bindSubmitButton((SubmitButtonHolder) holder, position);
        } else if (holder instanceof CheckboxViewHolder) {
            bindCheckBox((CheckboxViewHolder) holder, position);
        } else if (holder instanceof AddAgainButtonHolder) {
            btnAddAgainButton((AddAgainButtonHolder) holder, position);
        }

    }

    private void btnAddAgainButton(AddAgainButtonHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
        holder.btnAddAgain.setText(jsonModel.getText());
    }

    private void bindCheckBox(CheckboxViewHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
        holder.checkBox.setText(jsonModel.getText());
        if (DataValueHashMap.getValue(jsonModel.getId()).equals("1")) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        if (position != -1 && DataValueHashMap.getValue(jsonModelList.get(position).getId()).equalsIgnoreCase(FormConstants.EMPTY_STRING)) {
            DataValueHashMap.put(
                    jsonModelList.get(position).getId(),
                    !holder.checkBox.isChecked() ? FormConstants.EMPTY_STRING : holder.checkBox.getText().toString());
        }
    }

    private void bindSubmitButton(SubmitButtonHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
        holder.btnSubmit.setText(jsonModel.getText());
    }

    private void bindDate(DateViewHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
        holder.layoutDate.setHint(jsonModel.getText());

        if (!DataValueHashMap.getValue(jsonModel.getId()).isEmpty()) {
            holder.layoutDate.getEditText().setText(DataValueHashMap.getValue(jsonModel.getId()));
        } else {
            holder.layoutDate.getEditText().setText(FormConstants.EMPTY_STRING);
        }
    }

    private void bindSpace(SpaceViewHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
    }

    private void bindRadio(RadioViewHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
        holder.txtRadio.setText(jsonModel.getText());


        holder.rGroup.removeAllViews();
        for (int i = 0; i < jsonModel.getList().size(); i++) {
            RadioButton radioButton = new RadioButton(mContext);
            radioButton.setText(jsonModel.getList().get(i).getIndexText());
            radioButton.setId(jsonModel.getList().get(i).getIndex());
            radioButton.setButtonDrawable(R.drawable.toggle_states);
            radioButton.setPadding(16, 8, 8, 8);
            radioButton.setTextColor(mContext.getResources().getColor(R.color.grey_80));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                radioButton.setBackgroundTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
            }
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(16, 8, 8, 8);

            holder.rGroup.addView(radioButton, params);

        }

        if (position != -1 && DataValueHashMap.getValue(jsonModelList.get(position).getId()).equalsIgnoreCase(FormConstants.EMPTY_STRING)) {
            DataValueHashMap.put(
                    jsonModelList.get(position).getId(),
                    holder.rGroup.getCheckedRadioButtonId() == -1 ? FormConstants.EMPTY_STRING : ((RadioButton) holder.itemView.findViewById(holder.rGroup.getCheckedRadioButtonId())).getText().toString());

        }


        outer:
        for (int i = 0; i < holder.rGroup.getChildCount(); i++) {
            int id = holder.rGroup.getChildAt(i).getId();
            RadioButton radioButton = holder.rGroup.findViewById(id);
            if (!DataValueHashMap.getValue(jsonModel.getId()).isEmpty()) {
                if (radioButton.getText().toString().equalsIgnoreCase(DataValueHashMap.getValue(jsonModel.getId()))) {
                    holder.rGroup.clearCheck();
                    radioButton.setChecked(true);
                    break outer;
                }
            }
        }
    }

    private void bindSpinner(SpinnerViewHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
        holder.txtSpinner.setText(jsonModel.getText());
        List<String> categoriesSpin = new ArrayList<String>();
        for (int i = 0; i < jsonModel.getList().size(); i++) {
            categoriesSpin.add(jsonModel.getList().get(i).getIndexText());
        }
        ArrayAdapter<String> dataAdapterVisit;
        dataAdapterVisit = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, categoriesSpin);
        dataAdapterVisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(dataAdapterVisit);

        if (!DataValueHashMap.getValue(jsonModel.getId()).isEmpty()) {
            int spinnerPosition = dataAdapterVisit.getPosition(DataValueHashMap.getValue(jsonModel.getId()));
            holder.spinner.setSelection(spinnerPosition);
        } else {
            holder.spinner.setSelection(0);
        }
    }

    private void bindEditText(EditTextViewHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);


        holder.layoutEdittext.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("KEYPADPressed", String.valueOf(position));
                }
                return false;
            }
        });

        holder.layoutEdittext.setHint(jsonModel.getText());

        if (jsonModel.getMaxLength() != null) {
            holder.layoutEdittext.getEditText().setFilters(new InputFilter[] {new InputFilter.LengthFilter(jsonModel.getMaxLength())});
        }else{
            holder.layoutEdittext.getEditText().setFilters(new InputFilter[] {new InputFilter.LengthFilter(100)});
        }

        if (jsonModel.getInputType() != null && jsonModel.getInputType().equalsIgnoreCase(FormConstants.INPUT_TYPE_NUMBER)) {
            holder.layoutEdittext.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (jsonModel.getInputType() != null && jsonModel.getInputType().equalsIgnoreCase(FormConstants.INPUT_TYPE_NUMBER_DECIMAL)) {
            holder.layoutEdittext.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }else{
            holder.layoutEdittext.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        }

        if (jsonModel.getEditable() != null && !jsonModel.getEditable()) {
            holder.layoutEdittext.setEnabled(false);
        } else {
            holder.layoutEdittext.setEnabled(true);
        }

        if (!DataValueHashMap.getValue(jsonModel.getId()).isEmpty()) {
            holder.layoutEdittext.getEditText().setText(DataValueHashMap.getValue(jsonModel.getId()));
        } else {
            holder.layoutEdittext.getEditText().setText(FormConstants.EMPTY_STRING);
        }
    }


    private void bindTextView(TextViewHolder holder, int position) {
        JSONModel jsonModel = jsonModelList.get(position);
        holder.txtHead.setText(jsonModel.getText());
    }


    @Override
    public int getItemCount() {
        return jsonModelList.size();
    }


    @Override
    public int getItemViewType(int position) {


        int type = jsonModelList.get(position).getType();
        if (type == FormConstants.TYPE_TEXT) {
            return FormConstants.TYPE_TEXT;
        } else if (type == FormConstants.TYPE_EDITTEXT) {
            return FormConstants.TYPE_EDITTEXT;
        } else if (type == FormConstants.TYPE_SPINNER) {
            return FormConstants.TYPE_SPINNER;
        } else if (type == FormConstants.TYPE_RADIO) {
            return FormConstants.TYPE_RADIO;
        } else if (type == FormConstants.TYPE_SPACE) {
            return FormConstants.TYPE_SPACE;
        } else if (type == FormConstants.TYPE_DATE) {
            return FormConstants.TYPE_DATE;
        } else if (type == FormConstants.TYPE_SUBMIT_BUTTON) {
            return FormConstants.TYPE_SUBMIT_BUTTON;
        } else if (type == FormConstants.TYPE_CHECKBOX) {
            return FormConstants.TYPE_CHECKBOX;
        } else if (type == FormConstants.TYPE_ADD_AGAIN_BUTTON) {
            return FormConstants.TYPE_ADD_AGAIN_BUTTON;
        } else {
            return FormConstants.TYPE_SPACE;
        }
    }
}
