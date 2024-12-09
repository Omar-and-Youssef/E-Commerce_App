package dao;

import java.util.ArrayList;

import database.Database;
import entity.users.accounts.Admin;

public class AdminDAO {
private static ArrayList<Admin> admins= Database.getAdminDB();

public void addAdmin(Admin admin){
    admins.add(admin);
}
public void deleteAdmin(Admin admin){
    admins.remove(admin);
}
public void updateAdminDetails(Admin newAdmin){
    Admin a=getAdminById(newAdmin.getUserID());
    a.setName(newAdmin.getName());
    a.setEmail(newAdmin.getEmail());
    a.setAddress(newAdmin.getAddress());
}
// public void updateCustomerWallet(){}
// public void updatePassword()
//=======================================Get&Set=======================================
    public ArrayList<Admin> getAllAdmins(){
        return admins;
    }
    public Admin getAdminById(String id){
        for(Admin a: admins)
            if(a.getUserID().equals(id)) return a;
        return null;
    }
    public Admin getAdminByEmail(String email){
        for(Admin a: admins){
            if(a.getEmail().equals(email)){
                return a;
            }
        }
        return null;
    }
}
