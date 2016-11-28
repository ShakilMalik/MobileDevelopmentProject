package csis3175.mobiledevelopmentproject;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends ListActivity {
    DatabaseHelper dbh;
    String userID = "user";

    ArrayList<String> userData = new ArrayList<String>();
    ArrayList<String> datesData = new ArrayList<String>();
    ArrayList<String> listItems = new ArrayList<String>();  // List of strings which will serve as data for the listview
    ArrayAdapter<String> adapter; // Defining a string adapter which will handle the data of the listview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        final TextView idRemove = (TextView) findViewById(R.id.txtRemove);
        final Button btnRemove = (Button) findViewById(R.id.btnRemove);

        dbh = new DatabaseHelper(this);
        dbh.createTable(dbh.TABLE_NAME);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
        refreshData();

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbh.deleteRec(idRemove.getText().toString());
                refreshData();
                Toast.makeText(HomeScreen.this,"Record Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

    // Occurs when the user presses the "Add Item" button
    public void addItems(View v) {
        startActivity(new Intent(HomeScreen.this, AddTransaction.class));
    }

    // Method that will add a specified SQLDatabase to an arraylist
    public void DatabaseToArraylist() {
        DatabaseHelper dbh = new DatabaseHelper(this);
        Cursor crs = dbh.getReadableDatabase().rawQuery("SELECT * FROM " + dbh.TABLE_NAME, null);
        userData.clear();

        while (crs.moveToNext()) {
            String id = crs.getString(crs.getColumnIndex("id"));
            String date = crs.getString(crs.getColumnIndex("date"));
            String category = crs.getString(crs.getColumnIndex("category"));
            String value = crs.getString(crs.getColumnIndex("value"));
            String detail = crs.getString(crs.getColumnIndex("detail"));

            userData.add("#" + id + "\t" + date + "\n" + detail + ": $" + value + "\n" + category);
        }
    }

    //TODO
    public void DatesToArraylist() {
        DatabaseHelper dbh = new DatabaseHelper(this);
        Cursor crs = dbh.getReadableDatabase().rawQuery("SELECT * FROM " + dbh.TABLE_NAME, null);
        datesData.clear();

        while (crs.moveToNext()) {
            String date = crs.getString(crs.getColumnIndex("date"));
            datesData.add(date);
        }
    }

    //TODO
    public void sortData(ArrayList<String> datesArray, ArrayList<String> dataArray) {
        int min;
        String smin;
        String sj;

        // Selection Sort
        for (int i = 0; i < datesArray.size(); ++i) {
            min = i;
            smin = datesArray.get(min).substring(4, 6);

            for (int j = i + 1; j < datesArray.size(); ++j) {
                sj = datesArray.get(j).substring(4, 6);

                if (Integer.parseInt(sj) < Integer.parseInt(smin)) {
                    min = j;
                    smin = datesArray.get(min).substring(4, 6);
                }
            }
            // Swap
            String dateTemp = datesArray.get(i);
            String dataTemp = dataArray.get(i);
            datesArray.set(i, datesArray.get(min));
            dataArray.set(i, dataArray.get(min));
            datesArray.set(min, dateTemp);
            dataArray.set(min, dataTemp);
        }
    }

    public void refreshData() {
        DatabaseToArraylist();
        DatesToArraylist();
        sortData(datesData, userData);
        adapter.clear();
        adapter.addAll(userData);
    }

}