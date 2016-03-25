package utils;

import generics.Tuple;

import java.util.LinkedList;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;
//import org.omg.CORBA.CharSeqHolder;



public class StringUtilsTK {

	/*
	public static String toString(Object o) {
		String ret = ""; 
		String claz = o.getClass().getSimpleName();
		String box = StringUtils.repeat('-', claz.length()) + "-+\r\n";
		ret += claz+" |\r\n"+box;

		int maxLenght =0;
		for (Field q : o.getClass().getDeclaredFields()) 
			if (!q.isSynthetic())
				maxLenght = Math.max(maxLenght, q.getName().length());
		try {
			for (Field q : o.getClass().getDeclaredFields()) 
				if (!q.isSynthetic()) {
					Object val = q.get(o);
					String valStr;
					if (Object[].class.isInstance(val)) valStr = Arrays.toString((Object[]) val);
					else valStr = val+"";
					ret+=(String.format("%-"+maxLenght+"s", q.getName())+
							" \t= "+valStr+"\r\n");
				}
			
		} catch (Exception e) { 
			e.printStackTrace(); }
		
		return ret;
	}
	*/
	
	public static LinkedList<String> toLinesWithoutWordBreak(String input, int lineLength) {
		LinkedList<String> lines = new LinkedList<String>();
		String[] words = input.split(" ");
		String newLine = words[0];
		for (int w = 1; w<words.length; w++) {
			String word = words[w];
			if (newLine.length()+word.length()+1 > lineLength) 
			{
				lines.add(newLine);
				newLine = word;
			} else
			{
				newLine+= " "+word;
			}
		}
		lines.add(newLine);
		return lines;
	}
	
	public static String indent(String in) { return indent(in, "\t"); }
	public static String indent(String in, String indent) {
		String[] lines = lines(in);
		for (int l =0; l<lines.length; l++)
			lines[l] = indent+lines[l]+ "\r\n";
		return concatenate(lines);
	}
	
	public static String concatenate(String[] lines) {
		String ret = "";
		for (String line : lines)
			ret += line;
		return ret;
	}
	public static String[] lines(String in) {
		return in.split("\\r?\\n");
	}

	public static String subString(String or, String from, String to) {
		int i = or.indexOf(from);
		int j = or.indexOf(to,i);
		return or.substring(i, j);
	}
	
	
	public static String subString(String original, String regex) {
		int end;
		boolean found = false;
		for (end = 1; end<original.length(); end++) {
			if (original.substring(0, end).matches(regex)) {
				found = true;
			}
			else if (found) return original.substring(0, end-1);
		}
		if (original.substring(0, original.length()-1).matches(regex)) return original.substring(0, original.length()-1);
		if (original.substring(0, original.length()).matches(regex)) return original;
		return "";
	}

	
	
	/**
	 * This method also skips java comments
	 * Note: this method assumes brackets to be consistent
	 * @param or the String on which to operate
	 * @param bracketOpen the openings bracket
	 * @param bracketClose the closing bracket
	 * @return the string between the first bracketOpen and its corresponding bracketClose
	 */
	public static String getStringWithinBrackets(String or, char bracketOpen, char bracketClose) {
		Tuple<Integer, Integer> tup = getIndicesOfStringWithinBrackets(or, bracketOpen, bracketClose, 0);
		return  or.substring(tup.fst, tup.snd);
	}
	/**
	 * This method also skips java comments
	 * Note: this method assumes brackets to be consistent
	 * @param or the String on which to operate
	 * @param bracketOpen the openings bracket
	 * @param bracketClose the closing bracket
	 * @return the first and last index of the substring between the first bracketOpen and its corresponding bracketClose
	 */
	public static Tuple<Integer, Integer> getIndicesOfStringWithinBrackets(String or, char bracketOpen, char bracketClose, int from) {
//		if (or==null) return null;
		
		String open = "{[<(";
		String close = "}]>)";
		int l = or.length();
				
		int pos;
		int depth = 0;
		boolean foundFirst = false;
		int depthFirst = -1;
		for (pos = from; pos<or.length(); pos++) {
			char now = or.charAt(pos);
			if (now=='\'' && or.charAt(pos-1)!='\\') {
				char prev = '\\';
				while (now!='\'' || prev == '\\') {pos++; prev = now; now = or.charAt(pos); if (pos==l) 
					return null;} 
				continue;
			}
			if (now=='\"' && or.charAt(pos-1)!='\\') {
				char prev = '\\';
				while (now!='\"' || prev == '\\') { pos++; prev = now; now = or.charAt(pos); if (pos==l) 
					return null;} 
				continue;
			}
			if (now=='/' && or.charAt(pos+1)=='/') {
				while (!(now=='\n' || now=='\r')) {
					pos++; 
					now = or.charAt(pos);
					if (pos==l) 
						return null;} 
				continue;
			}
//			if (now=='/') System.out.println(">>"+or.charAt(pos+1)+"<<");
			if (now=='/' && or.charAt(pos+1)=='*') {
				char prev = ' ';
				while (!(now=='/' && prev == '*')) { pos++; prev = now; now = or.charAt(pos); if (pos==l) 
					return null;} 
				continue;
			}

//			System.out.println(">>"+or.substring(pos, pos+10)+"<<");
//			if (now==bracketOpen) 
//				System.out.println(or.substring(pos, pos+10));
			if (now==bracketOpen && !foundFirst) { 
				foundFirst = true; from = pos; depthFirst= depth-1; continue;}
			if (open.indexOf(now)!=-1) depth++;
			if (close.indexOf(now)!=-1) depth--;
			if (depth==depthFirst && foundFirst) 
				return new Tuple<Integer,Integer>(from+1, pos);
						// && now==bracketClose
			if (pos==l) 
				return null;
			
		} 
		return null;
	}
	
	public static void test_getStringWithinBrackets() {
		System.out.println("//hgf[hdhsdf\r\nahdhfda/*sd[ghgd*/dgsgd[fhd]gd \t::\t>>"+getStringWithinBrackets("//hgf[hdhsdf\r\nahdhfda/*sd[ghgd*/dgsgd[fhd]gd",'[',']')+"<<");
		System.out.println(">>d(s{\"f[s}\"}aa)aaa<<\t::\t>>"+getStringWithinBrackets("d(s{\"f[s}\"}aa)aaa",'(',')')+"<<");
		System.out.println(">>daa<<\t::\t>>"+getStringWithinBrackets("daa",'(',')')+"<<");
		System.out.println(">><<\t::\t>>"+getStringWithinBrackets("",'(',')')+"<<");
	}
	
//	public static int indexOf_noComments(String str, String find) {
//		int ret = -1;
//		while (true) {
//			str.indexOf(find);
//			if (str.indexOf("//")))
//		}
//		
//	}
	public static void main(String[] args) {

	}

}
