////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuItemTest{
    private static MenuItem item;

    @BeforeClass
    public static void beforeClass(){
        item = new MenuItem(ItemType.Gelati, "Coppa Nafta", 20);
    }

    @Test
    public void GetItemTypeTest(){
        assertEquals(ItemType.Gelati, item.getItemType());
    }

    @Test
    public void GetNameTest(){
        assertEquals("Coppa Nafta", item.getName());
    }

    @Test
    public void GetPriceTest(){
        assertEquals(20, item.getPrice(), 0);
    }
}