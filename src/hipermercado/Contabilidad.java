/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hipermercado;

import java.util.Calendar;

public class Contabilidad {

    private double saldo = 0;
  
    void añadeSaldo(double cantidad){
        //Obtenemos la hora y la fecha por pantalla con formato:
        Calendar fecha = Calendar.getInstance();
        System.out.println("Se registro el saldo el: "+fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+
        fecha.get(Calendar.YEAR)+" a las "+fecha.get(Calendar.HOUR_OF_DAY) +":"+fecha.get(Calendar.MINUTE)+":"+fecha.get(Calendar.SECOND));
        saldo += cantidad;
    }
    
    double  dameSaldo() {
        return saldo;
    }
}

