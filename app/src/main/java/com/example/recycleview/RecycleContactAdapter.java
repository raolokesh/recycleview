package com.example.recycleview;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecycleContactAdapter extends RecyclerView.Adapter<RecycleContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> data;
    int lastposition = -1;
    RecycleContactAdapter(Context context, ArrayList<ContactModel> data){
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgcontact.setImageResource(data.get(position).getImg());
        holder.txtname.setText(data.get(position).getName());
        holder.txtnumber.setText(data.get(position).getNumber());


        setAnimation(holder.itemView,position);

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update);

                TextView textaddcontact;
                EditText nameedit,numberedit;
                textaddcontact = dialog.findViewById((R.id.textaddcontact));
                nameedit = dialog.findViewById(R.id.nameedit);
                numberedit = dialog.findViewById(R.id.numberedit);
                Button btn_submit = dialog.findViewById((R.id.btn_submit));


                btn_submit.setText("Update");
                textaddcontact.setText("Update Contact Details");

                nameedit.setText(data.get(position).getName());
                numberedit.setText(data.get(position).getNumber());


                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        int img;
                        img = data.get(position).getImg();
                        if (!nameedit.getText().toString().equals("")){
                            name = nameedit.getText().toString();
                        } else{
                            Toast.makeText(context,"Please Enter Contact Name",Toast.LENGTH_LONG).show();
                        }
                        if (!numberedit.getText().toString().equals("")) {
                            number = numberedit.getText().toString();

                        }else{
                            Toast.makeText(context,"Please Enter Phone Number",Toast.LENGTH_LONG).show();
                        }

                        if(!name.equals("") && !number.equals("")){
                            data.set(position,new ContactModel(img,name,number));
                        }

                        notifyItemInserted(position);
                        dialog.dismiss();


                    }
                });

                dialog.show();

            }
        });

        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete this Contact").
                        setMessage("are you sure ").setIcon(R.drawable.delete).
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                data.remove(position);
                                notifyItemRemoved(position);

                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();
                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtnumber;
        ImageView imgcontact;

        LinearLayout llrow;



        public ViewHolder(View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.txtname);
            txtnumber = itemView.findViewById((R.id.txtnumber));
            imgcontact = itemView.findViewById(R.id.imgcontact);
            llrow = itemView.findViewById(R.id.llrow);
        }
    }


    private  void setAnimation(View viewtoanimation,int position){

        if(position>lastposition) {
            lastposition = position;
            Animation slidein = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewtoanimation.setAnimation(slidein);
        }
    }
}
