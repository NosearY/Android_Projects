package com.tea.launcher.util;

public class TeaProducts {

	private String[] imagePathStr = {};//such as http://www.XXX.com, just list of URL
	private String[] teaNameStr = {};//list of tea name
	private String[] hrefStr = {};
	private boolean isAccessible = true;
	private int countOfLoading = 0;
	private boolean isEndLoading = false;
	private boolean isStartLoading = true;
	public TeaProducts(){
		
	}
	
	public TeaProducts(String[] imagePathStr, String[] teaNameStr){
		this.imagePathStr = imagePathStr;
		this.teaNameStr = teaNameStr;
	}
	
	
	public void setImagePathStr(String[] imagePathStr){
		this.imagePathStr = imagePathStr;
	}
	
	public String[] getImagePathStr(){
		return this.imagePathStr;
	}
	
	public void setTeaNameStr(String[] teaNameStr){
		this.teaNameStr = teaNameStr;
	}
	
	public String[] getTeaNameStr(){
		return this.teaNameStr;
	}
	
	public void setHrefStr(String[] hrefStr){
		this.hrefStr = hrefStr;
	}
	
	public String[] getHrefStr(){
		return this.hrefStr;
	}
	
	public void setAccessible(boolean isAccessible){
		this.isAccessible = isAccessible;
	}
	
	public boolean getAccessible(){
		return this.isAccessible;
	}
	
	public int getCountOfLoading(){
		return this.countOfLoading;
	}
	
	public void setCountOfLoading(int countOfLoading){
		this.countOfLoading = countOfLoading;
	}
	
	public boolean getIsEndLoading(){
		return this.isEndLoading;
	}
	
	public void setIsEndLoading(boolean isEndLoading){
		this.isEndLoading = isEndLoading;
	}
	
	public boolean getIsStartLoading(){
		return this.isStartLoading;
	}
	
	public void setIsStartLoading(boolean isStartLoading){
		this.isStartLoading = isStartLoading;
	}
}
