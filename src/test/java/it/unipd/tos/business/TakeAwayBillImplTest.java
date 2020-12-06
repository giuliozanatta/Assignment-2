////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImplTest {
     
     private static TakeAwayBillImpl tab;
     
     @BeforeClass
     public static void beforeClass() {
          tab = new TakeAwayBillImpl();
     }
     
     @Test
     public void GetOrderedPriceTest() {
          List<MenuItem> list = new ArrayList<MenuItem>();
          User user = new User("Mario", "Rossi", 23);
          list.add(new MenuItem(ItemType.Bevande, "CocaCola", 5));
          list.add(new MenuItem(ItemType.Budini, "Caramello", 8));
          list.add(new MenuItem(ItemType.Gelati, "Coppa Nafta", 10));
          
          try {
               assertEquals(23, tab.getOrderPrice(list, user), 0);
          } catch (RestaurantBillException e) {
               
               e.printStackTrace();
          }
     }    
}