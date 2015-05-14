package elevator1;

import java.awt.Color;
import java.awt.Graphics;

public class lift {
	int id=0;
	int current_floor=0;
	int next_floor=-1;
	int status=0; //o for halt 1 for moving up 2 for moving down
	int x;
	int y=577;
	int count=0;//total request which the lift currently has
	int dist;// calculate dist from requested floor
	int totreq=20;
	int counter=0;
	int mem=0;
	int currfl=0;
	boolean door_open=false;
	boolean door_close=false;
	boolean liftmove=true;
	
	boolean enablebutt=false;//enable people entre and leaving
	int overloading=5; 
	int people_count=0;
	//int tot_people_;
	int wid=0;
	boolean takerequest=false;
	boolean statusup=false;
	int[] reqarr=new int[totreq]; //keeping floor requests 
	
	
	public void move_lift(){
		int first=this.reqarr[0];
		if(first!=-1){
		int floor_y=577-first*63;
		//System.out.println("floor_y is "+floor_y);
		
		// this lift is below the floor
		if(floor_y<this.y)
		{
			this.y=this.y-1;
			this.status=1;
			
		}
		//this lift is up the floor
		if(floor_y>this.y)
		{
			this.y=this.y+1;
			this.status=2;	
			
		}	
		
		if(this.y==floor_y)
			{
				
				this.current_floor=this.reqarr[0];
				//System.out.println("floor"+this.current_floor);
				if(this.counter<100)
				{
					this.takerequest=true;//can take request from lift panel
					this.enablebutt=true;//now poeple cannot leave or enter
					this.door_open=true;
					this.door_close=false;
					this.counter++;
					if(this.wid<32)
					{
						this.wid=this.wid+1;
					}
				}
				if(this.counter<200 && this.counter>=100)
				{
					this.takerequest=true;//can take request from lift panel
					this.enablebutt=true;//now poeple can leave or enter
					this.door_open=false;
					this.door_close=true;
					this.counter++;
					if(this.wid>0)
					{
						this.wid--;
					}
				}
				
			//shift array to the left
				if(this.counter==200)
				{
					for(int i=0;i<this.count-1;i++)
					{
						this.reqarr[i]=this.reqarr[i+1];
					}
					this.reqarr[this.count-1]=-1;
					this.count--;
					this.door_open=false;
					this.door_close=false;
					this.takerequest=false;//now cannot take request from lift panel
					this.enablebutt=false;//now poeple cannot leave or enter
					this.counter=0;
				}
			}
		}//if closed
	}//function closed
	
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.drawRect(x, y,63,63);
		g.setColor(Color.gray);
		g.fillRect(x, y, 63, 63);
		g.setColor(Color.black);
		g.drawString(Integer.toString(id+1),x+30,y+40);
		
		
	}

	
	public int checkstatus() 
	{
		// TODO Auto-generated method stub
		if(this.count==0) 
			{
			//this.takerequest=true;
			this.status=0;
			
			 return(this.count);
			}
		else 
			{
				return(5);
			}
	}

}
