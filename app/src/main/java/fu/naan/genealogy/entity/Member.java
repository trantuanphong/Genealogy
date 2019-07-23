package fu.naan.genealogy.entity;

import java.util.Date;

public class Member {
    private int memberID;
    private String memberName;
    private Date DOB;
    private Date DOD;
    private int gender;
    private String avatar;
    private int motherID;
    private int fatherID;

    public Member() {
    }

    public Member(int memberID) {
        this.memberID = memberID;
    }

    public Member(int memberID, String memberName) {
        this.memberID = memberID;
        this.memberName = memberName;
    }

    public Member(int memberID, String memberName, Date DOB, int gender) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.DOB = DOB;
        this.gender = gender;
    }

    public Member(String memberName, Date DOB, Date DOD, int isAlive, String avatar) {
        this.memberName = memberName;
        this.DOB = DOB;
        this.DOD = DOD;
        this.avatar = avatar;
    }

    public Member(String memberName, Date DOB, Date DOD, String avatar, int motherID, int fatherID) {
        this.memberName = memberName;
        this.DOB = DOB;
        this.DOD = DOD;
        this.avatar = avatar;
        this.motherID = motherID;
        this.fatherID = fatherID;
    }

    public Member(int memberID, String memberName, Date DOB, Date DOD, String avatar, int motherID, int fatherID) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.DOB = DOB;
        this.DOD = DOD;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getMotherID() {
        return motherID;
    }

    public void setMotherID(int motherID) {
        this.motherID = motherID;
    }

    public int getFatherID() {
        return fatherID;
    }

    public void setFatherID(int fatherID) {
        this.fatherID = fatherID;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return memberName;
    }
}
