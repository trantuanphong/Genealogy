package fu.naan.genealogy.algorithm;

import android.content.Context;

import java.util.ArrayList;

import fu.naan.genealogy.R;
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

    private String getNaming(Member member, ArrayList<FamilyNode> memberPath1, ArrayList<FamilyNode> memberPath2) {
        int gender = member.getGender();
        int res = memberPath2.size() - memberPath1.size();

        if (memberPath1.get(0).getNodeID() == memberPath2.get(0).getNodeID()) {
            switch (gender) {
                case 0: return context.getString(R.string.chong);
                case 1: return context.getString(R.string.vo);
            }
        }

        if (res >= 5) { //cao tang to
            switch (gender) {
                case 0: return context.getString(R.string.caoTangTo);
                case 1: return context.getString(R.string.caoTangTi);
            }
        } else if (res <= -5) {
            switch (gender) {
                case 0: return context.getString(R.string.huyenTonNam);
                case 1: return context.getString(R.string.huyenTonNu);
            }
        } else {
                MemberDAO memberDAO = new MemberDAO(context);
                switch (res) {
                    case 4: {
                        switch (gender) {
                            case 0: return context.getString(R.string.kyOng);
                            case 1: return context.getString(R.string.kyBa);
                        }
                    }
                    case -4:{
                        switch (gender) {
                            case 0: return context.getString(R.string.chatNam);
                            case 1: return context.getString(R.string.chatNu);
                        }
                    }
                    case 3: {
                        switch (gender) {
                            case 0: return context.getString(R.string.cuOng);
                            case 1: return context.getString(R.string.cuBa);
                        }
                    }
                    case -3: {
                        switch (gender) {
                            case 0: return context.getString(R.string.chauTrai);
                            case 1: return context.getString(R.string.chauGai);
                        }
                    }
                    case 2: {
                        switch (gender) {
                            case 0: return context.getString(R.string.ong);
                            case 1: return context.getString(R.string.ba);
                        }
                    }
                    case -2: {
                        switch (gender) {
                            case 0: return context.getString(R.string.chauTrai);
                            case 1: return context.getString(R.string.chauGai);
                        }
                    }
                    case 1: {//bo me, co di, chu bac, cau mo
                        if (memberPath2.get(0).getParentID() == memberPath1.get(0).getNodeID()) {
                            switch (gender) {
                                case 0: return context.getString(R.string.bo);
                                case 1: return context.getString(R.string.me);
                            }
                        }
                        Member member1 = memberDAO.selectByNodeId(memberPath2.get(1).getNodeID()).get(0);
                        Member member2 = memberDAO.selectByNodeId(memberPath1.get(0).getNodeID()).get(0);
                        if (member1.getDOB().getTime() > member2.getDOB().getTime()) {
                            switch (gender) {
                                case 0: return context.getString(R.string.bacTrai);
                                case 1: return context.getString(R.string.bacGai);
                            }
                        } else {
                            switch (gender) {
                                case 0: return context.getString(R.string.chu);
                                case 1: return context.getString(R.string.co);
                            }
                        }
                    }
                    case -1: {//con, chau
                        if (memberPath2.get(0).getNodeID() == memberPath1.get(1).getNodeID()) {
                            switch (gender) {
                                case 0: return context.getString(R.string.conTrai);
                                case 1: return context.getString(R.string.conGai);
                            }
                        } else {
                            switch (gender) {
                                case 0: return context.getString(R.string.chauTrai);
                                case 1: return context.getString(R.string.chauGai);
                            }
                        }
                    }
                    case 0: { //anh chi em
                        int position = 0;
                        for (int i = 0; i < memberPath2.size(); i++) {
                            if (memberPath2.get(i).getNodeID() == memberPath1.get(i).getNodeID()) {
                                position = i - 1;
                                break;
                            }
                        }
                        Member member1 = memberDAO.selectByNodeId(memberPath2.get(position).getNodeID()).get(0);
                        Member member2 = memberDAO.selectByNodeId(memberPath1.get(position).getNodeID()).get(0);
                        if (member1.getDOB().getTime() > member2.getDOB().getTime()) {
                            switch (gender) {
                                case 0: return context.getString(R.string.anh);
                                case 1: return context.getString(R.string.chi);
                            }
                        } else {
                            switch (gender) {
                                case 0: return context.getString(R.string.emTrai);
                                case 1: return context.getString(R.string.emGai);
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
