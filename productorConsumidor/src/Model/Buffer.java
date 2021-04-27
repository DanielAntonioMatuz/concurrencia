package Model;

import Model.File;

import java.util.Arrays;

public class Buffer {
    private File[] buffer;
    private final  int MAX= 10;

    public Buffer(){
        buffer = new File[MAX];
    }

    public void addFile(File file){
        int index = 0;
        while (buffer[index] != null)
            index++;
        buffer[index] = file;
    }

    public void deleteFile(){
        int index = 0;
        while (buffer[index] == null)
            index++;
        buffer[index] = null;
    }


    @Override
    public String toString() {
        return "Buffer{" +
                "buffer=" + Arrays.toString(buffer) +
                '}';
    }
}