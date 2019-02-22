package edu.eci.arsw.GuidFinderDesktop;

import java.util.UUID;

public class Threads extends Thread {
	private UUID[] guids; 
	private UUID guidToFind;
	private int posi;
	private int posf;
	boolean pause;
	boolean waiting;
	Object monitor=new Object();
	int count=0;
	
	public Threads(int posi,int posf, UUID[] guids, UUID uuid) {
		this.posi=posi;
		this.posf=posf;
		this.guids=guids;
		this.guidToFind=uuid;
		start();
	}
	
	@Override
	public void run() {
		int c=0;
		while(c<=posf) {
			synchronized(monitor) {
				if(!pause) {
					count();
				}else {
					try {
						monitor.wait();
						waiting();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			c++;
		}
		
		}

	
	public void count() {
		for(int i=posi; i<posf;i++) {
			if(guids[i].equals(guidToFind)){
				count++;
			}
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public void waiting() {
		setPause(true);		
		long tiempo= System.nanoTime();
		long seg=1000000;
		while(System.nanoTime()-tiempo<10*seg && !pause) {
			
		}
		setPause(false);
		
	}
	public void start() {
		this.pause=false;
	}
	public void setPause(boolean p) {
		this.pause=p;
	}
	public boolean isPause() {
		return pause;
	}
	public boolean isWaiting() {
		return waiting;
	}
}
