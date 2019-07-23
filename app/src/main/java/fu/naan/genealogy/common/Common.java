package fu.naan.genealogy.common;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fu.naan.genealogy.R;
import fu.naan.genealogy.activity.AddFamilyMemberScreenActivity;
import fu.naan.genealogy.activity.FamilyTreeScreenActivity;
import fu.naan.genealogy.activity.SearchScreenActivity;
import fu.naan.genealogy.activity.SettingScreenActivity;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Common {

    public static int GALLERY = 1, CAMERA = 2;
    public static int FOR_RESULT = 1001;

    public static void constructDefaultLayout(AppCompatActivity activity, int layoutID, int viewID) {
        includeLayout(activity,layoutID,viewID);
        initFab(activity);
    }

    private static void includeLayout(AppCompatActivity activity, int layoutID, int viewID) {
        activity.setContentView(R.layout.activity_common);
        CoordinatorLayout commonView = activity.findViewById(R.id.commonView);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = inflater.inflate(layoutID,
                (ViewGroup) activity.findViewById(viewID));
        childLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        commonView.addView(childLayout);
    }

    private static void initFab(AppCompatActivity activity) {
        // Set up the white button on the lower right corner
        // more or less with default parameter
        final ImageView fabIconNew = new ImageView(activity);
        fabIconNew.setImageDrawable(activity.getResources().getDrawable(R.drawable.menu));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(activity)
                .setContentView(fabIconNew)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(activity);
        ImageView rlIcon1 = new ImageView(activity);
        ImageView rlIcon2 = new ImageView(activity);
        ImageView rlIcon3 = new ImageView(activity);
        ImageView rlIcon4 = new ImageView(activity);

        rlIcon1.setImageDrawable(activity.getResources().getDrawable(R.drawable.settings));
        rlIcon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                activity.startActivity(new Intent(activity, SettingScreenActivity.class));
                return false;
            }
        });
        rlIcon2.setImageDrawable(activity.getResources().getDrawable(R.drawable.plus));
        rlIcon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                activity.startActivity(new Intent(activity, AddFamilyMemberScreenActivity.class));
                return false;
            }
        });
        rlIcon3.setImageDrawable(activity.getResources().getDrawable(R.drawable.search));
        rlIcon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                activity.startActivity(new Intent(activity, SearchScreenActivity.class));
                return false;
            }
        });
        rlIcon4.setImageDrawable(activity.getResources().getDrawable(R.drawable.tree));
        rlIcon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                activity.startActivity(new Intent(activity, FamilyTreeScreenActivity.class));
                return false;
            }
        });

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(activity)
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                .attachTo(rightLowerButton)
                .build();

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 90);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(90);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }
        });
    }

    public static String dateFormat(Date date){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat =  formatter.format(date);
        return dateFormat;
    }
    public static Date stringToDate(String dob) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dob);
        } catch (Exception e) {
            return Calendar.getInstance().getTime();
        }
    }

    public static Bitmap stringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public static byte[] convertBitmapToByte(Bitmap yourBitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        yourBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        return bArray;
    }
}
