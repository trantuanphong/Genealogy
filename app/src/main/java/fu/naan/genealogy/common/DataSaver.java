package fu.naan.genealogy.common;

import android.content.Context;
import android.content.SharedPreferences;

import fu.naan.genealogy.R;

public class DataSaver {

    private final String SHARED_PREFERENCES_NAME = "shared_preferences_name";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public DataSaver(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void setFirstTimeInstall() {
        editor.putBoolean("firstTime",false);
        editor.apply();
    }

    private boolean isFirstTimeInstall() {
        return sharedPreferences.getBoolean("firstTime",true);
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void initDefaultData(Context context) {
        if (isFirstTimeInstall()) {
            setFirstTimeInstall();

            editor.putString("ga5m",context.getResources().getString(R.string.ga5m));
            editor.putString("ga5f",context.getResources().getString(R.string.ga5f));

            editor.putString("ga4m",context.getResources().getString(R.string.ga4m));
            editor.putString("ga4f",context.getResources().getString(R.string.ga4f));

            editor.putString("ga3m",context.getResources().getString(R.string.ga3m));
            editor.putString("ga3f",context.getResources().getString(R.string.ga3f));

            editor.putString("ga2m",context.getResources().getString(R.string.ga2m));
            editor.putString("ga2f",context.getResources().getString(R.string.ga2f));

            editor.putString("ga1em",context.getResources().getString(R.string.ga1em));
            editor.putString("ga1ef",context.getResources().getString(R.string.ga1ef));
            editor.putString("ga1m",context.getResources().getString(R.string.ga1m));
            editor.putString("ga1f",context.getResources().getString(R.string.ga1f));
            editor.putString("ga1ym",context.getResources().getString(R.string.ga1ym));
            editor.putString("ga1yf",context.getResources().getString(R.string.ga1yf));

            editor.putString("g0em",context.getResources().getString(R.string.g0em));
            editor.putString("g0ef",context.getResources().getString(R.string.g0ef));
            editor.putString("g0m",context.getResources().getString(R.string.g0m));
            editor.putString("g0f",context.getResources().getString(R.string.g0f));
            editor.putString("g0ym",context.getResources().getString(R.string.g0ym));
            editor.putString("g0yf",context.getResources().getString(R.string.g0yf));

            editor.putString("gu1em",context.getResources().getString(R.string.gu1em));
            editor.putString("gu1ef",context.getResources().getString(R.string.gu1ef));
            editor.putString("gu1m",context.getResources().getString(R.string.gu1m));
            editor.putString("gu1f",context.getResources().getString(R.string.gu1f));

            editor.putString("gu2m",context.getResources().getString(R.string.gu2m));
            editor.putString("gu2f",context.getResources().getString(R.string.gu2f));

            editor.putString("gu3m",context.getResources().getString(R.string.gu3m));
            editor.putString("gu3f",context.getResources().getString(R.string.gu3f));

            editor.putString("gu4m",context.getResources().getString(R.string.gu4m));
            editor.putString("gu4f",context.getResources().getString(R.string.gu4f));

            editor.putString("gu5m",context.getResources().getString(R.string.gu5m));
            editor.putString("gu5f",context.getResources().getString(R.string.gu5f));

            editor.apply();
        }
    }

    public String getGA5M() {
        return sharedPreferences.getString("ga5m",context.getResources().getString(R.string.ga5m));
    }
    public String getGA5F() {
        return sharedPreferences.getString("ga5f",context.getResources().getString(R.string.ga5f));
    }
    public String getGA4M() {
        return sharedPreferences.getString("ga4m",context.getResources().getString(R.string.ga4m));
    }
    public String getGA4F() {
        return sharedPreferences.getString("ga4f",context.getResources().getString(R.string.ga4f));
    }
    public String getGA3M() {
        return sharedPreferences.getString("ga3m",context.getResources().getString(R.string.ga3m));
    }
    public String getGA3F() {
        return sharedPreferences.getString("ga3f",context.getResources().getString(R.string.ga3f));
    }
    public String getGA2M() {
        return sharedPreferences.getString("ga2m",context.getResources().getString(R.string.ga2m));
    }
    public String getGA2F() {
        return sharedPreferences.getString("ga2f",context.getResources().getString(R.string.ga2f));
    }
    public String getGA1EM() {
        return sharedPreferences.getString("ga1em",context.getResources().getString(R.string.ga1em));
    }
    public String getGA1EF() {
        return sharedPreferences.getString("ga1ef",context.getResources().getString(R.string.ga1ef));
    }
    public String getGA1M() {
        return sharedPreferences.getString("ga1m",context.getResources().getString(R.string.ga1m));
    }
    public String getGA1F() {
        return sharedPreferences.getString("ga1f",context.getResources().getString(R.string.ga1f));
    }
    public String getGA1YM() {
        return sharedPreferences.getString("ga1ym",context.getResources().getString(R.string.ga1ym));
    }
    public String getGA1YF() {
        return sharedPreferences.getString("ga1yf",context.getResources().getString(R.string.ga1yf));
    }
    public String getG0EM() {
        return sharedPreferences.getString("g0em",context.getResources().getString(R.string.g0em));
    }
    public String getG0EF() {
        return sharedPreferences.getString("g0ef",context.getResources().getString(R.string.g0ef));
    }
    public String getG0M() {
        return sharedPreferences.getString("g0m",context.getResources().getString(R.string.g0m));
    }
    public String getG0F() {
        return sharedPreferences.getString("g0f",context.getResources().getString(R.string.g0f));
    }
    public String getG0YM() {
        return sharedPreferences.getString("g0ym",context.getResources().getString(R.string.g0ym));
    }
    public String getG0YF() {
        return sharedPreferences.getString("g0yf",context.getResources().getString(R.string.g0yf));
    }
    public String getGU1EM() {
        return sharedPreferences.getString("gu1em",context.getResources().getString(R.string.gu1em));
    }
    public String getGU1EF() {
        return sharedPreferences.getString("gu1ef",context.getResources().getString(R.string.gu1ef));
    }
    public String getGU1M() {
        return sharedPreferences.getString("gu1m",context.getResources().getString(R.string.gu1m));
    }
    public String getGU1F() {
        return sharedPreferences.getString("gu1f",context.getResources().getString(R.string.gu1f));
    }
    public String getGU2M() {
        return sharedPreferences.getString("gu2m",context.getResources().getString(R.string.gu2m));
    }
    public String getGU2F() {
        return sharedPreferences.getString("gu2f",context.getResources().getString(R.string.gu2f));
    }
    public String getGU3M() {
        return sharedPreferences.getString("gu3m",context.getResources().getString(R.string.gu3m));
    }
    public String getGU3F() {
        return sharedPreferences.getString("gu3f",context.getResources().getString(R.string.gu3f));
    }
    public String getGU4M() {
        return sharedPreferences.getString("gu4m",context.getResources().getString(R.string.gu4m));
    }
    public String getGU4F() {
        return sharedPreferences.getString("gu4f",context.getResources().getString(R.string.gu4f));
    }
    public String getGU5M() {
        return sharedPreferences.getString("gu5m",context.getResources().getString(R.string.gu5m));
    }
    public String getGU5F() {
        return sharedPreferences.getString("gu5f",context.getResources().getString(R.string.gu5f));
    }
}
