package fu.naan.genealogy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import fu.naan.genealogy.R;
import fu.naan.genealogy.common.Common;
import fu.naan.genealogy.common.DataSaver;

public class SettingScreenActivity extends AppCompatActivity {

    private DataSaver saver;
    private TextView txtGA5M, txtGA5F, txtGA4M, txtGA4F,
        txtGA3M, txtGA3F, txtGA2M, txtGA2F,
        txtGA1EM, txtGA1EF, txtGA1M, txtGA1F, txtGA1YM, txtGA1YF,
        txtG0EM, txtG0EF, txtG0M, txtG0F, txtG0YM, txtG0YF,
        txtGU1EM, txtGU1EF, txtGU1M, txtGU1F,
        txtGU2M, txtGU2F, txtGU3M, txtGU3F,
        txtGU4M, txtGU4F, txtGU5M, txtGU5F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.constructDefaultLayout(this,R.layout.activity_setting_screen,R.id.settingScreen);

        saver = new DataSaver(this);

        txtGA5M = (TextView) findViewById(R.id.txtGA5M);
        txtGA5F = (TextView) findViewById(R.id.txtGA5F);

        txtGA4M = (TextView) findViewById(R.id.txtGA4M);
        txtGA4F = (TextView) findViewById(R.id.txtGA4F);

        txtGA3M = (TextView) findViewById(R.id.txtGA3M);
        txtGA3F = (TextView) findViewById(R.id.txtGA3F);

        txtGA2M = (TextView) findViewById(R.id.txtGA2M);
        txtGA2F = (TextView) findViewById(R.id.txtGA2F);

        txtGA1EM = (TextView) findViewById(R.id.txtGA1EM);
        txtGA1EF = (TextView) findViewById(R.id.txtGA1EF);
        txtGA1M = (TextView) findViewById(R.id.txtGA1M);
        txtGA1F = (TextView) findViewById(R.id.txtGA1F);
        txtGA1YM = (TextView) findViewById(R.id.txtGA1YM);
        txtGA1YF = (TextView) findViewById(R.id.txtGA1YF);

        txtG0EM = (TextView) findViewById(R.id.txtG0EM);
        txtG0EF = (TextView) findViewById(R.id.txtG0EF);
        txtG0M = (TextView) findViewById(R.id.txtG0M);
        txtG0F = (TextView) findViewById(R.id.txtG0F);
        txtG0YM = (TextView) findViewById(R.id.txtG0YM);
        txtG0YF = (TextView) findViewById(R.id.txtG0YF);

        txtGU1EM = (TextView) findViewById(R.id.txtGU1EM);
        txtGU1EF = (TextView) findViewById(R.id.txtGU1EF);
        txtGU1M = (TextView) findViewById(R.id.txtGU1M);
        txtGU1F = (TextView) findViewById(R.id.txtGU1F);

        txtGU2M = (TextView) findViewById(R.id.txtGU2M);
        txtGU2F = (TextView) findViewById(R.id.txtGU2F);

        txtGU3M = (TextView) findViewById(R.id.txtGU3M);
        txtGU3F = (TextView) findViewById(R.id.txtGU3F);

        txtGU4M = (TextView) findViewById(R.id.txtGU4M);
        txtGU4F = (TextView) findViewById(R.id.txtGU4F);

        txtGU5M = (TextView) findViewById(R.id.txtGU5M);
        txtGU5F = (TextView) findViewById(R.id.txtGU5F);

        loadData();
    }

    private void loadData() {
        txtGA5M.setText(saver.getGA5M());
        txtGA5F.setText(saver.getGA5F());

        txtGA4M.setText(saver.getGA4M());
        txtGA4F.setText(saver.getGA4F());

        txtGA3M.setText(saver.getGA3M());
        txtGA3F.setText(saver.getGA3F());

        txtGA2M.setText(saver.getGA2M());
        txtGA2F.setText(saver.getGA2F());

        txtGA1EM.setText(saver.getGA1EM());
        txtGA1EF.setText(saver.getGA1EF());
        txtGA1M.setText(saver.getGA1M());
        txtGA1F.setText(saver.getGA1F());
        txtGA1YM.setText(saver.getGA1YM());
        txtGA1YF.setText(saver.getGA1YF());

        txtG0EM.setText(saver.getG0EM());
        txtG0EF.setText(saver.getG0EF());
        txtG0M.setText(saver.getG0M());
        txtG0F.setText(saver.getG0F());
        txtG0YM.setText(saver.getG0YM());
        txtG0YF.setText(saver.getG0YF());

        txtGU1EM.setText(saver.getGU1EM());
        txtGU1EF.setText(saver.getGU1EF());
        txtGU1M.setText(saver.getGU1M());
        txtGU1F.setText(saver.getGU1F());

        txtGU2M.setText(saver.getGU2M());
        txtGU2F.setText(saver.getGU2F());

        txtGU3M.setText(saver.getGU3M());
        txtGU3F.setText(saver.getGU3F());

        txtGU4M.setText(saver.getGU4M());
        txtGU4F.setText(saver.getGU4F());

        txtGU5M.setText(saver.getGU5M());
        txtGU5F.setText(saver.getGU5F());

    }

    public void saveData(View view) {
        saver.getEditor().putString("ga5m",txtGA5M.getText().toString());
        saver.getEditor().putString("ga5f",txtGA5F.getText().toString());

        saver.getEditor().putString("ga4m",txtGA4M.getText().toString());
        saver.getEditor().putString("ga4f",txtGA4F.getText().toString());

        saver.getEditor().putString("ga3m",txtGA3M.getText().toString());
        saver.getEditor().putString("ga3f",txtGA3F.getText().toString());

        saver.getEditor().putString("ga2m",txtGA2M.getText().toString());
        saver.getEditor().putString("ga2f",txtGA2F.getText().toString());

        saver.getEditor().putString("ga1em",txtGA1EM.getText().toString());
        saver.getEditor().putString("ga1ef",txtGA1EF.getText().toString());
        saver.getEditor().putString("ga1m",txtGA1M.getText().toString());
        saver.getEditor().putString("ga1f",txtGA1F.getText().toString());
        saver.getEditor().putString("ga1ym",txtGA1YM.getText().toString());
        saver.getEditor().putString("ga1yf",txtGA1YF.getText().toString());

        saver.getEditor().putString("g0em",txtG0EM.getText().toString());
        saver.getEditor().putString("g0ef",txtG0EF.getText().toString());
        saver.getEditor().putString("g0m",txtG0M.getText().toString());
        saver.getEditor().putString("g0f",txtG0F.getText().toString());
        saver.getEditor().putString("g0ym",txtG0YM.getText().toString());
        saver.getEditor().putString("g0yf",txtG0YF.getText().toString());

        saver.getEditor().putString("gu1em",txtGU1EM.getText().toString());
        saver.getEditor().putString("gu1ef",txtGU1EF.getText().toString());
        saver.getEditor().putString("gu1m",txtGU1M.getText().toString());
        saver.getEditor().putString("gu1f",txtGU1F.getText().toString());

        saver.getEditor().putString("gu2m",txtGU2M.getText().toString());
        saver.getEditor().putString("gu2f",txtGU2F.getText().toString());

        saver.getEditor().putString("gu3m",txtGU3M.getText().toString());
        saver.getEditor().putString("gu3f",txtGU3F.getText().toString());

        saver.getEditor().putString("gu4m",txtGU4M.getText().toString());
        saver.getEditor().putString("gu4f",txtGU4F.getText().toString());

        saver.getEditor().putString("gu5m",txtGU5M.getText().toString());
        saver.getEditor().putString("gu5f",txtGU5F.getText().toString());

        saver.getEditor().apply();
        Toast.makeText(this,"Save success",Toast.LENGTH_LONG).show();
    }
}
