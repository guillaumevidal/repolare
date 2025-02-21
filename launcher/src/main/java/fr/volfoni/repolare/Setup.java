/*
 * Simple TV Launcher
 * Copyright 2017 Alexandre Del Bigio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.volfoni.repolare;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import fr.volfoni.repolare.fragments.PreferenceFragment;


public class Setup {
	private static final int DEFAULT_GRID_X = 3;
	private static final int DEFAULT_GRID_Y = 2;
	private static final int DEFAULT_PAGES = 1;

	private final Context mContext;
	private SharedPreferences mPreferences;

	public Setup(Context context) {
		mContext = context;
	}

	private SharedPreferences getPreferences() {
		if (mPreferences == null) {
			mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
		}
		return (mPreferences);
	}

	private int getInt(String name, int defaultValue) {
		try {
			String value = getPreferences().getString(name, null);
			if (value != null)
				return (Integer.parseInt(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (defaultValue);
	}

	public float getTransparency() {
		int t = getPreferences().getInt(PreferenceFragment.PREFERENCE_TRANSPARENCY, 20);
		return (float) t / 100;
	}

	public boolean iconsLocked() {
		try {
			return (getPreferences().getBoolean(PreferenceFragment.PREFERENCE_LOCKED, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (false);
	}

	public boolean showDate() {
		try {
			return (getPreferences().getBoolean(PreferenceFragment.PREFERENCE_SHOW_DATE, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (true);
	}

	public boolean showNames() {
		return getPreferences().getBoolean(PreferenceFragment.PREFERENCE_SHOW_NAME, true);
	}


	public int getGridX() {
		return getInt(PreferenceFragment.PREFERENCE_GRID_X, DEFAULT_GRID_X);
	}

	public int getGridY() {
		return getInt(PreferenceFragment.PREFERENCE_GRID_Y, DEFAULT_GRID_Y);
	}

	public int getPages() {
		return getInt(PreferenceFragment.PREFERENCE_PAGES, DEFAULT_PAGES);
	}

	public boolean colorfulIcons() {
		return getPreferences().getBoolean(PreferenceFragment.PREFERENCE_COLORFUL_ICONS, true);
	}

	public int getAllAppColumns() {
		return Integer.parseInt(getPreferences().getString(PreferenceFragment.PREFERENCE_ALL_APPS_COLS, "3"));
	}

	public int getCornerRadius() {
		return getPreferences().getInt(PreferenceFragment.PREFERENCE_CORNER_RADIUS, 4);
	}
}
