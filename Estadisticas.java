package mrnickapps.preguntasyrespuestas.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import modelo.Estadistica;
import mrnickapps.preguntasyrespuestas.R;

public class Estadisticas extends AppCompatActivity {

    private Estadistica estadistica;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Rapido").child("Estadisticas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                estadistica = dataSnapshot.getValue(Estadistica.class);
                estadisticaEnPantalla(estadistica);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Do nothing here
            }
        });
    }

    public void finJuego(View view){
        Intent intentoFinal = new Intent(this, MainActivity.class);
        startActivity(intentoFinal);
        System.exit(0);
    }

    public void estadisticaEnPantalla(Estadistica e){
        TextView a1 = (TextView) findViewById(R.id.aciertos_1); a1.setText(e.aciertos1);
        TextView a2 = (TextView) findViewById(R.id.aciertos_2); a2.setText(e.aciertos2);
        TextView a3 = (TextView) findViewById(R.id.aciertos_3); a3.setText(e.aciertos3);
        TextView a4 = (TextView) findViewById(R.id.aciertos_4); a4.setText(e.aciertos4);

        TextView e1 = (TextView) findViewById(R.id.errores_1); e1.setText(e.aciertos1);
        TextView e2 = (TextView) findViewById(R.id.errores_2); e2.setText(e.aciertos1);
        TextView e3 = (TextView) findViewById(R.id.errores_3); e3.setText(e.aciertos1);
        TextView e4 = (TextView) findViewById(R.id.errores_4); e4.setText(e.aciertos1);
    }
}
