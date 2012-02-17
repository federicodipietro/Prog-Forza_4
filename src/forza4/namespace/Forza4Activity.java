package forza4.namespace;


import java.util.jar.Attributes;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Forza4Activity extends Activity {
    /** Called when the activity is first created. */
	Pedina ped;
	Griglia tabella;
	int winX, winY, y, col, touchx, raggio;
	int matr[][]=new int [6][7];
	FrameLayout griglia;
	boolean gio=true;
	boolean win=false;
	//per suoni
	MediaPlayer Tock,lancio,vittoria;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        griglia = (FrameLayout)findViewById(R.id.frameLayout1);
        
        //***********souni****************
        Tock=MediaPlayer.create(Forza4Activity.this, R.raw.tok);
        vittoria=MediaPlayer.create(Forza4Activity.this, R.raw.win);
       // lancio=MediaPlayer.create(Forza4Activity.this, R.raw.lancio);
        //*********************************
       
        
        
        winX = getWindowManager().getDefaultDisplay().getWidth();
        winY = getWindowManager().getDefaultDisplay().getHeight();
        
        //****disegna tabella*********
        tabella=new Griglia(Forza4Activity.this, winX, winY);
        griglia.addView(tabella);
         //**************************** 
        
        
        if(winX/7>winY/6)
        	raggio=(int)winY/6;
        else
        	raggio=winX/7;
        //Log.d("raggio", Integer.toString(raggio));
        griglia.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event)
   			{
        		int eventaction = event.getAction();
        		switch (eventaction)
        		{
        		case MotionEvent.ACTION_UP:
        		{
        			if(!win)
        			{
        				touchx=(int)event.getX();
            			//Log.d("touchx", Integer.toString(touchx));
            			col=InputMatr.getCol(touchx, raggio);
            			
            			if(matr[0][col]==0)
            			{
            				matr=InputMatr.inputMatr(matr,col,gio);
                			Tock.start();
            				gio=!gio;
            				PrintG.printG(matr, raggio, Forza4Activity.this, griglia);
            				win=CheckWin.checkWin(matr, Forza4Activity.this,win);
            			}
            				
            			//Log.d("Colonna sel.", Integer.toString(col));
            			
            			
               			
               			
               			//tabella=new Griglia(Forza4Activity.this, winX, winY);
               			//griglia.addView(tabella);
               			
               			
               			if(win)vittoria.start();
               			//Tock.stop();
        			}
        			else
        				Toast.makeText(Forza4Activity.this, "Hanno vinto", Toast.LENGTH_SHORT).show();
        		}
        			break;
        		}
        		return true;
        		
   			}
       });
        
       
       
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Tock.release();
		vittoria.release();
		matr=null;
		gio=true;
		win=false;
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	
	}
}