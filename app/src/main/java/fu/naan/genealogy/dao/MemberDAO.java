package fu.naan.genealogy.dao;

import android.content.ContentValues;
import android.content.Context;
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
    public ArrayList<Member> selectByID(int id) {
        return null;
    }
    public ArrayList<Member> selectByName(String name) {
        return null;
    }
}
