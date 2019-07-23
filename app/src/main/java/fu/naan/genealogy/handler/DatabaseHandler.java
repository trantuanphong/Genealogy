package fu.naan.genealogy.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "genealogy.db";

    public static final String TABLE_MEMBER = "Member";
    public static final String MEMBER_COLUMN_ID = "MemberID";
    public static final String MEMBER_COLUMN_NAME = "MemberName";
    public static final String MEMBER_COLUMN_DOB = "DOB";
    public static final String MEMBER_COLUMN_DOD = "DOD";
    public static final String MEMBER_COLUMN_GENDER = "Gender";
    public static final String MEMBER_COLUMN_IS_ALIVE = "IsAlive";
    public static final String MEMBER_COLUMN_AVATAR = "Avatar";
    public static final String MEMBER_COLUMN_MOTHER = "MotherID";
    public static final String MEMBER_COLUMN_FATHER = "FatherID";

    public static final String TABLE_NODE = "FamilyNode";
    public static final String NODE_COLUMN_ID = "NodeID";
    public static final String NODE_COLUMN_PARENT_NODE = "ParentID";

    public static final String TABLE_MEMBER_IN_NODE = "MemberInNode";
    public static final String MEMBER_IN_NODE_COLUMN_NODE_ID = "NodeID";
    public static final String MEMBER_IN_NODE_COLUMN_MEMBER_ID = "MemberID";

    public DatabaseHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MEMBER = "CREATE TABLE " +
                TABLE_MEMBER + "("
                + MEMBER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MEMBER_COLUMN_NAME + " TEXT, "
                + MEMBER_COLUMN_DOB + " TEXT, "
                + MEMBER_COLUMN_DOD + " TEXT, "
                + MEMBER_COLUMN_GENDER + " INTEGER, "
                + MEMBER_COLUMN_AVATAR + " TEXT, "
                + MEMBER_COLUMN_IS_ALIVE + " INTEGER, "
                + MEMBER_COLUMN_MOTHER + " INTEGER, "
                + MEMBER_COLUMN_FATHER + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_MEMBER);

        String CREATE_TABLE_NODE = "CREATE TABLE " +
                TABLE_NODE + "("
                + NODE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NODE_COLUMN_PARENT_NODE + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_NODE);

        String CREATE_TABLE_MEMBER_IN_NODE = "CREATE TABLE " +
                TABLE_MEMBER_IN_NODE + "("
                + MEMBER_IN_NODE_COLUMN_NODE_ID + " INTEGER, "
                + MEMBER_IN_NODE_COLUMN_MEMBER_ID + " INTEGER, "
                + " PRIMARY KEY ("+ MEMBER_IN_NODE_COLUMN_NODE_ID +", "
                                    + MEMBER_IN_NODE_COLUMN_MEMBER_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_MEMBER_IN_NODE);
        initDefaultData(db);
    }

    private void initDefaultData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 1','22/12/1900', 0)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 2','01/10/1910', 1)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 3','21/02/1940', 0)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 4','21/03/1944', 1)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 5','11/11/1950', 0)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 6','11/11/1950', 1)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 7','11/11/1980', 0)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 8','02/02/1980', 0)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 9','02/02/1981', 1)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 10','02/02/1985', 0)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 11','02/02/1988', 1)");
        db.execSQL("INSERT INTO Member(MemberName, DOB, Gender) VALUES('Person 12','10/10/2000', 1)");

        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(-1)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(1)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(1)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(1)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(2)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(2)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(3)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(3)");
        db.execSQL("INSERT INTO FamilyNode(ParentID) VALUES(7)");

        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(1,1)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(1,2)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(2,3)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(2,4)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(3,5)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(3,6)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(4,7)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(5,8)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(6,9)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(7,10)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(8,11)");
        db.execSQL("INSERT INTO MemberInNode(NodeID, MemberID) VALUES(9,12)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
