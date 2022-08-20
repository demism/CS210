/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author demis
 */
public class Final {
	public static void main(String[] args) throws FileNotFoundException {
String startWord = null;
String modWord = null;
char currentChar;
boolean foundDigit = false;
Scanner input = new Scanner(System.in);
System.out.println("Enter word:");
startWord = input.nextLine();

int count = 0;
int isDigit=0;
while(count < startWord.length())
{
currentChar = startWord.charAt(count);
if (Character.isDigit(currentChar)){
isDigit = count;
foundDigit = true;
}
count++;
}
if (foundDigit){
char[] charArray = startWord.toCharArray();
for (int i=0; i<isDigit; i++)
charArray[i] = startWord.charAt(isDigit);
modWord = new String(charArray);
}
System.out.println(modWord);
        }
}
    
