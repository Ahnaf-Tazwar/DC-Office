/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author `Dipanker
 */
public class Admin extends User {

    public Admin(String t,     int i,     String p,     String d,     String e,
     String exp,     String ema) {
        this.type = t;
        this.id = i;
        this.newpass = p ;
        this.dob = d;
        this.education = e;
        this.experience = exp;
        this.email = ema;
    }

   
}
