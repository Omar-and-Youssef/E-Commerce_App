package dao;

import java.util.ArrayList;

import database.Database;
import entity.users.accounts.Admin;

public class AdminDAO {
private static ArrayList<Admin> admins= Database.getAdminDB();

public static void addAdmin(Admin admin){
    admins.add(admin);
}
public static boolean deleteAdmin(Admin admin){
    return admins.remove(admin);
}
public static boolean updateAdmin(Admin admin){
    for (int i=0;i<admins.size();i++) {
        if (admins.get(i).getUserID().equals(admin.getUserID())) {
            admins.set(i, admin);
            return true;
        }
    }
return false;
}
//=======================================Get&Set=======================================
public static ArrayList<Admin> getAllAdmins(){
    return admins;
}
public static Admin getAdminById(String id){
    for(Admin a: admins)
        if(a.getUserID().equals(id)) return a;
    return null;
}
public static Admin getAdminByEmail(String email){
    for(Admin a: admins){
        if(a.getEmail().equals(email)){
            return a;
        }
    }
    return null;
}
}
