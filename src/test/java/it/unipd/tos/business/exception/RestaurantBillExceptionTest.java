////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class RestaurantBillExceptionTest{
    private static RestaurantBillException tab;

    @BeforeClass
    public static void beforeClass(){
        tab = new RestaurantBillException("Errore");
    }

    @Test
    public void getMessageTest(){
        assertEquals("Errore", tab.getMessage());
    }
} 