package com.usman.csudh.util;
//Yvette Chavez (ychavez48@toromail.csudh.edu)

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a utility class to render menu options and to accept user input. This class will 
 * 
 * @author Usman Aslam
 *
 */
public class UIManager {
	Scanner kb = new Scanner("C:\\Users\\yvett\\Downloads\\exchange-rate.csv");

	private  String[] menuOptions;
	private  String prompt;
	
	private Scanner scanner;
	private InputStream in;
	private OutputStream out;
	
	private String intErrorMessage="\nInvalid input, please type an integer\n";
	private String doubleErrorMessage="\nInvalid input, please type in a number with decimal point\n";
	
	private String currencyCode;
    private String name;
    private double exchangeRate;
    
    public UIManager(String currencyCode, double exchangeRate, String name) {
        this.currencyCode = currencyCode;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }
    
	
	public UIManager(InputStream in, OutputStream out, String[] options, String prompt) {
		this.in=in;
		this.out=out;
		this.scanner=new Scanner(in);
		this.menuOptions=options;
		this.prompt=prompt;
	}
	public void print(String haveCurrency, double amount, String trade) throws IOException {
		
		File file = new File("exchange-rate.csv");
		try {
		    FileReader fileReader = new FileReader(file);
		    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    String line;

		    while ((line = bufferedReader.readLine()) != null) {
		        String[] parts = line.split(",");		        
		    }
		    List<UIManager> currencies = new ArrayList<>();

		    while ((line = bufferedReader.readLine()) != null) {
		        String[] parts = line.split(",");
		        haveCurrency = parts[0];
		        amount =Double.parseDouble(parts[1]);
		        trade = parts[2];
		        
		        UIManager currency = new UIManager(parts[0], Double.parseDouble(parts[1]),parts[2]);
		        
		        currencies.add(currency);
		    }

		} catch (FileNotFoundException e) {
		    System.out.println("Could not find the currency exchange file.");
		}
	}
	public String getIntErrorMessage() {
		return intErrorMessage;
	}


	public void setIntErrorMessage(String intErrorMessage) {
		this.intErrorMessage = intErrorMessage;
	}


	public String getDoubleErrorMessage() {
		return doubleErrorMessage;
	}


	public void setDoubleErrorMessage(String doubleErrorMessage) {
		this.doubleErrorMessage = doubleErrorMessage;
	}
	
	
	
	public  int getMainOption() throws IOException{
		int choice;
		int menuIndex=1;
		int totalOptions = menuOptions.length;

		while (true) {
			for (String option : menuOptions)
				print((menuIndex++)+" - "+option,null);

			choice = readInt(String.format( "%n%s [1-%d]: ", new Object[] {this.prompt, totalOptions }));
			if (choice > 0 && choice <= totalOptions)
				break;
			else 
				menuIndex=1;
		}
		return choice;
	}

	public  String readLine(String msg)throws IOException {
		print(msg, null);
		return this.scanner.nextLine();
	}

	public  String readToken(String msg)throws IOException {
		print(msg, null);
		return this.scanner.next();
	}

	public  double readDouble(String msg) throws IOException{

		while (true) {
			try {
				return Double.parseDouble(readToken(msg));
			} catch (Exception e) {
				print(doubleErrorMessage,new Object[] {});
			}
		}

	}

	public  int readInt(String msg)throws IOException {
		while (true) {
			try {
				return Integer.parseInt(readToken(msg));
			} catch (Exception e) {
				print(intErrorMessage,new Object[] {});
			}
		}

	}

	public  void print(String s, Object[] variables) throws IOException {
		
		s=String.format(s, variables); 
		this.out.write(s.getBytes());
		this.out.flush();

	}
}
