package forza4.namespace;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Forza4Activity extends Activity {
    /** Called when the activity is first created. */
	Pedina ped;
	Griglia tabella;
	int winX, winY, y, col, touchx, step;
	float divx, divy, diam, offsetX, offsetY;
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
        Log.d("dimensioni display (xy)", Integer.toString(winX)+"  "+Integer.toString(winY));
        if(winX/7>winY/6)
        	step=(int)winY/6;
        else
        	step=(int)winX/7;
        
        //****disegna tabella*********
        tabella=new Griglia(Forza4Activity.this, step*6, step*7);
        griglia.addView(tabella);
         //**************************** 
        
        //rapporto tra largh pedina e larghezza griglia*7
        Double temp =0.79277108433733*step;
        diam=temp.floatValue();
        
        //rapporto distanza bordo e primo buco e largh griglia*7
        temp=0.320448192771084*step;
        offsetX=temp.floatValue();
        
        //rapporto distanza bordo e primo buco e altezza griglia*6
        temp=0.30167597765363*step;
        offsetY=temp.floatValue();
        
        //rapporto tra distanza tra 2 buchi e largh griglia
        temp=0.13493975903615*step;
        divx = temp.floatValue();
        
        //rapporto tra distanza tra 2 buchi e altezza griglia
        temp=0.13128491620112*step;
        divy = temp.floatValue();
        
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
            			col=InputMatr.getCol(touchx, step);
            			
            			if(matr[0][col]==0)
            			{
            				matr=InputMatr.inputMatr(matr,col,gio);
                			Tock.start();
            				gio=!gio;
            				griglia.removeAllViews();
            				PrintG.printG(matr, offsetX, offsetY, diam, divx, divy, Forza4Activity.this, griglia);
            				griglia.addView(tabella);
            				win=CheckWin.checkWin(matr, Forza4Activity.this,win);
            			}
               			
               			if(win)vittoria.start();
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