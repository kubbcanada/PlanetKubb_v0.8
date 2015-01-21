package ca.longship.planetkubb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity implements OnClickListener {

	EditText loginName, loginPassword;
	TextView errorMessage;
	Button bLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginwindow);
		loginName = (EditText) findViewById(R.id.etLoginName);
		loginPassword = (EditText) findViewById(R.id.etLoginPassword);
		bLogin = (Button) findViewById(R.id.bLogin);
		errorMessage = (TextView) findViewById(R.id.tvError);
		// errorMessage.setVisibility(View.INVISIBLE);
		bLogin.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (loginName.getText().toString() == " ") {
			errorMessage.setText("You must enter a username");
			errorMessage.setVisibility(View.VISIBLE);
		} else if (loginPassword.getText().toString() == " ") {
			errorMessage.setText("You must enter a password");
			errorMessage.setVisibility(View.VISIBLE);
		} else {
			trytologin();
		}
	}

	private String trytologin() {
		CustomHttpClient test = new CustomHttpClient();
		String returned = "uh oh";
		try {
			returned = test.getInternetData(loginName.getText().toString(),
					loginPassword.getText().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//errorMessage.setText("I guess it worked");
		errorMessage.setVisibility(View.VISIBLE);

		return null;
		// TODO Auto-generated method stub
		// Create a new HttpClient and Post Header
	}
}
