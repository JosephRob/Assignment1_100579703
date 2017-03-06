package Asmt1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.io.*;

public class evaluate {
    /**
    * returns a HashMap of the chance of each word in the set being spam
    * @param file       the directory in which the training is set to take place
    * @return totalMap  HashMap of strings and doubles which are words and their chances of being spam
    * */
    public static HashMap<String, Double> train(File file) throws IOException{
        if((new File(file.getPath()+"/ham").exists()&&new File(file.getPath()+"/ham2").exists()&&new File(file.getPath()+"/spam").exists())==false){System.out.println("files not present");return null;}

        File hamFile=new File(file.getPath()+"/ham");
        ObservableList<TestFile> hamTestFiles = getEmails(hamFile,"");
        HashMap<String, Double>hamMap = new HashMap<String, Double>();
        List current=new ArrayList();
        int hamFiles=0;


        for(TestFile item : hamTestFiles) {
            if (item.getFileName()!="cmds") {
                current=new ArrayList();
                hamFiles+=1;
                String a="";
                for(int x=0;x<hamFile.getAbsolutePath().split("/").length;x++)a=a+"/..";
                BufferedReader br=new BufferedReader(new FileReader(a+hamFile+"/"+item.getFileName()));

                String line="";

                while((line=br.readLine())!=null){
                    if(line.equals(""))continue;

                    if((line.charAt(0)==' '||line.charAt(0)=='>'||line.charAt(0)=='\t')==false && line.length()>=5) {
                        if(((line.substring(0,4)).equals("From")||(line.substring(0,4)).equals("Cont")||(line.substring(0,2)).equals("X-")||(line.substring(0,4)).equals("Send")||(line.substring(0,5)).equals("Error")||(line.substring(0,4)).equals("Prec")||(line.substring(0,4)).equals("Repl")||(line.substring(0,4)).equals("Cont")||(line.substring(0,4)).equals("Orig")||(line.substring(0,4)).equals("Date")||(line.substring(0,5)).equals("List-")||(line.substring(0,4)).equals("MIME")||(line.substring(0,4)).equals("Mess")||(line.substring(0,4)).equals("To: ")||(line.substring(0,4)).equals("Rece")||(line.substring(0,4)).equals("Subj")||(line.substring(0,4)).equals("Refe")||(line.substring(0,4)).equals("Deli")||(line.substring(0,4)).equals("Retu"))==false) {
                            StringTokenizer splitter = new StringTokenizer(line.toLowerCase(), " <>,./;'[]?:{}|!@#$%^&*()_+-=\"\\0123456789");
                            while (splitter.hasMoreTokens()) {
                                String word = splitter.nextToken();
                                if(current.contains(word)==false) {
                                    if (hamMap.get(word) == null) {
                                        hamMap.put(word, 1.0);
                                        current.add(word);
                                    } else {
                                        hamMap.put(word, hamMap.get(word) + 1);
                                        current.add(word);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        hamFile=new File(file.getPath()+"/ham2");
        hamTestFiles = getEmails(hamFile,"");


        for(TestFile item : hamTestFiles) {
            if (item.getFileName()!="cmds") {
                current=new ArrayList();
                hamFiles+=1;
                String line = "";

                String a="";
                for(int x=0;x<hamFile.getAbsolutePath().split("/").length;x++)a=a+"/..";
                InputStream is = new FileInputStream(a+hamFile+"/"+item.getFileName());



                BufferedReader br=new BufferedReader(new FileReader(a+hamFile+"/"+item.getFileName()));


                while((line=br.readLine())!=null){
                    if(line.equals(""))continue;

                    if((line.charAt(0)==' '||line.charAt(0)=='>'||line.charAt(0)=='\t')==false && line.length()>=5) {
                        if(((line.substring(0,4)).equals("From")||(line.substring(0,4)).equals("Cont")||(line.substring(0,2)).equals("X-")||(line.substring(0,4)).equals("Send")||(line.substring(0,5)).equals("Error")||(line.substring(0,4)).equals("Prec")||(line.substring(0,4)).equals("Repl")||(line.substring(0,4)).equals("Cont")||(line.substring(0,4)).equals("Orig")||(line.substring(0,4)).equals("Date")||(line.substring(0,5)).equals("List-")||(line.substring(0,4)).equals("MIME")||(line.substring(0,4)).equals("Mess")||(line.substring(0,4)).equals("To: ")||(line.substring(0,4)).equals("Rece")||(line.substring(0,4)).equals("Subj")||(line.substring(0,4)).equals("Refe")||(line.substring(0,4)).equals("Deli")||(line.substring(0,4)).equals("Retu"))==false) {
                            StringTokenizer splitter = new StringTokenizer(line.toLowerCase(), " <>,./;'[]?:{}|!@#$%^&*()_+-=\"\\0123456789");
                            while (splitter.hasMoreTokens()) {
                                String word = splitter.nextToken();
                                if(current.contains(word)==false) {
                                    if (hamMap.get(word) == null) {
                                        hamMap.put(word, 1.0);
                                        current.add(word);
                                    } else {
                                        hamMap.put(word, hamMap.get(word) + 1);
                                        current.add(word);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        File spamFile=new File(file.getPath()+"/spam");
        ObservableList<TestFile> spamTestFiles = getEmails(spamFile,"");
        HashMap<String, Double>spamMap = new HashMap<String, Double>();
        int spamFiles=0;

        for(TestFile item : spamTestFiles) {
            try {
                current = new ArrayList();
                spamFiles += 1;
                String line = "";

                String a="";
                for(int x=0;x<spamFile.getAbsolutePath().split("/").length;x++)a=a+"/..";
                InputStream is = new FileInputStream(a + spamFile + "/" + item.getFileName());



                BufferedReader br=new BufferedReader(new FileReader(a+spamFile+"/"+item.getFileName()));


                while((line=br.readLine())!=null){
                    if(line.equals(""))continue;

                    if((line.charAt(0)==' '||line.charAt(0)=='>'||line.charAt(0)=='\t')==false && line.length()>=5) {
                        if(((line.substring(0,4)).equals("From")||(line.substring(0,4)).equals("Cont")||(line.substring(0,2)).equals("X-")||(line.substring(0,4)).equals("Send")||(line.substring(0,5)).equals("Error")||(line.substring(0,4)).equals("Prec")||(line.substring(0,4)).equals("Repl")||(line.substring(0,4)).equals("Cont")||(line.substring(0,4)).equals("Orig")||(line.substring(0,4)).equals("Date")||(line.substring(0,5)).equals("List-")||(line.substring(0,4)).equals("MIME")||(line.substring(0,4)).equals("Mess")||(line.substring(0,4)).equals("To: ")||(line.substring(0,4)).equals("Rece")||(line.substring(0,4)).equals("Subj")||(line.substring(0,4)).equals("Refe")||(line.substring(0,4)).equals("Deli")||(line.substring(0,4)).equals("Retu"))==false) {
                            StringTokenizer splitter = new StringTokenizer(line.toLowerCase(), " <>,./;'[]?:{}|!@#$%^&*()_+-=\"\\0123456789");
                            while (splitter.hasMoreTokens()) {
                                String word = splitter.nextToken();
                                if(current.contains(word)==false) {
                                    if (spamMap.get(word) == null) {
                                        spamMap.put(word, 1.0);
                                        current.add(word);
                                    } else {
                                        spamMap.put(word, spamMap.get(word) + 1);
                                        current.add(word);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch(Exception e){
                System.out.println(item.getFileName()+" error: "+e);
            }
        }


        HashMap<String, Double> totalMap=new HashMap<String, Double>();

        Iterator it = spamMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            if (hamMap.get(pair.getKey()) != null){
                double chance=(spamMap.get(pair.getKey())/spamFiles)/((spamMap.get(pair.getKey())/spamFiles)+(hamMap.get(pair.getKey())/hamFiles));
                totalMap.put(pair.getKey()+"",chance);
            }
            else{
                totalMap.put(pair.getKey()+"",1.0);
            }
            it.remove();
        }
        it = totalMap.entrySet().iterator();
        Vector<String> deleteMe=new Vector<String>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue().equals(1.0))deleteMe.add(pair.getKey()+"");
        }
        it=deleteMe.iterator();
        while(it.hasNext()){
            totalMap.remove(it.next());
        }
        return totalMap;
    }
    /**
     * returns a HashMap of the chances of each of the test emails being spam
     * @param fileName  the path of the directory where the test emails are located
     * @param spamMap   the HashMap of all the chances of words being spam generated by train
     * @return  hamTestFiles    an ObservableList of the test file classified by using spamMap
     * @see TestFile
     */
    public static ObservableList<TestFile> test(String fileName, HashMap<String, Double> spamMap)throws IOException{
        ObservableList<TestFile> hamResult=getEmails(new File(fileName),"");
        double propability=0;
        File file=new File(fileName);
        ObservableList<TestFile> hamTestFiles = getEmails(file,"");

        HashMap<String, Double>hamMap = new HashMap<String, Double>();
        List current=new ArrayList();


        for(TestFile item : hamTestFiles) {
            try {
                current = new ArrayList();
                String line = "";

                String a="";
                for(int x=0;x<file.getAbsolutePath().split("/").length;x++)a=a+"/..";

                BufferedReader br=new BufferedReader(new FileReader(a+file+"/"+item.getFileName()));

                while((line=br.readLine())!=null){
                    if(line.equals(""))continue;

                    if((line.charAt(0)==' '||line.charAt(0)=='>'||line.charAt(0)=='\t')==false && line.length()>=5) {
                        if(((line.substring(0,4)).equals("From")||(line.substring(0,4)).equals("Cont")||(line.substring(0,2)).equals("X-")||(line.substring(0,4)).equals("Send")||(line.substring(0,5)).equals("Error")||(line.substring(0,4)).equals("Prec")||(line.substring(0,4)).equals("Repl")||(line.substring(0,4)).equals("Cont")||(line.substring(0,4)).equals("Orig")||(line.substring(0,4)).equals("Date")||(line.substring(0,5)).equals("List-")||(line.substring(0,4)).equals("MIME")||(line.substring(0,4)).equals("Mess")||(line.substring(0,4)).equals("To: ")||(line.substring(0,4)).equals("Rece")||(line.substring(0,4)).equals("Subj")||(line.substring(0,4)).equals("Refe")||(line.substring(0,4)).equals("Deli")||(line.substring(0,4)).equals("Retu"))==false) {
                            StringTokenizer splitter = new StringTokenizer(line.toLowerCase(), " <>,./;'[]?:{}|!@#$%^&*()_+-=\"\\0123456789");
                            while (splitter.hasMoreTokens()) {
                                String word = splitter.nextToken();
                                if (current.contains(word) == false) {
                                    current.add(word);
                                }
                            }
                        }
                    }
                }

                double total = 0;
                //iterate through list of words which exist in TestFile and use that to claculate individual chance then classify
                for (int x = 0; x < current.size(); x++) {
                    if (spamMap.get(current.get(x)) != null) {
                        total += Math.log(1 - spamMap.get(current.get(x))) - Math.log(spamMap.get(current.get(x)));
                    }
                }
                total = 1 / (1 + Math.pow(Math.E, total));

                item.setSpamProbability(total);
                item.setActualClass("Ham");
                if (item.getFileName().charAt(0)=='s')
                    item.setActualClass("Spam");
                item.setPredictedClass("Ham");
                if(item.getSpamProbability()>=0.9999999)
                    item.setPredictedClass("Spam");
            }
            catch(Exception e){

                item.setSpamProbability(1.0);
                item.setActualClass(e+"");
            }
        }

        return hamTestFiles;
    }
    /**
     * returns an observable list of all file names in a directory
     * recursivly calls self for all sub directories
     * @param folder    directory to be searched
     * @param  deep     the path to the file from the first directory
     * @return items    an Observable list containing all the names of files in the directory and all sub directories
     */
    public static ObservableList<TestFile> getEmails(File folder, String deep) throws FileNotFoundException{
        File[] listOfFiles = folder.listFiles();
        ObservableList<TestFile> items = FXCollections.observableArrayList();

        for(int x=0;x<listOfFiles.length;x++){
            //System.out.println(listOfFiles[x].getName());
            if (listOfFiles[x].isDirectory()){
                items.addAll(getEmails(listOfFiles[x],listOfFiles[x].getName()+"/"+deep));
            }
            else {
                items.add(new TestFile(deep+listOfFiles[x].getName(),0,deep.split("/")[0]));
            }
        }

        return items;
    }
}
