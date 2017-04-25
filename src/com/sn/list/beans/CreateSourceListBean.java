package com.sn.list.beans;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sn.api.util.APIConstants;
import com.sn.api.util.SourceRequest;
import com.sn.database.objects.Source;

public class CreateSourceListBean {

	private List<Source> sources;

	public CreateSourceListBean() {
		sources = new ArrayList<Source>();
	}

	public List<Source> getSources() {
		try {
			JSONArray sourceJSON = SourceRequest.getAllSources();
			for (int i = 0; i < sourceJSON.length(); i++) {
				JSONObject jsonObj;

				jsonObj = sourceJSON.getJSONObject(i);
				Source source = new Source();
				source.setCategory(getProperty(jsonObj, APIConstants.CATEGORY));
				source.setCountry(getProperty(jsonObj, APIConstants.COUNTRY));
				source.setDescription(getProperty(jsonObj, APIConstants.DESCRIPTION));
				source.setId(getProperty(jsonObj, APIConstants.ID));
				source.setLanguage(getProperty(jsonObj, APIConstants.LANGUAGE));
				source.setName(getProperty(jsonObj, APIConstants.NAME));
				source.setUrl(getProperty(jsonObj, APIConstants.URL));
				
				sources.add(source);
			}
			
			return sources;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Source> getSourcesByTopic(String Topic) {
		
		
		
		return null;
	}
	
	private String getProperty(JSONObject jsonObj, String prop) throws JSONException {
		return jsonObj.getString(prop);
	}
}
