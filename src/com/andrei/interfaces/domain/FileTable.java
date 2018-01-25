package com.andrei.interfaces.domain;

import com.andrei.impl.domain.ToyFile;
import com.andrei.impl.domain.exceptions.ToyException;

import java.io.BufferedReader;
import java.util.Collection;

public interface FileTable {

    Integer openFile(String filename) throws ToyException;

    void closeFile(Integer fileDescriptor) throws ToyException;

    BufferedReader getFileReader(Integer fileDescriptor) throws ToyException;

    void closeAllFiles();

    Collection<ToyFile> getAllFiles();
}
