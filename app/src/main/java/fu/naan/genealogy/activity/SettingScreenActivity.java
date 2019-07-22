package fu.naan.genealogy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        txtGA5M.setText(saver.getSharedPreferences().getString("ga5m",getResources().getString(R.string.ga5m)));
        txtGA5F.setText(saver.getSharedPreferences().getString("ga5f",getResources().getString(R.string.ga5f)));
        txtGA4M.setText(saver.getSharedPreferences().getString("ga4m",getResources().getString(R.string.ga4m)));
        txtGA4F.setText(saver.getSharedPreferences().getString("ga4f",getResources().getString(R.string.ga4f)));
        txtGA3M.setText(saver.getSharedPreferences().getString("ga3m",getResources().getString(R.string.ga3m)));
        txtGA3F.setText(saver.getSharedPreferences().getString("ga3f",getResources().getString(R.string.ga3f)));
        txtGA2M.setText(saver.getSharedPreferences().getString("ga2m",getResources().getString(R.string.ga2m)));
        txtGA2F.setText(saver.getSharedPreferences().getString("ga2f",getResources().getString(R.string.ga2f)));
        txtGA1EM.setText(saver.getSharedPreferences().getString("ga1em",getResources().getString(R.string.ga1em)));
        txtGA1EF.setText(saver.getSharedPreferences().getString("ga1ef",getResources().getString(R.string.ga1ef)));
        txtGA1M.setText(saver.getSharedPreferences().getString("ga1m",getResources().getString(R.string.ga1m)));
        txtGA1F.setText(saver.getSharedPreferences().getString("ga1f",getResources().getString(R.string.ga1f)));
        txtGA1YM.setText(saver.getSharedPreferences().getString("ga1ym",getResources().getString(R.string.ga1ym)));
        txtGA1YF.setText(saver.getSharedPreferences().getString("ga1yf",getResources().getString(R.string.ga1yf)));
        txtG0EM.setText(saver.getSharedPreferences().getString("g0em",getResources().getString(R.string.g0em)));
        txtG0EF.setText(saver.getSharedPreferences().getString("g0ef",getResources().getString(R.string.g0ef)));
        txtG0M.setText(saver.getSharedPreferences().getString("g0m",getResources().getString(R.string.g0m)));
        txtG0F.setText(saver.getSharedPreferences().getString("g0f",getResources().getString(R.string.g0f)));
        txtG0YM.setText(saver.getSharedPreferences().getString("g0ym",getResources().getString(R.string.g0ym)));
        txtG0YF.setText(saver.getSharedPreferences().getString("g0yf",getResources().getString(R.string.g0yf)));
        txtGU1EM.setText(saver.getSharedPreferences().getString("gu1em",getResources().getString(R.string.gu1em)));
        txtGU1EF.setText(saver.getSharedPreferences().getString("gu1ef",getResources().getString(R.string.gu1ef)));
        txtGU1M.setText(saver.getSharedPreferences().getString("gu1m",getResources().getString(R.string.gu1m)));
        txtGU1F.setText(saver.getSharedPreferences().getString("gu1f",getResources().getString(R.string.gu1f)));
        txtGU2M.setText(saver.getSharedPreferences().getString("gu2m",getResources().getString(R.string.gu2m)));
        txtGU2F.setText(saver.getSharedPreferences().getString("gu2f",getResources().getString(R.string.gu2f)));
        txtGU3M.setText(saver.getSharedPreferences().getString("gu3m",getResources().getString(R.string.gu3m)));
        txtGU3F.setText(saver.getSharedPreferences().getString("gu3f",getResources().getString(R.string.gu3f)));
        txtGU4M.setText(saver.getSharedPreferences().getString("gu4m",getResources().getString(R.string.gu4m)));
        txtGU4F.setText(saver.getSharedPreferences().getString("gu4f",getResources().getString(R.string.gu4f)));
        txtGU5M.setText(saver.getSharedPreferences().getString("gu5m",getResources().getString(R.string.gu5m)));
        txtGU5F.setText(saver.getSharedPreferences().getString("gu5f",getResources().getString(R.string.gu5f)));
    }

    public void saveData(View view) {

    }
}
