package com.example.aelaf.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {
       private EditText etText;
       private int listPosition;
        private String itemToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etText = (EditText)findViewById(R.id.etEditItem);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            itemToEdit = bundle.getString("item");
            listPosition = bundle.getInt("pos");
            String itemInit = itemToEdit;
            //etText.setText(itemInit);
            etText.append(itemInit);

        }


    }

    public void onItemEdit(View v){
        Intent intent = new Intent();
        String editedValue =   etText.getText().toString();
    if(!editedValue.isEmpty()) {
        intent.putExtra("item", editedValue);
        intent.putExtra("taskId", listPosition);


        setResult(RESULT_OK, intent);
        finish();
    }
    else
        Toast.makeText(this,"Empty Item Not allowed",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void finish() {


        super.finish();
    }
}
