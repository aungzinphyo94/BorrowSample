package com.aungzinphyo.borrowsample.Db;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public abstract class BorrowModelDao {

    @Query("select * from BorrowModel")
    public abstract LiveData<List<BorrowModel>> getAllBorrowedItem();

    @Query("Select * from BorrowModel where id = :id")
    public abstract BorrowModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    public abstract void addBorrow(BorrowModel borrowModel);

    @Delete
    public abstract void deleteBorrow(BorrowModel borrowModel);
}
