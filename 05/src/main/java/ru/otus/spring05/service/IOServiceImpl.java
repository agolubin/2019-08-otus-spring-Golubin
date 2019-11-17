package ru.otus.spring05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private Scanner scanner;

    public IOServiceImpl(){
        this.scanner = new Scanner(System.in);
    }

    public void printOut(String st) {
      System.out.println(st);
    }

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt() {
        return scanner.nextInt();
    }
}
