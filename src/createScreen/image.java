package createScreen;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;

import org.bytedeco.tesseract.TessBaseAPI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import net.sourceforge.lept4j.Leptonica;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


public class image{
	private static String image_name;
	public static String text;
	public void set_image_name(String name){
		this.image_name = name; 
	}
	public String get_image_name()
	{
		return image_name;
	}
	
	public void set_text(String text){
		this.text = text; 
	}
	
	public String get_text() {
		return text;
	}
	
	/*
	 * public static void main(String[] args){ String temp = print();
	 * System.out.println(temp); }
	 */
	@Test
	public static void test() {
		String temp = print();
		 System.out.println(temp);
	}
	public static String print() {
		image text= new image();
		String input_file= "C:\\Program Files\\Tesseract-OCR\\04-Oct-2019__10_56_49AM.png";
		 String output_file="C:\\Program Files\\Tesseract-OCR\\output";
		 String tesseract_install_path="C:\\Program Files\\Tesseract-OCR";
		 String[] command =
		    {
		        "cmd",
		    };
		    Process p;
		 String result=null; 
		 try {
		 p = Runtime.getRuntime().exec(command);
		       new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
		        new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
		        PrintWriter stdin = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), StandardCharsets.UTF_8), true);
		        stdin.println("\""+tesseract_install_path+"\" \""+image_name+"\" \""+output_file+"\" -l eng");
		        stdin.close();
		        p.waitFor();
		        System.out.println();
		        result =  read_a_file(output_file+".txt");
		        text.set_text(result);
		        System.out.println(text.get_text());
		    } catch (Exception e) {
		 e.printStackTrace();
		    }
		return text.get_text();
	}
	
	public static String read_a_file(String file_name) {
		BufferedReader br = null; 
		String read_string="";
		try {
		String sCurrentLine;
		br = new BufferedReader(new FileReader(file_name));
		while ((sCurrentLine = br.readLine()) != null) {
		read_string=read_string+sCurrentLine;
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		try {
			if (br != null)br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		}
		return read_string;
	}
	

	
}
	