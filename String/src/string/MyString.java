package string;

/**
 * @imutable 
 * */
public class MyString {
	/**
	 * @representationobject chars
	 * */
	private char[] chars;
	public int length() {
		return chars.length;
	}
	public static MyString valueOf(char c) {
		char[] newcs = new char[] { c };
		return new MyString(newcs);
	}
	public MyString concat (MyString s) {
		char[] newcs = new char[length() + s.length()];
		for (int i=0;i<length(); ++i) {
			newcs[i] = chars[i];
		}
		for (int i = 0; i < s.length(); i++) {
			newcs[length()+i] = s.chars[i];
		}
		return new MyString(newcs);
	}
	public char charAt(int i) {
		return chars[i];
	}
	
	public char[] toCharArray() {
		return chars.clone(); //this prevents the client to modify our object
	}
	public MyString(char[] cs) {
		chars = cs.clone();
	}
}
