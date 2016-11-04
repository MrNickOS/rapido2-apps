package mrnickapps.preguntasyrespuestas.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mrnickapps.preguntasyrespuestas.R;

public class Resultados extends AppCompatActivity {

    private int rCorrectas;
    private int rIncorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        rCorrectas = getIntent().getIntExtra("corr", 0);
        rIncorrectas = getIntent().getIntExtra("incorr", 0);

        TextView viewCorr = (TextView) findViewById(R.id.ncorr);
        viewCorr.setText(rCorrectas);
        TextView viewIncorr = (TextView) findViewById(R.id.nIncorr);
        viewIncorr.setText(rIncorrectas);
    }

    public void estadisticas(View view){
        Intent intento = new Intent(this, Estadisticas.class);
        startActivity(intento);
        System.exit(0);
    }
}
