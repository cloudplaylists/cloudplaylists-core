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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Michael Lavelle
 * 
 */
@Entity(name = "Playlist")
public class Playlist extends PlaylistDescriptor {

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Media.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	@org.hibernate.annotations.IndexColumn(name = "position")
	@JoinTable(name = "Playlist_PlaylistMedia", joinColumns = @JoinColumn(name = "Playlist_id"), inverseJoinColumns = @JoinColumn(name = "playlistMedia_url"))
	// @Size(min=1,message="{tracks.empty}")
	private List<Media> media;

	@ManyToOne
	@JoinColumn(name = "userName")
	@JsonIgnore
	private User user;

	@SuppressWarnings("deprecation")
	public String getUrl() {
		return url != null ? url : ("http://cloudplaylists.com/"
				+ URLEncoder.encode(getUserName()) + "/" + name);
	}

	public Playlist() {
	}

	public Playlist(Playlist playlist) {
		super(playlist);
		this.user = playlist.getUser();
		this.media = playlist.getMedia();
	}

	@Override
	public String getUserName() {
		return user != null ? user.getUserName() : userName;
	}

	public User getUser() {
		return user;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
