package dna.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DnaSignature {
	public static void main(String[] args) throws FileNotFoundException {
		long start = System.currentTimeMillis();
		// calling read method
		Scanner input = readStatsFile();
		 // set the filename
		String sequenceFileName = getFileName(input);
		// create the file 
		PrintWriter pf = createStatsFile();

		File f = new File(sequenceFileName);
		
		while (!f.exists()) {
			System.out.println("Error ! file " + sequenceFileName + " not found ! ");
			System.out.println("please Enter the name of the DNA file : ");
			sequenceFileName = input.next();
			f = new File(sequenceFileName);
		}
		
		searchDNASequence(input,pf,f,sequenceFileName,start);
		
		

	}

	/**
	 * 
	 * @param count
	 * @param pf
	 * @param name
	 * @param start
	 * @param dna
	 */
	private static void printResult(int count,PrintWriter pf,String name,long start,String dna) {
		System.out.println("DNA sequence search completed. Results saved in dna_stats.txt file!");
		if (count != 0) {
			System.out.println("Sequence : " + dna + " found " + count + " times in " + name + " file !");
			pf.println("Sequence : " + dna + " found " + count + " times in " + name + " file !");

		} else {
			System.out.println("Sequence : " + dna + "  not found " + " in " + name + " file !");
			pf.println("Sequence : " + dna + "  not found " + " in " + name + " file !");
		}

		pf.close();

		long end = System.currentTimeMillis();
		System.out.println("Execution time = " + ((end - start) / 1000) + " seconds!");
		
	}

	/**
	 * 
	 * @param input
	 * @param pf
	 * @param f
	 * @param name
	 * @param start
	 */
	private static void searchDNASequence(Scanner input, PrintWriter pf,File f,String name,long start) {
		int count = 0;
		String dna = "";
		try {
			Scanner file = new Scanner(f);
			System.out.print("please enter the DNA sequence you want to search for : ");
			dna = input.next();
			pf.println("DNA sequence " + dna);
			String line;
			int LineNumber = 0;

			while (file.hasNextLine()) {
				line = file.nextLine();
				LineNumber++;

				int loc = line.indexOf(dna);
				if (loc != -1)
					pf.println("in line " + LineNumber + " found first dna " + dna + " in index : " + loc);

				while (loc != -1) {
					count++;

					line = line.substring(loc + dna.length());
					loc = line.indexOf(dna);
				}

			}
			file.close();

		} catch (IOException e) {
			System.out.println("Error in file");
		}
		
		printResult( count, pf, name, start, dna);
		
	}

	private static PrintWriter createStatsFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File("dna_stats.txt"));
		return writer;
	}

	private static String getFileName(Scanner input) {
		System.out.println("please Enter the name of the DNA file : ");
		String name = input.next();
		return name;
	}

	private static Scanner readStatsFile() throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		return input;
		
	}

}
