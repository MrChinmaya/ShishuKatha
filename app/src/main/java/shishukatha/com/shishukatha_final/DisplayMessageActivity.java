package shishukatha.com.shishukatha_final;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class DisplayMessageActivity extends AppCompatActivity {

    public Button submit;
    EditText Name;
    EditText Descp;
    Spinner spinner;
    Spinner spinner2;
    String name,age,childrennumber,details,email,mail,userid,id,googlename,profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android.support.v7.app.ActionBar AB=getSupportActionBar();//To remove title bar
//        AB.hide();// remove title
        setContentView(R.layout.activity_display_message);

        Name =findViewById(R.id.editText);
        Descp=findViewById(R.id.editText3);
        submit =findViewById(R.id.button3);
        spinner =findViewById(R.id.numberofchildrenspinner);
        spinner2 =findViewById(R.id.agespinner);

        googlename= getIntent().getStringExtra("Fetchedname");
        mail= getIntent().getStringExtra("Fetchedmail");
        id= getIntent().getStringExtra("userid");
        profile_pic=getIntent().getStringExtra("profile_pic");

        System.out.println("DisplayMessage"+googlename);
        System.out.println("DisplayMessage"+mail);
        System.out.println("DisplayMessage"+id);
        System.out.println("DisplayMessage"+profile_pic);

        Name.setText(googlename);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Number_of_Childrens, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Age, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                {
                    if(!Name.getText().toString().isEmpty() && !Descp.getText().toString().isEmpty()){
                       // final Intent intent = new Intent(DisplayMessageActivity.this,Menu.class);
                        final Intent intent = new Intent(DisplayMessageActivity.this,StoryMenu.class);
                        intent.putExtra("userid",id);
                        intent.putExtra("Fetchedname", name);
                        intent.putExtra("Fetchedmail", mail);
                        intent.putExtra("profile_pic",profile_pic);
                        register(view);
                        startActivity(intent);
                    }
                    else{
                        //Toast.makeText(DisplayMessageActivity.this,"Please fill any empty fields",Toast.LENGTH_SHORT).show();
                        Toasty.error(getApplicationContext(), "Please fill any empty fields", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }

        });
    }

    public void register(View view  ){
        name=Name.getText().toString();
        details=Descp.getText().toString();
        age= spinner.getSelectedItem().toString();
        childrennumber=spinner2.getSelectedItem().toString();
        email=mail.toString();
        userid=id.toString();
        Background_details backgroundDetails=new Background_details(this);
        backgroundDetails.execute(name,age,childrennumber,details,email,userid);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(DisplayMessageActivity.this, MainActivity.class));
        finish();
    }
}