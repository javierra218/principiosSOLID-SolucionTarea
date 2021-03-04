/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.access;

/**
 *
 * @author Rojas
 */
public class RepositoryFactory {
    
    private static RepositoryFactory instance;
    
    private RepositoryFactory(){
        
    }
    
    public static RepositoryFactory getInstance(){
        
        if (instance==null) {
            instance=new RepositoryFactory();
        }
        return instance;
    }
    
    public IVehicleRepository getRepository(String type){
        
        IVehicleRepository vehicleRepository=null;
        
        switch (type){
            case "default":
                vehicleRepository = new VehicleRepository();
                break;
        }
        return vehicleRepository;
    }
    
  
    
    
}
