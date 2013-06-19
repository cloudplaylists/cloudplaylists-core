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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Michael Lavelle
 */
public class PlaylistUpdate {

	private String name;
	private PlaylistVisibility playlistVisibility;
	
	public PlaylistVisibility getPlaylistVisibility() {
		return playlistVisibility;
	}

	public void setPlaylistVisibility(PlaylistVisibility playlistVisibility) {
		this.playlistVisibility = playlistVisibility;
	}

	public PlaylistUpdate()
	{
		this.urls = new ArrayList<String>();
		this.playlistVisibility = PlaylistVisibility.PUBLIC_DISPLAYED_ON_PROFILE;
	}
	
	public PlaylistUpdate(String name)
	{
		this.urls = new ArrayList<String>();
		this.name = name;
	}
	
	public PlaylistUpdate(String name,PlaylistVisibility playlistVisibility)
	{
		this.urls = new ArrayList<String>();
		this.name = name;
		this.playlistVisibility = playlistVisibility;
	}
	
	public PlaylistUpdate(String name,String[] urls)
	{
		this.urls = Arrays.asList(urls);
		this.name = name;
	}
	
	public PlaylistUpdate(String name,String[] urls,PlaylistVisibility playlistVisibility)
	{
		this.urls = Arrays.asList(urls);
		this.name = name;
		this.playlistVisibility = playlistVisibility;
	}


	private List<String> urls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
