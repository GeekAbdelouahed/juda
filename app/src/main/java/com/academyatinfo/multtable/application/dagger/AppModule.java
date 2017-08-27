package com.academyatinfo.multtable.application.dagger;

import android.app.Application;
import android.content.Context;

import com.academyatinfo.multtable.databases.DataBaseLearn;
import com.academyatinfo.multtable.databases.DataBaseLearnTable;
import com.academyatinfo.multtable.databases.DataBaseResultExam;

import dagger.Module;
import dagger.Provides;

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
    public DataBaseLearn dataBaseLearn() {
        return new DataBaseLearn(context);
    }

    @Provides
    @AppScope
    public DataBaseLearnTable dataBaseLearnTable() {
        return new DataBaseLearnTable(context);
    }

    @Provides
    @AppScope
    public DataBaseResultExam dataBaseResultExam() {
        return new DataBaseResultExam(context);
    }
}
