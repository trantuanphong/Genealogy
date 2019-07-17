package fu.naan.genealogy.entity;

import java.util.Date;

public class Member {
    private int memberID;
    private String memberName;
    private Date DOB;
    private Date DOD;
    private int isAlive;
    private String avatar;
    private String motherID;
    private String fatherID;

    public Member() {
    }

    public Member(int memberID) {
        this.memberID = memberID;
    }

    public Member(int memberID, String memberName) {
        this.memberID = memberID;
        this.memberName = memberName;
    }

    public Member(String memberName, Date DOB, Date DOD, int isAlive, String avatar) {
        this.memberName = memberName;
        this.DOB = DOB;
        this.DOD = DOD;
        this.isAlive = isAlive;
        this.avatar = avatar;
    }

    public Member(String memberName, Date DOB, Date DOD, int isAlive, String avatar, String motherID, String fatherID) {
        this.memberName = memberName;
        this.DOB = DOB;
        this.DOD = DOD;
        this.isAlive = isAlive;
        this.avatar = avatar;
        this.motherID = motherID;
        this.fatherID = fatherID;
    }

    public Member(int memberID, String memberName, Date DOB, Date DOD, int isAlive, String avatar, String motherID, String fatherID) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.DOB = DOB;
        this.DOD = DOD;
        this.isAlive = isAlive;
        this.avatar = avatar;
        this.motherID = motherID;
        this.fatherID = fatherID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getDOD() {
        return DOD;
    }

    public void setDOD(Date DOD) {
        this.DOD = DOD;
    }

    public int getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(int isAlive) {
        this.isAlive = isAlive;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    @Override
    public String toString() {
        return memberName;
    }
}
