package test;

import java.io.*;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random(1000);
		try {
			File writename = new File("Random.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for(int i = 0; i < 2000; i++) {
				out.write(random.nextInt(6) + "\r\n");
			}
			
			out.flush(); 
			out.close(); 
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
