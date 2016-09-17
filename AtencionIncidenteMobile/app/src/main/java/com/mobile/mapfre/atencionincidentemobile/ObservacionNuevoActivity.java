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
import com.android.volley.toolbox.StringRequest;
import com.mobile.mapfre.atencionincidentemobile.util.AppSingleton;

import java.util.HashMap;
import java.util.Map;

public class ObservacionNuevoActivity extends AppCompatActivity {

    private TextView observacion;
    private TextView numeroCti;
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacion_nuevo);
        setTitle("PRIMA AFP - Registrar Observación");

        Bundle bundle = getIntent().getExtras();
        final String incidenteId = bundle.getString("incidenteId");
        String numeroCtitxt = bundle.getString("numeroCti");

        registrar = (Button) findViewById(R.id.registrarObservacionBtn);
        observacion = (TextView) findViewById(R.id.observacionTxt);
        numeroCti = (TextView) findViewById(R.id.numeroCtiTxtObs);

        numeroCti.setText(numeroCtitxt);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://" + AppSingleton.server + "/AtencionIncidenteAPI/resources/observaciones";


                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                               error.printStackTrace();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("observacion", observacion.getText().toString());
                        params.put("incidenteId", incidenteId);
                        return params;
                    }
                };

                Intent intent = new Intent(getBaseContext(), SolicitudPendienteListActivity.class);
                startActivity(intent);
            }
        });
    }
}
