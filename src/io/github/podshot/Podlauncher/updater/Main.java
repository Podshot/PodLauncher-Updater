package io.github.podshot.Podlauncher.updater;

import java.awt.FlowLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Main {

	public static void main(String[] args) {

		String newPodLauncherFile = "";
		String filename = "PodLauncher.jar";

		JFrame frame = new JFrame();
		JProgressBar pBar = new JProgressBar(0,100);
		pBar.setSize(50, 50);
		pBar.setValue(0);
		pBar.setStringPainted(true);
		frame.add(pBar);
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			URL url=new URL(newPodLauncherFile); 
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			int filesize = connection.getContentLength(); 
			float totalDataRead=0; 
			BufferedInputStream in = new BufferedInputStream(connection.getInputStream()); 
			FileOutputStream fos = new java.io.FileOutputStream(filename); 
			BufferedOutputStream bout = new BufferedOutputStream(fos,1024); 
			byte[] data = new byte[1024]; 
			int i=0; 
			while((i=in.read(data,0,1024))>=0) { 
				totalDataRead=totalDataRead+i; 
				bout.write(data,0,i); 
				float Percent=(totalDataRead*100)/filesize; 
				pBar.setValue((int)Percent); 
			} 
			bout.close(); 
			in.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
