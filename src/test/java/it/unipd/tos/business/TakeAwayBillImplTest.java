////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillImplTest {
     
     private static TakeAwayBillImpl tab;
     private static ArrayList<User> gifted = new ArrayList<User>();
     
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
               assertEquals(23, tab.getOrderPrice(list, user, LocalTime.of(18, 22), gifted), 0);
          } catch (RestaurantBillException e) {
               
               e.printStackTrace();
          }
     }   
     
     @Test
     public void FiftyPercentDiscountTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Mario", "Rossi", 23);
         list.add(new MenuItem(ItemType.Bevande, "CocaCola", 5));
         list.add(new MenuItem(ItemType.Budini, "Caramello", 3));
         list.add(new MenuItem(ItemType.Gelati, "Coppa Nafta", 5));
         list.add(new MenuItem(ItemType.Gelati, "Gelato1", 3));
         list.add(new MenuItem(ItemType.Gelati, "Gelato2", 4));
         list.add(new MenuItem(ItemType.Gelati, "Gelato3", 4));
         list.add(new MenuItem(ItemType.Gelati, "Coppa Amarena", 4));
         list.add(new MenuItem(ItemType.Gelati, "Coppa Cioccolato", 4));
         list.add(new MenuItem(ItemType.Gelati, "Coppa EisCaffe", 4));
         list.add(new MenuItem(ItemType.Budini, "Cioccolato", 3));
         list.add(new MenuItem(ItemType.Bevande, "Fanta", 5));
         
         try {
            assertEquals(42.5, tab.getOrderPrice(list, user, LocalTime.of(18, 22), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }
     
     @Test
     public void TenPercentDiscountTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Mario", "Rossi", 23);
         list.add(new MenuItem(ItemType.Gelati, "Coppa Amarena", 20));
         list.add(new MenuItem(ItemType.Gelati, "Coppa Cioccolato", 20));
         list.add(new MenuItem(ItemType.Gelati, "Coppa EisCaffe", 15));
         list.add(new MenuItem(ItemType.Bevande, "Birra", 5));
         
         try {
            assertEquals(54, tab.getOrderPrice(list, user, LocalTime.of(18, 22), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }
     
     @Test
     public void TenAndFiftyDiscountTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Mario", "Rossi", 23);
         list.add(new MenuItem(ItemType.Gelati, "Coppa 9", 10));
         list.add(new MenuItem(ItemType.Gelati, "Coppa 4", 10));
         list.add(new MenuItem(ItemType.Gelati, "Coppa 3", 10));
         list.add(new MenuItem(ItemType.Gelati, "Coppa 7", 10));
         list.add(new MenuItem(ItemType.Gelati, "Coppa 4", 10));
         list.add(new MenuItem(ItemType.Gelati, "Coppa 1", 5));
         
         try {
            assertEquals(47.25, tab.getOrderPrice(list, user, LocalTime.of(18, 22), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }
     
     @Test(expected = RestaurantBillException.class)
     public void Limit30ElementsTest() throws RestaurantBillException {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Mario", "Rossi", 23);
         for(int i=0; i<50; i++) {
             list.add(new MenuItem(ItemType.Gelati, "Coppa Arcobaleno", 9));
         }
         tab.getOrderPrice(list, user, LocalTime.of(18, 22), gifted);
     }
    
     @Test
     public void CommissionTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Mario", "Rossi", 23);
         list.add(new MenuItem(ItemType.Bevande, "CocaCola", 5));
         list.add(new MenuItem(ItemType.Budini, "Caramello", 3));
         
         try {
            assertEquals(8.5, tab.getOrderPrice(list, user, LocalTime.of(18, 22), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }

     @Test
     public void UnderAgeGiftTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Mario", "Rossi", 13);
         list.add(new MenuItem(ItemType.Bevande, "CocaCola", 5));
         list.add(new MenuItem(ItemType.Budini, "Caramello", 3));
         
         try {
            assertEquals(0, tab.getOrderPrice(list, user, LocalTime.of(18, 0), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }
     
     @Test
     public void UnderAgeGiftRepeatedTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Marco", "Alberti", 13);
         gifted.add(user);
         list.add(new MenuItem(ItemType.Bevande, "CocaCola", 5));
         list.add(new MenuItem(ItemType.Budini, "Caramello", 3));
         
         try {
            assertEquals(8.5, tab.getOrderPrice(list, user, LocalTime.of(18, 0), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }
     
     @Test
     public void OverAgeGiftTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Giulio", "Stefani", 23);
         gifted.add(user);
         list.add(new MenuItem(ItemType.Bevande, "CocaCola", 5));
         list.add(new MenuItem(ItemType.Budini, "Caramello", 3));
         list.add(new MenuItem(ItemType.Gelati, "Coppa 2", 3));
         list.add(new MenuItem(ItemType.Bevande, "Caffè", 1));
         
         try {
            assertEquals(12, tab.getOrderPrice(list, user, LocalTime.of(18, 50), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }
     
     @Test
     public void NineteenHourTest() {
         List<MenuItem> list = new ArrayList<MenuItem>();
         User user = new User("Stefano", "Lazzaroni", 15);
         list.add(new MenuItem(ItemType.Bevande, "CocaCola", 5));
         list.add(new MenuItem(ItemType.Budini, "Caramello", 3));
         list.add(new MenuItem(ItemType.Gelati, "Coppa 2", 3));
         list.add(new MenuItem(ItemType.Bevande, "Caffè", 1));
         
         try {
            assertEquals(0, tab.getOrderPrice(list, user, LocalTime.of(19, 0), gifted), 0);
        } catch (RestaurantBillException e) {
            e.printStackTrace();
        }
     }
}