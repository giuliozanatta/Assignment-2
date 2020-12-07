////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImpl implements TakeAwayBill {
    
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user,
            LocalTime now, ArrayList<User> gifted) 
        throws RestaurantBillException {
     
        double tot = 0;
        Random rand = new Random(now.toSecondOfDay());
        
        if(itemsOrdered.size() > 30) {
            throw new RestaurantBillException("ERROR: LIMITE DI 30 ELEMENTI.");
        }
       
        if(gifted.size()<10 && gifted.contains(user)==false 
                && user.getAge() < 18) {
            if(((now.getHour() == 18 && now.getMinute() >= 0) 
                    || now.getHour() == 19) && rand.nextInt()%2 == 0) {
                         gifted.add(user);    
            }
        }else{
            tot = calcBill(itemsOrdered);
        }
          
        return tot;
    }
    
    private double calcBill(List<MenuItem> itemsOrdered) {
        double min = Double.MAX_VALUE;
        int numGelati = 0;
        double ord = 0; 
        double tot = 0;
        
        for(int i=0; i<itemsOrdered.size(); i++) {
            if(itemsOrdered.get(i).getItemType()==ItemType.Gelati) {
                if(itemsOrdered.get(i).getPrice()<min) {
                    min = itemsOrdered.get(i).getPrice();
                }
                numGelati++;
                ord += itemsOrdered.get(i).getPrice();
            }else {
                if(itemsOrdered.get(i).getItemType()!=ItemType.Bevande) {
                    ord += itemsOrdered.get(i).getPrice();
                }
            } 
            tot += itemsOrdered.get(i).getPrice();
        }     
        
        if(numGelati > 5) {
            tot -= min/2;
       }
        
        if(ord > 50) {
            tot -= tot*(0.1);
        }else {
            if(tot != 0 && tot < 10) {
                tot += 0.5;
            }
        }
        return tot;
    }
}
     