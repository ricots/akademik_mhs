package com.android.akademik_mhs.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.akademik_mhs.R;
import com.android.akademik_mhs.koneksi.config;
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

public class adapter_krs extends RecyclerView.Adapter<adapter_krs.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;

    public adapter_krs(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_krs, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item daftar =  dftr.get(position);
        holder.krs_matkul.setText(daftar.getNama_matkul());
        holder.krs_hari.setText(daftar.getHari());
        holder.krs_mulai.setText(daftar.getJam_mulai());
        holder.krs_selesai.setText(daftar.getJam_selesai());
        holder.krs_ruang.setText(daftar.getRuang());
        holder.item = daftar;
    }

    @Override
    public int getItemCount() {
        return dftr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView krs_matkul, krs_hari, krs_mulai, krs_selesai, krs_ruang;
        Item item;
        public ViewHolder(View itemView) {
            super(itemView);
            krs_ruang = (TextView) itemView.findViewById(R.id.krs_ruang);
            krs_matkul = (TextView) itemView.findViewById(R.id.krs_matkul);
            krs_hari = (TextView) itemView.findViewById(R.id.krs_hari);
            krs_mulai = (TextView) itemView.findViewById(R.id.krs_mulai);
            krs_selesai = (TextView) itemView.findViewById(R.id.krs_selesai);

        }
    }
}