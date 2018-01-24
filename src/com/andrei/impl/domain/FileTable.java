package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.utils.RandomSequenceProvider;
import com.andrei.interfaces.domain.Dictionary;
import com.andrei.interfaces.domain.IFileTable;
import com.andrei.interfaces.utils.NumberSequenceProvider;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;

public class FileTable implements IFileTable {
    private final NumberSequenceProvider descriptorProvider = new RandomSequenceProvider();

    private final Dictionary<Integer, ToyFile> files = new ToyDictionary<>();



    public Integer openFile(String filename) throws ToyException {

        int currentFileDescriptor = descriptorProvider.next();

        ToyFile file = new ToyFile(filename, currentFileDescriptor);
        files.put(currentFileDescriptor, file);

        return currentFileDescriptor;
    }

    public void closeFile(Integer fileDescriptor) throws ToyException {
        ToyFile file = tryGetFile(fileDescriptor);
        file.close();
        files.remove(fileDescriptor);
    }

    public BufferedReader getFileReader(Integer fileDescriptor) throws ToyException {
        return tryGetFile(fileDescriptor).getReader();
    }

    public void closeAllFiles() {
        new ArrayList<>(files.keySet()).forEach(fileDescriptor -> {
            try {
                closeFile(fileDescriptor);
            } catch (ToyException e) {
                System.err.println("Error closing file " + e.getMessage());
            }
        });
    }

    public Collection<ToyFile> getAllFiles() {
        return files.values();
    }

    private ToyFile tryGetFile(Integer fileDescriptor) throws ToyException {
        if (!files.containsKey(fileDescriptor)) {
            throw new ToyException("File descriptor not found");
        }

        return files.get(fileDescriptor);
    }

    public String toString() {
        return files.toString();
    }
}
