package com.anthony.app.module.statusview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anthony.app.R;
import com.anthony.statuslayout.StatusLayout;
import com.malinskiy.materialicons.IconDrawable;
import com.malinskiy.materialicons.Iconify;

import java.util.ArrayList;
import java.util.List;

public class ShowStatusActivity extends AppCompatActivity {

    Toolbar activityToolbar;

    StatusLayout statusLayout;

    private View.OnClickListener errorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplication(), "Try again button clicked", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_status);

        activityToolbar = (Toolbar) findViewById(R.id.activityToolbar);
        statusLayout = (StatusLayout) findViewById(R.id.progress);

        setToolbar();

        Drawable emptyDrawable = new IconDrawable(this, Iconify.IconValue.zmdi_shopping_basket)
                .colorRes(android.R.color.white);

        Drawable errorDrawable = new IconDrawable(this, Iconify.IconValue.zmdi_wifi_off)
                .colorRes(android.R.color.white);


        //Add which views you don't want to hide. In this case don't hide the toolbar
        List<Integer> skipIds = new ArrayList<>();
        skipIds.add(R.id.activityToolbar);

        String state = getIntent().getStringExtra("STATE");
        switch (state) {
            case "LOADING":
                statusLayout.showLoading(skipIds);
                setTitle("Loading");
                break;
            case "EMPTY":
                statusLayout.showEmpty(emptyDrawable,
                        "Empty Shopping Cart",
                        "Please add things in the cart to continue.", skipIds);
                setTitle("Empty");
                break;
            case "ERROR":
                statusLayout.showError(errorDrawable,
                        "No Connection",
                        "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                        "Try Again", errorClickListener, skipIds);
                setTitle("Error");
                break;
        }
    }

    private void setToolbar() {
        setSupportActionBar(activityToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Progress Activity");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}