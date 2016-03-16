package pl.edu.agh.mobilecalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText n1EditText;
    private EditText n2EditText;
    private EditText resEditText;

    private Button addButton;
    private Button subButton;
    private Button mulButton;
    private Button divButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        n1EditText = (EditText) findViewById(R.id.n1EditText);
        n2EditText = (EditText) findViewById(R.id.n2EditText);
        resEditText = (EditText) findViewById(R.id.resEditText);

        addButton = (Button) findViewById(R.id.addButton);
        subButton = (Button) findViewById(R.id.subButton);
        mulButton = (Button) findViewById(R.id.mulButton);
        divButton = (Button) findViewById(R.id.divButton);

        createButtonListeners();
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


    private void createButtonListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumbers();
            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subNumbers();
            }
        });
        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mulNumbers();
            }
        });
        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                divNumbers();
            }
        });
    }

    private void addNumbers() {
        int firstNumber = Integer.parseInt(n1EditText.getText().toString());
        int secondNumber = Integer.parseInt(n2EditText.getText().toString());

        int result = firstNumber + secondNumber;
        resEditText.setText(Integer.toString(result));
    }

    private void subNumbers() {
        int firstNumber = Integer.parseInt(n1EditText.getText().toString());
        int secondNumber = Integer.parseInt(n2EditText.getText().toString());

        int result = firstNumber - secondNumber;
        resEditText.setText(Integer.toString(result));
    }

    private void divNumbers() {
        int firstNumber = Integer.parseInt(n1EditText.getText().toString());
        int secondNumber = Integer.parseInt(n2EditText.getText().toString());

        int result = firstNumber / secondNumber;
        resEditText.setText(Integer.toString(result));
    }

    private void mulNumbers() {
        int firstNumber = Integer.parseInt(n1EditText.getText().toString());
        int secondNumber = Integer.parseInt(n2EditText.getText().toString());

        int result = firstNumber * secondNumber;
        resEditText.setText(Integer.toString(result));
    }
}
