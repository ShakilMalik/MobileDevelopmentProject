package csis3175.mobiledevelopmentproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class AddTransaction extends AppCompatActivity {
    DatabaseHelper dbh;
    int GalleryImage = 0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        dbh = new DatabaseHelper(this);

        long date = System.currentTimeMillis();
        EditText dateText = (EditText)findViewById(R.id.dateText);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String dateString = dateFormat.format(date);
        dateText.setText(dateString);

        final EditText expenseAmount = (EditText)findViewById(R.id.amountText);
        final EditText expenseDate = (EditText)findViewById(R.id.dateText);
        final EditText expenseNote = (EditText)findViewById(R.id.noteText);
        final ImageButton expensePhoto = (ImageButton)findViewById(R.id.addPhotoButton);
        final Spinner expenseCategory = (Spinner)findViewById(R.id.categorySpinner);
        final Button addExpenseBtn = (Button)findViewById(R.id.addExpense);

        addExpenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = expenseDate.getText().toString();
                String note = expenseNote.getText().toString();
                String category = expenseCategory.getSelectedItem().toString();
                String amount = expenseAmount.getText().toString();

                if (!amount.matches("") && !note.matches("")) { //TODO: Trim the variables
                    dbh.addRec(date, category, amount, note);

                    expenseAmount.setText("");
                    expenseNote.setText("");
                    Toast.makeText(AddTransaction.this, "Values Added", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddTransaction.this, HomeScreen.class));

                } else {
                    Toast.makeText(AddTransaction.this, "The Values Amount and Note Can Not Be Empty ", Toast.LENGTH_LONG).show();
                }
            }
        });

        expensePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, GalleryImage);

                }
                catch (Exception e) {}

            }
        });
    }
}
