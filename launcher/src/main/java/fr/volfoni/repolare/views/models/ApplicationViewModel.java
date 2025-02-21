package fr.volfoni.repolare.views.models;

import android.app.Application;

import fr.volfoni.repolare.AppInfo;
import fr.volfoni.repolare.AppManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ApplicationViewModel extends AndroidViewModel {
	public ApplicationViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<List<AppInfo>> getApplications() {
		return getApplications(false);
	}

	public LiveData<List<AppInfo>> getApplications(boolean reload) {
		return AppManager.getInstance(getApplication()).getApplications(reload);
	}
}
