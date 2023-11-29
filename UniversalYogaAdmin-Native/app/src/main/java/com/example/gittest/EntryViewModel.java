package com.example.gittest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gittest.db.DBHelper;

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

    public void insertEntry(Course entry) {
        new InsertAsyncTask().execute(entry);
    }

    public void updateEntry(Course updatedEntry) {
        new UpdateEntryAsyncTask().execute(updatedEntry);
    }

    private class InsertAsyncTask extends AsyncTask<Course, Void, Long> {
        @Override
        protected Long doInBackground(Course... entries) {
            Course entry = entries[0];
            boolean isSuccess = dbHelper.insertCourseData(entry);
            return isSuccess ? 1L : -1L;
        }

        @Override
        protected void onPostExecute(Long newRowId) {
            insertionResult.setValue(newRowId);
        }

    }

    public void deleteEntry(long entryId) {
        new DeleteAsyncTask().execute(entryId);
    }

    private class DeleteAsyncTask extends AsyncTask<Long, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Long... entryIds) {
            long entryId = entryIds[0];
            return dbHelper.deleteCourseData(entryId + "");
        }

        @Override
        protected void onPostExecute(Boolean success) {
            // do nothing for now
        }
    }


    private class UpdateEntryAsyncTask extends AsyncTask<Course, Void, Void> {
        @Override
        protected Void doInBackground(Course... entries) {
            Course updatedEntry = entries[0];
            dbHelper.updateYogaEntry(updatedEntry);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Handle the result if needed
        }
    }
}
