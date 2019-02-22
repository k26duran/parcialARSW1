package edu.eci.arsw.GuidFinderDesktop;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GuidFinder implements NativeKeyListener{
	
	private static UUID[] guids; 
	Object monitor=new Object();
	boolean detecta;
	List<Threads> hilos=new ArrayList<>();
	
	public GuidFinder() throws Exception {
		getGuids();
	}
	
	public static UUID[] getGuids() throws Exception 
	{
	
		if(guids==null){
			System.out.println("es nulo");
		FileInputStream fi;
		
			fi = new FileInputStream(new File("guids.eci"));
		
		ObjectInputStream oi = new ObjectInputStream(fi);

		
		guids= (UUID[]) oi.readObject();
	
		oi.close();
		fi.close();
		}
		return guids;
		
	}
	
	public void nativeKeyPressed(NativeKeyEvent e) {
		//System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		detecta=true;
		synchronized(monitor){
			System.out.println("evento");
			
			if(detecta) {
				for(int i=0; i<4;i++) {
					hilos.get(i).setPause(hilos.get(i).isPause());
					
					if(hilos.get(i).isPause()) {
						monitor.notifyAll();
				}
			}
		}
	}

	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	
	public int countGuids(UUID guidToFind) throws Exception 
	{
		
		int lon=guids.length;
		int cantidad=lon/4;
		int count=0;
		Scanner s=new Scanner(System.in);
		for(int i=0; i<4; i++) {
			if(i==3) hilos.add(new Threads(i*cantidad,lon,guids,guidToFind));
			else hilos.add(new Threads(i*cantidad,(i+1)*cantidad,guids,guidToFind));
		}
		
		try {
			GlobalScreen.registerNativeHook();
			
			}
			catch (NativeHookException ex) {
				System.err.println("There was a problem registering the native hook.");
				System.err.println(ex.getMessage());

			}
		GlobalScreen.addNativeKeyListener(new GuidFinder());
		
		System.out.println("esta en count");
		return count;

	}
}
