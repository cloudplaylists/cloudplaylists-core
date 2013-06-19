package com.cloudplaylists.domain;

public class ProfileUpdate {

	private String displayName;
	private MediaProvider profileImageProvider;
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public MediaProvider getProfileImageProvider() {
		return profileImageProvider;
	}
	public void setProfileImageProvider(MediaProvider profileImageProvider) {
		this.profileImageProvider = profileImageProvider;
	}
	
}
