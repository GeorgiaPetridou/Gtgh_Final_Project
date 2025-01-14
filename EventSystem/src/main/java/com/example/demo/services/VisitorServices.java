package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.users.Reservation;
import com.example.demo.users.Visitor;

@Service
public class VisitorServices {

	 private ReservationService reservationService;
	

	private List<Visitor> visitors = new ArrayList<Visitor>();
	
	
	//Getters and Setters
	public List<Visitor> getAllVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}


	
	//Methods
	
	//give uniqVisitorId
	private Integer UniqVisitorID() {
	    return visitors.stream()
	            .mapToInt(Visitor::getID) 
	            .max() 
	            .orElse(0) + 1; 
	}
	
	
	//Add visitor to the list
	public List<Visitor> addVisitor(Visitor visitor) {

        Integer visitorID = UniqVisitorID();
		
		visitor.setID(visitorID);
		visitors.add(visitor);
		return visitors;
	}
	
	//Remove visitor from the list
	public List<Visitor> removeVisitor(Integer ID) {
        Visitor visitorToRemove = visitors.stream()
                .filter(visitor -> visitor.getID().equals(ID))
                .findFirst()
                .orElse(null);

        if (visitorToRemove == null) {
            System.out.println("No visitor found with ID: " + ID);
            return visitors;
        }

        reservationService.removeAllReservationsForSpecificVisitor(ID);
        visitors.remove(visitorToRemove);
        return visitors;
    }

	

        
	//Update visitor's informations
	public List<Visitor> updateVisitor(Integer ID,String newName,String newSurname,String newEmail) {
		for (Visitor visitor : visitors) {
			if(visitor.getID().equals(ID)) {
				if(newName !=null) 
					visitor.setName(newName); 
				
				if(newSurname !=null) 
					visitor.setSurname(newSurname); 
				
				if(newEmail !=null) 
					visitor.setEmail(newEmail); 
			}
		}
		return visitors;
	
	}

	
	
	
}
