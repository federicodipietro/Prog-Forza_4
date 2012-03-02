package forza4.namespace;

import forza4.namespace.connection.MainForza4;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
	EditText user1;
	EditText user2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		user1= (EditText)findViewById(R.id.username);
		user2= (EditText)findViewById(R.id.username2);
		
		Button btn = (Button)findViewById(R.id.okbutton);
		btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this, MainForza4.class);
				intent.putExtra("user1", user1.getText().toString());
				intent.putExtra("user2", user2.getText().toString());
				startActivity(intent);
				
			}
		});
	}

}
