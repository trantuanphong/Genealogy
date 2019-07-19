package fu.naan.genealogy.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fu.naan.genealogy.entity.FamilyNode;
import fu.naan.genealogy.handler.DatabaseHandler;

public class FamilyNodeDAO extends DAO{

    public FamilyNodeDAO(Context context) {
        super(context);
    }

    public int insert(FamilyNode familyNode) {
        return 0;
    }
    public int update(FamilyNode familyNode) {
        return 0;
    }
    public int delete(FamilyNode familyNode) {
        return 0;
    }
    public ArrayList<FamilyNode> selectByParentID(int id) {
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_NODE + " WHERE "
                + DatabaseHandler.NODE_COLUMN_PARENT_NODE + " = " + id;
        return select(query);
    }

    public ArrayList<FamilyNode> selectByNodeID(int id) {
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_NODE + " WHERE "
                + DatabaseHandler.NODE_COLUMN_ID + " = " + id;
        Log.i("hihi",query);
        return select(query);
    }

    public ArrayList<FamilyNode> selectAll() {
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_NODE;
        return select(query);
    }

    private ArrayList<FamilyNode> select(String query) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<FamilyNode> familyNodes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int nodeID = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.NODE_COLUMN_ID));
                int parentID = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.NODE_COLUMN_PARENT_NODE));
                familyNodes.add(new FamilyNode(nodeID,parentID));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return familyNodes;
    }
}
