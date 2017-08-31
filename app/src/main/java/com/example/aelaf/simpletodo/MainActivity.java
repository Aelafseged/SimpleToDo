package com.example.aelaf.simpletodo;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aelaf.simpletodo.data.db.ToDo;
import com.example.aelaf.simpletodo.data.db.ToDoDbActions;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<ToDo> items;
    MyArrayAdapter itemsAdapter;
    ListView lvItems;
    private static int requestCode = 5;
    private ToDoDbActions toDoDbActions;

    //row views
    TextView txtTitle;
    TextView txtDescription;
    TextView txtDate;
    TextView txtPriority;

    String priority ="";


   private String updatedDate;

   private int _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView)findViewById(R.id.lvItems);
         toDoDbActions = new ToDoDbActions(this);

        readItems();
        itemsAdapter = new MyArrayAdapter(this,R.layout.row_todo,items);
        lvItems.setAdapter(itemsAdapter);
        setUpListOnLongClick();
        //setUpListClickListener();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddItem);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                addItem();
            }
        });

    }

    //delete action
   private void setUpListOnLongClick() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){


            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                int _id = items.get(i).get_id();
                String title = items.get(i).getName();
                String detail = items.get(i).getDetail();
                String date = items.get(i).getDate();
               final ToDo toDo = new ToDo(_id,title,detail,date,null);
                Log.i(TAG, "onItemLongClick: "+date);


               final EditDeleteFragmentDialog editDeleteFragmentDialog = new EditDeleteFragmentDialog();
                editDeleteFragmentDialog.show(getSupportFragmentManager(),null);
                final EditDeleteFragmentDialog.DialogListener mEditDeleteListener = new EditDeleteFragmentDialog.DialogListener() {
                    @Override
                    public void onEdit() {
                        Intent intent = new Intent(MainActivity.this,EditItemActivity.class);
                        intent.putExtra("item",items.get(i).getDetail());
                        updatedDate = items.get(i).getDate();
                        priority = items.get(i).getPriority();
                        MainActivity.this._id = items.get(i).get_id();

                        intent.putExtra("pos",i);
                        startActivityForResult(intent,requestCode);
                       editDeleteFragmentDialog.dismiss();

                    }

                    @Override
                    public void onDelete() {
                        toDoDbActions.deleteItem(toDo);
                        items.remove(i);
                        itemsAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"Task Titled "+toDo.getName()+" is Deleted",Toast.LENGTH_LONG).show();
                        editDeleteFragmentDialog.dismiss();
                    }


                };
                editDeleteFragmentDialog.register(mEditDeleteListener);
                return true;
            }
        });
    }


    private void readItems(){
       items =  toDoDbActions.getToDoItems();

      /*  File fileDir = getFilesDir();
        File todoFile = new File(fileDir,"todo.txt");
        try{
            items = new ArrayList<>(FileUtils.readLines(todoFile));

        }
        catch (IOException ex){
            items = new ArrayList<>();
        }*/
    }

    private void writeItems(ToDo todo){

        toDoDbActions.addItem(todo);

       File fileDir = getFilesDir();
        File todoFile = new File(fileDir,"todo.txt");
        try{
            FileUtils.writeLines(todoFile,items);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }
    public void addItem(){
        final EditText editText = (EditText) findViewById(R.id.etNewItem);
        final String itemValue = editText.getText().toString();
        if (itemValue.isEmpty()) {
            Toast.makeText(this,"Empty Item",Toast.LENGTH_SHORT).show();
        }
        else if (!itemValue.isEmpty()) {
            final PriorityDialog priorityDialog = new PriorityDialog();
            priorityDialog.show(getSupportFragmentManager(),null);
            final PriorityDialog.PriorityListener priorityListener = new PriorityDialog.PriorityListener() {

                private void goAdd() {
                   Calendar calendar =  Calendar.getInstance();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


                    ToDo todo = new ToDo(0, itemValue.substring(0,1).toUpperCase(),itemValue,formatter.format(calendar.getTime()),priority);
                    writeItems(todo);

                    itemsAdapter.add(todo);
                    editText.setText("");
                    Toast.makeText(getLayoutInflater().getContext(), "Item Titled  "+todo.getName()+" is Added", Toast.LENGTH_SHORT).show();
                    priority = "";

                }

                @Override
                public void onHigh() {
                    priority="High";

                }

                @Override
                public void onMedium() {
                    priority = "Medium";

                }

                @Override
                public void onLow() {
                    priority = "Low";

                }

                @Override
                public void onDone() {
                    if (priority=="") {
                        Toast.makeText(MainActivity.this, "Please Select Priority", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        goAdd();
                        priorityDialog.dismiss();
                    }
                }

            };
            priorityDialog.register(priorityListener);
        }//else if

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == requestCode && resultCode == RESULT_OK){
          String editedItem = data.getExtras().getString("item");
            int pos = data.getExtras().getInt("taskId");

            ToDo toDo = new ToDo(_id,editedItem.substring(0,1),editedItem,updatedDate,priority);
            priority = "";
            toDoDbActions.updateItem(toDo);

            items.set(pos,toDo);

          //  writeItems();
            Toast.makeText(this, "Item Edited", Toast.LENGTH_SHORT).show();
            itemsAdapter.notifyDataSetChanged();

        }

    }
    private  class MyArrayAdapter extends  ArrayAdapter<ToDo>{

        public MyArrayAdapter(@NonNull Context context, @LayoutRes int resource,List<ToDo> items) {
            super(context, resource,items);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = getLayoutInflater().inflate(R.layout.row_todo, parent, false);
            //work with rows

            txtTitle  =(TextView)row.findViewById(R.id.txtItemTitle);
            txtDescription  =(TextView)row.findViewById(R.id.txtItemDescription);
            txtDate  =(TextView)row.findViewById(R.id.txtDate);
            txtPriority = (TextView)row.findViewById(R.id.txtPriority);

            txtTitle.setText(items.get(position).getName().toString());
            txtDescription.setText(items.get(position).getDetail());
            txtPriority.setText(items.get(position).getPriority());
            txtDate.setText(items.get(position).getDate().toString());

            switch (txtPriority.getText().toString()) {
                case "High":
                    row.setBackgroundResource(R.color.colorHigh);
                    break;
                case "Medium":
                    row.setBackgroundResource(R.color.colorMedium);
                    break;
                case "Low":
                    row.setBackgroundResource(R.color.colorLow);
            }

            return  row;

        }

        @Nullable
        @Override
        public ToDo getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }
    }
}
