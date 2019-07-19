package fu.naan.genealogy.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

import fu.naan.genealogy.entity.MemberInNode;
import fu.naan.genealogy.handler.DatabaseHandler;

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
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_MEMBER_IN_NODE
                + " WHERE " + DatabaseHandler.MEMBER_IN_NODE_COLUMN_NODE_ID + " = " + id;
        return select(query);
    }
    public ArrayList<MemberInNode> selectByMemberID(int id) {
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_MEMBER_IN_NODE + " WHERE "
                + DatabaseHandler.MEMBER_IN_NODE_COLUMN_MEMBER_ID + " = " + id;
        return select(query);
    }

    private ArrayList<MemberInNode> select(String query) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<MemberInNode> memberInNodes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int nodeID = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.MEMBER_IN_NODE_COLUMN_NODE_ID));
                int memberID = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.MEMBER_IN_NODE_COLUMN_MEMBER_ID));
                memberInNodes.add(new MemberInNode(nodeID,memberID));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return memberInNodes;
    }
}
