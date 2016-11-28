package csis3175.mobiledevelopmentproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Income extends AppCompatActivity {
    DatabaseHelper dbh;
    ArrayList<String> incomeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        dbh = new DatabaseHelper(this);
        ListView lv = (ListView) findViewById(R.id.listView3);
        Button btnAB = (Button) findViewById(R.id.btnAddIncome);

        Cursor r = dbh.viewRec("James");
        StringBuilder str = new StringBuilder();
        if (r.getCount() > 0) {
            while (r.moveToNext()) {
                str.append(r.getString(1) + "\n");
                str.append(r.getString(2) + "/Month \n");
                str.append(r.getString(3) + " of each month \n");
            }
            incomeList.add(str.toString());
        } else {
            Toast.makeText(Income.this, "No record to display", Toast.LENGTH_LONG).show();
        }

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, incomeList);
        lv.setAdapter(aa);

        btnAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Income.this, AddTransaction.class));
            }
        });
    }
}
