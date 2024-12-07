package entity.users.details;
import java.util.Date;

public class Membership {
    private MembershipType membershipType;
    private Date startDate;
    private Date expirationDate;
//=======================================Constructor===================================
    public Membership(){
        membershipType=MembershipType.STANDARD;
    }
//=======================================Methods=======================================
    public void upgradeMemberShip(Date expirationDate){
        membershipType=MembershipType.PRIME;
        startDate=new Date();
        expirationDate=this.expirationDate;
    }
    public boolean checkMemberShipValidity(){
        Date currentDate=new Date();
        if(membershipType==MembershipType.PRIME&&currentDate.compareTo(expirationDate)>=0){
            membershipType=MembershipType.STANDARD;
            return false;
        }
        return true;
    }
//=======================================Get&Set=======================================
    public Date getStartDate(){
        if(MembershipType.PRIME==membershipType)return startDate;
        return null;
    }
    public Date getExpirtDate(){
        if(MembershipType.PRIME==membershipType)return expirationDate;
        return null;
    }
    public MembershipType getMembershipType(){
        return membershipType;
    }
}
