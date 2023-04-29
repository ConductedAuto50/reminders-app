package com.ritvik.reminder2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.ritvik.reminder2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        ListView lv = findViewById(R.id.viewdata);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        DatabaseHelper databaseHelper= new DatabaseHelper(MainActivity.this);
        List<ReminderModel> everyone = databaseHelper.getAll();
        ArrayAdapter reminderArrayAdapter = new ArrayAdapter<ReminderModel>(MainActivity.this, android.R.layout.simple_list_item_1,everyone);
        lv.setAdapter(reminderArrayAdapter);
//        Toast.makeText(this,everyone.toString(),Toast.LENGTH_SHORT).show();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
//                DatabaseHelper databaseHelper= new DatabaseHelper(MainActivity.this);
//                List<ReminderModel> everyone = databaseHelper.getAll();
//                ArrayAdapter reminderArrayAdapter = new ArrayAdapter<ReminderModel>(MainActivity.this, android.R.layout.simple_list_item_1,everyone);
//                lv.setAdapter(reminderArrayAdapter);
                addreminderfunc();
            }
        });
    }

    private void addreminderfunc(){
        Intent addremIntent = new Intent(this, AddReminderActivity.class);
        startActivity(addremIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}