package forza4.namespace;

import android.content.Context;
import android.widget.FrameLayout;

public class PrintG {
	public static Pedina ped;
	
	public static void printG (int matr[][], int raggio, Context context, FrameLayout fl)
	{
		for(int i=0; i<6; i++)
		{
			for(int k=0; k<7; k++)
			{
				if(matr[i][k]!=0)
				{
					ped=new Pedina(context, k*raggio, i*raggio, raggio, matr[i][k]);
					fl.addView(ped);
				}
			}
		}
	}

}
