////////////////////////////////////////////////////////////////////
// [GIULIO] [ZANATTA] [1163163]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.util.UUID;

public class User{

    private String uniqueID;
    private String name;
    private String surname;
    private int age;

   
    public User(String name, String surname, int age) {
        this.uniqueID = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    
    public String getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
}