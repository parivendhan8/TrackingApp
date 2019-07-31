package com.example.trackingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter_Location extends BaseAdapter {

    Context context;
//    ArrayList<String> ID;
//    ArrayList<String> Name;
//    ArrayList<String> PhoneNumber;
//    ArrayList<String> Role;
//    ArrayList<String> Notes;
//    ArrayList<String> FollowDate;
//    ArrayList<String> DemoDate;
    ArrayList<String> Latitude;
    ArrayList<String> Longitude;
    ArrayList<String> Date_Time;





    public ListAdapter_Location(
            Context context2,
//            ArrayList<String> id,
//            ArrayList<String> name,
//            ArrayList<String> phone,
//            ArrayList<String> role,
//            ArrayList<String> notes,
//            ArrayList<String> followDate,
//            ArrayList<String> demoDate,
            ArrayList<String> Latitude,
            ArrayList<String> Longitude,
            ArrayList<String> Date_Time
    )
    {

        this.context = context2;
//        this.ID = id;
//        this.Name = name;
//        this.PhoneNumber = phone;
//        this.Role = role;
//        this.Notes = notes;
//        this.FollowDate = followDate;
//        this.DemoDate= demoDate;
        this.Latitude = Latitude;
        this.Longitude= Longitude;
        this.Date_Time= Date_Time;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Latitude.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items_location, null);

            holder = new Holder();

//            holder.ID_TextView = child.findViewById(R.id.textViewID);
//            holder.Name_TextView = child.findViewById(R.id.textViewNAME);
//            holder.PhoneNumberTextView = child.findViewById(R.id.textViewPHONE_NUMBER);
//            holder.RoleTxt = child.findViewById(R.id.textViewRole);
//            holder.NotesTxt = child.findViewById(R.id.textViewnotes);
//            holder.FollowTxt = child.findViewById(R.id.textViewFollowdate);
//            holder.DemoTxt = child.findViewById(R.id.textDemoDate);
            holder.Latitude = child.findViewById(R.id.txtLatitude_Location);
            holder.Longitude = child.findViewById(R.id.txtLongitude_Location);
            holder.Date_Time = child.findViewById(R.id.txtDateTime_Location);



            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
//        holder.ID_TextView.setText(ID.get(position));
//        holder.Name_TextView.setText(Name.get(position));
//        holder.PhoneNumberTextView.setText(PhoneNumber.get(position));
//        holder.RoleTxt.setText(Role.get(position));
//        holder.NotesTxt.setText(Notes.get(position));
//        holder.FollowTxt.setText(FollowDate.get(position));
//        holder.DemoTxt.setText(DemoDate.get(position));
        holder.Latitude.setText(Latitude.get(position));
        holder.Longitude.setText(Longitude.get(position));
        holder.Date_Time.setText(Date_Time.get(position));

        return child;
    }

    public class Holder {

//        TextView ID_TextView;
//        TextView Name_TextView;
//        TextView PhoneNumberTextView;
//        TextView RoleTxt;
//        TextView NotesTxt;
//        TextView FollowTxt;
//        TextView DemoTxt;
        TextView Latitude;
        TextView Longitude;
        TextView Date_Time;

    }

}