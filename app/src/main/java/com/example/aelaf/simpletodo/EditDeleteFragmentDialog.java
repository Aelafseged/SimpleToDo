package com.example.aelaf.simpletodo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by aelaf on 8/19/17.
 */



public class EditDeleteFragmentDialog extends DialogFragment {
  public interface DialogListener{
       void onEdit();
      void onDelete();
  }
  DialogListener mDialogListener;
public void register(DialogListener dialogListener){
    mDialogListener = dialogListener;
}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.delete_edit,null);
        builder.setView(view);
        builder.setMessage("What do you want to do?");
        ImageView imgEdit = (ImageView) view.findViewById(R.id.imgedit);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.imgdelete);
        imgEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mDialogListener!=null)
                mDialogListener.onEdit();
                else
                    EditDeleteFragmentDialog.this.dismiss();
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mDialogListener!=null)
                mDialogListener.onDelete();
                else
                    EditDeleteFragmentDialog.this.dismiss();
            }
        });

       /* builder.setMessage("What do you want to do")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //delete
                        if(mDeleteListener!=null)
                        mDeleteListener.onPositive();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // daved the day
                        if(mDeleteListener!=null)
                        mDeleteListener.onNegative();
                    }
                });*/
        // Create the AlertDialog object and return it
        return builder.create();
    }
}