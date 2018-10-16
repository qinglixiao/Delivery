package com.std.framework.innerrouter;

import com.google.auto.service.AutoService;
import com.std.framework.annotation.Router;
import com.std.framework.utils.Consts;
import com.std.framework.utils.Logger;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.std.framework.utils.Consts.ACTIVITY;
import static com.std.framework.utils.Consts.FRAGMENT;
import static com.std.framework.utils.Consts.SERVICE;


@AutoService(Processor.class)//用来生成META-INF/services/javax.annotation.processing.Processor文件
public class AnnotateProcessor extends AbstractProcessor {
    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;
    private Logger logger;
    private Map<String, String> rootMap = new TreeMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        logger =  new Logger(processingEnv.getMessager());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(annotations != null && annotations.size() > 0){
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Router.class);
            parseRouter(elementsAnnotatedWith);

        }
        return true;
    }

    private void parseRouter(Set<? extends Element> elements){
        if(elements != null && elements.size() > 0){
            logger.info(">>> found annotation size "+elements.size()+"<<<");
            rootMap.clear();

            TypeMirror type_Activity = mElementUtils.getTypeElement(ACTIVITY).asType();
            TypeMirror type_Service = mElementUtils.getTypeElement(SERVICE).asType();
            TypeMirror fragmentTm = mElementUtils.getTypeElement(FRAGMENT).asType();
            TypeMirror fragmentTmV4 = mElementUtils.getTypeElement(Consts.FRAGMENT_V4).asType();


        }
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> anns = new LinkedHashSet<>();
        anns.add(Router.class.getCanonicalName());
        return anns;
    }
}
