package fu.naan.genealogy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fu.naan.genealogy.R;
import fu.naan.genealogy.common.Common;
import fu.naan.genealogy.dao.MemberDAO;
import fu.naan.genealogy.entity.Member;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class AddFamilyMemberScreenActivity extends AppCompatActivity {
    private Button btnUpload;
    private TextView name;
    private Bitmap avatar;
    private EditText dob;
    private EditText dod;
    private int gender;
    private Spinner relationshipWith;
    private Member relationshipMember;
    private int relationshipType;
    private DatePickerDialog picker;
    private Button btnAdd;
    private MemberDAO dao = new MemberDAO(this);

    private static final String IMAGE_DIRECTORY = "/DCIM";
    private ImageView imageview;
    private Button btnSearch;
    private TextView txtMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.constructDefaultLayout(this,R.layout.activity_add_family_member_screen, R.id.addFamilyMemberScreen);
        btnUpload = findViewById(R.id.btn_upload);
        btnAdd = findViewById(R.id.btnAdd);
        btnSearch = findViewById(R.id.btnSearch);
        name = findViewById(R.id.txtName);
        dob = findViewById(R.id.txtDOB);
        dod = findViewById(R.id.txtDOD);
        imageview = findViewById(R.id.imageView);
        txtMember = findViewById(R.id.txtMember);

        datePicker(dob, dod);

        RadioButton male = findViewById(R.id.rdMale);
        RadioButton female = findViewById(R.id.rdFemale);

        if (male.isChecked()) {
            gender = 1;
        } else if (female.isChecked()) {
            gender = 0;
        }

        RadioButton parents = findViewById(R.id.rdParents);
        RadioButton partner = findViewById(R.id.rdPartner);

        if (parents.isChecked()) {
            relationshipType = 0;
        } else if (partner.isChecked()) {
            relationshipType = 1;
        }

        requestMultiplePermissions();
        btnUpload.setOnClickListener(v -> showPictureDialog());

        btnAdd.setOnClickListener(v -> {
            if (!validate()) {
                return;
            }

            dao.insert(new Member(name.getText().toString(),
                    Common.stringToDate(dob.getText().toString()), gender, avatar));
            Toast.makeText(AddFamilyMemberScreenActivity.this, "Create member successfully!", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean validate() {
        RadioButton male = findViewById(R.id.rdMale);
        RadioButton female = findViewById(R.id.rdFemale);
        if (male.isChecked()) {
            gender = 1;
        } else if (female.isChecked()) {
            gender = 0;
        }
        RadioButton parents = findViewById(R.id.rdParents);
        RadioButton partner = findViewById(R.id.rdPartner);
        if (parents.isChecked()) {
            relationshipType = 0;
        } else if (partner.isChecked()) {
            relationshipType = 1;
        }
        if (name.getText() == "") {
            Toast.makeText(this, "Name is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (dob.getText().toString() == "") {
            Toast.makeText(this, "Date of Birth is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (gender != 0 && gender != 1) {
            Toast.makeText(this, "Gender is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (relationshipType != 0 && relationshipType != 1) {
            Toast.makeText(this, "Relationship type is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (txtMember.getText() == "") {
            Toast.makeText(this, "Relationship with member is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void datePicker(EditText dob, EditText dod) {
        dob.setInputType(InputType.TYPE_NULL);
        dob.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(AddFamilyMemberScreenActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            picker.show();
        });

        dod.setInputType(InputType.TYPE_NULL);
        dod.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(AddFamilyMemberScreenActivity.this,
                    (view, year12, monthOfYear, dayOfMonth) -> dod.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year12), year, month, day);
            picker.show();
        });
    }

    private void showPictureDialog(){
        android.app.AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                (dialog, which) -> {
                    switch (which) {
                        case 0:
                            choosePhotoFromGallary();
                            break;
                        case 1:
                            takePhotoFromCamera();
                            break;
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, Common.GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Common.CAMERA);
    }

    public void findPerson(View view) {
        Intent i = new Intent(this, SearchScreenActivity.class);
        i.putExtra("forResult",true);
        startActivityForResult(i, Common.FOR_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == Common.GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);
                    avatar = bitmap;

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == Common.CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(thumbnail);
            avatar = thumbnail;
            saveImage(thumbnail);
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == Common.FOR_RESULT) {
            if (resultCode == RESULT_OK) {
                txtMember.setText(data.getIntExtra("memberID",-1) + "");
            }
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(error -> Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show())
                .onSameThread()
                .check();
    }
}
