////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
    
    private static User user;
    
    @BeforeClass
    public static void beforeClass() {
        user = new User("Mario", "Rossi", 23);
    }
    
    @Test
    public void GetIdTest() {
     String id = user.getUniqueID();
     assertEquals(user.getUniqueID(), id);
    }
     
    @Test
    public void GetNameTest() {
        assertEquals(user.getName(), "Mario");
    }
    
    @Test
    public void GetSurnameTest() {
        assertEquals(user.getSurname(), "Rossi");
    }
    
    @Test
    public void GetAgeTest() {
        assertEquals(user.getAge(), 23);
    }
}
