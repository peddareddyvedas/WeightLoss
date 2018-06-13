package com.vedas.weightloss.Models;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Personalinfo {

    public Personalinfo() {

    }


    @DatabaseField(generatedId = true)
    public int memberid;


    @DatabaseField(columnName = "name")
    public String mname;

    @DatabaseField(columnName = "email")
    public String memail;

    @DatabaseField(columnName = "birthDay")
    public String mbirthday;


    @DatabaseField(columnName = "gender")
    public String mgender;

    @DatabaseField(columnName = "height")
    public String mheight;

    @DatabaseField(columnName = "weight")
    public String mweight;

    @DatabaseField(columnName = "activityGoal")
    public String goal;

    @DatabaseField(columnName = "activityLevel")
    public String activityLevel;



   /* @DatabaseField(columnName = "user_id", canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public User user;
*/




}
