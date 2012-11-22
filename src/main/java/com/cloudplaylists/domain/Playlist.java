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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
/**
 * @author Michael Lavelle
 * 
 */
@Entity(name = "Playlist")
public class Playlist {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(updatable = false, nullable = false)
	private String id;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = PlaylistMedia.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@org.hibernate.annotations.IndexColumn(name = "position")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	// @Size(min=1,message="{tracks.empty}")
	private List<PlaylistMedia> playlistMedia;

	@ManyToOne
	@JoinColumn(name = "userName")
	@JsonIgnore
	private User user;

	@Transient
	private String userName;
	
	@Transient
	private String url;

	@Column(columnDefinition = "tinyint", nullable = false)
	private boolean privatePlaylist;
	@Column(columnDefinition = "tinyint", nullable = false)
	@JsonIgnore
	private boolean deleted;
	@Column(columnDefinition = "tinyint", nullable = false)
	private boolean hideOnProfile;
	private Date addedDate;

	public String getUserName() {
		return user == null ? userName : user.getUserName();
	}

	@SuppressWarnings("deprecation")
	public String getUrl() {
		return url != null ? url : ("http://cloudplaylists.com/" + URLEncoder.encode(getUserName()) + "/" + name);
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private String displayName;

	public List<PlaylistMedia> getPlaylistMedia() {
		return playlistMedia;
	}

	public void setPlaylistMedia(List<PlaylistMedia> playlistMedia) {
		this.playlistMedia = playlistMedia;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
