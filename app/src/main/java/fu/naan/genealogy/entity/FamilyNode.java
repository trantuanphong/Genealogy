package fu.naan.genealogy.entity;

public class FamilyNode {
    private int NodeID;
    private int ParentID;

    public FamilyNode() {
    }

    public FamilyNode(int nodeID) {
        NodeID = nodeID;
    }

    public FamilyNode(int nodeID, int parentID) {
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

    @Override
    public String toString() {
        return getNodeID() + "";
    }
}
