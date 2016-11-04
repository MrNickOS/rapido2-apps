package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by usuario on 03/11/2016.
 */
public class Pregunta implements Serializable{

    public String enunciado;
    public int idPregunta;
    public String correcta;
    public ArrayList<String> incorrectas;

    public Pregunta(String enun, int id, String correct, ArrayList<String> err) {
        enunciado = enun;
        idPregunta = id;
        correcta = correct;
        incorrectas = err;
    }

    public Pregunta (){

    }
}
