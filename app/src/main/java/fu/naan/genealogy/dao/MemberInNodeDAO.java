package fu.naan.genealogy.dao;

import android.content.Context;

import java.util.ArrayList;

import fu.naan.genealogy.entity.MemberInNode;

public class MemberInNodeDAO extends DAO{

    public MemberInNodeDAO(Context context) {
        super(context);
    }

    public int insert(MemberInNode memberInNode) {
        return 0;
    }
    public int update(MemberInNode memberInNode) {
        return 0;
    }
    public int delete(MemberInNode memberInNode) {
        return 0;
    }
    public ArrayList<MemberInNode> selectByNodeID(int id) {
        return null;
    }
    public ArrayList<MemberInNode> selectByMemberID(int id) {
        return null;
    }
}
