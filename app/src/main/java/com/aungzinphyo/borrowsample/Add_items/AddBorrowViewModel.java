package com.aungzinphyo.borrowsample.Add_items;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.aungzinphyo.borrowsample.Db.AppDatabase;
import com.aungzinphyo.borrowsample.Db.BorrowModel;

public class AddBorrowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBorrowViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addBorrow(final BorrowModel borrowModel){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.itemAndPersonModel().addBorrow(borrowModel);
            }
        });
        thread.start();
    }
}
