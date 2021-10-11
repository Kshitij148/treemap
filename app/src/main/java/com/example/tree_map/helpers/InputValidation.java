package com.example.tree_map.helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class InputValidation {
    private Context context;
//    /**
//     * constructor
//     *
//     * @param context
//     */
    public InputValidation(Context context) {
        this.context = context;
    }
//    /**
//     * method to check InputEditText filled .
//     *
//     * @param textInputEditText
//     * @param textInputLayout
//     * @param message
//     * @return
//     */
    public boolean isEditTextFilled(EditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            editText.setError(message);
//            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            editText.setError(null);
        }
        return true;
    }
//    /**
//     * method to check InputEditText has valid email .
//     *
//     * @param textInputEditText
//     * @param textInputLayout
//     * @param message
//     * @return
//     */
    public boolean isEditTextEmail(EditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            editText.setError(message);
//            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            editText.setError(null);
        }
        return true;
    }
    public boolean isEditTextMatches(EditText editText1, EditText editText2, String message) {
        String value1 = editText1.getText().toString().trim();
        String value2 = editText2.getText().toString().trim();
        Log.d("test","TESTTTTTTTTTTTTT");
        Log.d("TEST",value1);
        Log.d("TEST",value2);
        if (!value1.equals(value2)) {
            editText2.setError(message);
//            hideKeyboardFrom(textInputEditText2);
            Log.d("test","ERROR");
            return false;
        } else {
            editText2.setError(null);
            Log.d("test","PASS");
        }
        return true;
    }
//    /**
//     * method to Hide keyboard
//     *
//     * @param view
//     */
//    private void hideKeyboardFrom(View view) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//    }
}
