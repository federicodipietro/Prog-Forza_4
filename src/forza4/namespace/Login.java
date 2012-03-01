package forza4.namespace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		EditText username = (EditText)findViewById(R.id.username);
		
		Button btn = (Button)findViewById(R.id.okbutton);
		btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this, Forza4Activity.class);
				startActivity(intent);
			}
		});
	}

}
