package pl.edu.agh.mobilecalculator;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText n1EditText;
    private EditText n2EditText;
    private EditText resEditText;

    private Button addButton;
    private Button subButton;
    private Button mulButton;
    private Button divButton;

    LogicService logicService;
    boolean mBound = false;

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

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            this.bindService(new Intent(MainActivity.this, LogicService.class),
                    logicConnection, Context.BIND_AUTO_CREATE);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            mBound = false;
            this.unbindService(logicConnection);
        }
    }

    private ServiceConnection logicConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            LogicService.LocalBinder binder = (LogicService.LocalBinder) service;
            logicService = binder.getService();
            mBound = true;
            Toast.makeText(MainActivity.this, "Logic Service Connected!",
                    Toast.LENGTH_SHORT).show();
        }
        public void onServiceDisconnected(ComponentName className) {
            logicService = null;
            mBound = false;
            Toast.makeText(MainActivity.this, "Logic Service Disconnected!",
                    Toast.LENGTH_SHORT).show();
        }
    };



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
        double firstNumber = Double.parseDouble(n1EditText.getText().toString());
        double secondNumber = Double.parseDouble(n2EditText.getText().toString());
        final double result;

        if (mBound) {
            System.out.println("Calculated by logic service");
            result = logicService.add(firstNumber, secondNumber);
        } else {
            result = firstNumber + secondNumber;
        }
        resEditText.setText(Double.toString(result));
    }

    private void subNumbers() {
        double firstNumber = Double.parseDouble(n1EditText.getText().toString());
        double secondNumber = Double.parseDouble(n2EditText.getText().toString());
        final double result;

        if (mBound) {
            System.out.println("Calculated by logic service");
            result = logicService.sub(firstNumber, secondNumber);
        } else {
            result = firstNumber - secondNumber;
        }
        resEditText.setText(Double.toString(result));
    }


    private void divNumbers() {
        double firstNumber = Double.parseDouble(n1EditText.getText().toString());
        double secondNumber = Double.parseDouble(n2EditText.getText().toString());
        final double result;

        if (mBound) {
            System.out.println("Calculated by logic service");
            result = logicService.div(firstNumber, secondNumber);
        } else {
            result = firstNumber / secondNumber;
        }
        resEditText.setText(Double.toString(result));
    }


    private void mulNumbers() {
        double firstNumber = Double.parseDouble(n1EditText.getText().toString());
        double secondNumber = Double.parseDouble(n2EditText.getText().toString());
        final double result;

        if (mBound) {
            System.out.println("Calculated by logic service");
            result = logicService.mul(firstNumber, secondNumber);
        } else {
            result = firstNumber * secondNumber;
        }
        resEditText.setText(Double.toString(result));
    }
}
