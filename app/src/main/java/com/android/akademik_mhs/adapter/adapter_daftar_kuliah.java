package com.android.akademik_mhs.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.akademik_mhs.R;
import com.android.akademik_mhs.activity.daftar;
import com.android.akademik_mhs.activity.login;
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

public class adapter_daftar_kuliah extends RecyclerView.Adapter<adapter_daftar_kuliah.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;

    public adapter_daftar_kuliah(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_daftar_kuliah, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item daftar =  dftr.get(position);
        holder.id_matkul.setText(daftar.getId_matkul());
        holder.matkul.setText(daftar.getNama_matkul());
        holder.jam_mulai.setText(daftar.getJam_mulai());
        holder.jam_selesai.setText(daftar.getJam_selesai());
        holder.dosen.setText(daftar.getDosen());
        holder.id_nid.setText(daftar.getNid());
        holder.hari.setText(daftar.getHari());
        holder.semester.setText(daftar.getSemester());
        holder.item = daftar;
    }

    @Override
    public int getItemCount() {
        return dftr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView id_matkul,matkul,jam_mulai,jam_selesai,dosen,id_nid,hari,semester;
        SharedPreferences sp;
        String npmnya;
        Item item;
        FloatingActionButton save_jadwal;

        public ViewHolder(View itemView) {
            super(itemView);
            semester = (TextView) itemView.findViewById(R.id.semester);
            id_nid = (TextView) itemView.findViewById(R.id.id_nid);
            hari = (TextView) itemView.findViewById(R.id.hari);
            id_matkul = (TextView) itemView.findViewById(R.id.id_matkul);
            matkul = (TextView) itemView.findViewById(R.id.matkul);
            jam_mulai = (TextView) itemView.findViewById(R.id.jam_mulai);
            jam_selesai = (TextView) itemView.findViewById(R.id.jam_selesai);
            dosen = (TextView) itemView.findViewById(R.id.dosen);
            save_jadwal = (FloatingActionButton) itemView.findViewById(R.id.save_jadwal);

            sp = context.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            npmnya = sp.getString(config.EMAIL_SHARED_PREF, "Not Available");

            save_jadwal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringRequest postRequest = new StringRequest(Request.Method.POST, config.SIMPAN_JADWAL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(context,
                                            response.toString(),
                                            Toast.LENGTH_SHORT).show();
                                    save_jadwal.setEnabled(false);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context,
                                    error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(config.KEY_ID_MATKUL,id_matkul.getText().toString());
                            params.put(config.KEY_NPM,npmnya);
                            params.put(config.NID,id_nid.getText().toString());
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(postRequest);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public void simpan_jadwal(final String idmtakul,final String npm, final String nid_dosen){


    }
}