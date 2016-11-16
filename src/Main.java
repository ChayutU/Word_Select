import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Chayut on 10-Oct-16.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Vector typeList;
        String text = "", inFileName, outFileName;
        String[] line;
        char ch;
        int begin, end, type;

        File inFile, outFile;
        FileReader fr;
        CSVReader br;
        FileWriter fw;

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n\n*******************************");
        ;
        System.out.println("*******************************");
        do {
            //Get input file name
            do {
                System.out.print("\n >>> Enter input file  ('q' to quit): ");
                inFileName = (streamReader.readLine()).trim();
                if (inFileName.equals("q"))
                    System.exit(1);
                inFile = new File(System.getProperty("user.dir") + "//" + inFileName);
            } while (!inFile.exists());

            //Get output file name

            System.out.print(" >>> Enter output file (.html only): ");
            outFileName = (streamReader.readLine()).trim();
            outFile = new File(System.getProperty("user.dir") + "//" + outFileName);

            fr = new FileReader(inFile);
            br = new CSVReader(fr);
            fw = new FileWriter(outFile);

            fw.write(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
            line = br.readNext();
            int len = line.length;
            double[] sum = new double[len];
            System.out.println("csv column = " + sum.length);
            int round = 0;
            while ((line = br.readNext()) != null) {
                for (int i = 0; i < len-1; i++) {
                    sum[i] += Double.parseDouble(line[i]);
                }
                System.out.println(++round);
            } //while all line
            System.out.println("sum finished");
            fr.close();
            fr = new FileReader(inFile);
            br = new CSVReader(fr);

            ArrayList select = new ArrayList();

            for (int i = 0; i < sum.length; i++) {
                if (sum[i] > 1000) {
                    select.add(i);
                }
            }
            System.out.println("added");
            while ((line = br.readNext()) != null) {
                fw.write(line[len-1] + ",");
                System.out.println(line[len-1]);
                Iterator in = select.iterator();
                while (in.hasNext()) {
                    int at = (int) in.next();
                    fw.write(line[at] + ",");
                }
                fw.write("\r\n");
            }

            fr.close();
            fw.close();
            System.out.println("\n *** Status: View result: " + outFileName);
        } while (true);
    } //main
}
