
public class WordSearch {
  private char[][]data;
  private int row;
  private int cols;
  public WordSearch(int row,int cols) {
	  data=new char[row][cols];
	  this.row=row;
	  this.cols=cols;
  }
  private void clear() {
	  char[][]newdata=new char[row][cols];
	  data=newdata;
  }
  public String toString() {
	  String result="";
	  for(int i=0;i<data.length;i++) {
		  for(int index=0;index<data[i].length;index++) {
			  result+=data[i][index]+" ";
		  }result+="/n";
	  }
	  return result;
  }
  public boolean addWordHorizontal(String word,int row,int column) {
	  for(int i=0;i<word.length();i++) {
		  if(i>=this.cols) {
			  return false;
		  }
		  data[row][column+i]=word.charAt(i);
	  }return true;
  }
  public boolean addWordVertical(String word,int row,int column) {
	  for(int i=0;i<word.length();i++) {
		  if(i>=this.row) {
			  return false;
		  }
		  data[row+i][column]=word.charAt(i);
	  }return true;
  }
  public boolean addWordDiagonal(String word,int row,int column) {
	 int nrow=row;
	 int ncol=column;
	 char[] realword=word.toCharArray();
	 for(int i=0;i<realword.length;i++) {
		 data[nrow][ncol]=realword[i];
		 nrow++;
		 ncol++;
		 if(nrow>=this.row||ncol>=this.cols) {
			return false; 
		 }
	   }return true;
	 }
}

