package com.mobile.mapfre.atencionincidentemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.mobile.mapfre.atencionincidentemobile.model.SolicitudPentiente;
import com.mobile.mapfre.atencionincidentemobile.util.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SolicitudPendienteFindActivity extends AppCompatActivity {

    private TextView numeroCti;
    private TextView matricula;
    private TextView sistema;
    private TextView proceso;
    private TextView subproceso;
    private TextView asunto;
    private TextView descripcion;
    private TextView datosAModificar;
    private TextView tipoSolicitud;
    private Button aprobar;
    private Button observar;
    String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("PRIMA AFP - Detalle de Solicitud");
        setContentView(R.layout.activity_solicitud_pendiente_find);

        Bundle bundle = getIntent().getExtras();

        numeroCti = (TextView) findViewById(R.id.numeroCtiTxtDet);
        matricula= (TextView) findViewById(R.id.matriculaTxt);
        sistema = (TextView) findViewById(R.id.sistsemaTxt);
        proceso = (TextView) findViewById(R.id.procesoDetTxt);
        subproceso = (TextView) findViewById(R.id.subprocesoDetTxt);
        asunto = (TextView) findViewById(R.id.asuntoTxt);
        descripcion =(TextView) findViewById(R.id.descripcionTxt);
        datosAModificar =(TextView) findViewById(R.id.datosModificarTxt);
        tipoSolicitud = (TextView) findViewById(R.id.tipoSolicitudDetLbl);
        aprobar = (Button) findViewById(R.id.aprobarBtn);
        observar = (Button)findViewById(R.id.observarBtn);

        final String incidenteId = bundle.getString("incidenteId");

        final SolicitudPentiente solicitudPentiente = new SolicitudPentiente();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, AppSingleton.server+"incidentes/"+incidenteId, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    solicitudPentiente.setNumeroCti(response.getString("numeroCti"));
                    solicitudPentiente.setSistema(response.getString("sistema"));
                    solicitudPentiente.setAsunto(response.getString("asunto"));
                    solicitudPentiente.setSubProceso(response.getString("subProceso"));
                    solicitudPentiente.setProceso(response.getString("proceso"));
                    solicitudPentiente.setMatricula(response.getString("matricula"));
                    solicitudPentiente.setDescripcion(response.getString("descripcion"));
                    solicitudPentiente.setTipoSolicitud(response.getString("tipoSolicitud"));
                    solicitudPentiente.setDatosAModificar(response.getString("datosAModificar"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                error.printStackTrace();
            }
        });

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest, REQUEST_TAG);

        numeroCti.setText(solicitudPentiente.getNumeroCti());
        matricula.setText(solicitudPentiente.getMatricula());
        sistema.setText(solicitudPentiente.getSistema());
        proceso.setText(solicitudPentiente.getProceso());
        subproceso.setText(solicitudPentiente.getSubProceso());
        asunto.setText(solicitudPentiente.getAsunto());
        descripcion.setText(solicitudPentiente.getDescripcion());
        datosAModificar.setText(solicitudPentiente.getDatosAModificar());
        tipoSolicitud.setText(solicitudPentiente.getTipoSolicitud());

        aprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = AppSingleton.server + "incidentes/" + incidenteId + "/aprobar";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                // Result handling
                                System.out.println(response);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Error handling
                        System.out.println("Something went wrong!");
                        error.printStackTrace();

                    }
                });
                AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);
                Intent intent = new Intent(getBaseContext(), SolicitudPendienteListActivity.class);
                startActivity(intent);
            }
        });

        observar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ObservacionNuevoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("incidenteId", incidenteId);
                bundle.putString("numeroCti", solicitudPentiente.getNumeroCti());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
