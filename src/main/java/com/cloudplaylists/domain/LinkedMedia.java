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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Michael Lavelle
 */
@Entity
public class LinkedMedia {

	@SuppressWarnings("unused")
	@Id
	@JsonIgnore
	private LinkedMediaId id;
	
	@ManyToOne
	@JsonIgnore
	private Media linkedMedia;
	
	public LinkedMedia()
	{
		
	}
	
	@SuppressWarnings("unused")
	@ManyToOne
	@JoinColumn(name="url",insertable=false,updatable=false)
	@JsonIgnore
	private Media sourceMedia;
	
	private String providerMediaId;
	
	public String getProviderMediaId() {
		return providerMediaId;
	}

	public void setProviderMediaId(String providerMediaId) {
		this.providerMediaId = providerMediaId;
	}
	
	public LinkedMedia(Media sourceMedia,Media targetMedia)
	{
		this.sourceMedia = sourceMedia;
		this.providerMediaId = targetMedia.getProviderMediaId();
		this.id = new LinkedMediaId(sourceMedia,targetMedia);
		this.linkedMedia = targetMedia;
	}

	@JsonIgnore
	public Media getLinkedMedia() {
		return linkedMedia;
	}
	
}
