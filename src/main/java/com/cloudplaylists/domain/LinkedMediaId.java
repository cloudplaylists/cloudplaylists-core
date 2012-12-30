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
import java.io.Serializable;

import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * @author Michael Lavelle
 */
@SuppressWarnings("serial")
@Embeddable
public class LinkedMediaId implements Serializable {

	public LinkedMediaId(Media sourceMedia,Media targetMedia)
	{
		this.url = sourceMedia.getUrl();
		this.linkedProviderId = targetMedia.getMediaProvider();
	}
	
	public LinkedMediaId() {}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((linkedProviderId == null) ? 0 : linkedProviderId.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedMediaId other = (LinkedMediaId) obj;
		if (linkedProviderId == null) {
			if (other.linkedProviderId != null)
				return false;
		} else if (!linkedProviderId.equals(other.linkedProviderId))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	private String url;
	
	@JsonProperty("providerId")
	private MediaProvider linkedProviderId;
}
