package fu.naan.genealogy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fu.naan.genealogy.R;
import fu.naan.genealogy.activity.MemberDetailScreenActivity;
import fu.naan.genealogy.entity.Member;

public class ListMemberRecycleAdapter extends
        RecyclerView.Adapter<ListMemberRecycleAdapter.ViewHolder> {

    private ArrayList<Member> members;
    private Context context;

    public ListMemberRecycleAdapter(Context context, ArrayList<Member> members) {
        this.context = context;
        this.members = members;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_member, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemTitle.setText(members.get(position).getMemberName());
        holder.itemDetail.setText(members.get(position).getDOB().toString());
        holder.itemImage.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public Context getContext() {
        return context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(getContext(), MemberDetailScreenActivity.class);
                        intent.putExtra("memberID", members.get(position).getMemberID());
                        getContext().startActivity(intent);
                    } else {
                        Toast.makeText(getContext(),"Please select!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
