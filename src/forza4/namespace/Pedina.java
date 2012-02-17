package forza4.namespace;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class Pedina extends View{

	//private ShapeDrawable palla;
	//private Bitmap img=null;
	private Bitmap ped=null;
	private int x,y;
	public Pedina(Context context,int POSx,int POSy, int raggio, int gio) {
		super(context);
		Bitmap img=null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		x=POSx;
		y=POSy;
		//palla= new ShapeDrawable(new OvalShape());
		if(gio==1)
			img = BitmapFactory.decodeResource(context.getResources(),R.drawable.giallo);
			//palla.getPaint().setColor(Color.GREEN);
		else if(gio==2)
			img = BitmapFactory.decodeResource(context.getResources(), R.drawable.rosso);
			//palla.getPaint().setColor(Color.YELLOW);
		//palla.setBounds(POSx, POSy, POSx+raggio, POSy+raggio);
		ped=getResizeBitmap(img, raggio, raggio);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//palla.draw(canvas);
		canvas.drawBitmap(ped,x,y,null);
	}
	//per ridimensionamento bitmap pedina
	public static Bitmap getResizeBitmap(Bitmap img,int newHeigth,int newWidth){
		int width = img.getWidth();
		int heigth = img.getHeight();
		float scaleWidth = ((float)newWidth/width);
		float scaleHeigth = ((float)newHeigth/heigth);
		//crea matrice per manipolazione
		Matrix matrix = new Matrix();
		//resize bitmap
		matrix.postScale(scaleWidth, scaleHeigth);
		//ricrea la nuova bitmap
		Bitmap newBitmap = Bitmap.createBitmap(img,0, 0,width,heigth,matrix,false);
		return newBitmap;
	}
}
