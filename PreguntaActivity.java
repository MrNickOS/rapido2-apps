package mrnickapps.preguntasyrespuestas.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import mrnickapps.preguntasyrespuestas.R;
import modelo.Pregunta;


public class PreguntaActivity extends AppCompatActivity {

    private ArrayList<Pregunta> lasPreguntas;
    private Pregunta actual;
    private int indice;
    private RadioGroup radioGroup;
    private DatabaseReference reference;
    private int aciertoActual;
    private int errorActual;

    private int aciertosUsuario;
    private int erroresUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        reference = FirebaseDatabase.getInstance().getReference();
        lasPreguntas = (ArrayList<Pregunta>) getIntent().getSerializableExtra("preguntas");
        indice = getIntent().getIntExtra("index", 1);
        aciertosUsuario = getIntent().getIntExtra("corr", 0);
        erroresUsuario = getIntent().getIntExtra("incorr", 0);
        if(indice == 1)
            Collections.shuffle(lasPreguntas);
        actual = lasPreguntas.get(indice-1);

        reference.child("Rapido").child("Estadisticas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aciertoActual = dataSnapshot.child("Aciertos"+actual.idPregunta).getValue(Integer.class);
                errorActual = dataSnapshot.child("Errores"+actual.idPregunta).getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Do nothing
            }
        });
    }

    public void contestaPregunta(View view) {
        Intent intento = new Intent(this, PreguntaActivity.class);;
        if (actual.idPregunta == 4){
            intento = new Intent(this, Resultados.class);
        }
        String respondida = "";
        if(radioGroup.getCheckedRadioButtonId()!=-1){
            int id= radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(id);
            int radioId = radioGroup.indexOfChild(radioButton);
            RadioButton boton = (RadioButton) radioGroup.getChildAt(radioId);
            respondida = (String) boton.getText();
        }

        boolean esCorrecto = respondida.equals(actual.correcta);
        if(esCorrecto) {
            reference.child("Rapido").child("Estadisticas").child("Aciertos" + actual.idPregunta).setValue(aciertoActual+1);
            aciertosUsuario++;
            Toast t = Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT);
            t.show();
        } else
            reference.child("Rapido").child("Estadisticas").child("Errores"+actual.idPregunta).setValue(errorActual);
            erroresUsuario++;

        intento.putExtra("preguntas", lasPreguntas);
        intento.putExtra("index", indice);
        intento.putExtra("corr", aciertosUsuario);
        intento.putExtra("incorr", erroresUsuario);
        startActivity(intento);
        System.exit(0);
    }

    public void cargarPregunta(){
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add(actual.correcta);
        opciones.add(actual.incorrectas.get(0));
        opciones.add(actual.incorrectas.get(1));
        Collections.shuffle(opciones);

        TextView txtPreguntaX = (TextView) findViewById(R.id.textView3);
        TextView txtEnunciado = (TextView) findViewById(R.id.textView4);
        RadioButton opcion1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton opcion2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton opcion3 = (RadioButton) findViewById(R.id.radioButton3);

        txtPreguntaX.setText("Pregunta"+indice);
        txtEnunciado.setText(actual.enunciado);
        opcion1.setText(opciones.get(0));
        opcion2.setText(opciones.get(1));
        opcion3.setText(opciones.get(2));

        cargarImagenPregunta();
    }

    public void cargarImagenPregunta(){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        switch(actual.idPregunta){
            case 1:
                layout.setBackgroundResource(R.drawable.pregunta1);
                break;
            case 2:
                layout.setBackgroundResource(R.drawable.pregunta2);
                break;
            case 3:
                layout.setBackgroundResource(R.drawable.pregunta3);
                break;
            case 4:
                layout.setBackgroundResource(R.drawable.pregunta4);
                break;
        }
    }
}
