package forza4.namespace;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

public class Pedina extends View{

	private Bitmap ped=null;
	private float x,y;
	public Pedina(Context context,float POSx,float POSy, float raggio, int gio) {
		super(context);
		Bitmap img=null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		x=POSx;
		y=POSy;
		if(gio==1)
			img = BitmapFactory.decodeResource(context.getResources(),R.drawable.giallov2);
		else if(gio==2)
			img = BitmapFactory.decodeResource(context.getResources(), R.drawable.rossov2);
		ped=getResizeBitmap(img, raggio, raggio);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(ped,x,y,null);
	}
	//per ridimensionamento bitmap pedina
	public static Bitmap getResizeBitmap(Bitmap img,float newHeigth,float newWidth){
		int width = img.getWidth();
		int heigth = img.getHeight();
		float scaleWidth = ((float)newWidth/width);
		float scaleHeigth = ((float)newHeigth/heigth);
		//crea matrice per manipolazione
		Matrix matrix = new Matrix();
		//resize bitmap
		matrix.postScale(scaleWidth, scaleHeigth);
		//ricrea la nuova bitmap
		Bitmap newBitmap = Bitmap.createBitmap(img,0, 0,width,heigth,matrix,true);
		return newBitmap;
	}
}
