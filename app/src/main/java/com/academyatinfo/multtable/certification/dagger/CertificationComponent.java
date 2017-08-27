package com.academyatinfo.multtable.certification.dagger;

import com.academyatinfo.multtable.application.dagger.AppComponent;
import com.academyatinfo.multtable.certification.mvp.Certification;

import dagger.Component;

/**
 * Created by geek on 01/08/17.
 */
@CertificationScope
@Component(modules = CertificationModule.class, dependencies = AppComponent.class)
public interface CertificationComponent {

    void inject(Certification certification);

}
