package com.qait.gauravjain.unix;

import java.io.*;
import java.lang.ProcessBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandExecutor {
	private static String output(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}
	
	public String execCommand(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder(args);
			Process process = pb.start();
			process.waitFor();
			if(process.exitValue()==0){
				return CommandExecutor.output(process.getInputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String execCommand(String[] args, String directory) {
		try {
			ProcessBuilder pb = new ProcessBuilder(args);
			pb.directory(new File(directory));
			Process process = pb.start();
			process.waitFor();
			if(process.exitValue()==0){
				return CommandExecutor.output(process.getInputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]){
		String[] commands = {"git","log"}; 
		CommandExecutor cm = new CommandExecutor();
		String output = cm.execCommand(commands,"test/");
		String line[] = output.split("\n");
		String word[]=line[0].split(" ");
		System.out.println(word[1]);
	}
	
}