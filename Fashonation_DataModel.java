package com.example.dhruva.fashonation;

/**
 * Created by Dhruva on 23-03-2016.
 */
public class Fashonation_DataModel
{
    String name;
    String version;
    int id_;
    int image;

    public Fashonation_DataModel(String name, String version, int id_, int image) {
        this.name = name;
        this.version = version;
        this.id_ = id_;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
