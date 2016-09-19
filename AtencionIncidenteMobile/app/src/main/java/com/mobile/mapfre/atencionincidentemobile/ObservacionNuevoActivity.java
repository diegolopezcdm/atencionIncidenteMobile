package com.mobile.mapfre.atencionincidentemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mobile.mapfre.atencionincidentemobile.util.AppSingleton;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ObservacionNuevoActivity extends AppCompatActivity {

    private EditText observacion;
    private TextView numeroCti;
    private Button registrar;
    String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacion_nuevo);
        setTitle("PRIMA AFP - Registrar Observación");

        Bundle bundle = getIntent().getExtras();
        final String incidenteId = bundle.getString("incidenteId");
        String numeroCtitxt = bundle.getString("numeroCti");

        registrar = (Button) findViewById(R.id.registrarObservacionBtn);
        observacion = (EditText) findViewById(R.id.observacionTxt);
        numeroCti = (TextView) findViewById(R.id.numeroCtiTxtObs);

        numeroCti.setText(numeroCtitxt);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = AppSingleton.server + "incidentes/"+incidenteId+"/observar";


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
                    public byte[] getBody() throws AuthFailureError
                    {
                        String body = "{\"detalle\" : \""+observacion.getText().toString()+"\"}";
                        try
                        {
                            return body.getBytes(getParamsEncoding());
                        }
                        catch (UnsupportedEncodingException uee)
                        {
                            throw new RuntimeException("Encoding not supported: "
                                    + getParamsEncoding(), uee);
                        }
                    }

                   /*- @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("detalle", observacion.getText().toString());
                        return params;
                    }*/

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/json");
                        return params;
                    }
                };

                AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest, REQUEST_TAG);

                Intent intent = new Intent(getBaseContext(), SolicitudPendienteListActivity.class);
                startActivity(intent);
            }
        });
    }
}
