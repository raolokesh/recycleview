package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class recycleview extends AppCompatActivity {


    ArrayList<ContactModel> data = new ArrayList<>();

    RecyclerView recycle_view;
    FloatingActionButton btnOpendialog;
    RecycleContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        recycle_view = findViewById(R.id.recycle_view);
        btnOpendialog = findViewById(R.id.btnOpendialog);





        recycle_view.setLayoutManager(new LinearLayoutManager(this));


        data.add(new ContactModel(R.mipmap.ic_launcher,"Lokesh","55454442"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"sdfhdsh","54564654"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"fdhsdfhdsf","4352346246"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"Lokesh","55454442"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"sdfhdsh","54564654"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"fdhsdfhdsf","4352346246"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"Lokesh","55454442"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"sdfhdsh","54564654"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"fdhsdfhdsf","4352346246"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"Lokesh","55454442"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"sdfhdsh","54564654"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"fdhsdfhdsf","4352346246"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"Lokesh","55454442"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"sdfhdsh","54564654"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"fdhsdfhdsf","4352346246"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"Lokesh","55454442"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"sdfhdsh","54564654"));
        data.add(new ContactModel(R.mipmap.ic_launcher,"fdhsdfhdsf","4352346246"));

        adapter = new RecycleContactAdapter(this,data);
        recycle_view.setAdapter(adapter);

        btnOpendialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(recycleview.this);
                dialog.setContentView(R.layout.add_update);

                EditText nameedit,numberedit;
                nameedit = dialog.findViewById(R.id.nameedit);
                numberedit = dialog.findViewById(R.id.numberedit);
                Button btn_submit = dialog.findViewById((R.id.btn_submit));

                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        if (!nameedit.getText().toString().equals("")){
                            name = nameedit.getText().toString();
                        } else{
                            Toast.makeText(recycleview.this,"Please Enter Contact Name",Toast.LENGTH_LONG).show();
                        }
                        if (!numberedit.getText().toString().equals("")) {
                            number = numberedit.getText().toString();

                        }else{
                            Toast.makeText(recycleview.this,"Please Enter Phone Number",Toast.LENGTH_LONG).show();
                        }

                        if(!name.equals("") && !number.equals("")){
                            data.add(new ContactModel(name,number));
                        }

                        adapter.notifyItemInserted(data.size()-1);
                        recycle_view.scrollToPosition(data.size()-1);
                        dialog.dismiss();


                    }
                });

                dialog.show();





            }
        });






    }
}