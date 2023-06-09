package fi.arcada.projekt_chi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView outputText;
    EditText inputText;

    SharedPreferences sharedPref;
    SharedPreferences.Editor prefEditor;


    // Deklarera 4 Button-objekt
    Button btn1, btn2, btn3, btn4;
    // Deklarera 4 heltalsvariabler för knapparnas värden
    int val1, val2, val3, val4;

    TextView column1, column2, row1, row2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        prefEditor = sharedPref.edit();


        column1 = findViewById(R.id.textViewCol1);
        column2 = findViewById(R.id.textViewCol2);
        row1 = findViewById(R.id.textViewRow1);
        row2 = findViewById(R.id.textViewRow2);

        String column1Title = sharedPref.getString("column1", "Column 1");
        column1.setText(column1Title);

        String column2Title = sharedPref.getString("column2", "Column 2");
        column2.setText(column2Title);

        String row1Title = sharedPref.getString("row1", "Row 1");
        row1.setText(row1Title);

        String row2Title = sharedPref.getString("row2", "Row 2");
        row2.setText(row2Title);

        /*String column1Title = inputText.getText().toString();
        prefEditor.putString("column1Title", column1Title);
        prefEditor.apply();

        column1.setText(String.format("%s", column1Title));*/


        // Koppla samman Button-objekten med knapparna i layouten
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);



    }

    /**
     *  Klickhanterare för knapparna
     */
    public void buttonClick(View view) {

        // Skapa ett Button-objekt genom att type-casta (byta datatyp)
        // på det View-objekt som kommer med knapptrycket
        Button btn = (Button) view;

        // Kontrollera vilken knapp som klickats, öka värde på rätt vaiabel
        if (view.getId() == R.id.button1) val1++;
        if (view.getId() == R.id.button2) val2++;
        if (view.getId() == R.id.button3) val3++;
        if (view.getId() == R.id.button4) val4++;

        // Slutligen, kör metoden som ska räkna ut allt!
        calculate();
    }

    /**
     * Metod som uppdaterar layouten och räknar ut själva analysen.
     */
    public void calculate() {

        // Uppdatera knapparna med de nuvarande värdena
        btn1.setText(String.valueOf(val1));
        btn2.setText(String.valueOf(val2));
        btn3.setText(String.valueOf(val3));
        btn4.setText(String.valueOf(val4));

        // Mata in värdena i Chi-2-uträkningen och ta emot resultatet
        // i en Double-variabel
        double chi2 = Significance.chiSquared(val1, val2, val3, val4);

        // Mata in chi2-resultatet i getP() och ta emot p-värdet
        double pValue = Significance.getP(chi2);

        /**
         *  - Visa chi2 och pValue åt användaren på ett bra och tydligt sätt!
         *
         *  - Visa procentuella andelen jakande svar inom de olika grupperna.
         *    T.ex. (val1 / (val1+val3) * 100) och (val2 / (val2+val4) * 100
         *
         *  - Analysera signifikansen genom att jämföra p-värdet
         *    med signifikansnivån, visa resultatet åt användaren
         *
         */

    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }


}