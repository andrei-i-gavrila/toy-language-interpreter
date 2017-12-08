package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.IDictionary;
import com.andrei.interfaces.domain.IFileTable;

import java.io.BufferedReader;

public class FileTable implements IFileTable {
    private static int NEXT_DESCRIPTOR = 1;

    private final IDictionary<String, ToyFile> fileNames;
    private final IDictionary<Integer, ToyFile> fileDescriptors;


    FileTable() {
        fileDescriptors = new Dictionary<>();
        fileNames = new Dictionary<>();
    }

    private boolean isFileOpened(String filename) {
        return fileNames.has(filename);
    }


    @Override
    public Integer openFile(String filename) throws ToyException {
        if (isFileOpened(filename)) {
            throw new ToyException("File already opened");
        }

        int currentFileDescriptor = NEXT_DESCRIPTOR++;

        ToyFile file = new ToyFile(filename);
        fileDescriptors.put(currentFileDescriptor, file);
        fileNames.put(filename, file);

        return currentFileDescriptor;
    }

    @Override
    public void closeFile(Integer fileDescriptor) throws ToyException {
        ToyFile file = tryGetFile(fileDescriptor);
        file.close();
        fileNames.remove(file.getFilename());
        fileDescriptors.remove(fileDescriptor);
    }

    @Override
    public BufferedReader getFileReader(Integer fileDescriptor) throws ToyException {
        return tryGetFile(fileDescriptor).getReader();
    }

    @Override
    public void closeAllFiles() {
        this.fileDescriptors.keySet().forEach(fileDescriptor -> {
            try {
                closeFile(fileDescriptor);
            } catch (ToyException e) {
                System.err.println("Error closing file " + e.getMessage());
            }
        });
    }

    private ToyFile tryGetFile(Integer fileDescriptor) throws ToyException {
        if (!fileDescriptors.has(fileDescriptor)) {
            throw new ToyException("File descriptor not found");
        }
        return fileDescriptors.get(fileDescriptor);
    }

    @Override
    public String toString() {
        return fileDescriptors.entrySet().stream()
                .map(entry -> entry.getValue() + " : " + entry.getKey())
                .reduce((result, current) -> result + "\n" + current)
                .orElse("Empty");
    }
}
