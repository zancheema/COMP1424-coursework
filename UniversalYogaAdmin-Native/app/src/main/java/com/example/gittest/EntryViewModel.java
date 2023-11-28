package com.example.gittest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class EntryViewModel extends AndroidViewModel {
    private final MutableLiveData<Long> insertionResult = new MutableLiveData<>();
    private DBHelper dbHelper; // Remove the assignment with null

    public EntryViewModel(@NonNull Application application) {
        super(application);
        dbHelper = new DBHelper(application); // Correct the syntax for creating a new instance
    }

    public LiveData<Long> getInsertionResult() {
        return insertionResult;
    }

    public void insertEntry(YogaEntry entry) {
        new InsertAsyncTask().execute(entry);
    }

    public void updateEntry(YogaEntry updatedEntry) {
        new UpdateEntryAsyncTask().execute(updatedEntry);
    }

    private class InsertAsyncTask extends AsyncTask<YogaEntry, Void, Long> {
        @Override
        protected Long doInBackground(YogaEntry... entries) {
            YogaEntry entry = entries[0];
            boolean isSuccess = dbHelper.insertCourseData(entry);
            return isSuccess ? 1L : -1L;
        }

        @Override
        protected void onPostExecute(Long newRowId) {
            insertionResult.setValue(newRowId);
        }

    }


    private class UpdateEntryAsyncTask extends AsyncTask<YogaEntry, Void, Void> {
        @Override
        protected Void doInBackground(YogaEntry... entries) {
            YogaEntry updatedEntry = entries[0];
            dbHelper.updateYogaEntry(updatedEntry);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Handle the result if needed
        }
    }
}
