package fu.naan.genealogy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import fu.naan.genealogy.R;
import fu.naan.genealogy.adapter.ListMemberRecycleAdapter;
import fu.naan.genealogy.common.Common;
import fu.naan.genealogy.dao.MemberDAO;

public class SearchScreenActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListMemberRecycleAdapter listMemberRecycleAdapter;
    private MemberDAO memberDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.constructDefaultLayout(this,R.layout.activity_search_screen,R.id.searchScreen);
        memberDAO = new MemberDAO(this);
        boolean forResult = getIntent().getBooleanExtra("forResult",false);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listMemberRecycleAdapter = new ListMemberRecycleAdapter(this,memberDAO.selectByName(""),forResult);
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
