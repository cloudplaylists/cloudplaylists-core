package com.cloudplaylists.domain;

/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.net.URLEncoder;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Michael Lavelle
 * 
 */
@MappedSuperclass
public class PlaylistDescriptor {

	@Id 
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(updatable = false, nullable = false)
	private String id;

	protected String name;

	protected String displayName;
	
	public void setPlaylistVisibility(PlaylistVisibility playlistVisibility) {
		
		switch (playlistVisibility)
		{
			case PRIVATE : setPrivatePlaylist(true);
			break;
			case PUBLIC_DISPLAYED_ON_PROFILE : 
			{
				this.setPrivatePlaylist(false);
				this.setHideOnProfile(false);
			}
			break;
			case PUBLIC_HIDDEN_ON_PROFILE :
			{
				this.setPrivatePlaylist(false);
				this.setHideOnProfile(true);
			}
			break;
		}
	}


	@Column(insertable = false, updatable = false)
	protected String userName;

	@Transient
	protected String url;

	@Column(columnDefinition = "tinyint", nullable = false)
	private boolean privatePlaylist;
	@Column(columnDefinition = "tinyint", nullable = false)
	@JsonIgnore
	private boolean deleted;
	@Column(columnDefinition = "tinyint", nullable = false)
	private boolean hideOnProfile;
	private Date addedDate;

	public PlaylistDescriptor() {
	}

	public PlaylistDescriptor(String userName, String name, String displayName) {
		this.userName = userName;
		this.name = name;
		this.displayName = displayName;
	}

	public PlaylistDescriptor(PlaylistDescriptor descriptor) {
		this.name = descriptor.getName();
		this.displayName = descriptor.getDisplayName();
		this.addedDate = descriptor.getAddedDate();
		this.hideOnProfile = descriptor.isHideOnProfile();
		this.deleted = descriptor.isDeleted();
		this.id = descriptor.getId();
		this.url = descriptor.getUrl();
		this.userName = descriptor.getUserName();
		this.privatePlaylist = descriptor.isPrivatePlaylist();
	}
	
	public PlaylistVisibility getPlaylistVisibility()
	{
		return  isPrivatePlaylist() ? PlaylistVisibility.PRIVATE :
			 (isHideOnProfile() ? PlaylistVisibility.PUBLIC_HIDDEN_ON_PROFILE : PlaylistVisibility.PUBLIC_DISPLAYED_ON_PROFILE);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPrivatePlaylist() {
		return privatePlaylist;
	}

	public void setPrivatePlaylist(boolean privatePlaylist) {
		this.privatePlaylist = privatePlaylist;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isHideOnProfile() {
		return hideOnProfile;
	}

	public void setHideOnProfile(boolean hideOnProfile) {
		this.hideOnProfile = hideOnProfile;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@SuppressWarnings("deprecation")
	public String getUrl() {
		return url != null ? url : "http://cloudplaylists.com/"
				+ URLEncoder.encode(getUserName()) + "/" + name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
