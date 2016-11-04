package mrnickapps.preguntasyrespuestas.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import modelo.Pregunta;
import mrnickapps.preguntasyrespuestas.R;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Pregunta> preguntas;
    private static final String TAG = "MainActivity";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        preguntas = new ArrayList<>();

        mDatabase.child("Rapido").child("Preguntas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Pregunta preg = postSnapshot.getValue(Pregunta.class);
                    preguntas.add(preg);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Do nothing
            }
        });
    }

    public void iniciarJuego(View view){
        Intent intento = new Intent(this, PreguntaActivity.class);
        intento.putExtra("preguntas", preguntas);
        startActivity(intento);
        System.exit(0);
    }
}
