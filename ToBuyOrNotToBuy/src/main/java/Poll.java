/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YH Jonathan Kwok
 */
public class Poll {
    String Name;
    int id;

    public Poll(String Name, int id) {
        this.Name = Name;
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }
    
    
}
