package com.finsol.tarea2_2pm1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        ListView listautos;
        List<Datos> AutosList;
        ArrayList<String> autoLista;
        private static CustomAdapter adaptercustom;
        EditText txtBuscar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listautos = (ListView) findViewById(R.id.list);
        AutosList = new ArrayList<>();

        autoLista = new ArrayList<String>();
        txtBuscar = findViewById(R.id.txtBuscar);

        ObtenerLista();

        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //llenar lista
                Log.i("LOG","Length: "+txtBuscar.getText().length());
                if(txtBuscar.getText().length() >= 0) {
                    //listautos.removeAllViews();
                    if (txtBuscar.getText().length() == 0){
                        AutosList.clear();
                        ObtenerLista();
                    }else {
                        AutosList.clear();
                        ObtenerLista(txtBuscar.getText().toString());
                        Log.i("LOG", txtBuscar.getText().toString());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

        private void ObtenerLista()
        {
            RequestQueue peticion = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    RestApiMethods.ApiRead, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {
                    try
                    {
                        Log.i("LOG",RestApiMethods.ApiRead);
                        //JSONObject jsonObject =  new JSONObject(response);

                        JSONArray autoarray = new JSONArray(response);
                        Log.i("LOG", ""+(autoarray.length()));
                        for(int i = 0; i < autoarray.length(); i ++)
                        {
                            JSONObject RowAuto = autoarray.getJSONObject(i);

                            Datos auto = new Datos
                                    (
                                            RowAuto.getInt("userId"),
                                            RowAuto.getInt("id"),
                                            RowAuto.getString("title"),
                                            RowAuto.getString("body")
                                    );

                            AutosList.add(auto);
                            autoLista.add("User Id: "+String.valueOf(auto.getUserId())+"\nTitle:"+auto.getTitle());
                        }

                        adaptercustom = new CustomAdapter(AutosList, getApplicationContext());

                       // ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, autoLista);
                        listautos.setAdapter(adaptercustom);
                    }
                    catch (JSONException e)
                    {
                        Log.i("LOG",RestApiMethods.ApiRead);
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            peticion.add(stringRequest);
        }

    private void ObtenerLista(String id)
    {
        RequestQueue peticion = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                RestApiMethods.ApiRead+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    Log.i("LOG",RestApiMethods.ApiRead+id);
                    JSONObject jsonObject =  new JSONObject(response);
                    Log.i("LOG",""+jsonObject.getInt("userId"));
                        Datos auto = new Datos
                                (
                                        jsonObject.getInt("userId"),
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("title"),
                                        jsonObject.getString("body")

                                );

                        AutosList.add(auto);
                  //      autoLista.add("User Id: "+String.valueOf(auto.getUserId())+"\nTitle:"+auto.getTitle());


                    adaptercustom = new CustomAdapter(AutosList, getApplicationContext());

                    // ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, autoLista);
                    listautos.setAdapter(adaptercustom);
                }
                catch (JSONException e)
                {
                    Log.i("LOG",RestApiMethods.ApiRead);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        peticion.add(stringRequest);
    }
    }