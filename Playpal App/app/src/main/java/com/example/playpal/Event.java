package com.example.playpal;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private String sport;
    private String date;
    private String time;
    private String location;
    private int playersNeeded;
    private String skillLevel;
    private String description;

    public Event(String sport, String date, String time, String location,
                 int playersNeeded, String skillLevel, String description) {
        this.sport = sport;
        this.date = date;
        this.time = time;
        this.location = location;
        this.playersNeeded = playersNeeded;
        this.skillLevel = skillLevel;
        this.description = description;
    }

    protected Event(Parcel in) {
        sport = in.readString();
        date = in.readString();
        time = in.readString();
        location = in.readString();
        playersNeeded = in.readInt();
        skillLevel = in.readString();
        description = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    // Getters
    public String getSport() { return sport; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public int getPlayersNeeded() { return playersNeeded; }
    public String getSkillLevel() { return skillLevel; }
    public String getDescription() { return description; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sport);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(location);
        dest.writeInt(playersNeeded);
        dest.writeString(skillLevel);
        dest.writeString(description);
    }
}