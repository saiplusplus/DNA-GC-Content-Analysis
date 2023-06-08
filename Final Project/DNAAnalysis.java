import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DNAAnalysis {
    
    // Function to convert the contents of dna_filename into a string of nucleotides
    public static String filenameToString(String dnaFilename) throws IOException {
        BufferedReader inputfile = new BufferedReader(new FileReader(dnaFilename));
        StringBuilder seq = new StringBuilder();
        int lineNum = 0;
        String line;
        while ((line = inputfile.readLine()) != null) {
            lineNum++;
            if (lineNum % 4 == 2) {
                line = line.trim();
                seq.append(line);
            }
        }
        inputfile.close();
        return seq.toString();
    }
    
    // Function to return GC Classification
    public static String classify(double gcContent) {
        String classification = "high";
        if (gcContent < 0.37) {
            classification = "low";
        } else if (gcContent >= 0.37 && gcContent <= 0.55) {
            classification = "moderate";
        }
        return classification;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("You must supply a file name as an argument when running this program.");
            System.exit(2);
        }

        String fileName = args[0];

        String nucleotides;
        try {
            nucleotides = filenameToString(fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        int total_count = 0;
        int gc_count = 0;
        int at_count = 0;
        int g_count = 0;
        int c_count = 0;
        int a_count = 0;
        int t_count = 0;

        for (char base : nucleotides.toCharArray()) {
            total_count++;

            if (base == 'C' || base == 'G') {
                gc_count++;
            }
            if (base == 'T' || base == 'A') {
                at_count++;
            }
            if (base == 'G') {
                g_count++;
            }
            if (base == 'C') {
                c_count++;
            }
            if (base == 'A') {
                a_count++;
            }
            if (base == 'T') {
                t_count++;
            }
        }

        int sum_counts = g_count + c_count + a_count + t_count;
        int len_nuc = nucleotides.length();
        double gc_content = (double) gc_count / sum_counts;
        double at_content = (double) at_count / sum_counts;
        double at_gc = (double) at_count / gc_count;

        System.out.println("GC-content: " + gc_content);
        System.out.println("AT-content: " + at_content);
        System.out.println("G count: " + g_count);
        System.out.println("C count: " + c_count);
        System.out.println("A count: " + a_count);
        System.out.println("T count: " + t_count);
        System.out.println("Sum of G+C+A+T counts: " + sum_counts);
        System.out.println("Total count: " + total_count);
        System.out.println("Length of nucleotides: " + len_nuc);
        System.out.println("AT/GC Ratio: " + at_gc);
        System.out.println("GC Classification: " + classify(gc_content) + " GC content");

        // You can add more assertions here to check properties that you think
        // should be true about your results. If the condition listed is false,
        // then the given message will be printed.
        assert total_count == nucleotides.length() : "total_count != length of nucleotides";
    }
}