/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain;

import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Rojas
 */
public class ParkingCostFactory {
    
    private Map<TypeEnum, IParkingCost> dictionary;

    // Singleton
    private static ParkingCostFactory instance;

    /**
     * Método que obtiene la instancia de la clase
     * 
     * @return Devuelve la instancia unica de ParkingCostFactory 
     */
    public static ParkingCostFactory getInstance() {
        if (instance == null) {
            instance = new ParkingCostFactory();
        }
        return instance;
    }

    /**
     * Determina la clase que se encargara de calcular el costo del parqueadero
     * 
     * @param veh El tipo de vehiculo al cual se le va a calcular el costo
     * @return Devuelve la clase encargada de calcular el costo del parqueadero dependiendo del tipo de vehiculo
     */
    public IParkingCost getParkingCost(TypeEnum veh){
        IParkingCost result = null;
        
        if (dictionary.containsKey(veh)) {
            result = dictionary.get(veh);
        }
        
        return result;
    }
    
    private ParkingCostFactory() {
        dictionary = new EnumMap<>(TypeEnum.class);
        dictionary.put(TypeEnum.MOTO, new MotoParkingCost());
        dictionary.put(TypeEnum.CAR, new CarParkingCost());
        dictionary.put(TypeEnum.TRUCK, new TruckParkingCost());
        // Si se requie otro vehiculo, se abre un registro en este diccionario
        // No se viola el principio porque este este modulo no está pensado
        // para que sea estable.
    }
    
}
