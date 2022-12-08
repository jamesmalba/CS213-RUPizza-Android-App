package com.example.cs213_pizzaapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 *
 * @author Alexis Wilson, James Alba
 */
public class PizzaOrderSelectedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ImageView pizzaImage;
    private Button addPizzaToOrderButton;
    private TextView pizzaCrust, selectedPizzaType, pizzaPrice;
    private CheckBox sausageCheckbox, pepperoniCheckbox, greenPepperCheckbox, onionCheckbox,
            mushroomCheckbox, bbqChickenCheckbox, provoloneCheckbox, cheddarCheckbox, beefCheckbox,
            pineappleCheckbox, blackOlivesCheckbox, spinachCheckbox, baconCheckbox, hamCheckbox;
    private Spinner pizzaSizeSpinner;
    private Pizza pizza;
    private PizzaFactory pizzaFactory = new NYPizza();
    public static final String[] PIZZA_SIZE_SPINNER_VALUES = new String[]{"SMALL", "MEDIUM", "LARGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_pizza); //CHOOSE PIZZA ACTIVITY HERE
        assignReferences(); //sets references for all view components


        Intent intent = getIntent();
        pizzaSizeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PIZZA_SIZE_SPINNER_VALUES));

        selectedPizzaType.setText(intent.getStringExtra("ITEM"));

        switch(intent.getStringExtra("ITEM")) {
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

    /**
     * On Start lifetime cycle for our activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        pizzaSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = parent.getItemAtPosition(position).toString();
                if (selectedSize.equals("SMALL")) pizza.setSizeToSmall();
                if (selectedSize.equals("MEDIUM")) pizza.setSizeToMedium();
                if (selectedSize.equals("LARGE")) pizza.setSizeToLarge();
                updatePriceOutput();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addPizzaToOrderButton.setOnClickListener(this::addToOrder);
        updatePriceOutput();
    }

    @Override
    public void onClick(View v) {
        boolean isChecked = ((CheckBox)v).isChecked();
        if (sausageCheckbox.equals(v)) addPizzaTopping(Topping.SAUSAGE, isChecked);
        if (pepperoniCheckbox.equals(v)) addPizzaTopping(Topping.PEPPERONI, isChecked);
        if (greenPepperCheckbox.equals(v)) addPizzaTopping(Topping.GREEN_PEPPER, isChecked);
        if (onionCheckbox.equals(v)) addPizzaTopping(Topping.ONION, isChecked);
        if (mushroomCheckbox.equals(v)) addPizzaTopping(Topping.MUSHROOM, isChecked);
        if (bbqChickenCheckbox.equals(v)) addPizzaTopping(Topping.BBQ_CHICKEN, isChecked);
        if (provoloneCheckbox.equals(v)) addPizzaTopping(Topping.PROVOLONE, isChecked);
        if (cheddarCheckbox.equals(v)) addPizzaTopping(Topping.CHEDDAR, isChecked);
        if (beefCheckbox.equals(v)) addPizzaTopping(Topping.BEEF, isChecked);
        if (blackOlivesCheckbox.equals(v)) addPizzaTopping(Topping.BLACKOLIVES, isChecked);
        if (pineappleCheckbox.equals(v)) addPizzaTopping(Topping.PINEAPPLE, isChecked);
        if (spinachCheckbox.equals(v)) addPizzaTopping(Topping.SPINACH, isChecked);
        if (baconCheckbox.equals(v)) addPizzaTopping(Topping.BACON, isChecked);
        if (hamCheckbox.equals(v)) addPizzaTopping(Topping.HAM, isChecked);
        updatePriceOutput();
    }



    /**
     * onItemSelected handler for all of the addin spinners.
     * @param parent AdapterView parent
     * @param view View that had it's item selected
     * @param position Position in the list that was selected
     * @param id id of the selected item
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Check if spinner and cast
        if(parent instanceof Spinner) {
            String selectedSize = parent.getItemAtPosition(position).toString();
            if (selectedSize.equals("SMALL")) pizza.setSizeToSmall();
            if (selectedSize.equals("MEDIUM")) pizza.setSizeToMedium();
            if (selectedSize.equals("LARGE")) pizza.setSizeToLarge();

            updatePriceOutput();
        }
    }

    /**
     * onNothingSelected handler for all the addin spinners. We actually don't use this function but we must implement it in order to compile
     * @param parent parent view that called this function
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Adds the current Coffee order to the current order. Called by the "Add to Order" button
     *
     * @param v the view that calls this handler
     */
    public void addToOrder(View v) {
        Order.getInstance().add(pizza);
        Toast.makeText(getBaseContext(), R.string.order_successfully_added, Toast.LENGTH_SHORT).show();
        Intent gotoMainActivity = new Intent(this, MainActivity.class);
        startActivity(gotoMainActivity);
    }

    private void addPizzaTopping(Topping topping, boolean isCheck) {
        if(isCheck) {
            if (pizza.getToppings().size() > 7) {
                Toast.makeText(getBaseContext(), R.string.too_many_toppings, Toast.LENGTH_LONG).show();
                addPizzaToOrderButton.setEnabled(false);
            } else {
                pizza.add(topping);
            }
        } else {
            pizza.remove(topping);
            if (pizza.getToppings().size() <= 7) addPizzaToOrderButton.setEnabled(true);
        }
    }

    private void nyBYOSelected() {
        pizza = pizzaFactory.createBuildYourOwn();
        pizzaCrust.setText(R.string.handTossed);
        pizzaImage.setImageResource(R.drawable.ny_byo);
        buildYourOwnSelected();
    }
    private void chicagoBYOSelected() {
        pizza = pizzaFactory.createBuildYourOwn();
        pizzaCrust.setText(R.string.pan);
        pizzaImage.setImageResource(R.drawable.chicago_default);
        buildYourOwnSelected();
    }

    private void nyBBQSelected() {
        pizza = pizzaFactory.createBBQChicken();
        pizzaCrust.setText(R.string.thin);
        pizzaImage.setImageResource(R.drawable.ny_bbq);
        presetSelected();
        bbqPreset();
    }

    private void nyDeluxeSelected() {
        pizza = pizzaFactory.createDeluxe();
        pizzaCrust.setText(R.string.brooklyn);
        pizzaImage.setImageResource(R.drawable.ny_deluxe);
        presetSelected();
        deluxePreset();
    }

    private void nyMeatzzaSelected() {
        pizza = pizzaFactory.createMeatzza();
        pizzaCrust.setText(R.string.handTossed);
        pizzaImage.setImageResource(R.drawable.ny_meatzza);
        presetSelected();
        meatzzaPreset();
    }

    private void chicagoBBQSelected() {
        pizza = pizzaFactory.createBBQChicken();
        pizzaCrust.setText(R.string.pan);
        pizzaImage.setImageResource(R.drawable.chicago_bbq);
        presetSelected();
        bbqPreset();
    }

    private void chicagoDeluxeSelected() {
        pizza = pizzaFactory.createDeluxe();
        pizzaCrust.setText(R.string.deepDish);
        pizzaImage.setImageResource(R.drawable.chicago_deluxe);
        presetSelected();
        deluxePreset();
    }

    private void chicagoMeatzzaSelected() {
        pizza = pizzaFactory.createMeatzza();
        pizzaCrust.setText(R.string.stuffed);
        pizzaImage.setImageResource(R.drawable.chicago_meatzza);
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
     * Calls pizza method to get the price of the current pizza and displays it in the price display text field.
     */
    public void updatePriceOutput() {
        pizzaPrice.setText(String.format("%.2f", pizza.price()));
    }


    /**
     * Private helper method that assigns all the references to all android components from view.
     */
    private void assignReferences() {
        //FILL ALL ITEM IDS HERE
        pizzaPrice = (TextView) findViewById(R.id.pizza_price_textview);
        addPizzaToOrderButton = (Button) findViewById(R.id.place_order_button);
        selectedPizzaType = (TextView) findViewById(R.id.pizza_order_textview);
        pizzaCrust = (TextView) findViewById(R.id.pizza_crust_textview);
        pizzaSizeSpinner = (Spinner) findViewById(R.id.size_picker_spinner);
        sausageCheckbox = (CheckBox) findViewById(R.id.sausage_topping_checkbox);
        pepperoniCheckbox = (CheckBox) findViewById(R.id.pepperoni_topping_checkbox);
        greenPepperCheckbox = (CheckBox) findViewById(R.id.green_pepper_topping_checkbox);
        onionCheckbox = (CheckBox) findViewById(R.id.onion_topping_checkbox);
        mushroomCheckbox = (CheckBox) findViewById(R.id.mushroom_topping_checkbox);
        bbqChickenCheckbox = (CheckBox) findViewById(R.id.bbq_chicken_topping_checkbox);
        provoloneCheckbox = (CheckBox) findViewById(R.id.provolone_topping_checkbox);
        cheddarCheckbox = (CheckBox) findViewById(R.id.cheddar_topping_checkbox);
        beefCheckbox = (CheckBox) findViewById(R.id.beef_topping_checkbox);
        pineappleCheckbox = (CheckBox) findViewById(R.id.pineapple_topping_checkbox);
        blackOlivesCheckbox = (CheckBox) findViewById(R.id.black_olives_topping_checkbox);
        spinachCheckbox = (CheckBox) findViewById(R.id.spinach_topping_checkbox);
        baconCheckbox = (CheckBox) findViewById(R.id.bacon_topping_checkbox);
        hamCheckbox = (CheckBox) findViewById(R.id.ham_topping_checkbox);
        pizzaImage = (ImageView) findViewById(R.id.pizza_order_imageview);
    }



}
