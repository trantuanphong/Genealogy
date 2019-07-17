package fu.naan.genealogy.dao;

import android.content.Context;

import java.util.ArrayList;

import fu.naan.genealogy.entity.Node;

public class NodeDAO extends DAO{

    public NodeDAO(Context context) {
        super(context);
    }

    public int insert(Node node) {
        return 0;
    }
    public int update(Node node) {
        return 0;
    }
    public int delete(Node node) {
        return 0;
    }
    public ArrayList<Node> selectByID(int id) {
        return null;
    }
}
