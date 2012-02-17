package forza4.namespace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class Griglia extends View{

	private Bitmap tabella=null;
	
	public Griglia(Context context,int newWidth,int newHeigth) {
		super(context);
		int temp=1;
		if(newWidth<newHeigth)temp=newWidth;
		else if(newWidth>=newHeigth)temp=newHeigth;
		
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		tabella=Pedina.getResizeBitmap(BitmapFactory.decodeResource(context.getResources(),
				R.drawable.griglia),temp , temp);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(tabella,0,0,null);
	}
}
