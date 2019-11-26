package com.shamweel.jsontoforms.validate;

import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.shamweel.jsontoforms.FormConstants;
import com.shamweel.jsontoforms.models.JSONModel;
import com.shamweel.jsontoforms.sigleton.DataValueHashMap;
import com.shamweel.jsontoforms.viewholder.CheckboxViewHolder;
import com.shamweel.jsontoforms.viewholder.EditTextViewHolder;
import com.shamweel.jsontoforms.viewholder.RadioViewHolder;

import java.util.List;

public class CheckFieldValidations {

    public static boolean isFieldsValidated(RecyclerView recyclerView, List<JSONModel> jsonModelList) {
        final boolean[] isValidated = {true};

        for (int i = 0; i < jsonModelList.size(); i++) {
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
            if (viewHolder != null && viewHolder.itemView != null) {
                if (viewHolder instanceof EditTextViewHolder) {
                    ((EditTextViewHolder) viewHolder).layoutEdittext.setErrorEnabled(false);
                } else if (viewHolder instanceof RadioViewHolder) {
                    for (int j = 0; j < ((RadioViewHolder) viewHolder).rGroup.getChildCount(); j++) {
                        ((RadioButton) ((RadioViewHolder) viewHolder).rGroup.getChildAt(j)).setError(null);
                    }
                } else if (viewHolder instanceof CheckboxViewHolder) {
                    ((CheckboxViewHolder) viewHolder).checkBox.setError(null);
                }
            }
        }

        for (int i = 0; i < jsonModelList.size(); i++) {

            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);

            JSONModel jsonModel = jsonModelList.get(i);
            String fieldValue = DataValueHashMap.getValue(jsonModel.getId());
            String EMPTY_STRING = "";


            if (jsonModel.getRequired() != null && jsonModel.getRequired()) {
                if (viewHolder != null && viewHolder.itemView != null) {
                    if (jsonModel.getType() == FormConstants.TYPE_EDITTEXT
                            && fieldValue.equalsIgnoreCase(EMPTY_STRING)) {
                        ((EditTextViewHolder) viewHolder).layoutEdittext.setErrorEnabled(true);
                        ((EditTextViewHolder) viewHolder).layoutEdittext.setError(FormConstants.FIELD_REQUIRED);
                        recyclerView.smoothScrollToPosition(i);
                        isValidated[0] = false;

                    } else if (jsonModel.getType() == FormConstants.TYPE_CHECKBOX
                            && fieldValue.equalsIgnoreCase(EMPTY_STRING)) {
                        ((CheckboxViewHolder) viewHolder).checkBox.setError(FormConstants.FIELD_REQUIRED);
                        recyclerView.smoothScrollToPosition(i);
                        isValidated[0] = false;

                    } else if (jsonModel.getType() == FormConstants.TYPE_RADIO
                            && fieldValue.equalsIgnoreCase(EMPTY_STRING)) {
                        for (int j = 0; j < ((RadioViewHolder) viewHolder).rGroup.getChildCount(); j++) {
                            ((RadioButton) ((RadioViewHolder) viewHolder).rGroup.getChildAt(j)).setError(FormConstants.FIELD_REQUIRED);
                        }
                        recyclerView.smoothScrollToPosition(i);

                        isValidated[0] = false;
                    }

                }
            }


        }


        return isValidated[0];
    }

}
