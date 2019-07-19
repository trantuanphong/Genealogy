package fu.naan.genealogy.algorithm;

import android.content.Context;

import java.util.ArrayList;

import fu.naan.genealogy.dao.FamilyNodeDAO;
import fu.naan.genealogy.dao.MemberDAO;
import fu.naan.genealogy.dao.MemberInNodeDAO;
import fu.naan.genealogy.entity.FamilyNode;
import fu.naan.genealogy.entity.Member;

public class IdentifyRelationship {

    private Context context;

    public IdentifyRelationship(Context context) {
        this.context = context;
    }

    public String getNaming(Member member, ArrayList<FamilyNode> memberPath1, ArrayList<FamilyNode> memberPath2) {
        int gender = 1;
        int res = memberPath1.size() - memberPath2.size();

        if (memberPath1.get(0).getNodeID() == memberPath2.get(0).getNodeID()) {
            switch (gender) {
                case 0: return "Chong";
                case 1: return "Vo";
            }
        }

        if (res >= 5) { //cao tang to
            switch (gender) {
                case 0: return "Cao Tang To";
                case 1: return "Cao Tang Ti";
            }
        } else if (res <= -5) {
            return "Huyen Ton";
        } else {
                MemberDAO memberDAO = new MemberDAO(context);
                switch (res) {
                    case 4: {
                        switch (gender) {
                            case 0: return "Ky Ong";
                            case 1: return "Ky Ba";
                        }
                    }
                    case -4:{
                        switch (gender) {
                            case 0: return "Chat nam";
                            case 1: return "Chat nu";
                        }
                    }
                    case 3: {
                        switch (gender) {
                            case 0: return "Cu Ong";
                            case 1: return "Cu Ba";
                        }
                    }
                    case -3: {
                        switch (gender) {
                            case 0: return "Chau trai";
                            case 1: return "Chau gai";
                        }
                    }
                    case 2: {
                        switch (gender) {
                            case 0: return "Ong";
                            case 1: return "Ba";
                        }
                    }
                    case -2: {
                        switch (gender) {
                            case 0: return "Chau trai";
                            case 1: return "Chau gai";
                        }
                    }
                    case 1: {//co di, chu bac, cau mo
                        Member member1 = memberDAO.selectByNodeId(memberPath1.get(0).getNodeID()).get(0);
                        Member member2 = memberDAO.selectByNodeId(memberPath2.get(1).getNodeID()).get(0);
                        if (member1.getDOB().getTime() < member2.getDOB().getTime()) {
                            switch (gender) {
                                case 0: return "Bac trai";
                                case 1: return "Bac gai";
                            }
                        } else {
                            switch (gender) {
                                case 0: return "Chu";
                                case 1: return "Co";
                            }
                        }
                    }
                    case -1: {//con, chau
                        if (memberPath1.get(0).getNodeID() == memberPath2.get(1).getNodeID()) {
                            switch (gender) {
                                case 0: return "Con trai";
                                case 1: return "Con gai";
                            }
                        } else {
                            switch (gender) {
                                case 0: return "Chau trai";
                                case 1: return "Chau gai";
                            }
                        }
                    }
                    case 0: { //anh chi em
                        int position = 0;
                        for (int i = 0; i < memberPath1.size(); i++) {
                            if (memberPath1.get(i).getNodeID() == memberPath2.get(i).getNodeID()) {
                                position = i - 1;
                                break;
                            }
                        }
                        Member member1 = memberDAO.selectByNodeId(memberPath1.get(position).getNodeID()).get(0);
                        Member member2 = memberDAO.selectByNodeId(memberPath2.get(position).getNodeID()).get(0);
                        if (member1.getDOB().getTime() < member2.getDOB().getTime()) {
                            switch (gender) {
                                case 0: return "Anh";
                                case 1: return "Chi";
                            }
                        } else {
                            switch (gender) {
                                case 0: return "Em trai";
                                case 1: return "Em gai";
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
