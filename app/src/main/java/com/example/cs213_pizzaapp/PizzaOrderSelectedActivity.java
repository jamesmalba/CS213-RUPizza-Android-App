package com.example.cs213_pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 * @author Alexis Wilson, James Alba
 */
public class PizzaOrderSelectedActivity extends AppCompatActivity {
    private Button selectedPizzaName;
    private Button addPizzaToOrderButton;
    private TextView pizzaCrust, pizzaSize, selectedPizzaType;
    private CheckBox sausageCheckbox, pepperoniCheckbox, greenPepperCheckbox, onionCheckbox,
            mushroomCheckbox, bbqChickenCheckbox, provoloneCheckbox, cheddarCheckbox, beefCheckbox,
            pineappleCheckbox, blackOlivesCheckbox, spinachCheckbox, baconCheckbox, hamCheckbox;
    private Spinner pizzaSizeSpinner;
    private Pizza p;
    public static final String[] PIZZA_SIZE_SPINNER_VALUES = new String[]{"SMALL", "MEDIUM", "LARGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.); //CHOOSE PIZZA ACTIVITY HERE
        assignRefrences(); //sets references for all view components


        Intent intent = getIntent();
        //populate spinner
        pizzaSizeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PIZZA_SIZE_SPINNER_VALUES));


        selectedPizzaType.setText(intent.getStringExtra("ITEM"));
        String selectedPizzaName = intent.getStringExtra("ITEM");
        switch(selectedPizzaName) {
            case "NY Build Your Own Pizza": nyBYOSelected();
            case "NY BBQ Pizza": nyBBQSelected();
            case "NY Deluxe Pizza": nyDeluxeSelected();
            case "NY Meatzza Pizza": nyMeatzzaSelected();
            case "Chicago Build Your Own Pizza": chicagoBYOSelected();
            case "Chicago BBQ Pizza": chicagoBBQSelected();
            case "Chicago Deluxe Pizza": chicagoDeluxeSelected();
            case "Chicago Meatzza Pizza": chicagoMeatzzaSelected();
        }
    }

    private void nyBYOSelected() {
        buildYourOwnSelected();

    }

    private void chicagoBYOSelected() {
        buildYourOwnSelected();

    }

    private void nyBBQSelected() {
        presetSelected();
        bbqPreset();

    }

    private void nyDeluxeSelected() {
        presetSelected();
        deluxePreset();

    }

    private void nyMeatzzaSelected() {
        presetSelected();
        meatzzaPreset();
    }

    private void chicagoBBQSelected() {
        presetSelected();
        bbqPreset();
    }

    private void chicagoDeluxeSelected() {
        presetSelected();
        deluxePreset();
    }

    private void chicagoMeatzzaSelected() {
        presetSelected();
        meatzzaPreset();
    }

    private void bbqPreset() {
        bbqChickenCheckbox.setSelected(true);
        greenPepperCheckbox.setSelected(true);
        provoloneCheckbox.setSelected(true);
        cheddarCheckbox.setSelected(true);
    }

    private void deluxePreset() {
        sausageCheckbox.setSelected(true);
        pepperoniCheckbox.setSelected(true);
        greenPepperCheckbox.setSelected(true);
        onionCheckbox.setSelected(true);
        mushroomCheckbox.setSelected(true);
    }

    private void meatzzaPreset() {
        sausageCheckbox.setSelected(true);
        pepperoniCheckbox.setSelected(true);
        beefCheckbox.setSelected(true);
        hamCheckbox.setSelected(true);
    }

    /**
     * Private helper method that sets all checkboxes to not be clickable for preset.
     */
    private void presetSelected() {
        sausageCheckbox.setClickable(false);
        pepperoniCheckbox.setClickable(false);
        greenPepperCheckbox.setClickable(false);
        onionCheckbox.setClickable(false);
        mushroomCheckbox.setClickable(false);
        bbqChickenCheckbox.setClickable(false);
        provoloneCheckbox.setClickable(false);
        cheddarCheckbox.setClickable(false);
        beefCheckbox.setClickable(false);
        pineappleCheckbox.setClickable(false);
        blackOlivesCheckbox.setClickable(false);
        spinachCheckbox.setClickable(false);
        baconCheckbox.setClickable(false);
        hamCheckbox.setClickable(false);
    }

    /**
     * Private helper method that sets all checkboxes to be clickable.
     */
    private void buildYourOwnSelected() {
        sausageCheckbox.setClickable(true);
        pepperoniCheckbox.setClickable(true);
        greenPepperCheckbox.setClickable(true);
        onionCheckbox.setClickable(true);
        mushroomCheckbox.setClickable(true);
        bbqChickenCheckbox.setClickable(true);
        provoloneCheckbox.setClickable(true);
        cheddarCheckbox.setClickable(true);
        beefCheckbox.setClickable(true);
        pineappleCheckbox.setClickable(true);
        blackOlivesCheckbox.setClickable(true);
        spinachCheckbox.setClickable(true);
        baconCheckbox.setClickable(true);
        hamCheckbox.setClickable(true);
    }


    /**
     * Private helper method that assigns all the references to all android components from view.
     */
    private void assignRefrences() {
        //FILL ALL ITEM IDS HERE
        selectedPizzaName = (Button) findViewById(R.id.);
        addPizzaToOrderButton = (Button) findViewById(R.id.);
        selectedPizzaType = (TextView) findViewById(R.id.);
        pizzaCrust = (TextView) findViewById(R.id.);
        pizzaSize = (TextView) findViewById(R.id.);
        sausageCheckbox = (CheckBox) findViewById(R.id.);
        pepperoniCheckbox = (CheckBox) findViewById(R.id.);
        greenPepperCheckbox = (CheckBox) findViewById(R.id.);
        onionCheckbox = (CheckBox) findViewById(R.id.);
        mushroomCheckbox = (CheckBox) findViewById(R.id.);
        bbqChickenCheckbox = (CheckBox) findViewById(R.id.);
        provoloneCheckbox = (CheckBox) findViewById(R.id.);
        cheddarCheckbox = (CheckBox) findViewById(R.id.);
        beefCheckbox = (CheckBox) findViewById(R.id.);
        pineappleCheckbox = (CheckBox) findViewById(R.id.);
        blackOlivesCheckbox = (CheckBox) findViewById(R.id.);
        spinachCheckbox = (CheckBox) findViewById(R.id.);
        baconCheckbox = (CheckBox) findViewById(R.id.);
        hamCheckbox = (CheckBox) findViewById(R.id.);
        pizzaSizeSpinner = (Spinner) findViewById(R.id.);
    }

}
