package net.luna.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * FileHelper
 * 
 * @author @falscherIdiot
 * @version 2
 */
public class FileHelper {
    private String filePath;
    private ArrayList<String> content;
    private int fileLength;
    private long hashSum;

    /** Basic-Constructor */
    public FileHelper() {
        filePath = null;
        content = new ArrayList<String>();
        fileLength = 0;
        hashSum = 0;
    }

    /**
     * Reads File
     * 
     * @param filePath
     * @throws IOException when File does not exists or cannot be read
     */
    public void readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        resetArgs();

        int l = 0;
        String tmp;

        while ((tmp = br.readLine()) != null) {
            content.add(tmp);
            l++;
        }

        this.filePath = filePath;
        fileLength = l;
        hashSum = calHashSum(content);

        br.close();
    }

    /**
     * Writes into given File
     * 
     * @param filePath
     * @param content
     * @param append
     * @throws IOException
     */
    public void writeFile(String filePath, ArrayList<String> content, boolean append) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath), append));
        for (String line : content) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Writes into given File (SINGLE LINE!)
     * 
     * @param filePath
     * @param content
     * @param append
     * @throws IOException
     */
    public void writeFile(String filePath, String content, boolean append) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath), append));

        if (append) {
            bw.newLine();
            bw.write(content);
        } else {
            bw.write(content);
        }
        bw.close();
    }

    /**
     * Writes into given File and overrides Arguments
     * 
     * @param filePath
     * @param content
     * @param append
     * @throws IOException
     */
    public void writeSafedFile(String filePath, ArrayList<String> content, boolean append) throws IOException {
        writeFile(filePath, content, append);
        resetArgs();
        this.filePath = filePath;
        this.content = content;
        fileLength = 0;
        hashSum = calHashSum(content);
    }

    /**
     * Sets filePath, content, fileLength and hashSum to its default values
     */
    private void resetArgs() {
        this.filePath = null;
        content.clear();
        fileLength = 0;
        hashSum = 0;
    }

    /**
     * Calculates given Lists conentent into HashSum
     * 
     * @return hashSum of given content
     */
    private long calHashSum(ArrayList<String> content) {
        String tmp = "";
        for (String line : content) {
            tmp += line + "\n";
        }
        return tmp.hashCode();
    }

    /**
     * get filePath
     * 
     * @return String
     */
    public String gFilePath() {
        return filePath;
    }

    /**
     * get content
     * 
     * @return ArrayList<String>
     */
    public ArrayList<String> gContent() {
        return content;
    }

    /**
     * get fileLength
     * 
     * @return int
     */
    public int gFileLength() {
        return fileLength;
    }

    /**
     * get hashSum
     * 
     * @return long
     */
    public long gHashSum() {
        return hashSum;
    }
}