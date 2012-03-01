package forza4.namespace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		Button start = (Button) findViewById(R.id.buttonStart);
		Button exit = (Button) findViewById(R.id.buttonExit);
		start.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				Intent intent = new Intent(HomeActivity.this, Login.class); // creare la schermata di login e avviarla con l'intent
				startActivity(intent);
			}
		});
		exit.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				finish();
	            System.exit(0);
			}
		});
		
	}

}
