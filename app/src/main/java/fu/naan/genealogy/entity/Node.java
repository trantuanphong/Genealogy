package fu.naan.genealogy.entity;

public class Node {
    private int NodeID;
    private int ParentID;

    public Node() {
    }

    public Node(int nodeID) {
        NodeID = nodeID;
    }

    public Node(int nodeID, int parentID) {
        NodeID = nodeID;
        ParentID = parentID;
    }

    public int getNodeID() {
        return NodeID;
    }

    public void setNodeID(int nodeID) {
        NodeID = nodeID;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }
}
