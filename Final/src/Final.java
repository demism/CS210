import java.io.File;
import java.util.Scanner;

public class Final {
	public static void main(String[] args) {
			    int num;
			    int item;
			    File fileIn = new File("data2.txt");
		        Scanner fileScan = new Scanner(fileIn);
		        num = fileScan.nextInt();
		        for (int i=0; i<num ;++i ){
		            item = fileScan.nextInt();
		            System.out.print(item);
		        } 
		        fileScan.close();
			}

	}
