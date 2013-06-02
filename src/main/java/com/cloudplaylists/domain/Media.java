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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * @author Michael Lavelle
 * 
 */
@Entity(name = "Media")
public class Media {

	public MediaProvider getMediaProvider() {
		return mediaProvider;
	}

	public void setMediaProvider(MediaProvider mediaProvider) {
		this.mediaProvider = mediaProvider;
	}

	@Id
	private String url;
	private Date addedDate;
	private String title;
	@Column(columnDefinition = "tinyint", nullable = true)
	private Boolean deadLink;

	
    public void setLinkedMedia(Map<MediaProvider, LinkedMedia> linkedMedia) {
		this.linkedMedia = linkedMedia;
	}

	public Boolean getDeadLink() {
		return deadLink;
	}

	public void setDeadLink(Boolean deadLink) {
		this.deadLink = deadLink;
	}

	@JsonIgnore
	public Map<MediaProvider, LinkedMedia> getLinkedMedia() {
		return linkedMedia;
	}
    
    public Set<MediaProvider> getProviderIds()
    {
    	Set<MediaProvider> providers = new HashSet<MediaProvider>();
    	providers.addAll(linkedMedia.keySet());
    	providers.add(getMediaProvider());
    	return providers;
    }

	@OneToMany(mappedBy="sourceMedia",fetch=FetchType.EAGER)
    @MapKeyColumn(name="linkedProviderId")	
    @JsonIgnore
	private Map<MediaProvider,LinkedMedia> linkedMedia = new HashMap<MediaProvider,LinkedMedia>();
	
	@Column(columnDefinition="int(11) not null default 0")
	private MediaProvider mediaProvider;
	
	private String providerMediaId;
	
	public String getProviderMediaId() {
		return providerMediaId;
	}

	public void setProviderMediaId(String providerMediaId) {
		this.providerMediaId = providerMediaId;
	}

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
