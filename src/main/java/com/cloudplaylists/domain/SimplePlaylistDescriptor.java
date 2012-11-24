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

/**
 * @author Michael Lavelle
 * 
 */
public class SimplePlaylistDescriptor implements PlaylistDescriptor {

	private String name;
	private String displayName;
	private String userName;

	public SimplePlaylistDescriptor(String userName, String name,
			String displayName) {
		this.userName = userName;
		this.name = name;
		this.displayName = displayName;
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
	@Override
	public String getUrl() {
		return "http://cloudplaylists.com/" + URLEncoder.encode(getUserName())
				+ "/" + name;
	}

}
