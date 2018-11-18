import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class WordSearch{
  private char[][]data;
  private int seed;
  private Random randgen;
  private ArrayList<String>wordsToAdd;// all words from a text file get added indicating they have not yet been added
  private ArrayList<String>wordsAdded;// all words that were successfully added get added to wordsAdded
  private boolean answer;
  public WordSearch(int row,int cols,String filename,int seed,boolean answer)throws FileNotFoundException {
	  this.seed=seed;
	  this.answer=answer;
	  if(row<=0||cols<=0) {
		  throw new IllegalArgumentException("row and cols must be greater than 0");
	  }
	  if(seed<0 || seed>10000) {
		  throw new IllegalArgumentException();
		  
	  }
	  data=new char[row][cols];
	  this.clear();
	  File file= new File(filename);
	  wordsAdded=new ArrayList<String>();
	  wordsToAdd=new ArrayList<String>();
	  Scanner in=new Scanner(file);
	  randgen=new Random(this.seed);
	  while(in.hasNext()) {
		  wordsToAdd.add(in.next().toLowerCase());
	  }
	  this.addAllWords();
	  String contains="";
	  for(int i=0;i<wordsAdded.size();i++) {
		  contains+=wordsAdded.get(i);// clears all the elements in wordsAdded
	  }
	  wordsAdded.clear();
	  for(int i=0;i<wordsToAdd.size();i++) {
		  if(contains.contains(wordsToAdd.get(i))) {
			  wordsAdded.add(wordsToAdd.get(i));
		  }
	  } 
  }
  public WordSearch(int row,int cols,String filename,boolean answer)throws FileNotFoundException {
	  this.answer=answer;
	  if(row<=0||cols<=0) {
		  throw new IllegalArgumentException("row and cols must be greater than 0");
	  }
	  data=new char[row][cols];
	  this.clear();
	  File file= new File(filename);
	  wordsAdded=new ArrayList<String>();
	  wordsToAdd=new ArrayList<String>();
	  Scanner in=new Scanner(file);
	  this.seed=(int)(Math.random()*10000);
	  randgen=new Random(seed);
	  while(in.hasNext()) {
		  wordsToAdd.add(in.next().toLowerCase());
	  }
	  this.addAllWords();
	  String contains="";
	  for(int i=0;i<wordsAdded.size();i++) {
		  contains+=wordsAdded.get(i);// clears all the elements in wordsAdded
	  }
	  wordsAdded.clear();
	  for(int i=0;i<wordsToAdd.size();i++) {
		  if(contains.contains(wordsToAdd.get(i))) {
			  wordsAdded.add(wordsToAdd.get(i));
		  }
	  } 
  }
  private void addAllWords() {
	  ArrayList<Integer> list=new ArrayList<Integer>();
	  for(int i=0;i<wordsToAdd.size();i++) {
	  int wordIndex=randgen.nextInt(wordsToAdd.size());
	  while(list.contains(wordIndex)) {
		  wordIndex=randgen.nextInt(wordsToAdd.size());// makes sure it doesn't have the same index twice
	  }
	  list.add(wordIndex);// adds the new index to the list
	  String word=wordsToAdd.get(wordIndex);
	  int rowIncrement=randgen.nextInt()%2;// assigns either 0,1 or -1 to the icrements
	  int colIncrement=randgen.nextInt()%2;
	  if(rowIncrement==0&& colIncrement==0) {
		  while(rowIncrement==0&& colIncrement==0) {
		  rowIncrement=randgen.nextInt()%2;
		  colIncrement=randgen.nextInt()%2;
		  }
	  }
	  int row=0;
	  int col=0;
	  for(int index=row;index<data.length;index++) {
		 for(int index2=col;index2<data[0].length;index2++) {
			 if(this.addWord(word,index,index2,rowIncrement,colIncrement)) {
				wordsAdded.add(word); 
				break;
			 }
		 }
		 if(wordsAdded.contains(word)) {
			 break;
		 }
		  
	   }
	  }
  }
	  
  private void clear() {// remember to turn this into private
	  for(int i=0;i<data.length;i++) {
		  for(int index=0;index<data[i].length;index++) {
			  data[i][index]='_';
		  }
	  }
  }
  public String toString() {
          if(wordsAdded.size()==0){
             return "grid was too small,make it bigger";
          }
	  String result="";
	  String answer="";
	  for(int i=0;i<data.length;i++) {
		  result+="|";
		  for(int index=0;index<data[i].length;index++) {  //the puzzle with dashed
			  result+=data[i][index]+" ";
		  }result+="|"+"\n";
	  }
	  for(int i=0;i<wordsAdded.size()-1;i++) { // adds the words
		  result+=wordsAdded.get(i)+",";
	  }
	  result+=wordsAdded.get(wordsAdded.size()-1);
	  result+="(seed: "+this.seed+")";
	  for(int i=0;i<data.length;i++) {
		  for(int index=0;index<data[0].length;index++) { //the puzzle witout dashes
			  if(data[i][index]=='_') {
				  data[i][index]=(char)(randgen.nextInt(25)+97);
			  }
		  }
	  }
	  
	  for(int i=0;i<data.length;i++) {
		  answer+="|";
		  for(int index=0;index<data[0].length;index++) {
			  answer+=" "+data[i][index];
		  }answer+="|"+"\n";
		  
	  }answer
	  +="words: ";
	  for(int i=0;i<wordsAdded.size()-1;i++) {
		  answer+=wordsAdded.get(i)+",";
	  }
	  answer+=wordsAdded.get(wordsAdded.size()-1);
	  answer+="(seed: "+ this.seed+")";
	  if(!this.answer) {
	  return answer;
	  }
	  else {
		  return result;
	  }
  }
  //turn all add methods except the real one into private and make all print statements invisible
  private boolean addWord(String word,int r,int c,int rowIncrement,int colIncrement) {
	  // index out of bounds exceptions
	  if(r>=data.length) {
		  throw  new IndexOutOfBoundsException("row is out of bounds");
	  }
	  if(c>=data[0].length) {
		  throw new IndexOutOfBoundsException("column is out bounds");
	  }
	  if(rowIncrement !=1&&rowIncrement!=-1&&rowIncrement!=0) {
		  throw new IllegalArgumentException("rowIncrement is invalid");
	  }
	  if(colIncrement!=1&&colIncrement!=0&&colIncrement!=-1) {
		  throw new IllegalArgumentException("colIncrement is invalid");
	  }
	   if(rowIncrement==0 && colIncrement==0) {
		   return false;
	   }// exception ends here
	   if(rowIncrement==1) {
		   if(word.length()>data.length-r) {  //checks if the word fits in the row
			   return false;
		   } 
	   }
	   else if(rowIncrement==-1) {
		   if(word.length()>r+1) {
			   return false;
		   }   // row cases end here
	   }
	   if(colIncrement==1) {
		   if(word.length()>data[r].length-c) {  // checks if the word fits in the column
			   return false;
		   }
	   }
	   else if(colIncrement==-1) {
		   if(word.length()>c+1) {
			   return false;//    column checking ends here
		   }
	   }
	   for(int i=0;i<word.length();i++) {
		   if(data[r+rowIncrement*i][c+colIncrement*i]!=word.charAt(i) && data[r+rowIncrement*i][c+colIncrement*i]!='_') {
			   return false;
		   }
	   }
	   for(int i=0;i<word.length();i++) {
		  data[r+rowIncrement*i][c+colIncrement*i]=word.charAt(i);
	   }return true;
	   
	   
  }
  private boolean addWordHorizontal(String word,int row,int column) {// the method is thoroughtly checked
	 //checks to see if the word fits the grid
	 if(word.length()>data[row].length-column||row>=data.length) {
		 return false;
	 }
	 // checks to see if the corresponding letters match the char in the word
	 for(int i=column;i<column+word.length();i++) {
		 if(data[row][i]!='_'&&data[row][i]!=word.charAt(i-column)) {
			 return false;
		 }
	 }
	 //now adds the chars in word at the specified location
	 for(int i=column;i<column+word.length();i++) {
		 data[row][i]=word.charAt(i-column);
	 }return true;
	 
  }
  private boolean addWordVertical(String word,int row,int column) {// not the easiest algorithm to read
	                                                              // for future reference see the diagonal algorithm and use
	                                                              // a similiar pattern
	//checks to see if the word fits the grid
		 if(word.length()>data.length-row||column>=data[row].length) {
			 return false;
		 }
		 // checks to see if the corresponding letters match the char in the word
		 for(int i=row;i<row+word.length();i++) {
			 if(data[i][column]!='_'&&data[i][column]!=word.charAt(i-column)) {
				 return false;
			 }
		 }
		 //now adds the chars in word at the specified location
		 for(int i=row;i<row+word.length();i++) {
			 data[i][column]=word.charAt(i-row);
		 }return true;
		 
	  }
    private  boolean addWordDiagonal(String word,int row,int column) {
    	if(word.length()>data.length-row||word.length()>data[row].length-column) {
    		return false;
    	}
    	for(int i=0;i<word.length();i++) {
    		if(data[row+i][column+i]!='_'&&data[row+i][column+i]!=word.charAt(i)) {
    			return false;
    		}
    	}
    	for(int i=0;i<word.length();i++) {
    		data[row+i][column+i]=word.charAt(i);
    	}return true;
    }
    public static void main(String[]args) {
    	if (args.length<3){
    		System.out.println("you need have at least three arguments:row,col,filename");
    		System.exit(1);
    	}
    	try {
    		if(args.length==3) {
    			int row=Integer.parseInt(args[0]);
    			int cols=Integer.parseInt(args[1]);
    			WordSearch beta=new WordSearch(row,cols,args[2],false);
    			System.out.println(beta);
    		}
    	
    		else {
    			int row=Integer.parseInt(args[0]);
    			int cols=Integer.parseInt(args[1]);
    			int seed=Integer.parseInt(args[3]);
    			boolean answer=false;
    			if(args.length>4) {
    				if(args[4].equals("key")) {
    					answer=true;
    				}
    			}
    	         WordSearch beta=new WordSearch(row,cols,args[2],seed,answer);
    	         System.out.println(beta);
    		}     
    		
    	}
    	catch(FileNotFoundException e) {
    	System.out.println("filename not found");	
    	}
    	catch(IllegalArgumentException e) {
    		System.out.println("seed has to be between [0,10000] && row or cols can't be 0");
    	}
    }
  }
