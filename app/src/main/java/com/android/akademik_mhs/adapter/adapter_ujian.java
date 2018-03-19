package com.android.akademik_mhs.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.akademik_mhs.R;
import com.android.akademik_mhs.oop.Item;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adapter_ujian extends RecyclerView.Adapter<adapter_ujian.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;

    public adapter_ujian(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ujian, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item daftar =  dftr.get(position);
        holder.list_id_ujian.setText(daftar.getId_ujian());
        holder.list_ujian.setText("ujian " + daftar.getJenis_ujian() + " tahun ajaran " +  daftar.getTahun_ajaran() +
                " akan dilaksanakan pada " + daftar.getTanggal() + ", " + daftar.getKeterangan());
        holder.item = daftar;
    }

    @Override
    public int getItemCount() {
        return dftr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView list_id_ujian,list_ujian;
        Item item;

        public ViewHolder(View itemView) {
            super(itemView);
            list_id_ujian = (TextView) itemView.findViewById(R.id.list_id_ujian);
            list_ujian = (TextView) itemView.findViewById(R.id.list_info);

        }
    }

}