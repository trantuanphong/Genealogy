package fu.naan.genealogy.common;

import android.content.Context;
import android.content.SharedPreferences;

import fu.naan.genealogy.R;

public class DataSaver {

    private final String SHARED_PREFERENCES_NAME = "shared_preferences_name";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DataSaver(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void initDefaultData(Context context) {
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
