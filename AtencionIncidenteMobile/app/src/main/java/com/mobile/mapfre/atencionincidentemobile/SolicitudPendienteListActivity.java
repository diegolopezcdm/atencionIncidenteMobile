package com.mobile.mapfre.atencionincidentemobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mobile.mapfre.atencionincidentemobile.adapters.SolicitudPendienteAdapter;
import com.mobile.mapfre.atencionincidentemobile.model.SolicitudPentiente;
import com.mobile.mapfre.atencionincidentemobile.util.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SolicitudPendienteListActivity extends AppCompatActivity {

    String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";
    private RecyclerView rv;
    private SolicitudPendienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_pendiente_list);
        setTitle("PRIMA AFP - Solicitudes por Aprobar");
        final List<SolicitudPentiente> solicitudPentientes = new ArrayList<>();

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(AppSingleton.server+"incidentes",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject jo;
                        Integer id;
                        String numeroCti;
                        String proceso;
                        String tipoSolicitud;

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jo = response.getJSONObject(i);
                                id = jo.getInt("id");
                                numeroCti = jo.getString("numeroCti");
                                proceso = jo.getString("proceso");
                                tipoSolicitud = jo.getString("tipoSolicitud");

                                solicitudPentientes.add(new SolicitudPentiente(id, numeroCti, proceso, tipoSolicitud));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                                        }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayReq, REQUEST_TAG);

        rv = (RecyclerView)findViewById(R.id.solicitudPendienteRv);
        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        rv.setLayoutManager(llm);
        adapter = new SolicitudPendienteAdapter(solicitudPentientes);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
