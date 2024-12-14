package ftm.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy_FTM_Files {
	
	static FileInputStream fis1 = null, fis2 = null;
	static FileOutputStream fos1 = null, fos2 = null;
	
	public static void copyFiles(String path) throws IOException {
		try{
			fis1 = new FileInputStream(path+"\\SIMPLE_ICL.eof");
			fis2 = new FileInputStream(path+"\\Simple_ICL.DAT");
			
			// destination folder
            String destFolderPath = "D:\\Citizen\\Destination\\";

            File eofFile = new File(destFolderPath + "SIMPLE_ICL.eof");
            File datFile = new File(destFolderPath + "Simple_ICL.DAT");

            // check if files already exist in destination folder
            if (eofFile.exists() || datFile.exists()) {
                System.out.println("File already exists in destination folder.");
                return;
            }
			
			fos1 = new FileOutputStream("D:\\Citizen\\Destination\\SIMPLE_ICL.eof");
			fos2 = new FileOutputStream("D:\\Citizen\\Destination\\Simple_ICL.DAT");

			// Byte array to store the contents of file
			byte[] buffer = new byte[1024];

			int bytesRead;
			while((bytesRead = fis1.read(buffer)) != -1) {
				fos1.write(buffer, 0, bytesRead);
			}
			while((bytesRead = fis2.read(buffer)) != -1) {
				fos2.write(buffer, 0, bytesRead);
			}
			System.out.println("Copied the files successfully");
		}
		catch(FileNotFoundException e) {
			System.out.println("Invalid File Path!!!");
			System.exit(0);
		}
		finally {

			// Closing the streams
			if (fis1 != null) {
				fis1.close();
			}
			if (fis2 != null) {
				fis2.close();
			}
			if (fos1 != null) {
				fos1.close();
			}
			if (fos2 != null) {
				fos2.close();
			}
		}
		File eofFile = new File("D:\\Citizen\\Destination\\SIMPLE_ICL.eof");
		File datFile = new File("D:\\Citizen\\Destination\\Simple_ICL.DAT");

		if (eofFile.exists() && datFile.exists()) {
		    System.out.println("Files copied successfully");
		} else {
		    System.out.println("Failed to copy files");
		}
	}
}
