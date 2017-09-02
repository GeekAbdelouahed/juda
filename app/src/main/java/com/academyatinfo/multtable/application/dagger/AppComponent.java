package com.academyatinfo.multtable.application.dagger;

import com.academyatinfo.multtable.databases.DataBaseLearn;
import com.academyatinfo.multtable.databases.DataBaseLearnTable;
import com.academyatinfo.multtable.databases.DataBaseResults;

import dagger.Component;

/**
 * Created by geek on 01/08/17.
 */
@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

    DataBaseLearn dataBaseLearn();

    DataBaseLearnTable dataBaseLearnTable();

    DataBaseResults dataBaseResultExam();

}
