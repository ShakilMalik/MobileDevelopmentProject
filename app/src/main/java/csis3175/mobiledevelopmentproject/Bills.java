package csis3175.mobiledevelopmentproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Bills extends AppCompatActivity {
    DatabaseHelper dbh;
    ArrayList<String> billsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        dbh = new DatabaseHelper(this);
        ListView lv = (ListView)findViewById(R.id.listView);
        Button btnAB = (Button)findViewById(R.id.btnAddBill);

        Cursor r = dbh.viewRec("Jesse");
        StringBuilder str = new StringBuilder();
        if(r.getCount()>0){
            while(r.moveToNext()){
                str.append(r.getString(1) + "\n");
                str.append(r.getString(2) + "/Month \n");
                str.append(r.getString(3) + " of each month \n");
            }
            billsList.add(str.toString());
        }else{
        Toast.makeText(Bills.this,"No record to display",Toast.LENGTH_LONG).show();
    }
    //putting arraylist into listview
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,billsList);
        lv.setAdapter(aa);

        btnAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Bills.this, AddTransaction.class));
            }
        });
    }
}
