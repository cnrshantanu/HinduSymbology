package com.bag.hindugodsymbology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

public class God_Bean implements Parcelable {

	String _id;
	String God_Name;
	String Classification;
	String Symbology;
	String Summary;
	String Marker_Image;
	String Main_Image;
	
	HashMap<String,String> Markers = new HashMap<String,String>();
	
	public String get_id() {
		return _id;
	}
	
	public HashMap<String, String> getMarkers() {
		return Markers;
	}

	public void setMarkers(HashMap<String, String> markers) {
		Markers = markers;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	public String getGod_Name() {
		return God_Name;
	}
	public void setGod_Name(String god_Name) {
		God_Name = god_Name;
	}
	public String getClassification() {
		return Classification;
	}
	public void setClassification(String classification) {
		Classification = classification;
	}
	public String getSymbology() {
		return Symbology;
	}
	public void setSymbology(String symbology) {
		Symbology = symbology;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public String getMarker_Image() {
		return Marker_Image;
	}
	public void setMarker_Image(String marker_Image) {
		Marker_Image = marker_Image;
	}
	public String getMain_Image() {
		return Main_Image;
	}
	public God_Bean(String _id, String god_Name, String classification,
			String symbology, String summary, String marker_Image,
			String main_Image, HashMap<String, String>  MarkersList) {
		super();
		this._id = _id;
		God_Name = god_Name;
		Classification = classification;
		Symbology = symbology;
		Summary = summary;
		Marker_Image = marker_Image;
		Main_Image = main_Image;
		Markers = MarkersList;
	}
	public God_Bean() {
		// TODO Auto-generated constructor stub
	}
	public void setMain_Image(String main_Image) {
		Main_Image = main_Image;
	}


	// Parcelling part
	public God_Bean(Parcel in){
		//String[] data = new String[7];
		
		this.Marker_Image = in.readString();
		this.Main_Image = in.readString();
		this.Summary = in.readString();
		this.Symbology =in.readString();
		this._id = in.readString();
		this.God_Name = in.readString();
		this.Classification = in.readString();
		final int N = in.readInt();
        for (int i=0; i<N; i++) {
            String key = in.readString();
            String value = in.readString();
            
            this.Markers.put(key, value);
        }
    	
	}

	@Override
	public int describeContents(){
		return this.hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		//dest.writeStringArray(new String[] {this.Marker_Image,this.Main_Image, this.Summary, this.Symbology, this._id, this.God_Name, this.Classification});
		dest.writeString(Marker_Image);
		dest.writeString(Main_Image);
		dest.writeString(Summary);
		dest.writeString(Symbology);
		dest.writeString(_id);
		dest.writeString(God_Name);
		dest.writeString(Classification);
		int N = Markers.size();
       dest.writeInt(N);
        if (Markers.size()> 0) {
            for (Map.Entry<String, String> entry : Markers.entrySet()) {
                dest.writeString(entry.getKey());
                dest.writeString(entry.getValue());
                
            }
        }
	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public God_Bean createFromParcel(Parcel in) {
			return new God_Bean(in); 
		}

		public God_Bean[] newArray(int size) {
			return new God_Bean[size];
		}

	};
}


