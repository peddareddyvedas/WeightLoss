package com.vedas.weightloss.DataBase;

import android.util.Log;

import com.j256.ormlite.stmt.UpdateBuilder;
import com.vedas.weightloss.Models.Personalinfo;
import com.vedas.weightloss.Models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rise on 15/05/2018.
 */

public class PersonalDataController {

    public DataBaseHelper helper;
    public ArrayList<Personalinfo> personalInformation = new ArrayList<>();

    public Personalinfo personalinfo;
    public static PersonalDataController myObj;

    public static PersonalDataController getInstance() {
        if (myObj == null) {
            myObj = new PersonalDataController();
        }
        return myObj;
    }

    //Inserting member data
    public Boolean insertPersonalData(Personalinfo personalinfo) {
        try {
            helper.getPersonalinformDao().create(personalinfo);
            fetchpersonalData();
            //Log.e("fetc", "" + personalInformation + "id" + "call" + userdata.userid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Fetching all the personal data
    public ArrayList<Personalinfo> fetchpersonalData() {
        personalInformation = null;
        personalInformation = new ArrayList<>();

        try {
            personalInformation = (ArrayList<Personalinfo>) helper.getPersonalinformDao().queryForAll();
            if (personalInformation.size() > 0) {
                personalinfo = personalInformation.get(0);
                Log.e("currentUser", "call" + personalinfo);
                Log.e("currentcount", "call" + personalInformation.size());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.e("fetching", "user data fectched successfully" + personalInformation.size());

        return personalInformation;
    }


    //Deleting all users in database
    public void deletePersonalData(List<Personalinfo> personal_list) {
        try {
            PersonalDataController.getInstance().helper.getPersonalinformDao().delete(personal_list);
            Log.e("Delete", "delete all members");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean updatePersonalData(Personalinfo personalinfo) {
        try {
            UpdateBuilder<Personalinfo, Integer> updateBuilder =helper.getPersonalinformDao().updateBuilder();
            updateBuilder.updateColumnValue("member_id", personalinfo.memberid);
            updateBuilder.updateColumnValue("name", personalinfo.mname);
            updateBuilder.updateColumnValue("email", personalinfo.memail);
            updateBuilder.updateColumnValue("birthDay", personalinfo.mbirthday);
            updateBuilder.updateColumnValue("gender", personalinfo.mgender);
            updateBuilder.updateColumnValue("height", personalinfo.mheight);
            updateBuilder.updateColumnValue("weight", personalinfo.mweight);
            updateBuilder.updateColumnValue("activityGoal", personalinfo.goal);
            updateBuilder.updateColumnValue("activityLevel", personalinfo.activityLevel);

            Log.e("update", "updatedmemberheight" + personalinfo.mheight);

            updateBuilder.where().eq("member_id", personalinfo.memberid);
            updateBuilder.update();
            Log.e("update", "memner data updated sucessfully" + personalinfo.memberid);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}


