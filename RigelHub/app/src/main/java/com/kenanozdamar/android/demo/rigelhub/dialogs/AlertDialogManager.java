package com.kenanozdamar.android.demo.rigelhub.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StringRes;

import com.kenanozdamar.android.demo.rigelhub.R;
import com.kenanozdamar.android.demo.rigelhub.error.ErrorType;



public class AlertDialogManager {
    public static void displayErrorAlert(Context context, ErrorType type) {
        switch (type) {
            case EmptySearchResult:
                displayAlertDialog(context, R.string.empty_search_result_dialog_title, R.string.empty_search_result_dialog_message);
                break;
        }
    }

    private static void displayAlertDialog(Context context,
                                           @StringRes int title,
                                           @StringRes int message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }
}
