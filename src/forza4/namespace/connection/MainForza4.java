package forza4.namespace.connection;

//bisogna creare activity per login tipo indovina il numero e passare i nomi
//che devono essere prova1 e prova2

import java.util.Timer;
import java.util.TimerTask;

import forza4.namespace.CheckWin;
import forza4.namespace.Griglia;
import forza4.namespace.InputMatr;
import forza4.namespace.Pedina;
import forza4.namespace.PrintG;
import forza4.namespace.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainForza4 extends Activity implements MessageReceiver{

	private static final int SHOW_TOAST = 0;
	ConnectionManager connection;
	
	enum Stato{
		WAIT_FOR_START,WAIT_FOR_START_ACK,
		WAIT_FOR_SELECT,USER_SELECTING}
	
	Stato statoCorrente;
	Handler handler;
	Button b;
	Timer timer;
	
	//comunica colonna che altro player ha selezionato
	private String selectedCol;
	
	
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        griglia = (FrameLayout)findViewById(R.id.frameLayout1);
        
        //***********souni****************
        Tock=MediaPlayer.create(MainForza4.this, R.raw.tok);
        vittoria=MediaPlayer.create(MainForza4.this, R.raw.win);
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
        tabella=new Griglia(MainForza4.this, step*6, step*7);
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
		
        //***************************da passare da login*****************
        String nomeMio,nomeAvversario;
		nomeMio= getIntent().getExtras().getString("TextUtent");
		nomeAvversario= getIntent().getExtras().getString("TextAvvers");
		//^^^^^^^*******************************************************************
		
		//*******************CONNESSIONE*****************************************************
		connection = new ConnectionManager(nomeMio, nomeAvversario, this);
		timer = new Timer();
		TimerTask sendStart = new TimerTask() {
			
			@Override
		public void run() {
				
			if (statoCorrente==Stato.WAIT_FOR_START_ACK){
				connection.send("START");	
			}else{
				Log.d("ATTENZIONE","Sending START but the state is "+ statoCorrente);
				}
			}
		};
		//decido chi comincia
		if (nomeAvversario.hashCode()<nomeMio.hashCode()){
		//inizio per primo
			gio=true;
			Toast.makeText(MainForza4.this, "Sei giocatore 1", Toast.LENGTH_SHORT).show();
			timer.schedule(sendStart, 1000L,5000L);
			statoCorrente=Stato.WAIT_FOR_START_ACK;
		}else{
		//inizia avversario e io aspetto il pacchetto
			gio=false;
			Toast.makeText(MainForza4.this, "Sei giocatore 2", Toast.LENGTH_SHORT).show();
			statoCorrente=Stato.WAIT_FOR_START;
		}
		//creo handler 
		handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg){
			switch(msg.what){
				case MainForza4.SHOW_TOAST:
					Toast.makeText(MainForza4.this, msg.getData().getString("toast"), Toast.LENGTH_LONG).show();
					break;
				default:
					super.handleMessage(msg);
					}
				}
			};
			//************************************************************************************************
				
			if (statoCorrente==Stato.USER_SELECTING){
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
	            				//puo' dare problemi
	                			//gio=!gio;
	            				
	            				statoCorrente=Stato.WAIT_FOR_SELECT;
	            				
	            				//invia colonna selezionata*****
	            				connection.send("SELECTED:"+Integer.toString(col));
	            				//*******
	            				
	            				griglia.removeAllViews();
	            				PrintG.printG(matr, offsetX, offsetY, diam, divx, divy, MainForza4.this, griglia);
	            				griglia.addView(tabella);
	            				win=CheckWin.checkWin(matr, MainForza4.this,win);
	            			}
	               			
	               			if(win)vittoria.start();
	        			}
	        			else
	        				Toast.makeText(MainForza4.this, "Hanno vinto", Toast.LENGTH_SHORT).show();
	        		}
	        			break;
	        		}
	        		return true;
	   			}
			});
		}else
			Toast.makeText(MainForza4.this,"Attendi", Toast.LENGTH_SHORT).show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//gestisce stati sistema
	public void receiveMessage(String msg) {
		
		if(msg.equals("START")){
			if(statoCorrente==Stato.WAIT_FOR_START){
				//mando ACK
				connection.send("STARTACK");
				Message osmsg = handler.obtainMessage(MainForza4.SHOW_TOAST);
				Bundle b = new Bundle();
				b.putString("toast", "Aspetta");
				osmsg.setData(b);
				handler.sendMessage(osmsg);
				statoCorrente=Stato.WAIT_FOR_SELECT;
			}else{
				Log.e("ATTENZIONE","Ricevuto START ma lo stato e' "+statoCorrente);
			}
		}
		//ricevo start_ack e aspetto che avversario gioca
		else if(msg.equals("STARTACK")){
			if(statoCorrente==Stato.WAIT_FOR_START_ACK){
				
				timer.cancel();
				
				Log.w("ATTENZIONE", "Timer cancel");
				
				statoCorrente=Stato.USER_SELECTING;
				Toast.makeText(MainForza4.this, "Tocca a te", Toast.LENGTH_SHORT).show();
				
			}else{
				Log.e("ATTENZIONE","Ricevuto STARTACK ma lo stato e' "+statoCorrente);
			}
		}else if(msg.startsWith("SELECTED")){
			if (statoCorrente==Stato.WAIT_FOR_SELECT){
				selectedCol=msg.split(":")[1];
				Message osmsg = handler.obtainMessage(MainForza4.SHOW_TOAST);
				Bundle b = new Bundle();
				//controlla i decode
				matr=InputMatr.inputMatr(matr,Integer.decode(selectedCol),gio);
    			Tock.start();
    			griglia.removeAllViews();
				PrintG.printG(matr, offsetX, offsetY, diam, divx, divy, MainForza4.this, griglia);
				griglia.addView(tabella);
				win=CheckWin.checkWin(matr, MainForza4.this,win);
    			
				b.putString("toast", "Tocca a te");
				osmsg.setData(b);
				handler.sendMessage(osmsg);
				statoCorrente=Stato.USER_SELECTING;
			}else{
				Log.e("ATTENZIONE","Ricevuto SELECTED ma lo stato e' :"+statoCorrente);
			}
		}//else if(statoCorrente==Stato.USER_SELECTING){
		 //	String bet = b.getText().toString();
		//	connection.send("SELECTED:"+Integer.toString(col));
		//	statoCorrente=Stato.WAIT_FOR_SELECT;
		//}
	}

}
