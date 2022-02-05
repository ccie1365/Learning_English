package com.javad_mozaffari.grammar.Room_Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NameDao {

    @Insert
    void addName(com.javad_mozaffari.grammar.Room_Database.NameModel nameModel);

    @Update
    void updateName(com.javad_mozaffari.grammar.Room_Database.NameModel nameModel);

    @Delete
    void deleteName(com.javad_mozaffari.grammar.Room_Database.NameModel nameModel);

    @Query("select * from NameTable")
    List<com.javad_mozaffari.grammar.Room_Database.NameModel> getName();
}
