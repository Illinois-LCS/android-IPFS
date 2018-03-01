package com.lcs_uiuc.hackillinois.ipfs;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * LCS-UIUC Hackillinois Project 2018
 *
 * resources used:
 * https://github.com/codepath/android_guides/wiki/Fragment-Navigation-Drawer
 *
 *
 */

public class buttonReport extends Fragment {

    private OnButtonFragmentInteractionListener mListener;

public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_report11, container, false);
}

public void onButtonPressed(View view) {

    EditText mMain = (EditText) view.findViewById(R.id.editMain);
    EditText mSubj = (EditText) view.findViewById(R.id.editSubj);


    String sSubj = mSubj.getText().toString();
    String sMain = mMain.getText().toString();


    Intent i = new Intent(Intent.ACTION_SEND);
    i.setType("message/rfc822");
    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"lcscatuiuc@gmail.com"});
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

/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
public interface OnButtonFragmentInteractionListener {
        // TODO: Update argument type and name
    public void onButtonFragmentInteraction(View v);
    }

}