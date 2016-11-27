package FPTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileParser {
	static HashMap<Integer, List<String>> database;
	static String fileName;
	
	FileParser(HashMap<Integer, List<String>> database, String fileName){
		FileParser.database = database;
		FileParser.fileName = fileName;
		parseFile();
	}
	
	void parseFile() {
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			
			for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
				List<String> eachLine = new ArrayList<String>();
				
				for (String s : line.split(",")) {
					eachLine.add(s);
				}
				
				database.put(i, eachLine);
			}
			
			bufferedReader.close();	
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open the file : " + fileName);
		} catch (IOException e) {
			System.out.println("Unable to read the file : " + fileName);
		}
	}
}
