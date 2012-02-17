package forza4.namespace;

import android.content.Context;
import android.widget.FrameLayout;

public class PrintG {
	public static Pedina ped;
	
	public static void printG (int matr[][], int offsetX, int offsetY, int diam, int div, Context context, FrameLayout fl)
	{
		for(int i=0; i<6; i++)
		{
			for(int k=0; k<7; k++)
			{
				if(matr[i][k]!=0)
				{
					ped=new Pedina(context, offsetX+k*(diam+div), offsetY+i*(diam+div), diam, matr[i][k]);
					fl.addView(ped);
				}
			}
		}
	}

}
