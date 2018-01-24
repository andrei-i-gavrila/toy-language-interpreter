package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ToyFile {

    private final Integer fileDescriptor;
    private final String filename;
    private final BufferedReader reader;

    public ToyFile(String filename, Integer fileDescriptor) throws ToyException {
        this.filename = filename;
        reader = tryOpenReader(filename);
        this.fileDescriptor = fileDescriptor;
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

    public String toString() {
        return filename;
    }

    public Integer getFileDescriptor() {
        return fileDescriptor;
    }
}
