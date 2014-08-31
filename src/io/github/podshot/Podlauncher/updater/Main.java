package io.github.podshot.Podlauncher.updater;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Main {

	public static void main(String[] args) throws IOException {

		String newPodLauncherFile = "http://podshot.github.io/PodLauncher/update/PodLauncher.jar";
		String filename = "PodLauncher.jar";

		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		JProgressBar pBar = new JProgressBar(0,100);
		pBar.setLocation(119, 75);
		pBar.setSize(146, 17);
		pBar.setValue(0);
		pBar.setStringPainted(true);
		frame.getContentPane().add(pBar);
		
		JLabel lblUpdatingPodlauncher = new JLabel("Updating PodLauncher");
		lblUpdatingPodlauncher.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUpdatingPodlauncher.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdatingPodlauncher.setBounds(119, 50, 146, 14);
		frame.getContentPane().add(lblUpdatingPodlauncher);
		frame.setVisible(true);
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
		
		Runtime rt = Runtime.getRuntime();
		rt.exec("java -jar PodLauncher.jar -updated");
		System.exit(0);
	}
}
