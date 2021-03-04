/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.infra;

/**
 *
 * @author Rojas
 */
public class Utilities {
    
    public static int redondear(int numero){
        int residuo=numero%100;
        if (residuo!=0) {
            numero+=100-residuo;
        }
        return numero;
    }
}
