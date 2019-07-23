package fu.naan.genealogy.algorithm;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import fu.naan.genealogy.common.DataSaver;
import fu.naan.genealogy.dao.FamilyNodeDAO;
import fu.naan.genealogy.dao.MemberDAO;
import fu.naan.genealogy.dao.MemberInNodeDAO;
import fu.naan.genealogy.entity.FamilyNode;
import fu.naan.genealogy.entity.Member;

public class IdentifyRelationship {

    private Context context;
    private DataSaver dataSaver;

    public IdentifyRelationship(Context context) {
        this.context = context;
        dataSaver = new DataSaver(context);
    }

    private String getNaming(Member member, ArrayList<FamilyNode> memberPath1, ArrayList<FamilyNode> memberPath2) {
        int gender = member.getGender();
        int res = memberPath2.size() - memberPath1.size();

        if (memberPath1.get(0).getNodeID() == memberPath2.get(0).getNodeID()) {
            switch (gender) {
                case 0: return dataSaver.getG0M();
                case 1: return dataSaver.getG0F();
            }
        }

        if (res >= 5) { //cao tang to
            switch (gender) {
                case 0: return dataSaver.getGA5M();
                case 1: return dataSaver.getGA5F();
            }
        } else if (res <= -5) {
            switch (gender) {
                case 0: return dataSaver.getGU5M();
                case 1: return dataSaver.getGU5F();
            }
        } else {
                MemberDAO memberDAO = new MemberDAO(context);
                switch (res) {
                    case 4: {
                        switch (gender) {
                            case 0: return dataSaver.getGA4M();
                            case 1: return dataSaver.getGA4F();
                        }
                    }
                    case -4:{
                        switch (gender) {
                            case 0: return dataSaver.getGU4M();
                            case 1: return dataSaver.getGU4F();
                        }
                    }
                    case 3: {
                        switch (gender) {
                            case 0: return dataSaver.getGA3M();
                            case 1: return dataSaver.getGA3F();
                        }
                    }
                    case -3: {
                        switch (gender) {
                            case 0: return dataSaver.getGU3M();
                            case 1: return dataSaver.getGU3F();
                        }
                    }
                    case 2: {
                        switch (gender) {
                            case 0: return dataSaver.getGA2M();
                            case 1: return dataSaver.getGA2F();
                        }
                    }
                    case -2: {
                        switch (gender) {
                            case 0: return dataSaver.getGU2M();
                            case 1: return dataSaver.getGU2F();
                        }
                    }
                    case 1: {//bo me, co di, chu bac, cau mo
                        if (memberPath2.get(0).getParentID() == memberPath1.get(0).getNodeID()) {
                            switch (gender) {
                                case 0: return dataSaver.getGA1M();
                                case 1: return dataSaver.getGA1F();
                            }
                        }
                        Member member1 = memberDAO.selectByNodeId(memberPath2.get(1).getNodeID()).get(0);
                        Member member2 = memberDAO.selectByNodeId(memberPath1.get(0).getNodeID()).get(0);
                        if (member1.getDOB().getTime() > member2.getDOB().getTime()) {
                            switch (gender) {
                                case 0: return dataSaver.getGA1EM();
                                case 1: return dataSaver.getGA1EF();
                            }
                        } else {
                            switch (gender) {
                                case 0: return dataSaver.getGA1YM();
                                case 1: return dataSaver.getGA1YF();
                            }
                        }
                    }
                    case -1: {//con, chau
                        if (memberPath2.get(0).getNodeID() == memberPath1.get(1).getNodeID()) {
                            switch (gender) {
                                case 0: return dataSaver.getGU1M();
                                case 1: return dataSaver.getGU1F();
                            }
                        } else {
                            switch (gender) {
                                case 0: return dataSaver.getGU1EM();
                                case 1: return dataSaver.getGU1EF();
                            }
                        }
                    }
                    case 0: { //anh chi em
                        int position = 0;
                        for (int i = 0; i < memberPath2.size(); i++) {
                            if (memberPath2.get(i).getNodeID() == memberPath1.get(i).getNodeID()) {
                                position = i-1;
                                break;
                            }
                        }
                        Member member1 = memberDAO.selectByNodeId(memberPath2.get(position).getNodeID()).get(0);
                        Member member2 = memberDAO.selectByNodeId(memberPath1.get(position).getNodeID()).get(0);

                        if (member1.getDOB().getTime() > member2.getDOB().getTime()) {
                            switch (gender) {
                                case 0: return dataSaver.getG0EM();
                                case 1: return dataSaver.getG0EF();
                            }
                        } else {
                            switch (gender) {
                                case 0: return dataSaver.getG0YM();
                                case 1: return dataSaver.getG0YF();
                            }
                        }
                    }
                }
            }
        return "Unknow";
    }

    public String identify(int memberId1, int memberId2) {
        ArrayList<FamilyNode> memberRPath1 = getRelationPath(memberId1);
        ArrayList<FamilyNode> memberRPath2 = getRelationPath(memberId2);
        MemberDAO memberDAO = new MemberDAO(context);
        Member member1 = memberDAO.selectByID(memberId1);
        Member member2 = memberDAO.selectByID(memberId2);
        String memberRole1 = getNaming(member1,memberRPath1,memberRPath2);
        String memberRole2 = getNaming(member2,memberRPath2,memberRPath1);
        return memberRole1 + " - " + memberRole2;
    }

    private ArrayList<FamilyNode> getRelationPath(int memberID) {
        ArrayList<FamilyNode> familyNodes = new ArrayList<>();
        FamilyNodeDAO familyNodeDAO = new FamilyNodeDAO(this.context);
        MemberInNodeDAO memberInNodeDAO = new MemberInNodeDAO(this.context);
        FamilyNode currentNode = familyNodeDAO.selectByNodeID(
                memberInNodeDAO.selectByMemberID(memberID).get(0).getNodeID()).get(0);
        familyNodes.add(currentNode);
        int parentID;
        while (true){
            parentID = familyNodes.get(familyNodes.size()-1).getParentID();
            if (parentID == -1) break;
            familyNodes.add(familyNodeDAO.selectByNodeID(parentID).get(0));
        }
        return familyNodes;
    }

}
