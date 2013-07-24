package com.bag.hindugodsymbology;

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
	public String get_id() {
		return _id;
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
			String main_Image) {
		super();
		this._id = _id;
		God_Name = god_Name;
		Classification = classification;
		Symbology = symbology;
		Summary = summary;
		Marker_Image = marker_Image;
		Main_Image = main_Image;
	}
	public God_Bean() {
		// TODO Auto-generated constructor stub
	}
	public void setMain_Image(String main_Image) {
		Main_Image = main_Image;
	}


	// Parcelling part
	public God_Bean(Parcel in){
		String[] data = new String[7];

		in.readStringArray(data);
		this.Marker_Image = data[0];
		this.Main_Image = data[1];
		this.Summary = data[2];
		this.Symbology = data[3];
		this._id = data[4];
		this.God_Name = data[5];
		this.Classification = data[6];
		
	}

	@Override
	public int describeContents(){
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] {this.Marker_Image,this.Main_Image, this.Summary, this.Symbology, this._id, this.God_Name, this.Classification});
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


