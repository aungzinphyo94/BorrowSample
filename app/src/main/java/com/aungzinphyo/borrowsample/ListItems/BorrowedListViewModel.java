package com.aungzinphyo.borrowsample.ListItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.aungzinphyo.borrowsample.Db.AppDatabase;
import com.aungzinphyo.borrowsample.Db.BorrowModel;

import java.util.List;

public class BorrowedListViewModel extends AndroidViewModel {

    private final LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public BorrowedListViewModel(Application application) {
        super(application);
        appDatabase = appDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItem();
    }

    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(BorrowModel borrowModel){
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void>{

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase){
            db = appDatabase;
        }


        @Override
        protected Void doInBackground(BorrowModel... borrowModels) {
            db.itemAndPersonModel().deleteBorrow(borrowModels[0]);
            return null;
        }
    }
}
