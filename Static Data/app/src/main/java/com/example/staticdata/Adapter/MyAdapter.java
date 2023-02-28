package com.example.staticdata.Adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staticdata.Activity.MainActivity;
import com.example.staticdata.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    RecyclerView recyclerView;

    ArrayList<Modal> list;

    TextInputEditText nameED, emailED, contactED;
    TextView dob;
    Button okbtn, cancelbtn;
    RadioGroup gender;
    RadioButton selectradiobtn;
    Context context;

    public MyAdapter(ArrayList<Modal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Modal modal = list.get(position);

        holder.t1_name.setText(modal.getName());
        holder.t2_email.setText(modal.getEmail());
        holder.t3_contact.setText(modal.getContact());

        holder.dout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.dout);
                popupMenu.inflate(R.menu.menuitem);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.edit_id:
                                edititem();
                                popupMenu.dismiss();
                                break;

                            case R.id.delete_id:
                                delete();
                                break;

                            case R.id.cancelM_id:
                                popupMenu.dismiss();
                                break;
                        }
                        return true;
                    }

                    private void delete() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setIcon(R.drawable.baseline_delete_24);
                        builder.setTitle("Delete!");
                        builder.setMessage("Are You Sure ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list.remove(holder.getPosition());
                                notifyDataSetChanged();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                });
            }

            private void edititem() {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialoge_layout);
                dialog.setCancelable(false);

                okbtn = dialog.findViewById(R.id.okbtn_add_id);
                cancelbtn = dialog.findViewById(R.id.cancelbtn_add_id);
                nameED = dialog.findViewById(R.id.nameED_id);
                emailED = dialog.findViewById(R.id.emailED_id);
                contactED = dialog.findViewById(R.id.contactED_id);
                dob = dialog.findViewById(R.id.date_id);
                gender = dialog.findViewById(R.id.radiogroup_id);

                nameED.setText(list.get(position).name);
                emailED.setText(list.get(position).email);
                contactED.setText(list.get(position).contact);
                dob.setText(list.get(position).date);

                dob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.getWeekYear();

                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                dob.setText(i + "/" + i1 + "/" + i2);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String Name = nameED.getText().toString();
                        String Email = emailED.getText().toString();
                        String Contact = contactED.getText().toString();
                        String DOB = dob.getText().toString();
                        selectradiobtn = gender.findViewById(gender.getCheckedRadioButtonId());

                        if (Name.isEmpty() || Email.isEmpty() || Contact.isEmpty() || DOB.isEmpty() || selectradiobtn == null) {
                            nameED.setError("");
                            emailED.setError("");
                            contactED.setError("");
                            dob.setError("");
                            Toast.makeText(context, "Select Gender", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, selectradiobtn.getText(), Toast.LENGTH_SHORT).show();

                            Modal modal = new Modal(Name, Email, Contact, DOB);
                            list.set(position, modal);

                            dialog.dismiss();
                        }
                        notifyDataSetChanged();

                    }
                });

                cancelbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1_name, t2_email, t3_contact, dout;

        CardView singlerow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1_name = itemView.findViewById(R.id.name_id);
            t2_email = itemView.findViewById(R.id.email_id);
            t3_contact = itemView.findViewById(R.id.contact_id);
            dout = itemView.findViewById(R.id.dout_id);
            singlerow = itemView.findViewById(R.id.singlerow_id);
        }
    }
}
