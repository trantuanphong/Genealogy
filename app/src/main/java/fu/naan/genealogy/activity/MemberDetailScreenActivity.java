package fu.naan.genealogy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import fu.naan.genealogy.R;
import fu.naan.genealogy.common.Common;
import fu.naan.genealogy.dao.FamilyNodeDAO;
import fu.naan.genealogy.dao.MemberDAO;
import fu.naan.genealogy.dao.MemberInNodeDAO;
import fu.naan.genealogy.entity.Member;
import fu.naan.genealogy.entity.MemberInNode;

public class MemberDetailScreenActivity extends AppCompatActivity {

    TextView idView;
    EditText  memberName, memberDob;
    RadioGroup memberGroupGender;
    RadioButton rdMale, rdFemale;
    DatePickerDialog picker;
    Member currentMember;
    MemberDAO memberDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.constructDefaultLayout(this,
                R.layout.activity_member_detail_screen, R.id.memberDetailScreen);

        memberDAO = new MemberDAO(this);

        Intent intent = getIntent();
        int memberID = intent.getIntExtra("memberID",0);

        idView = (TextView) findViewById(R.id.memberID);
        memberName =(EditText) findViewById(R.id.memberName);
        memberGroupGender = (RadioGroup) findViewById(R.id.radioGender);
        rdMale = (RadioButton) findViewById(R.id.radioMale);
        rdFemale = (RadioButton) findViewById(R.id.radioFemale);

        memberDob = (EditText) findViewById(R.id.DOB);
        memberDob.setInputType(InputType.TYPE_NULL);
        memberDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MemberDetailScreenActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                memberDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        detailMember(memberID);
        idView.setText(currentMember.getMemberID() + "");
        memberName.setText(currentMember.getMemberName());
        memberDob.setText(Common.dateFormat(currentMember.getDOB()));
        if (currentMember.getGender() == 1){
            rdFemale.setChecked(true);
        }else {
            rdMale.setChecked(true);
        }

    }
    public void detailMember(int id){
        currentMember = memberDAO.selectByID(id);
    }

    public void updateMember(View view){
        currentMember.setMemberName(memberName.getText().toString());
        currentMember.setDOB(Common.stringToDate(memberDob.getText().toString()));
        if(rdMale.isChecked()){
            currentMember.setGender(0);
        }else {
            currentMember.setGender(1);
        }
        memberDAO.update(currentMember);
        Toast.makeText(this,"Update success!!!",Toast.LENGTH_LONG).show();
    }

    public void deleteMember(View view){
        FamilyNodeDAO familyNodeDAO = new FamilyNodeDAO(this);
        MemberInNodeDAO memberInNodeDAO = new MemberInNodeDAO(this);

        boolean isDeleted = false;
        ArrayList<MemberInNode> members = memberInNodeDAO.selectByMemberID(currentMember.getMemberID());
        int nodeId = members.get(0).getNodeID();
        members = memberInNodeDAO.selectByNodeID(nodeId);
        if(members.size()>=2){
            memberInNodeDAO.deleteByMemberID(currentMember.getMemberID());
            memberDAO.deleteByMemberID(currentMember.getMemberID());
            isDeleted = true;
        } else {
            if(familyNodeDAO.hadChildren(nodeId)){
                Toast.makeText(this, "Can not delete node had children!!!", Toast.LENGTH_SHORT).show();
            } else {
                memberInNodeDAO.deleteByMemberID(currentMember.getMemberID());
                memberDAO.deleteByMemberID(currentMember.getMemberID());
                familyNodeDAO.deleteByNodeID(nodeId);
                isDeleted = true;
            }
        }
        if (isDeleted) {
            Toast.makeText(this,"Delete success!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, FamilyTreeScreenActivity.class));
        }
    }
}
