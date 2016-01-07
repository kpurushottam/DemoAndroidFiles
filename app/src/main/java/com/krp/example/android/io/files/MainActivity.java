package com.krp.example.android.io.files;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private SharedPreferences sharedPrefs;
    private String KEY_PREFS_INPUT;

    private EditText etInput;


    private SharedPreferences.OnSharedPreferenceChangeListener mSharedPrefsListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String key) {
            Toast.makeText(getApplicationContext(),
                    sharedPrefs.getString(key, "No value found!!"), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefs = getSharedPreferences(TAG, MODE_PRIVATE);
        etInput = (EditText) findViewById(R.id.input_prefs);

        sharedPrefs.registerOnSharedPreferenceChangeListener(mSharedPrefsListener);
    }

    public void addToPrefs(View view) {
        sharedPrefs.edit().putString(KEY_PREFS_INPUT, etInput.getText().toString()).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sharedPrefs.unregisterOnSharedPreferenceChangeListener(mSharedPrefsListener);
    }
}
