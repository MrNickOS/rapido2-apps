package modelo;

import java.io.Serializable;

/**
 * Created by usuario on 03/11/2016.
 */
public class Estadistica implements Serializable{

    public int aciertos1;
    public int aciertos2;
    public int aciertos3;
    public int aciertos4;

    public int errores1;
    public int errores2;
    public int errores3;
    public int errores4;


    public Estadistica(){
        // Constructor utilizado para obtener objetos con el DataSnapshot
    }

    public Estadistica(int a1, int a2, int a3, int a4, int a5,
                       int e1, int e2, int e3, int e4){

        aciertos1 = a1; errores1 = e1;
        aciertos2 = a2; errores2 = e2;
        aciertos3 = a3; errores3 = e3;
        aciertos4 = a4; errores4 = e4;
    }
}
