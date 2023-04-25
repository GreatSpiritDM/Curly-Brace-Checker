/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabAssignment5;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

/**
 *
 * @author 
 */
public class CurlyCheckerIO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("SportsTeam1.java");// check(file); //make these sout that say sportsteam1 correct brackets? T/F and so forth
        System.out.println("First file, "+file+", has correct curly braces? " +check(file));
        File file2 = new File("SportsTeam2.java");
        //check(file2);
        System.out.println("First file, "+file2+", has correct curly braces? " +check(file2));
        // File file3 = new File("example.java"); //Comment this out later
        // System.out.println("First file, "+file3+", has correct curly braces? " +check(file3));
        //check(file3);
        
        
        
    }
    public static boolean check(File file){ // Move all of your logic here.
        Scanner scan = new Scanner(System.in);
        String initialFile = "";
        try{
            scan = new Scanner(file);
            System.out.println("File loaded");
        } catch(FileNotFoundException fne){
            System.out.println(file + " not found");
            System.exit(0);
        }
        // System.out.println("Our file:");    
        // Assemble a String to work on
        while (scan.hasNext()){
            // System.out.println(scan.nextLine());
            initialFile += scan.nextLine()+"\n";
        }
        // System.out.println(initialFile);
        //System.out.println(strip(initialFile)); // Comment this out
        char[] array = strip(initialFile).toCharArray();
        Stack<Character> stack = new Stack();
        System.out.println("Scanning the char array");
        for (int i = 0; i < array.length; i++) {
            if (array[i]=='{' && !(stack.isEmpty())){
                if (stack.contains('{')){
                    stack.push('{');
                } else{
                    stack.pop();
                }
            }
            else if (array[i]=='}' && !(stack.isEmpty())){
                if (stack.contains('}')){
                    stack.push('}');
                } else{
                    stack.pop();
                }
            } else if (array[i]=='{'){
                stack.push('{');
            } else if (array[i]=='}'){
                stack.push('}');
            }
            
        } 
        
        // Now we add the logic for the char array and the stack
        // assign strip(initialFile) as a char array and then iterate through it
        // when you hit a {, put a { on the stack or remove a } from the stack
        // when you hit a }, remove a { from the stack or put a } on the stack
        // Then if the stack is empty afterwards, return true. else, return false.
        
        return(stack.isEmpty()); //if so, returns true, else returns false
        
        
        
        
    }
    public static String strip(String initialFile){ // This removes the strings and block comments
            String finalFile = "";
            String quoteString = "\"[^\"]*\""; // This regular expression matches Strings (things in double quotes, which should always be Strings
            Pattern quotePattern = Pattern.compile(quoteString);
            Matcher matcher = quotePattern.matcher(initialFile);
            finalFile = initialFile.replaceAll(quoteString, "");
            
            // Add regex for '{' and '}'
            String charString = "'[\\}\\{]'"; //This regular expression finds  '{' or '}' and unlike the other regex, I wrote it myself :D
            Pattern charPattern = Pattern.compile(charString);
            matcher = charPattern.matcher(finalFile);
            finalFile = finalFile.replaceAll(charString, "");
            
            String commentString = "/\\*(.|[\\r\\n])*?\\*/"; // This regular expression matches block comments.
            Pattern commentPattern = Pattern.compile(commentString);
            matcher = commentPattern.matcher(finalFile);
            finalFile = finalFile.replaceAll(commentString, "");
        
            String inlineString = "//.*"; // Regex for inline comments  
            Pattern inlinePattern = Pattern.compile(inlineString);
            matcher = inlinePattern.matcher(finalFile);
            finalFile = finalFile.replaceAll(inlineString, "");
            
            return finalFile;
    }
}
