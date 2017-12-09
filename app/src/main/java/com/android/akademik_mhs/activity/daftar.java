package com.android.akademik_mhs.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.akademik_mhs.R;
import com.android.akademik_mhs.koneksi.config;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class daftar extends AppCompatActivity {
    EditText input_npm,input_nm,input_pass;
    Spinner input_prodi;
    Button btnregis;
    ProgressDialog PD;
    String prodi_mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        input_nm = (EditText) findViewById(R.id.input_nama);
        input_npm = (EditText) findViewById(R.id.input_npm);
        input_pass = (EditText) findViewById(R.id.input_password);

        String list[]={"pilih prodi","sistem informasi"};
        input_prodi = (Spinner) findViewById(R.id.prodi);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list);
        input_prodi.setAdapter(AdapterList);

        PD = new ProgressDialog(this);
        PD.setMessage("silahkan tunggu.....");
        PD.setCancelable(false);

        btnregis = (Button) findViewById(R.id.regis);
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regis_user();
            }
        });
    }

    public void regis_user() {
        PD.show();
        final String npm_mhs = input_npm.getText().toString();
        final String nama_mhs = input_nm.getText().toString();
        final String pass_mhs = input_pass.getText().toString();

        if(input_prodi.getSelectedItemPosition() ==1){
             prodi_mhs = "pr01";
        }else if(input_prodi.getSelectedItemPosition() ==2){
            prodi_mhs = "pr02";
        }

        StringRequest postRequest = new StringRequest(Request.Method.POST, config.REGIS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(),
                                response.toString(),
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(daftar.this, login.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(config.KEY_NPM,npm_mhs);
                params.put(config.KEY_NAMA,nama_mhs);
                params.put(config.KEY_PASSWORD,pass_mhs);
                params.put(config.KEY_PRODI,prodi_mhs);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(daftar.this);
        requestQueue.add(postRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // moveTaskToBack(true);;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
