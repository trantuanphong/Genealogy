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
                + MEMBER_COLUMN_ID + " INTEGER PRIMARY KEY, "
                + MEMBER_COLUMN_NAME + " TEXT, "
                + MEMBER_COLUMN_DOB + " TEXT, "
                + MEMBER_COLUMN_DOD + " TEXT, "
                + MEMBER_COLUMN_AVATAR + " TEXT, "
                + MEMBER_COLUMN_IS_ALIVE + " INTEGER, "
                + MEMBER_COLUMN_MOTHER + " INTEGER, "
                + MEMBER_COLUMN_FATHER + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_MEMBER);

        String CREATE_TABLE_NODE = "CREATE TABLE " +
                TABLE_NODE + "("
                + NODE_COLUMN_ID + " INTEGER PRIMARY KEY, "
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
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(1,'Person 1')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(2,'Person 2')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(3,'Person 3')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(4,'Person 4')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(5,'Person 5')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(6,'Person 6')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(7,'Person 7')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(8,'Person 8')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(9,'Person 9')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(10,'Person 10')");
        db.execSQL("INSERT INTO Member(MemberID, MemberName) VALUES(11,'Person 11')");

        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(1,-1)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(2,1)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(3,1)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(4,1)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(5,2)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(6,2)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(7,3)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(8,3)");
        db.execSQL("INSERT INTO FamilyNode(NodeID,ParentID) VALUES(9,7)");

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
