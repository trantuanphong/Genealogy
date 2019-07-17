package fu.naan.genealogy.entity;

public class MemberInNode {
    private int NodeID;
    private int MemberID;

    public MemberInNode(int nodeID, int memberID) {
        NodeID = nodeID;
        MemberID = memberID;
    }

    public int getNodeID() {
        return NodeID;
    }

    public void setNodeID(int nodeID) {
        NodeID = nodeID;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int memberID) {
        MemberID = memberID;
    }
}
