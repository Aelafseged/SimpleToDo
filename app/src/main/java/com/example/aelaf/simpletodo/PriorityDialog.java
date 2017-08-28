package com.example.aelaf.simpletodo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aelaf.simpletodo.R;

import java.util.Calendar;

import static com.example.aelaf.simpletodo.R.id.btnDone;



public class PriorityDialog extends DialogFragment {

   public interface PriorityListener{
        void onHigh();
        void onMedium();
        void onLow();
        void onDone();


    }
    public PriorityDialog() {
    }

 public   PriorityListener mPriorityListener;
    public void register(PriorityListener priorityListener){
        mPriorityListener = priorityListener;


    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.priority,null);
        builder.setView(view);
        builder.setMessage("Select Priority");
      RadioButton radioHigh = (RadioButton) view.findViewById(R.id.rdhigh);


        RadioButton radioMedium = (RadioButton) view.findViewById(R.id.rdmedium);
        RadioButton radioLow = (RadioButton) view.findViewById(R.id.rdlow);
        Button btnDone = (Button) view.findViewById(R.id.btnDone);

        radioHigh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mPriorityListener!=null)
                    mPriorityListener.onHigh();


            }
        });

        radioMedium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mPriorityListener!=null)
                    mPriorityListener.onMedium();


            }
        });
        radioLow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mPriorityListener!=null)
                    mPriorityListener.onLow();


            }
        });



        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mPriorityListener!=null) {
                    mPriorityListener.onDone();

                }
            }
        });


        return builder.create();
  }


}
