package fu.naan.genealogy.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fu.naan.genealogy.entity.Member;
import fu.naan.genealogy.handler.DatabaseHandler;

public class MemberDAO extends DAO{

    public MemberDAO(Context context) {
        super(context);
    }

    public int insert(Member member) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.MEMBER_COLUMN_NAME, member.getMemberName());
        values.put(DatabaseHandler.MEMBER_COLUMN_DOB, member.getDOB().toString());
        values.put(DatabaseHandler.MEMBER_COLUMN_DOD, member.getDOD().toString());
        values.put(DatabaseHandler.MEMBER_COLUMN_AVATAR, member.getAvatar());
        values.put(DatabaseHandler.MEMBER_COLUMN_IS_ALIVE, member.getIsAlive());
        values.put(DatabaseHandler.MEMBER_COLUMN_MOTHER, member.getMotherID());
        values.put(DatabaseHandler.MEMBER_COLUMN_FATHER, member.getFatherID());
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.insert(DatabaseHandler.TABLE_MEMBER, null, values);
        db.close();
        return 0;
    }
    public int update(Member member) {
        return 0;
    }
    public int delete(Member member) {
        return 0;
    }

    private ArrayList<Member> select(String query) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<Member> members = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.MEMBER_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHandler.MEMBER_COLUMN_NAME));
                members.add(new Member(id,name));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return members;
    }

    public ArrayList<Member> selectAll() {
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_MEMBER;
        return select(query);
    }

    public Member selectByID(int id) {
        String query = "SELECT * FROM " + DatabaseHandler.TABLE_MEMBER + " WHERE "
                + DatabaseHandler.MEMBER_COLUMN_ID + " = " + id;
        ArrayList<Member> members = select(query);
        return members.size() > 0 ? members.get(0) : null;
    }

    public ArrayList<Member> selectByName(String name) {
        return null;
    }
}
