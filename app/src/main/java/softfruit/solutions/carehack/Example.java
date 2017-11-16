package softfruit.solutions.carehack;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import softfruit.solutions.carehack.adapter.CategoriesAdapter;
import softfruit.solutions.carehack.fragments.BookingFragment;
import softfruit.solutions.carehack.model.Category;

/**
 * Created by ajithvgiri on 13/11/17.
 */

public class Example extends Activity implements BookingFragment.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        List<Category> categories = new ArrayList<>();
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, categories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoriesAdapter);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_general_medicine);
    }


    @Override
    public void onFragmentInteraction(@NotNull Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
