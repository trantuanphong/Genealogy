package fu.naan.genealogy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Edge;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.ViewHolder;
import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerConfiguration;
import fu.naan.genealogy.R;
import fu.naan.genealogy.algorithm.IdentifyRelationship;
import fu.naan.genealogy.common.Common;
import fu.naan.genealogy.common.DataSaver;
import fu.naan.genealogy.dao.FamilyNodeDAO;
import fu.naan.genealogy.dao.MemberDAO;
import fu.naan.genealogy.dao.MemberInNodeDAO;
import fu.naan.genealogy.entity.FamilyNode;
import fu.naan.genealogy.entity.Member;
import fu.naan.genealogy.entity.MemberInNode;

public class FamilyTreeScreenActivity extends AppCompatActivity {

    private FamilyNodeDAO familyNodeDAO;
    StoredData choice1, choice2;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.constructDefaultLayout(this,R.layout.activity_family_tree_screen,R.id.familyTreeScreen);

        choice1 = new StoredData();
        choice2 = new StoredData();

        GraphView graphView = findViewById(R.id.graph);
//        text1 = findViewById(R.id.member1);
//        text2 = findViewById(R.id.member2);

        Graph graph = new Graph();
        familyNodeDAO = new FamilyNodeDAO(this);
        HashMap<Integer,Node> nodes = loadFamilyNode();

        ArrayList<Edge> edges = loadNodeRelationship();
        for (Edge edge : edges) {
            int source = Integer.parseInt(edge.getSource().getData().toString());
            int destination = Integer.parseInt(edge.getDestination().getData().toString());
            graph.addEdge(nodes.get(source),nodes.get(destination));
        }

        // you can set the graph via the constructor or use the adapter.setGraph(Graph) method
        final BaseGraphAdapter<ViewHolder> adapter = new BaseGraphAdapter<ViewHolder>(graph) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_member, parent, false);
                return new CusFamilyNode(view);
            }
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                CusFamilyNode familyNodeView = (CusFamilyNode)viewHolder;
                int nodeID = Integer.parseInt(data.toString());
                ArrayList<MemberInNode> memberInNodes = new MemberInNodeDAO(getContext()).selectByNodeID(nodeID);
                for (MemberInNode memberInNode : memberInNodes) {
                    LinearLayout linearLayout = new LinearLayout(getContext());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                    TextView textView = new TextView(getContext());
                    MemberDAO memberDAO = new MemberDAO(getContext());
                    Member member = memberDAO.selectByID(memberInNode.getMemberID());

                    textView.setText(member.getMemberName());
                    textView.setGravity(Gravity.CENTER);
                    int color;
                    if (member.getGender() == 1) {
                        color = Color.MAGENTA;
                    } else {
                        color = Color.CYAN;
                    }
                    textView.setTextColor(color);

                    ImageView imageView = new ImageView(getContext());
                    if (member.getAvatar() != null) {
                        imageView.setImageBitmap(member.getAvatar());
                    } else {
                        imageView.setImageResource(R.mipmap.ic_launcher_round);
                    }
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (member.getMemberID() == choice1.getId()) {
                                choice1.unChoose();
                                count--;
                            } else if (member.getMemberID() == choice2.getId()) {
                                choice2.unChoose();
                                count--;
                            } else {
                                if (count == 2) {
                                    count = 0;
                                    choice1.unChoose();
                                    choice2.unChoose();
                                }
                                if (count == 0) {
                                    choice1.choose(member.getMemberID(), color, textView);
                                } else {
                                    choice2.choose(member.getMemberID(), color, textView);
                                }
                                count++;
                            }
                        }
                    });
                    imageView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Intent intent = new Intent(getContext(),MemberDetailScreenActivity.class);
                            intent.putExtra("memberID", member.getMemberID());
                            getContext().startActivity(intent);
                            return false;
                        }
                    });

                    linearLayout.addView(imageView);
                    linearLayout.addView(textView);
                    familyNodeView.parentLinearLayout.addView(linearLayout);
                }
            }
        };
        graphView.setAdapter(adapter);

        // set the algorithm here
        final BuchheimWalkerConfiguration configuration = new BuchheimWalkerConfiguration.Builder()
                .setSiblingSeparation(100)
                .setLevelSeparation(300)
                .setSubtreeSeparation(300)
                .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
                .build();
        adapter.setAlgorithm(new BuchheimWalkerAlgorithm(configuration));

        new DataSaver(this).initDefaultData(this);
    }

    private Context getContext() {
        return this;
    }

    private ArrayList<Edge> loadNodeRelationship() {
        FamilyNode root = familyNodeDAO.selectByParentID(-1).get(0);
        return recursive(new Node(root),root, new ArrayList<Edge>());
    }

    private HashMap<Integer,Node> loadFamilyNode() {
        ArrayList<FamilyNode> familyNodes = familyNodeDAO.selectAll();
        HashMap<Integer,Node> nodes = new HashMap<>();
        for (FamilyNode familyNode : familyNodes) {
            nodes.put(familyNode.getNodeID(),new Node(familyNode));
        }
        return nodes;
    }

    private ArrayList<Edge> recursive(Node parent, FamilyNode root, ArrayList<Edge> edges) {
        ArrayList<FamilyNode> children = familyNodeDAO.selectByParentID(root.getNodeID());
        for (FamilyNode child : children) {
            final Node childNode = new Node(child);
            Edge edge = new Edge(parent, new Node(child));
            edges.add(edge);
            recursive(childNode,child, edges);
        }
        return edges;
    }

    private class CusFamilyNode extends ViewHolder {
        LinearLayout parentLinearLayout;

        CusFamilyNode(View itemView) {
            super(itemView);
            parentLinearLayout = itemView.findViewById(R.id.parentLinearLayout);
        }
    }

    private class StoredData {
        private int id, color;
        private TextView textView;

        public StoredData() {id = -1;}

        void unChoose() {
            textView.setTextColor(color);
            id = -1;
        }

         void choose(int chooseId, int currentColor, TextView textView) {
            id = chooseId;
            color = currentColor;
            this.textView = textView;
            textView.setTextColor(Color.YELLOW);
        }

        int getId() {
            return id;
        }
    }

    public void identify(View view) {
        if (count < 2) {
            Toast.makeText(this,"Please select 2 people!", Toast.LENGTH_LONG).show();
        } else {
            String text = new IdentifyRelationship(getContext()).identify(choice1.getId(),choice2.getId());
            Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
        }
    }

}
