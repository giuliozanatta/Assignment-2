////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImpl implements TakeAwayBill {
    
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) 
        throws RestaurantBillException {
        
        double numGelati = 0;
        double min = Double.MAX_VALUE;
        double ord = 0;
        double tot = 0;
        
        if(itemsOrdered.size() > 30) {
            throw new RestaurantBillException("ERROR: LIMITE DI 30 ELEMENTI.");
        }
      
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
     