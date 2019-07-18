package fu.naan.genealogy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Edge;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.ViewHolder;
import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerConfiguration;
import fu.naan.genealogy.R;
import fu.naan.genealogy.dao.FamilyNodeDAO;
import fu.naan.genealogy.entity.FamilyNode;

public class FamilyTreeScreenActivity extends AppCompatActivity {

    private FamilyNodeDAO familyNodeDAO;
    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_tree_screen);
        graphView = findViewById(R.id.graph);

        Graph graph = new Graph();
        familyNodeDAO = new FamilyNodeDAO(this);
        ArrayList<Node> nodes = loadFamilyNode();

        ArrayList<Edge> edges = loadNodeRelationship();
        for (Edge edge : edges) {
            int source = Integer.parseInt(edge.getSource().getData().toString());
            int destination = Integer.parseInt(edge.getDestination().getData().toString());
            graph.addEdge(nodes.get(source-1),nodes.get(destination-1));
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
                CusFamilyNode familyNode = (CusFamilyNode)viewHolder;
                familyNode.textView.setText(data.toString());
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
    }

    private Context getContext() {
        return this;
    }

    private ArrayList<Edge> loadNodeRelationship() {
        FamilyNode root = familyNodeDAO.selectByParentID(-1).get(0);
        return recursive(new Node(root),root, new ArrayList<Edge>());
    }

    private ArrayList<Node> loadFamilyNode() {
        ArrayList<FamilyNode> familyNodes = familyNodeDAO.selectAll();
        ArrayList<Node> nodes = new ArrayList<>();
        for (FamilyNode familyNode : familyNodes) {
            nodes.add(new Node(familyNode));
        }
        return nodes;
    }

    private ArrayList<Edge> recursive(Node parent, FamilyNode root, ArrayList<Edge> edges) {
        ArrayList<FamilyNode> children = familyNodeDAO.selectByParentID(root.getNodeID());
        for (FamilyNode child : children) {
            final Node childNode = new Node(child);
            Edge edge = new Edge(parent, new Node(child));
            edges.add(edge);
            Log.i("hihi",edge.getSource().getData().toString() + " " + edge.getDestination().getData().toString());
            recursive(childNode,child, edges);
        }
        return edges;
    }

    private class CusFamilyNode extends ViewHolder {
        TextView textView;

        CusFamilyNode(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
