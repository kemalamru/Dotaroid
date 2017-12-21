package com.kar.dotaroid.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Kemal Amru Ramadhan on 12/19/17.
 */

public class SearchUtils {

    // Material Search Bar Listener
    public static Observable<String> setSearchListener(MaterialSearchBar materialSearchBar) {
        final PublishSubject<String> subject = PublishSubject.create();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchConfirmed(CharSequence text) {
                //searchPlayer(text.toString());
                materialSearchBar.disableSearch();
                //Log.d(TAG, "Enter Search");
                subject.onComplete();
            }

            @Override
            public void onSearchStateChanged(boolean enabled) {}
            @Override
            public void onButtonClicked(int buttonCode) {
            }
        });

        return subject
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(query -> query.length() > 1)
                .distinctUntilChanged();
//                .doOnNext(query -> Log.d("Player Search", "Search Query: " + query));
    }
}
