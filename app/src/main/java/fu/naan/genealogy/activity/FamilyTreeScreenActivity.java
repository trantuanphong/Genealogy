package fu.naan.genealogy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.ViewHolder;
import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerConfiguration;
import fu.naan.genealogy.R;
import fu.naan.genealogy.entity.Member;

public class FamilyTreeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_tree_screen);
        GraphView graphView = findViewById(R.id.graph);

        // example tree00
        final Graph graph = new Graph();

        final Node node1 = new Node(new Member(1,"A"));
        final Node node2 = new Node(new Member(2,"B"));
        final Node node3 = new Node(new Member(3,"C"));
        final Node node4 = new Node(new Member(4,"D"));
        final Node node5 = new Node(new Member(1,"E"));
        final Node node6 = new Node(new Member(1,"F"));
        final Node node8 = new Node(new Member(1,"G"));
        final Node node7 = new Node(new Member(1,"H"));
        final Node node9 = new Node(new Member(1,"I"));
        final Node node10 = new Node(new Member(1,"J"));
        final Node node11 = new Node(new Member(1,"K"));
        final Node node12 = new Node(new Member(1,"L"));

        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node4);
        graph.addEdge(node2, node5);
        graph.addEdge(node2, node6);
        graph.addEdge(node6, node7);
        graph.addEdge(node6, node8);
        graph.addEdge(node4, node9);
        graph.addEdge(node4, node10);
        graph.addEdge(node4, node11);
        graph.addEdge(node11, node12);

        // you can set the graph via the constructor or use the adapter.setGraph(Graph) method
        final BaseGraphAdapter<ViewHolder> adapter = new BaseGraphAdapter<ViewHolder>(graph) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_member, parent, false);
                return new FamilyNode(view);
            }
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                FamilyNode familyNode = (FamilyNode)viewHolder;
                familyNode.textView.setText(data.toString());
                familyNode.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),data.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
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

    private class FamilyNode extends ViewHolder {
        TextView textView;

        FamilyNode(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
