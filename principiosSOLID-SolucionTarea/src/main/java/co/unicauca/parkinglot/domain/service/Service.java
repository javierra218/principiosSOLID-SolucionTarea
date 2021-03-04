/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain.service;

import co.unicauca.parkinglot.access.IVehicleRepository;
import co.unicauca.parkinglot.domain.IParkingCost;
import co.unicauca.parkinglot.domain.ParkingCostFactory;
import co.unicauca.parkinglot.domain.TypeEnum;
import co.unicauca.parkinglot.domain.Vehicle;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Rojas
 */
public class Service {

    private IVehicleRepository repository;

    /*public Service(){
        
    }*/
    public Service(IVehicleRepository repo) {
        this.repository = repo;

    }

    /* public long calculateParkingCost(){
        
    }*/
    public long calculateParkingCost(Vehicle veh, LocalDateTime input, LocalDateTime output) {
         long pago=0;
      
      
      if(veh.getType()==TypeEnum.TRUCK)  {
        if (Sorteo()) {
            return 0;
        }
      }
            IParkingCost parkingCost = ParkingCostFactory.getInstance().getParkingCost(veh.getType());
            pago=parkingCost.calculateCost(veh,input,output);
        
        return  pago;

    }

    public boolean Sorteo() {
        int numParticipante=0;
        try {
        String num =JOptionPane.showInputDialog("ingrese el numero  entre 1 y 1000");    
        numParticipante= Integer.parseInt(num);
        } catch (Exception e) {
            
        }
        int numeroGanador = (int) (Math.random() * 1000);
        System.out.println(" el numero ganador es :"+numeroGanador+"\n el numero que ingresaste es: "+numParticipante);
        if (numParticipante == numeroGanador) {
            
            return true;
        }
        return false;
    }

    /* public boolean saveVehicle(){
        
    }*/
    public boolean saveVehicle(Vehicle veh) {

        return repository.save(veh);
    }

    public List<Vehicle> listVehicles() {
        List<Vehicle> vehicles = repository.list();
        return vehicles;
    }

}
