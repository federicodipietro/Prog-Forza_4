package forza4.namespace;

import android.content.Context;
import android.widget.Toast;

public class CheckWin{
	//static boolean win=false;
	public static boolean checkWin(int matr[][], Context context,boolean win){
	int temp;
				
	for(int j=0;j<6;j++)//j=coordy
	{   if(win)break;
		for (int i=0;i<7;i++)//i= coordX
		{
			temp=matr[j][i];
			if (temp != 0)
			{	
				//orizontali
			if (i<4)
				{
						if(temp==matr[j][i+1] && temp==matr[j][i+2] 
								&& temp==matr[j][i+3])
					{
							win=ChihaVinto(temp, context,win);
							//if(!win)System.out.println("orizzontale da sx verso dx");
							break;
					}	
			}else if (i>=4){
						if(temp==matr[j][i-1] && temp==matr[j][i-2] &&
								temp==matr[j][i-3])
						{
							win=ChihaVinto(temp, context,win);
							//if(!win)System.out.println("orizzontale da dx verso sx");
							break;
						}
				}
				//verticali
			if(j<=2){
				
					if (temp==matr[j+1][i] && temp==matr[j+2][i] && temp==matr[j+3][i]){
						win=ChihaVinto(temp, context,win);
					if(win){
						//System.out.println("verticale");
					}
					}
				}
				
				//diagonale
				//basso sx
			if (j>2 && i<=3){
					if(temp==matr[j-1][i+1] && 
					   temp==matr[j-2][i+2] &&
					   temp==matr[j-3][i+3]){
						win=ChihaVinto(temp, context,win);
						if (win)break;
					}
				}//alto sx
				else if(j<=2 && i<=3){
					if (temp==matr[j+1][i+1] &&
						temp==matr[j+2][i+2] &&
						temp==matr[j+3][i+3]){
						win=ChihaVinto(temp, context,win);
						if(win)break;
					}
				}//alto dx
				else if(j<=2 && i>=3){
					if (temp==matr[j+1][i-1] && 
						temp==matr[j+2][i-2] &&
						temp==matr[j+3][i-3]){
						win=ChihaVinto(temp, context,win);
						if(win)break;
					}
				}//basso dx
				else if(j>2 && i>=3){
					if (temp==matr[j-1][i-1] &&
						temp==matr[j-2][i-2] &&
						temp==matr[j-3][i-3]){
						win=ChihaVinto(temp, context,win);
						if(win)break;
					}
				}
			}
		}
	}
	return win;
}
public static boolean ChihaVinto(int player, Context context,boolean win){
	if (player==1){
		Toast.makeText(context, "Giocatore 1 Vince", Toast.LENGTH_LONG).show();
		return true;
	}else if (player==2){
		Toast.makeText(context, "Giocatore 2 Vince", Toast.LENGTH_LONG).show();
		return true;
	}else return false;
	
}
}