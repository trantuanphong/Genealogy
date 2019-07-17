package fu.naan.genealogy.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "genealogy.db";

    public static final String TABLE_MEMBER = "MEMBER";
    public static final String MEMBER_COLUMN_ID = "_id";
    public static final String MEMBER_COLUMN_NAME = "Name";
    public static final String MEMBER_COLUMN_DOB = "DOB";
    public static final String MEMBER_COLUMN_DOD = "DOD";
    public static final String MEMBER_COLUMN_IS_ALIVE = "IsAlive";
    public static final String MEMBER_COLUMN_AVATAR = "Avatar";
    public static final String MEMBER_COLUMN_MOTHER = "MotherID";
    public static final String MEMBER_COLUMN_FATHER = "FatherID";

    public static final String TABLE_NODE = "Node";
    public static final String NODE_COLUMN_ID = "NodeID";
    public static final String NODE_COLUMN_PARENT_NODE = "ParentNodeID";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
