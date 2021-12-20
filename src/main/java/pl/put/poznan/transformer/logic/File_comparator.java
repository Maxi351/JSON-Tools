package pl.put.poznan.transformer.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class File_comparator {
    public ArrayList<Character> file_compare_rek(ArrayList<String> file1, ArrayList<String> file2, int s1, int s2)
    {

        if(file1.size()<=s1)
        {
            ArrayList<Character> pom = new ArrayList<>();
            for(int i=0;i<file2.size()-s2;i++)
                pom.add('i');
            return pom;
        }
        if(file2.size()<=s2)
        {
            ArrayList<Character> pom = new ArrayList<>();
            for(int i=0;i<file1.size()-s1;i++)
                pom.add('d');
            return pom;
        }


//        if(file1.get(s1)==file2.get(s2)) {
        if(file1.get(s1).equals(file2.get(s2))) {
            ArrayList<Character> pom = new ArrayList<>();
            pom.add('c');
            pom.addAll(file_compare_rek(file1, file2, s1 + 1, s2 + 1));

            return pom;
        }
        else
        {

            ArrayList<Character> case1 = new ArrayList<>();
            ArrayList<Character> case2 = new ArrayList<>();

            case1.add('d');
            case2.add('i');

            case1.addAll(file_compare_rek(file1,file2,s1+1,s2));
            case2.addAll(file_compare_rek(file1,file2,s1,s2+1));

            if(case1.size()<case2.size())
            {
                return case1;
            }
            else
            {
                return case2;
            }

        }
    }

    private ArrayList<String> read_file(String dir) throws FileNotFoundException
    {
        File my_file = new File(dir);

        ArrayList<String> file_data = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(my_file);

            while(myReader.hasNextLine()) {
                file_data.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        return file_data;

    }

    public ArrayList<Character> file_compare(ArrayList<String> file1, ArrayList<String> file2)
    {
        return file_compare_rek(file1,file2,0,0);
    }

    public ArrayList<Character> file_compare_dir(String dir1,String dir2) throws FileNotFoundException
    {
        ArrayList<String> file1;
        ArrayList<String> file2;

        try{
            file1 = read_file(dir1);
            file2 = read_file(dir2);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw e;
        }
        return file_compare(file1,file2);
    }

    public int merge_files(String dir1,String dir2, String output_dir)
    {
        ArrayList<String> file1;
        ArrayList<String> file2;
        ArrayList<Character> diff;

        try{
            file1 = read_file(dir1);
            file2 = read_file(dir2);

            diff = file_compare(file1,file2);

        }
        catch (FileNotFoundException e) {
            return -1;
            //throw e;
        }

        try {
            FileWriter myWriter = new FileWriter(output_dir);
            String separator = ":";
            String sign;
            int liczba_tych_samych=0;
            for(int i=0,s1=0,s2=0;i<diff.size();i++)
            {
                if(diff.get(i)=='c')
                {
                    sign = " "+separator;
                    myWriter.write(sign + file1.get(s1)+'\n');
                    s1++;
                    s2++;
                    liczba_tych_samych++;
                }
                else if(diff.get(i)=='d')
                {
                    sign = "-" + separator;
                    myWriter.write(sign + file1.get(s1)+'\n');
                    s1++;
                }
                else if(diff.get(i)=='i')
                {
                    sign = "+" + separator;
                    myWriter.write(sign + file2.get(s2)+'\n');
                    s2++;
                }

            }
            myWriter.close();
            return liczba_tych_samych;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
//            System.out.println("Error");

        }
    }

    public String merge_strings_lines(String f1,String f2)
    {
        ArrayList<String> file1 = new ArrayList<>(Arrays.asList(f1.split("\n")));
        ArrayList<String> file2 = new ArrayList<>(Arrays.asList(f2.split("\n")));
        ArrayList<Character> diff;


        diff = file_compare(file1,file2);

        String separator = ":";
        String sign;
        String output = "";

        for(int i=0,s1=0,s2=0;i<diff.size();i++)
        {
            if(diff.get(i)=='c')
            {
                sign = " "+separator;
                output += sign + file1.get(s1)+'\n';
                s1++;
                s2++;
            }
            else if(diff.get(i)=='d')
            {
                sign = "-" + separator;
                output += sign + file1.get(s1)+'\n';
                s1++;
            }
            else if(diff.get(i)=='i')
            {
                sign = "+" + separator;
                output += sign + file2.get(s2)+'\n';
                s2++;
            }

        }

        return output;
    }

}
