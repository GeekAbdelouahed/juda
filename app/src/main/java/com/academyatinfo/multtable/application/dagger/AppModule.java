package com.academyatinfo.multtable.application.dagger;

import android.app.Application;
import android.content.Context;

import com.academyatinfo.multtable.databases.DataTableLevel;
import com.academyatinfo.multtable.databases.DataTables;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by geek on 01/08/17.
 */
@Module
public class AppModule {

    private final Context context;

    public AppModule(Application context) {
        this.context = context;
    }

    @Provides
    @AppScope
    public DataTableLevel dataBaseLearn() {
        return new DataTableLevel(context);
    }

    @Provides
    @AppScope
    public DataTables dataBaseLearnTable() {
        return new DataTables(context);
    }

    @Provides
    @AppScope
    public Realm getRealmData() {
        return Realm.getDefaultInstance();
    }
}
