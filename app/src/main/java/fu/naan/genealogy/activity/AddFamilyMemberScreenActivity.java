package fu.naan.genealogy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fu.naan.genealogy.R;
import fu.naan.genealogy.common.Common;

public class AddFamilyMemberScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.constructDefaultLayout(this,
                R.layout.activity_add_family_member_screen,R.id.addFamilyMemberScreen);
    }
}
