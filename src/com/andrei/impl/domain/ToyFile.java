package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;

import java.io.*;

public class ToyFile {

    private final String filename;
    private final BufferedReader reader;
    
    public ToyFile(String filename) throws ToyException{
        this.filename = filename;
        reader = tryOpenReader(filename);
    }

    private BufferedReader tryOpenReader(String filename) throws ToyException {
        try {
            return new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new ToyException(e.getMessage());
        }
    }

    public void close() throws ToyException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new ToyException(e.getMessage());
        }
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return filename;
    }
}
