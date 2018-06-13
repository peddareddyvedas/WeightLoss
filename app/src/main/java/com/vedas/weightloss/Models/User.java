package com.vedas.weightloss.Models;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User")
public class User {
    
    public User() {
    }
    @DatabaseField(id = true, columnName = "userid")
    public String userid;

    @DatabaseField(columnName = "password")
    public String password;

    @DatabaseField(columnName = "registertype")
    public String registerType;

    @DatabaseField(columnName = "registertime")
    public String registerTime;

    @DatabaseField(columnName = "longitude")
    public String longitude;

    @DatabaseField(columnName = "latitude")
    public String latitude;



    @DatabaseField(columnName = "username")
    public String username;

    @DatabaseField
    public Boolean isVerified;

    @DatabaseField(columnName = "preferedLanguage")
    public   String preferedLanguage;




    /*//foreign collection fields
   @ForeignCollectionField
   public ForeignCollection<Payees> payees;

    @ForeignCollectionField
    public ForeignCollection<Transaction> transactions;

    public ForeignCollection<Payees> getPayees() {
        return payees;
    }

    public ForeignCollection<Transaction> getTransactions() {
        return transactions;
    }*/

}
