package com.std.framework.innerrouter;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/8.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
@AutoService(Processor.class)
public class ChunyuProcessor extends AbstractProcessor{

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
