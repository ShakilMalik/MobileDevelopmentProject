package csis3175.mobiledevelopmentproject;

import android.app.ListActivity;
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

public class Budget extends ListActivity {
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    DatabaseHelper dbh;

    int Bill, Education, Entertainment, Gift, Groceries, Household, Travel, Personal, Other;
    ArrayList<String> budget = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        Button btnAB = (Button)findViewById(R.id.btnAddExpense);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
    }

    public void addItems(View v) {
        startActivity(new Intent(Budget.this, AddTransaction.class));
    }

    public void DatesToArraylist() {
        DatabaseHelper dbh = new DatabaseHelper(this);
        Cursor crs = dbh.getReadableDatabase().rawQuery("SELECT * FROM " + dbh.TABLE_NAME, null);
        budget.clear();

        while (crs.moveToNext()) {
            String category = crs.getString(crs.getColumnIndex("category"));
            String value = crs.getString(crs.getColumnIndex("value"));

            //TODO
        }
    }
}
