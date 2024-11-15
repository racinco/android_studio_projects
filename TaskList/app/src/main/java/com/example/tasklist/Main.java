package com.example.tasklist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import static android.content.ContentValues.TAG;

/* to create navigation Drawer
    1. Create navigation header using linear layout
    2. Create a navigation menu by using menu resource file in// create mebu drawer repository then create menu layout resource file
    3. make the activity_main drawer layout
    4. Create a Main fragement
 */
public class Main extends AppCompatActivity implements RecViewAdapter.DeleteContact, RecViewAdapter.UpdateContact {

    // This method is for updating the contact
    @Override
    public void OnUpdateContact(long taskId, Boolean newStatus) {
        TaskDatabase database = TaskDatabase.getInstance(this);
        Log.d(TAG, "onDeleteContact: Contact id to be deleted: " + taskId);
        database.taskdao().updateStatus(taskId,newStatus);
    }

    // this delete method is overhere since the activity/ context is pertaining to this
    @Override
    public void OnDeleteContact(long taskID) {
        TaskDatabase database = TaskDatabase.getInstance(this);
        Log.d(TAG, "onDeleteContact: Contact id to be deleted: " + taskID);
        TaskEntity deteteTaskEntity = new TaskEntity(taskID);
        database.taskdao().deleteSingleTask(deteteTaskEntity);
    }


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar materialToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        // initialize views
        initViews();

        // set toggle NOTE: the three dash lines at the top
        setSupportActionBar(materialToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, materialToolbar,
                R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // animation

        // set onclick Listener

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.android:
                        break;
                    case R.id.otherFeatures:
                        break;
                }
                return false;
            }
        });

        // replace the Frame layout Container by a fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainFragment());
        transaction.commit();
    }

    private void initViews (){
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        materialToolbar = findViewById(R.id.toolbar);

    }


}