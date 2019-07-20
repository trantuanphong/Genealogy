package fu.naan.genealogy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import fu.naan.genealogy.R;
import fu.naan.genealogy.adapter.ListMemberRecycleAdapter;
import fu.naan.genealogy.dao.MemberDAO;

public class SearchScreenActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListMemberRecycleAdapter listMemberRecycleAdapter;
    private MemberDAO memberDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        memberDAO = new MemberDAO(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listMemberRecycleAdapter = new ListMemberRecycleAdapter(this,memberDAO.selectByName(""));
        recyclerView.setAdapter(listMemberRecycleAdapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listMemberRecycleAdapter.setMembers(memberDAO.selectByName(newText));
        listMemberRecycleAdapter.notifyDataSetChanged();
        return false;
    }
}
