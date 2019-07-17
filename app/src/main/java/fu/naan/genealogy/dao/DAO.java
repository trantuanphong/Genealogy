package fu.naan.genealogy.dao;

import android.content.Context;

import fu.naan.genealogy.handler.DatabaseHandler;

class DAO {
    DatabaseHandler databaseHandler;

    DAO(Context context) {
        databaseHandler = new DatabaseHandler(context,null, null, 1);
    }
}
