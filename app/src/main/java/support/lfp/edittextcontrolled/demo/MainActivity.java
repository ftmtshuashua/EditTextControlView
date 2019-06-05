package support.lfp.edittextcontrolled.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import support.lfp.edittextcontrolled.EditTextControlView;
import support.lfp.edittextcontrolled.processor.IntegerBorderProcessor;
import support.lfp.edittextcontrolled.processor.PasswordVisibilityProcessor;
import support.lfp.edittextcontrolled.processor.ValueTrimProcessor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditTextControlView v_1 = findViewById(R.id.view_1);
        v_1.addProcessor(new IntegerBorderProcessor(0, 10));

        EditTextControlView v_2 = findViewById(R.id.view_2);
        v_2.addProcessor(new PasswordVisibilityProcessor(findViewById(R.id.view_2_1), PasswordVisibilityProcessor.Style.TOUCH));

        EditTextControlView v_3 = findViewById(R.id.view_3);
        v_3.addProcessor(new ValueTrimProcessor());
    }
}
