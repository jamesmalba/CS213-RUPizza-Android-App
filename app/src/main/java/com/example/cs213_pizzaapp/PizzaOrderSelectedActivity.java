package com.example.cs213_pizzaapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author Alexis Wilson, James Alba
 */
public class PizzaOrderSelectedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView pizzaImage;
    private Button addPizzaToOrderButton;
    private EditText pizzaPrice;
    private TextView pizzaCrust, selectedPizzaType;
    private CheckBox sausageCheckbox, pepperoniCheckbox, greenPepperCheckbox, onionCheckbox,
            mushroomCheckbox, bbqChickenCheckbox, provoloneCheckbox, cheddarCheckbox, beefCheckbox,
            pineappleCheckbox, blackOlivesCheckbox, spinachCheckbox, baconCheckbox, hamCheckbox;
    private Spinner pizzaSizeSpinner;
    private Pizza pizza;
    private PizzaFactory pizzaFactory;
    public static final String[] PIZZA_SIZE_SPINNER_VALUES = new String[]{"SMALL", "MEDIUM", "LARGE"};
    private ArrayList<String> toppingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_pizza);
        assignReferences();


        Intent intent = getIntent();
        pizzaSizeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PIZZA_SIZE_SPINNER_VALUES));

        selectedPizzaType.setText(intent.getStringExtra("ITEM"));
        if (intent.getStringExtra("ITEM").equals("NY Build Your Own Pizza"))nyBYOSelected();
        if (intent.getStringExtra("ITEM").equals("NY BBQ Pizza")) nyBBQSelected();
        if (intent.getStringExtra("ITEM").equals("NY Deluxe Pizza")) nyDeluxeSelected();
        if (intent.getStringExtra("ITEM").equals("NY Meatzza Pizza")) nyMeatzzaSelected();
        if (intent.getStringExtra("ITEM").equals("Chicago Build Your Own Pizza")) chicagoBYOSelected();
        if (intent.getStringExtra("ITEM").equals("Chicago BBQ Pizza")) chicagoBBQSelected();
        if (intent.getStringExtra("ITEM").equals("Chicago Deluxe Pizza")) chicagoDeluxeSelected();
        if (intent.getStringExtra("ITEM").equals("Chicago Meatzza Pizza")) chicagoMeatzzaSelected();
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
        checkBoxListener();
        checkBoxListenerTwo();
        addPizzaToOrderButton.setOnClickListener(this::addToOrder);
        updatePriceOutput();
    }

    private void checkBoxListener() {
        sausageCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.SAUSAGE, isChecked);
            updatePriceOutput();
        });
        greenPepperCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.GREEN_PEPPER, isChecked);
            updatePriceOutput();
        });
        pepperoniCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.PEPPERONI, isChecked);
            updatePriceOutput();
        });
        onionCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.ONION, isChecked);
            updatePriceOutput();
        });
        mushroomCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.MUSHROOM, isChecked);
            updatePriceOutput();
        });
        bbqChickenCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.BBQ_CHICKEN, isChecked);
            updatePriceOutput();
        });
        provoloneCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.PROVOLONE, isChecked);
            updatePriceOutput();
        });
    }

    private void checkBoxListenerTwo() {
        beefCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.BEEF, isChecked);
            updatePriceOutput();
        });
        blackOlivesCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.BLACKOLIVES, isChecked);
            updatePriceOutput();
        });
        pineappleCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.PINEAPPLE, isChecked);
            updatePriceOutput();
        });
        spinachCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.SPINACH, isChecked);
            updatePriceOutput();
        });
        baconCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.BACON, isChecked);
            updatePriceOutput();
        });
        hamCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.HAM, isChecked);
            updatePriceOutput();
        });
        cheddarCheckbox.setOnClickListener(view -> {
            boolean isChecked = ((CheckBox)view).isChecked();
            addPizzaTopping(Topping.CHEDDAR, isChecked);
            updatePriceOutput();
        });
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
            if (pizza.getToppings().size() == 7) {
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
        pizzaFactory = new NYPizza();
        pizza = pizzaFactory.createBuildYourOwn();
        pizzaCrust.setText(R.string.handTossed);
        pizzaImage.setImageResource(R.drawable.ny_byo);
        buildYourOwnSelected();
    }
    private void chicagoBYOSelected() {
        pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createBuildYourOwn();
        pizzaCrust.setText(R.string.pan);
        pizzaImage.setImageResource(R.drawable.chicago_default);
        buildYourOwnSelected();
    }

    private void nyBBQSelected() {
        pizzaFactory = new NYPizza();
        pizza = pizzaFactory.createBBQChicken();
        pizzaCrust.setText(R.string.thin);
        pizzaImage.setImageResource(R.drawable.ny_bbq);
        presetSelected();
        bbqPreset();

    }

    private void nyDeluxeSelected() {
        pizzaFactory = new NYPizza();
        pizza = pizzaFactory.createDeluxe();
        pizzaCrust.setText(R.string.brooklyn);
        pizzaImage.setImageResource(R.drawable.ny_deluxe);
        presetSelected();
        deluxePreset();

    }

    private void nyMeatzzaSelected() {
        pizzaFactory = new NYPizza();
        pizza = pizzaFactory.createMeatzza();
        pizzaCrust.setText(R.string.handTossed);
        pizzaImage.setImageResource(R.drawable.ny_meatzza);
        presetSelected();
        meatzzaPreset();

    }

    private void chicagoBBQSelected() {
        pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createBBQChicken();
        pizzaCrust.setText(R.string.pan);
        pizzaImage.setImageResource(R.drawable.chicago_bbq);
        presetSelected();
        bbqPreset();

    }

    private void chicagoDeluxeSelected() {
        pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createDeluxe();
        pizzaCrust.setText(R.string.deepDish);
        pizzaImage.setImageResource(R.drawable.chicago_deluxe);
        presetSelected();
        deluxePreset();

    }

    private void chicagoMeatzzaSelected() {
        pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createMeatzza();
        pizzaCrust.setText(R.string.stuffed);
        pizzaImage.setImageResource(R.drawable.chicago_meatzza);
        presetSelected();
        meatzzaPreset();

    }

    private void bbqPreset() {
        bbqChickenCheckbox.setChecked(true);
        greenPepperCheckbox.setChecked(true);
        provoloneCheckbox.setChecked(true);
        cheddarCheckbox.setChecked(true);
    }

    private void deluxePreset() {
        sausageCheckbox.setChecked(true);
        pepperoniCheckbox.setChecked(true);
        greenPepperCheckbox.setChecked(true);
        onionCheckbox.setChecked(true);
        mushroomCheckbox.setChecked(true);
    }

    private void meatzzaPreset() {
        sausageCheckbox.setChecked(true);
        pepperoniCheckbox.setChecked(true);
        beefCheckbox.setChecked(true);
        hamCheckbox.setChecked(true);
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
        setCheckedFalse();
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
        setCheckedFalse();
    }
    /**
     * Private helper method deselects all check boxes.
     */
    private void setCheckedFalse() {
        sausageCheckbox.setChecked(false);
        pepperoniCheckbox.setChecked(false);
        greenPepperCheckbox.setChecked(false);
        onionCheckbox.setChecked(false);
        mushroomCheckbox.setChecked(false);
        bbqChickenCheckbox.setChecked(false);
        provoloneCheckbox.setChecked(false);
        cheddarCheckbox.setChecked(false);
        beefCheckbox.setChecked(false);
        pineappleCheckbox.setChecked(false);
        blackOlivesCheckbox.setChecked(false);
        spinachCheckbox.setChecked(false);
        baconCheckbox.setChecked(false);
        hamCheckbox.setChecked(false);
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
        pizzaPrice = (EditText) findViewById(R.id.pizza_price_textview);
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
