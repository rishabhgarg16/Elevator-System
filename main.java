package elevator1;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class main extends Applet implements Runnable,ActionListener,MouseListener{

	private Image i;
	private Graphics doubleG;
	
	int total_floors=10;
	int total_lifts=4;
	
	Button[] btup=new Button[9];
	Button[] btdn=new Button[9];
	
	Button[] lift1_bt=new Button[total_floors];
	Button[] lift2_bt=new Button[total_floors];
	Button[] lift3_bt=new Button[total_floors];
	Button[] lift4_bt=new Button[total_floors];
	
	Button lp1_enter=new Button();
	Button lp1_leave=new Button();
	
	Button lp2_enter=new Button();
	Button lp2_leave=new Button();
	
	Button lp3_enter=new Button();
	Button lp3_leave=new Button();
	
	Button lp4_enter=new Button();
	Button lp4_leave=new Button();
	
	//emergency buttons
	Button l1_e=new Button();
	Button l2_e=new Button();
	Button l3_e=new Button();
	Button l4_e=new Button();
	
	lift[] lift=new lift[total_lifts];
	
	floor[] floor=new floor[total_floors];
	
	public void init(){
		addMouseListener(this);	
		
		setSize(1366,650);
		for(int i=0;i<9;i++)
		{
			btup[i]=new Button("up");
			add(btup[i]);
			btup[i].addActionListener(this);
			
			btdn[i]=new Button("down");
			add(btdn[i]);
			btdn[i].addActionListener(this);
		}
		for(int i=0;i<total_floors;i++)
		{
			lift1_bt[i]=new Button("floor"+i);
			add(lift1_bt[i]);
			lift1_bt[i].addActionListener(this);
		}
		
		for(int i=0;i<total_floors;i++)
		{
			lift2_bt[i]=new Button("floor"+i);
			add(lift2_bt[i]);
			lift2_bt[i].addActionListener(this);
		}
		for(int i=0;i<total_floors;i++)
		{
			lift3_bt[i]=new Button("floor"+i);
			add(lift3_bt[i]);
			lift3_bt[i].addActionListener(this);
		}
		for(int i=0;i<total_floors;i++)
		{
			lift4_bt[i]=new Button("floor"+i);
			add(lift4_bt[i]);
			lift4_bt[i].addActionListener(this);
		}
		lp1_enter=new Button("enter people");
		add(lp1_enter);
		lp1_enter.addActionListener(this);
	
		lp2_enter=new Button("enter people");
		add(lp2_enter);
		lp2_enter.addActionListener(this);
		
		lp3_enter=new Button("enter people");
		add(lp3_enter);
		lp3_enter.addActionListener(this);
		
		lp4_enter=new Button("enter people");
		add(lp4_enter);
		lp4_enter.addActionListener(this);
	
		lp1_leave=new Button("leave people");
		add(lp1_leave);
		lp1_leave.addActionListener(this);
		
		lp2_leave=new Button("leave people");
		add(lp2_leave);
		lp2_leave.addActionListener(this);
	
		lp3_leave=new Button("leave people");
		add(lp3_leave);
		lp3_leave.addActionListener(this);
	
		lp4_leave=new Button("leave people");
		add(lp4_leave);
		lp4_leave.addActionListener(this);
	
		//emergency buttons
		l1_e=new Button("Emergency");
		add(l1_e);
		l1_e.addActionListener(this);
		
		l2_e=new Button("Emergency");
		add(l2_e);
		l2_e.addActionListener(this);
		
		l3_e=new Button("Emergency");
		add(l3_e);
		l3_e.addActionListener(this);
		
		l4_e=new Button("Emergency");
		add(l4_e);
		l4_e.addActionListener(this);
		
	}
	
	public main(){
		for(int i=0;i<total_lifts;i++)
		{
			lift[i]=new lift();
			for(int j=0;j<lift[i].totreq;j++)
			{
				lift[i].reqarr[j]=-1;
			}
			lift[i].x=200+100*i;
			lift[i].id=i;
		}
		for(int i=0;i<total_floors;i++)
		{
			floor[i]=new floor();
			floor[i].fl_id=i;
		}
		Thread thread=new Thread(this);
		thread.start();
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
		
		for(int i=0;i<total_lifts;i++)
		{
			lift[i].checkstatus();
		}
		repaint();
		for(int i=0;i<total_lifts;i++)
		{
			if(lift[i].people_count<=lift[i].overloading)	
			{
				lift[i].move_lift();
			}
			
		}
		
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	public void update(Graphics g) {
		if(i == null){
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();			
		}		
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);		
		g.drawImage(i, 0, 0, this);
	}
	
	public void paint(Graphics g){
		Font f=new Font("TimesRoman", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.black);
		for(int i=0;i<9;i++)
		{
			btup[i].setBackground(Color.red);
			btup[i].setLocation(50,600-63*i);
			
			btdn[i].setBackground(Color.green);
			btdn[i].setLocation(100,600-63*(i+1));
		}
		
			g.setColor(Color.red);
			g.drawRect(200, 10, 63, 680);
			for(int i=0;i<total_floors+1;i++)
			{
				g.setColor(Color.blue);
				g.drawLine(200, 640-63*i,263, 640-63*i);
			}
			g.setColor(Color.red);
			g.drawRect(300, 10, 63, 680);
			for(int i=0;i<total_floors+1;i++)
			{
				g.setColor(Color.blue);
				g.drawLine(300, 640-63*i,363, 640-63*i);
			}
			g.setColor(Color.red);
			g.drawRect(400, 10, 63, 680);
			for(int i=0;i<total_floors+1;i++)
			{
				g.setColor(Color.blue);
				g.drawLine(400, 640-63*i,463, 640-63*i);
			}
			g.setColor(Color.red);
			g.drawRect(500, 10, 63, 680);
			for(int i=0;i<total_floors+1;i++)
			{
				g.setColor(Color.blue);
				g.drawLine(500, 640-63*i,563, 640-63*i);
			}
		
		for(int i=0;i<total_lifts;i++)
		{
				lift[i].paint(g);
		}
		
		for(int i=0;i<3;i++)
		{
			lift1_bt[i].setBackground(Color.yellow);
			lift1_bt[i].setLocation(720+50*i,76);
		
		}
		for(int i=3;i<6;i++)
		{
			lift1_bt[i].setBackground(Color.yellow);
			lift1_bt[i].setLocation(720+50*(i-3),106);
		
		}
		for(int i=6;i<9;i++)
		{
			lift1_bt[i].setBackground(Color.yellow);
			lift1_bt[i].setLocation(720+50*(i-6),136);
		
		}
		lift1_bt[9].setBackground(Color.yellow);
		lift1_bt[9].setLocation(772,166);
		
		g.setColor(Color.black);
		g.drawString("LIFT 1",762,226);
		
		//second lift
		for(int i=0;i<3;i++)
		{
			lift2_bt[i].setBackground(Color.yellow);
			lift2_bt[i].setLocation(1000+50*i,76);
		
		}
		for(int i=3;i<6;i++)
		{
			lift2_bt[i].setBackground(Color.yellow);
			lift2_bt[i].setLocation(1000+50*(i-3),106);
		
		}
		for(int i=6;i<9;i++)
		{
			lift2_bt[i].setBackground(Color.yellow);
			lift2_bt[i].setLocation(1000+50*(i-6),136);
		
		}
		lift2_bt[9].setBackground(Color.yellow);
		lift2_bt[9].setLocation(1050,166);
		
		g.setColor(Color.black);
		g.drawString("LIFT 2",1040,226);
		
		//third lift
		for(int i=0;i<3;i++)
		{
			lift3_bt[i].setBackground(Color.yellow);
			lift3_bt[i].setLocation(720+50*i,300);
		
		}
		for(int i=3;i<6;i++)
		{
			lift3_bt[i].setBackground(Color.yellow);
			lift3_bt[i].setLocation(720+50*(i-3),330);
		
		}
		for(int i=6;i<9;i++)
		{
			lift3_bt[i].setBackground(Color.yellow);
			lift3_bt[i].setLocation(720+50*(i-6),360);
		
		}
		lift3_bt[9].setBackground(Color.yellow);
		lift3_bt[9].setLocation(772,390);
		
		g.setColor(Color.black);
		g.drawString("LIFT 3",762,450);
		
		//foruthlift
		for(int i=0;i<3;i++)
		{
			lift4_bt[i].setBackground(Color.yellow);
			lift4_bt[i].setLocation(1000+50*i,300);
		
		}
		for(int i=3;i<6;i++)
		{
			lift4_bt[i].setBackground(Color.yellow);
			lift4_bt[i].setLocation(1000+50*(i-3),330);
		
		}
		for(int i=6;i<9;i++)
		{
			lift4_bt[i].setBackground(Color.yellow);
			lift4_bt[i].setLocation(1000+50*(i-6),360);
		
		}
		lift4_bt[9].setBackground(Color.yellow);
		lift4_bt[9].setLocation(1050,390);
		
		g.setColor(Color.black);
		g.drawString("LIFT 4",1040,450);
		
		for(int m=0;m<total_lifts;m++)
		{
			g.setColor(Color.red);
			g.fillRect(lift[m].x+32-lift[m].wid, lift[m].y, lift[m].wid, 63);
			g.fillRect(lift[m].x+32, lift[m].y, lift[m].wid, 63);
	
			g.setColor(Color.gray);
			g.fillRect(lift[m].x, lift[m].y, 32-lift[m].wid, 63);
		    g.fillRect(lift[m].x+32+lift[m].wid, lift[m].y, 32-lift[m].wid, 63);
		}
		
		//overloading buttons
		lp1_enter.setLocation(722,516);
		lp1_leave.setLocation(722,540);
			
		lp2_enter.setLocation(872,516);
		lp2_leave.setLocation(872,540);
		
		lp3_enter.setLocation(1022,516);
		lp3_leave.setLocation(1022,540);
		
		lp4_enter.setLocation(1172,516);
		lp4_leave.setLocation(1172,540);
		
			if(lift[0].people_count>lift[0].overloading)
			{
				g.setColor(Color.red);
				g.drawString("Overloading",722,500);
			}
			
			if(lift[1].people_count>lift[1].overloading)
			{
				g.setColor(Color.red);
				g.drawString("Overloading",872,500);
			}
			if(lift[2].people_count>lift[2].overloading)
			{
				g.setColor(Color.red);
				g.drawString("Overloading",1022,500);
			}
			if(lift[3].people_count>lift[3].overloading)
			{
				g.setColor(Color.red);
				g.drawString("Overloading",1172,500);
			}
		
			g.setColor(Color.blue);
			g.drawString("Total people:",612,600);
			
			
			for(int i=0;i<total_lifts;i++)
			{
			g.setColor(Color.blue);
			g.drawString(Integer.toString(lift[i].people_count),752+i*160,600);
			
			}
		
		//emergency buttons
		l1_e.setBackground(Color.red);
		l1_e.setLocation(620,117);
	
		l2_e.setBackground(Color.red);
		l2_e.setLocation(1170,117);
	
		l3_e.setBackground(Color.red);
		l3_e.setLocation(620,342);
	
		l4_e.setBackground(Color.red);
		l4_e.setLocation(1170,342);
		
		
		for(int i=0;i<total_floors;i++)
		{
			if(lift[0].y>=577-63*i&&lift[0].y<(577-63*i)+63)
			{
				lift[0].currfl=i;
				g.drawString(Integer.toString(lift[0].currfl),792 ,56);
			}
			if(lift[1].y>=577-63*i&&lift[1].y<(577-63*i)+63)
			{
				lift[1].currfl=i;
				g.drawString(Integer.toString(lift[1].currfl),1070 ,56);
			}if(lift[2].y>=577-63*i&&lift[2].y<(577-63*i)+63)
			{
				lift[2].currfl=i;
				g.drawString(Integer.toString(lift[2].currfl),792 ,280);
			}if(lift[3].y>=577-63*i&&lift[3].y<(577-63*i)+63)
			{
				lift[3].currfl=i;
				g.drawString(Integer.toString(lift[3].currfl),1070,280);
			}			
		}
		
		g.setColor(Color.black);
		g.drawRect(785,35,25,25);
		g.drawRect(1063,35,25,25);
		g.drawRect(785,260, 25, 25);
		g.drawRect(1063,260,25,25);
		
		
}
	
	//int lift_allot_no;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("rishabh");
		for(int i=0;i<=9;i++){
		
			if(i<9)
			{		
			if(e.getSource()==btup[i])
			{
				System.out.println("button pressed is "+i);
				
				System.out.println();
				System.out.println();
				int min=1000;
				int min_dist=1000;
				int lift_id_alloted=0;
			
				int lift_y=1000;
				int first_element=0;
				int flag=0;
				int tot_dist=0;
				int min_tot_dist=1500;
				int k=0;
				for(int j=0;j<total_lifts;j++)
				{
					
					//check for all halt lifts
					if(lift[j].checkstatus()==0)
					{
						//System.out.println("hi");
						
						lift[j].dist=Math.abs(lift[j].y-(577-i*63));
						//System.out.println("i "+i);
						//System.out.println("dist is "+j+" "+ lift[j].dist);
						
						if(min>lift[j].dist)
						{
							
							min=lift[j].dist;
							floor[i].lift_allot_no=j;
						}
					}
					
					if(lift[j].checkstatus()!=0&&lift[j].status==1)
					{
						//System.out.println("run up");
						
						//check for all moving up lifts below the floor
						if(lift[j].y>=577-i*63)
						{
							lift[j].dist=Math.abs(lift[j].y-(577-i*63));
							//System.out.println("lift no "+j+ "dist is "+lift[j].dist);
							
							if(min>lift[j].dist)
							{
								min=lift[j].dist;
								//System.out.println("min is "+min+ "lift no is "+ j);
								
								floor[i].lift_allot_no=j;
							}
						}
						
						
						//check for lifts that are up the floor and moving up
						
						else if(lift[j].y<(577-63*i))
						{
							//take the first element on the required array.
								
							//System.out.println("run up above florr");
							if(lift[j].count>0)
								{
									 first_element=lift[j].reqarr[0];
								}
								tot_dist+=Math.abs((577-63*first_element)-lift[j].y);
								for( k=0;k<lift[j].count-1;k++)
								{
									
									
									if(lift[j].reqarr[k+1]!=-1&&lift[j].reqarr[k+1]>i)
									{
										tot_dist=tot_dist+Math.abs(k-i)*63;
										lift[j].mem=k;
										flag=1;
										break;
									}
									else 
									{
										tot_dist+=Math.abs(lift[j].reqarr[k]-lift[j].reqarr[k+1])*63;
									}
								}
								// to calculate the distance when lift halting station is above the floor.
								
								if (flag==0)
								{
									tot_dist=tot_dist+Math.abs((lift[j].count-1)-i)*63;
									lift[j].mem=k;
								}
						
								if(min_tot_dist>tot_dist)
								{
									min_tot_dist=tot_dist;
									
									lift_id_alloted=j;
								}
								
								
								
							}
						}
								
					//check for ALL moving down lifts
						 flag=0;
						 tot_dist=0;
						 
				
					if(lift[j].checkstatus()!=0&&lift[j].status==2)
					{
				
						//System.out.println("moving down");
						
						//System.out.println("lift no is "+j);
						
						if(lift[j].count>0)
						{
							 first_element=lift[j].reqarr[0];
						}
						tot_dist+=Math.abs(577-63*first_element-lift[j].y);
						for( k=0;k<lift[j].count-1;k++)
						{
							
							
							if(lift[j].reqarr[k+1]!=-1&&lift[j].reqarr[k+1]>i)
							{
								tot_dist=tot_dist+Math.abs(k-i)*63;
								lift[j].mem=k;
								flag=1;
								break;
							}
							else{
								tot_dist+=Math.abs(lift[j].reqarr[k]-lift[j].reqarr[k+1])*63;
							}
						}
						
						if (flag==0)
						{
							tot_dist=tot_dist+Math.abs((lift[j].count-1)-i)*63;
							lift[j].mem=k;
						}
				
						if(min_tot_dist>tot_dist)
						{
							min_tot_dist=tot_dist;
							lift_id_alloted=j;
						}
						
						
					}
						
					
				}//for loop of lift closed...	
				
				if(min>min_tot_dist)
				{
					min=min_tot_dist;
					floor[i].lift_allot_no =lift_id_alloted;
					int rem = lift[floor[i].lift_allot_no].mem;
					
					if(lift[floor[i].lift_allot_no].count==0)
					{
						lift[floor[i].lift_allot_no].reqarr[0]=i;
					}
					
					else
					{
						if(lift[floor[i].lift_allot_no].reqarr[rem+1]==-1){
							lift[floor[i].lift_allot_no].reqarr[rem+1]=i;
						}
						else
						{
							int store= lift[floor[i].lift_allot_no].reqarr[rem+1];
							lift[floor[i].lift_allot_no].reqarr[rem+1]=i;
							rem+=2;
							while(lift[floor[i].lift_allot_no].reqarr[rem]!=-1)
							{
								int a= lift[floor[i].lift_allot_no].reqarr[rem];
								lift[floor[i].lift_allot_no].reqarr[rem]=store;
								store=a;
								rem++;
							}
							lift[floor[i].lift_allot_no].reqarr[rem]=store;
						}
					}
					lift[floor[i].lift_allot_no].count++;
				}
				else
				{
						lift[floor[i].lift_allot_no].reqarr[lift[floor[i].lift_allot_no].count]=i;
						lift[floor[i].lift_allot_no].count++;
						//sorting for first count values remaing values =0 
						for (int c=0;c<(lift[floor[i].lift_allot_no].count)-1;c++)
						  {
						    for (int d=0;d<lift[floor[i].lift_allot_no].count-c-1;d++)
						    {
						      if (lift[floor[i].lift_allot_no].reqarr[d]>lift[floor[i].lift_allot_no].reqarr[d+1]) /* For decreasing order use < */
						      {
						        int swap=lift[floor[i].lift_allot_no].reqarr[d];
						        lift[floor[i].lift_allot_no].reqarr[d]   = lift[floor[i].lift_allot_no].reqarr[d+1];
						        lift[floor[i].lift_allot_no].reqarr[d+1] = swap;
						      }
						    }
						  }
						
				}
				
				
				
				
			}//button up closed 	
					 
				
				
				
				
			}//if closed...
			
			
			if(i>0)
			{
			if(e.getSource()==btdn[i-1])
			{
	System.out.println("button pressed is "+(i-1));
				
				System.out.println();
				System.out.println();
				int min=1000;
				int min_dist=1000;
				int lift_id_alloted=0;
			
				int lift_y=1000;
				int first_element=0;
				int flag=0;
				int tot_dist=0;
				int min_tot_dist=1500;
				int k=0;
				for(int j=0;j<total_lifts;j++)
				{
					
					//check for all halt lifts
					if(lift[j].checkstatus()==0)
					{
						//System.out.println("hi");
						
						lift[j].dist=Math.abs(lift[j].y-(577-(i)*63));
						//System.out.println("i "+i);
						//System.out.println("dist is "+j+" "+ lift[j].dist);
						
						if(min>lift[j].dist)
						{
							
							min=lift[j].dist;
							floor[i].lift_allot_no=j;
						}
					}
					
					//check for lifts moving down
					if(lift[j].checkstatus()!=0&&lift[j].status==2)
					{
						//System.out.println("run up");
						
						//check for all lifts up the floor and moving down
						if(lift[j].y<=577-(i)*63)
						{
							lift[j].dist=Math.abs(lift[j].y-(577-i*63));
							//System.out.println("lift no "+j+ "dist is "+lift[j].dist);
							
							if(min>lift[j].dist)
							{
								min=lift[j].dist;
								//System.out.println("min is "+min+ "lift no is "+ j);
								
								floor[i].lift_allot_no=j;
							}
						}
						
						
						//check for lifts that are down the floor and moving down
						
						else if(lift[j].y>(577-63*i))
						{
							//take the first element on the required array.
								
							//System.out.println("run down the floor");
							if(lift[j].count>0)
								{
									 first_element=lift[j].reqarr[0];
								}
								tot_dist+=Math.abs((577-63*first_element)-lift[j].y);
								for( k=0;k<lift[j].count-1;k++)
								{
									if(lift[j].reqarr[k+1]!=-1&&lift[j].reqarr[k+1]<i)
									{
										tot_dist=tot_dist+Math.abs(k-i)*63;
										lift[j].mem=k;
										flag=1;
										break;
									}
									else 
									{
										tot_dist+=Math.abs(lift[j].reqarr[k]-lift[j].reqarr[k+1])*63;
									}
								}
								// to calculate the distance when lift halting station is above the floor.
								
								if (flag==0)
								{
									tot_dist=tot_dist+Math.abs((lift[j].count-1)-i)*63;
									lift[j].mem=k;
								}
						
								if(min_tot_dist>tot_dist)
								{
									min_tot_dist=tot_dist;
									lift_id_alloted=j;
								}
							}
						}
								
					//check for ALL moving up lifts
						 flag=0;
						 tot_dist=0;
						 
				
					if(lift[j].checkstatus()!=0&&lift[j].status==1)
					{
				
						//System.out.println("moving up");
						
						//System.out.println("lift no is "+j);
						
						if(lift[j].count>0)
						{
							 first_element=lift[j].reqarr[0];
						}
						tot_dist+=Math.abs(577-63*first_element-lift[j].y);
						for( k=0;k<lift[j].count-1;k++)
						{
							if(lift[j].reqarr[k+1]!=-1&&lift[j].reqarr[k+1]<i)
							{
								tot_dist=tot_dist+Math.abs(k-i)*63;
								lift[j].mem=k;
								flag=1;
								break;
							}
							else{
								tot_dist+=Math.abs(lift[j].reqarr[k]-lift[j].reqarr[k+1])*63;
							}
						}
						
						if (flag==0)
						{
							tot_dist=tot_dist+Math.abs((lift[j].count-1)-i)*63;
							lift[j].mem=k;
						}
				
						if(min_tot_dist>tot_dist)
						{
							min_tot_dist=tot_dist;
							lift_id_alloted=j;
						}
					}
						
					
				}//for loop of lift closed...	
				
				if(min>min_tot_dist)
				{
					min=min_tot_dist;
					floor[i].lift_allot_no =lift_id_alloted;
					int rem = lift[floor[i].lift_allot_no].mem;
					
					if(lift[floor[i].lift_allot_no].count==0)
					{
						lift[floor[i].lift_allot_no].reqarr[0]=i;
					}
					
					else
					{
						if(lift[floor[i].lift_allot_no].reqarr[rem+1]==-1){
							lift[floor[i].lift_allot_no].reqarr[rem+1]=i;
						}
						else
						{
							int store= lift[floor[i].lift_allot_no].reqarr[rem+1];
							lift[floor[i].lift_allot_no].reqarr[rem+1]=i;
							rem+=2;
							while(lift[floor[i].lift_allot_no].reqarr[rem]!=-1)
							{
								int a= lift[floor[i].lift_allot_no].reqarr[rem];
								lift[floor[i].lift_allot_no].reqarr[rem]=store;
								store=a;
								rem++;
							}
							lift[floor[i].lift_allot_no].reqarr[rem]=store;
						}
					}
					lift[floor[i].lift_allot_no].count++;
				}
				else
				{
						lift[floor[i].lift_allot_no].reqarr[lift[floor[i].lift_allot_no].count]=i;
						lift[floor[i].lift_allot_no].count++;
						//sorting for first count values remaining values =0  in reverse order
						for (int c=0;c<(lift[floor[i].lift_allot_no].count)-1;c++)
						  {
						    for (int d=0;d<lift[floor[i].lift_allot_no].count-c-1;d++)
						    {
						      if (lift[floor[i].lift_allot_no].reqarr[d]<lift[floor[i].lift_allot_no].reqarr[d+1]) /* For decreasing order use < */
						      {
						        int swap=lift[floor[i].lift_allot_no].reqarr[d];
						        lift[floor[i].lift_allot_no].reqarr[d]   = lift[floor[i].lift_allot_no].reqarr[d+1];
						        lift[floor[i].lift_allot_no].reqarr[d+1] = swap;
						      }
						    }
						  }
						//Arrays.sort(lift[lift_id_alloted].reqarr);
				}
				
			}}
		//take request from lifts
		
			if(lift[0].takerequest==true||lift[0].count>=0)
			{
				if(e.getSource()==lift1_bt[i])
				{
					System.out.println("req is  "+i);
					if(lift[0].status==0){
						lift[0].reqarr[lift[0].count]=i;
						lift[0].count++;
					}
					
					else if(lift[0].status==1)
					{
						if(lift[0].y>=577-(63*i))
						{
							lift[0].reqarr[lift[0].count]=i;
							lift[0].count++;
							//sort the array
							for (int c=0;c<(lift[0].count)-1;c++)
							  {
							    for (int d=0;d<lift[0].count-c-1;d++)
							    {
							      if (lift[0].reqarr[d]>lift[0].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[0].reqarr[d];
							        lift[0].reqarr[d]   = lift[0].reqarr[d+1];
							        lift[0].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						if(lift[0].y<577-(63*i))
						{
							lift[0].reqarr[lift[0].count]=i;
							lift[0].count++;
						}
					}
					else if(lift[0].status==2)
					{
						//request is below the lift
						if(lift[0].y<=577-(63*i))
						{
							lift[0].reqarr[lift[0].count]=i;
							lift[0].count++;
							//sort the array in reverse array
							for (int c=0;c<(lift[0].count)-1;c++)
							  {
							    for (int d=0;d<lift[0].count-c-1;d++)
							    {
							      if (lift[0].reqarr[d]<lift[0].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[0].reqarr[d];
							        lift[0].reqarr[d]   = lift[0].reqarr[d+1];
							        lift[0].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						//request is above the lift y coordinate
						if(lift[0].y>577-(63*i))
						{
							lift[0].reqarr[lift[0].count]=i;
							lift[0].count++;
						}
					}
				}
			}
		
		
		
			if(lift[1].takerequest==true||lift[1].count>=0)
			{
				if(e.getSource()==lift2_bt[i])
				{
					if(lift[1].status==0){
						lift[1].reqarr[lift[1].count]=i;
						lift[1].count++;
					}
					
					else if(lift[1].status==1)
					{
						if(lift[1].y>=577-(63*i))
						{
							lift[1].reqarr[lift[1].count]=i;
							lift[1].count++;
							//sort the array
							for (int c=0;c<(lift[0].count)-1;c++)
							  {
							    for (int d=0;d<lift[0].count-c-1;d++)
							    {
							      if (lift[1].reqarr[d]>lift[1].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[0].reqarr[d];
							        lift[1].reqarr[d]   = lift[1].reqarr[d+1];
							        lift[1].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						if(lift[1].y<577-(63*i))
						{
							lift[1].reqarr[lift[1].count]=i;
							lift[1].count++;
						}
					}
					else if(lift[1].status==2)
					{
						//request is below the lift
						if(lift[1].y<=577-(63*i))
						{
							lift[1].reqarr[lift[1].count]=i;
							lift[1].count++;
							//sort the array in reverse array
							for (int c=0;c<(lift[1].count)-1;c++)
							  {
							    for (int d=0;d<lift[1].count-c-1;d++)
							    {
							      if (lift[1].reqarr[d]<lift[1].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[1].reqarr[d];
							        lift[1].reqarr[d]   = lift[1].reqarr[d+1];
							        lift[1].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						//request is above the lift y coordinate
						if(lift[1].y>577-(63*i))
						{
							lift[1].reqarr[lift[1].count]=i;
							lift[1].count++;
						}
					}
				}
			}
		
			if(lift[2].takerequest==true||lift[2].count>=0)
			{
				if(e.getSource()==lift3_bt[i])
				{
					if(lift[2].status==0){
						lift[2].reqarr[lift[2].count]=i;
						lift[2].count++;
					}
					
					else if(lift[2].status==1)
					{
						if(lift[2].y>=577-(63*i))
						{
							lift[2].reqarr[lift[2].count]=i;
							lift[2].count++;
							//sort the array
							for (int c=0;c<(lift[2].count)-1;c++)
							  {
							    for (int d=0;d<lift[2].count-c-1;d++)
							    {
							      if (lift[0].reqarr[d]>lift[2].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[2].reqarr[d];
							        lift[2].reqarr[d]   = lift[2].reqarr[d+1];
							        lift[2].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						if(lift[2].y<577-(63*i))
						{
							lift[2].reqarr[lift[2].count]=i;
							lift[2].count++;
						}
					}
					else if(lift[2].status==2)
					{
						//request is below the lift
						if(lift[2].y<=577-(63*i))
						{
							lift[2].reqarr[lift[2].count]=i;
							lift[2].count++;
							//sort the array in reverse array
							for (int c=0;c<(lift[2].count)-1;c++)
							  {
							    for (int d=0;d<lift[2].count-c-1;d++)
							    {
							      if (lift[2].reqarr[d]<lift[2].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[2].reqarr[d];
							        lift[2].reqarr[d]   = lift[2].reqarr[d+1];
							        lift[2].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						//request is above the lift y coordinate
						if(lift[2].y>577-(63*i))
						{
							lift[2].reqarr[lift[2].count]=i;
							lift[2].count++;
						}
					}
				}
			}
		
			if(lift[3].takerequest==true||lift[3].count>=0)
			{
				if(e.getSource()==lift4_bt[i])
				{
					if(lift[3].status==0){
						lift[3].reqarr[lift[3].count]=i;
						lift[3].count++;
					}
					
					else if(lift[3].status==1)
					{
						if(lift[3].y>=577-(63*i))
						{
							lift[3].reqarr[lift[3].count]=i;
							lift[3].count++;
							//sort the array
							for (int c=0;c<(lift[3].count)-1;c++)
							  {
							    for (int d=0;d<lift[3].count-c-1;d++)
							    {
							      if (lift[3].reqarr[d]>lift[3].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[3].reqarr[d];
							        lift[3].reqarr[d]   = lift[3].reqarr[d+1];
							        lift[3].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						if(lift[3].y<577-(63*i))
						{
							lift[3].reqarr[lift[3].count]=i;
							lift[3].count++;
						}
					}
					else if(lift[3].status==2)
					{
						//request is below the lift
						if(lift[3].y<=577-(63*i))
						{
							lift[3].reqarr[lift[3].count]=i;
							lift[3].count++;
							//sort the array in reverse array
							for (int c=0;c<(lift[3].count)-1;c++)
							  {
							    for (int d=0;d<lift[3].count-c-1;d++)
							    {
							      if (lift[3].reqarr[d]<lift[3].reqarr[d+1]) /* For decreasing order use < */
							      {
							        int swap=lift[3].reqarr[d];
							        lift[3].reqarr[d]   = lift[3].reqarr[d+1];
							        lift[3].reqarr[d+1] = swap;
							      }
							    }
							  }
							
						}
						//request is above the lift y coordinate
						if(lift[3].y>577-(63*i))
						{
							lift[3].reqarr[lift[3].count]=i;
							lift[3].count++;
						}
					}
				}
			}
		
		
		}//for loop for floors closed  
		
			if(lift[0].enablebutt==true)
			{
				if(e.getSource()==lp1_enter)
				{
						lift[0].people_count++;
						System.out.println(lift[0].people_count);
				}
				if(e.getSource()==lp1_leave)
				{
						lift[0].people_count--;
				}
			}
			
			if(lift[1].enablebutt==true)
			{
				if(e.getSource()==lp2_enter)
				{
						lift[1].people_count++;
				}
				if(e.getSource()==lp2_leave)
				{
						lift[1].people_count--;
				}
			}
			
			if(lift[2].enablebutt==true)
			{
				if(e.getSource()==lp3_enter)
				{
						lift[2].people_count++;
				}
				if(e.getSource()==lp3_leave)
				{
						lift[2].people_count--;
				}
			}
			
			if(lift[3].enablebutt==true)
			{
				if(e.getSource()==lp4_enter)
				{
						lift[3].people_count++;
				}
				if(e.getSource()==lp4_leave)
				{
						lift[3].people_count--;
				}
			}
		
			if(e.getSource()==l1_e)
			{
				if(lift[0].count==0)
				{
					lift[0].reqarr[0]=(577-lift[0].y)/63;
					lift[0].count=1;
				}
				else if(lift[0].count!=0)
				{
					if(lift[0].status==1)
					{
						for(int i =0 ; i<lift[0].count;i++){
							lift[0].reqarr[i]=-1;
						}
						lift[0].reqarr[0]=lift[0].currfl;
						lift[0].count=1;
					}
					if(lift[0].status==2)
					{
						for(int i =0 ; i<lift[0].count;i++){
							lift[0].reqarr[i]=-1;
						}
						lift[0].reqarr[0]=lift[0].currfl;
						lift[0].count=1;
					}
				}
			}
			
			
			if(e.getSource()==l2_e)
			{
				if(lift[1].count==0)
				{
					lift[1].reqarr[0]=(577-lift[1].y)/63;
					lift[1].count=1;
				}
				else if(lift[1].count!=0)
				{
					if(lift[1].status==1)
					{
						for(int i =0 ; i<lift[1].count;i++){
							lift[1].reqarr[i]=-1;
						}
						lift[1].reqarr[0]=lift[1].currfl;
						lift[1].count=1;
					}
					if(lift[1].status==2)
					{
						for(int i =0 ; i<lift[1].count;i++){
							lift[1].reqarr[i]=-1;
						}
						lift[1].reqarr[0]=lift[1].currfl;
						lift[1].count=1;
					}
				}
			}
			
			
			if(e.getSource()==l3_e)
			{
				if(lift[2].count==0)
				{
					lift[2].reqarr[0]=(577-lift[2].y)/63;
					lift[2].count=1;
				}
				else if(lift[2].count!=0)
				{
					if(lift[2].status==1)
					{
						for(int i =0 ; i<lift[2].count;i++){
							lift[2].reqarr[i]=-1;
						}
						lift[2].reqarr[0]=lift[2].currfl;
						lift[0].count=1;
					}
					if(lift[2].status==2)
					{
						for(int i =0 ; i<lift[2].count;i++){
							lift[2].reqarr[i]=-1;
						}
						lift[2].reqarr[0]=lift[2].currfl;
						lift[2].count=1;
					}
				}
			}
			
			
			if(e.getSource()==l4_e)
			{
				if(lift[3].count==0)
				{
					lift[3].reqarr[0]=(577-lift[3].y)/63;
					lift[3].count=1;
				}
				else if(lift[3].count!=0)
				{
					if(lift[3].status==1)
					{
						for(int i =0 ; i<lift[3].count;i++){
							lift[3].reqarr[i]=-1;
						}
						lift[3].reqarr[0]=lift[3].currfl;
						lift[3].count=1;
					}
					if(lift[3].status==2)
					{
						for(int i =0 ; i<lift[3].count;i++){
							lift[3].reqarr[i]=-1;
						}
						lift[3].reqarr[0]=lift[3].currfl;
						lift[3].count=1;
					}
				}
			}
			
		
	}//function closed
	
			
	
	Point p =  new Point() ;
	public void mouseClicked(MouseEvent e) {
		p.x=e.getX();
		p.y=e.getY();
		System.out.println("x= "+ p.x + "y= "+p.y);
		// TODO Auto-generated method stub
	}

	
	
		
	
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	

	
	
	
}


