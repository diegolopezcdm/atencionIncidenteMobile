package com.mobile.mapfre.atencionincidentemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button solicitudPendienteBtn;
    private Button salirBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PRIMA AFP - Grupo Crédito");
        solicitudPendienteBtn = (Button) findViewById(R.id.button);
        solicitudPendienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SolicitudPendienteListActivity.class);
                startActivity(intent);
            }
        });

        salirBtn = (Button) findViewById(R.id.button5);
        salirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
