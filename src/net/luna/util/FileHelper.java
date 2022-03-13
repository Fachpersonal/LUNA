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
    private File file;
    private ArrayList<String> content;
    private int fileLength;

    /** Basic-Constructor */
    public FileHelper() {
        file = null;
        content = new ArrayList<String>();
        fileLength = 0;
    }

    /**
     * Reads File
     * 
     * @param userFile
     * @throws IOException when File does not exists or cannot be read
     */
    public void readFile(File userFile) throws IOException {
        file = userFile;
        BufferedReader br = new BufferedReader(new FileReader(file));
        content.clear();
        int l = 0;
        String tmp;

        while ((tmp = br.readLine()) != null) {
            content.add(tmp);
            l++;
        }

        fileLength = l;

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
        file = new File(filePath);
        this.content = content;
        fileLength = 0;
    }

    /**
     * Sets file, content, fileLength to its default values
     */
    public void unloadSafedFile() {
        file = null;
        content.clear();
        fileLength = 0;
    }

    /**
     * get file
     * 
     * @return File
     */
    public File gFile() {
        return file;
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

}