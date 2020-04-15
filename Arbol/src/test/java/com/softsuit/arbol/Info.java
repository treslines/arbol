package com.softsuit.arbol;

public class Info implements TwigInfo {

	private String description = "no info";
	
	public Info(String description) {
		this.description = description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}

}
