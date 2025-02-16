package dao;

import java.util.ArrayList;

import database.Database;
import entity.users.accounts.Admin;

public class AdminDAO{
Database database=new Database();
private ArrayList<Admin> admins= database.getAdminDB();
//==========================================CRUD==========================================
public void addAdmin(Admin admin){
    admins.add(admin);
}
public boolean deleteAdmin(Admin admin){
    return admins.remove(admin);
}
public boolean updateAdmin(Admin admin){
    for (int i=0;i<admins.size();i++) {
        if (admins.get(i).getUserID().equals(admin.getUserID())) {
            admins.set(i, admin);
            return true;
        }
    }
return false;
}
public ArrayList<Admin> getAllAdmins(){
    return admins;
}
public Admin getAdminById(String id){
    for(Admin a: admins)
        if(a.getUserID().equals(id)) return a;
    return null;
}
public Admin getAdminByEmail(String email){
    try{
    for(Admin a: admins)
        if(a.getEmail().equals(email))
            return a;
    }
    catch(Exception e){return null;}
        return null;
}
public boolean adminInDB(Admin admin){
    return getAdminById(admin.getUserID())!=null;
}
}
