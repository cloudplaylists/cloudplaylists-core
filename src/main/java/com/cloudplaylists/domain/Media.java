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

import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * @author Michael Lavelle
 * 
 */
@Entity(name = "Media")
public class Media {

	@Id
	private String url;
	private Date addedDate;
	private String title;

	@SuppressWarnings("deprecation")
	@JsonIgnore
	public String getEncodedUrl() {
		return URLEncoder.encode(getUrl());
	}
	
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
