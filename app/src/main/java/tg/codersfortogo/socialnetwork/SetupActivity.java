package tg.codersfortogo.socialnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText Username, Fullname,Countryname;
    private Button SaveInformation;
    private CircleImageView ProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Username = (EditText) findViewById(R.id.setup_username);
        Fullname = (EditText) findViewById(R.id.setup_full_name);
        Countryname = (EditText) findViewById(R.id.setup_country);
        SaveInformation = (Button) findViewById(R.id.setup_button);
        ProfileImage = (CircleImageView) findViewById(R.id.setup_profile_image);

}
}