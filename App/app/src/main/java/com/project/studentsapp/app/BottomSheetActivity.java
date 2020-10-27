package com.project.studentsapp.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.project.studentsapp.R;
import com.project.studentsapp.data.*;

public class BottomSheetActivity extends BottomSheetDialogFragment {

    MainActivity context = null;

    public BottomSheetActivity(MainActivity context) {
        this.context = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavigationView navigationView = view.findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getOrder() == 2) {
                    deleteAllStudents();
                }
                else {
                    switchColor();
                }
                return true;
            }
        });
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    StudentsController.deleteAll();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    };

    private void deleteAllStudents() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete all students? This action can not be undone.").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
    }
    private void switchColor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
        builder.setTitle("Choose a color");

        Spanned[] colors =  {
                HtmlCompat.fromHtml("<font color='#212121'>Red</font>", HtmlCompat.FROM_HTML_MODE_LEGACY),
                HtmlCompat.fromHtml("<font color='#212121'>Yellow</font>", HtmlCompat.FROM_HTML_MODE_LEGACY),
                HtmlCompat.fromHtml("<font color='#212121'>Green</font>", HtmlCompat.FROM_HTML_MODE_LEGACY),
                HtmlCompat.fromHtml("<font color='#212121'>Blue</font>", HtmlCompat.FROM_HTML_MODE_LEGACY),
                HtmlCompat.fromHtml("<font color='#212121'>Pink</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        };

        int checkedItem = 1;
        builder.setSingleChoiceItems(colors, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user checked an item
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}