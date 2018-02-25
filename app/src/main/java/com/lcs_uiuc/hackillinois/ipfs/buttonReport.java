package com.lcs_uiuc.hackillinois.ipfs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class buttonReport extends android.support.v4.app.Fragment {



    public void onClick (View view){





        EditText mMain = (EditText) getView().findViewById(R.id.editMain);
        EditText mSubj = (EditText) getView().findViewById(R.id.editSubj);


        String sSubj = mSubj.getText().toString();
        String sMain = mMain.getText().toString();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"lcsatuiuc@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, sSubj);
        i.putExtra(Intent.EXTRA_TEXT, sMain);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        try{
            startActivity(Intent.createChooser(i, "Send mail..."));

        }
        catch (android.content.ActivityNotFoundException ex){

        }

        }






}
