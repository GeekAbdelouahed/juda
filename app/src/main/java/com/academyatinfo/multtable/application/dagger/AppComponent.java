package com.academyatinfo.multtable.application.dagger;

import com.academyatinfo.multtable.databases.DataTableLevel;
import com.academyatinfo.multtable.databases.DataTables;

import dagger.Component;
import io.realm.Realm;

/**
 * Created by geek on 01/08/17.
 */
@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

    DataTableLevel dataBaseLearn();

    DataTables dataBaseLearnTable();

    Realm getRealmData();

}
