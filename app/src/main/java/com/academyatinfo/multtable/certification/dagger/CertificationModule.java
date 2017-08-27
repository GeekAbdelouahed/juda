package com.academyatinfo.multtable.certification.dagger;

import com.academyatinfo.multtable.certification.mvp.CertificationContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by geek on 01/08/17.
 */
@Module
public class CertificationModule {

    private final CertificationContract.View view;

    public CertificationModule(CertificationContract.View view) {
        this.view = view;
    }

    @Provides
    @CertificationScope
    public CertificationContract.View getView(){
        return view;
    }


}
