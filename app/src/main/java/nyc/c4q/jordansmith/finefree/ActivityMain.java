package nyc.c4q.jordansmith.finefree;

import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jordansmith.finefree.model.Car;
import nyc.c4q.jordansmith.finefree.sqlite.CarDatabaseHelper;
import nyc.c4q.jordansmith.finefree.sqlite.SqlHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class ActivityMain extends AppCompatActivity {

    public static final String PLATE_KEY = "Car License";
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navDrawerView;
    private ActionBarDrawerToggle drawerToggle;
    SubMenu submenu;

    private SQLiteDatabase db;
    private List<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarDatabaseHelper helper = CarDatabaseHelper.getInstance(this);
        db = helper.getWritableDatabase();
        cars = SqlHelper.selectAllCars(db);

        setupToolbar();
        setupDrawerContent(navDrawerView);
        drawerToggle = setupDrawerToggle();

        startDefaultHomeFragment();


    }

    private void startDefaultHomeFragment() {
        if (!cars.isEmpty()) {

            FragmentHome fragmentHome = new FragmentHome();
            Bundle bundle = new Bundle();
            bundle.putSerializable(PLATE_KEY, cars.get(0));
//            bundle.putString(PLATE_KEY, cars.get(0).getLicensePlate());
            fragmentHome.setArguments(bundle);


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_holder, fragmentHome)
                    .commit();
        } else {

            //inflate fragment that has no cars

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_holder, new FragmentNoCar())
                    .commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        addCarstoNav();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navDrawerView = (NavigationView) findViewById(R.id.nvView);
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(final MenuItem menuItem) {
        switch (menuItem.getTitle().toString()) {
            case "Home":
                startDefaultHomeFragment();
                break;
            case "Settings":
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_holder, new FragmentSettings())
                        .commit();
                break;
            case "Add new Car":
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_holder, new FragmentNewCar())
                        .commit();
                break;
        }

        // Submenu of cars
        String plate;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getName().equals(menuItem.getTitle())) {
                plate = cars.get(i).getLicensePlate();
                Bundle bundle = new Bundle();
                bundle.putSerializable(PLATE_KEY, cars.get(i));
                FragmentHome fragmentHome = new FragmentHome();
                fragmentHome.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                        .replace(R.id.fragment_holder, fragmentHome)
                        .commit();

            }
        }


        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void addCarstoNav() {
        final Menu menu = navDrawerView.getMenu();
        if (submenu == null) {
            submenu = menu.addSubMenu("Your Cars");
            for (int i = 0; i < cars.size(); i++) {
                submenu.add(cars.get(i).getName()).setTitle(cars.get(i).getName().toString())
                        .setIcon(R.drawable.ic_car_black_36dp);

                System.out.println(submenu.getItem(i).getItemId() + " " + submenu.getItem(i).getTitle());

                final MenuItem item = submenu.getItem(i);
                Button button = new Button(this);
                button.setText("Remove");
                button.setTextColor(getResources().getColor(R.color.white));
                item.setActionView(button);
                item.getActionView().setBackground(getDrawable(R.drawable.button_shape));
                item.getActionView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        System.out.println("CLICKED " + item.getTitle());
                        cupboard().withDatabase(db).delete(Car.class, "name = ?", (String) item.getTitle());

                        updateSubmenu();
                    }
                });
            }
        } else {
            for (int i = 0; i < cars.size(); i++) {
                submenu.clear();
                submenu.add(cars.get(i).getName()).setTitle(cars.get(i).getName().toString())
                        .setIcon(R.drawable.ic_car_black_36dp);

            }

        }
    }

    private void updateSubmenu() {
        cars = SqlHelper.selectAllCars(db);
        System.out.println("SIZE AFTER REMOVE " + cars.size());

        submenu.clear();

        for (int i = 0; i < cars.size(); i++) {
            submenu.add(cars.get(i).getName()).setTitle(cars.get(i).getName().toString())
                    .setIcon(R.drawable.ic_car_black_36dp);

            final MenuItem item = submenu.getItem(i);
            Button button = new Button(this);
            button.setText("Remove");
            button.setTextColor(getResources().getColor(R.color.white));
            item.setActionView(button);
            item.getActionView().setBackground(getDrawable(R.drawable.button_shape));
            item.getActionView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    System.out.println("CLICKED " + item.getTitle());
                    cupboard().withDatabase(db).delete(Car.class, "name = ?", (String) item.getTitle());

                    updateSubmenu();
                }
            });
        }




    }
}
