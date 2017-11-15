package softfruit.solutions.quickdoc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by ajithvgiri on 13/11/17.
 */

public class Example extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        final EditText editext = findViewById(R.id.editText);

    }
}
