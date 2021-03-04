/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain;

/**
 *
 * @author Rojas
 */
public class Vehicle {
    
    /*
     * Placa
     */
    private String plate;
    
    /*
     * Tipo: carro, moto, camion
     */
    private TypeEnum type;
    
    /*
     * Constructor por defecto de la clase Vehicle
     */
    public Vehicle(){
    }
    
    /*
     * Constructor parametrizado de la clase Vehicle
     * 
     * @param plate placa del vehiculo
     * @param tipo tipo del vehiculo
     */
    public Vehicle(String plate, TypeEnum tipo){
        this.plate = plate;
        this.type = tipo;
    }
    
    //Getters y Setters
    
    /**
     * Metodo para obtener la placa del vehiculo
     * @return Devuelve la placa del vehiculo actual 
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Metodo para modificar la placa del vehiculo
     * @param plate La nueva placa que se le asignara al vehiculo
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * Metodo para obtener el tipo de vehiculo
     * @return Devuelve el tipo del vehiculo actual
     */
    public TypeEnum getType() {
        return type;
    }

    /**
     * Metodo para modificar el tipo del vehiculo actual
     * @param type El nuevo tipo de se le asignara al vehiculo
     */
    public void setType(TypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String resul;
        resul = "Vehicle{plate="+plate +", type="+type+"}";
        return resul;
    }
    
    
    
}
