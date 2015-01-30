package com.intere.rcp.boggle.core.model;

public class UIPlayer extends Player {
	private PairedList<String, Integer> wordList = new PairedList<>();
	
	public UIPlayer() {
		super();
	}
	
	public UIPlayer(String username) {
		super(username);
	}
	
	@Override
	public PairedList<String, Integer> getWordList() {
		return wordList;
	}
}
