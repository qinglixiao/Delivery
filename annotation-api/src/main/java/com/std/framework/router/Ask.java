package com.std.framework.router;

import android.text.TextUtils;

import com.std.framework.router.exceptions.CYRouterException;
import com.std.framework.router.utils.RouterHelper;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLEncoder;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class Ask {
    private static final String PACKAGE = "me.shunyu.router.module";

    private String schema;
    private String host;
    private String path;

    private RPromise promise;
    private ParamsWrapper paramsWrapper;
    private Exception _e;

    public Ask(String url) {
        parse(url);
    }

    public void request() {
        if (_e != null) {
            promise.capture(_e);
            return;
        }
        invoke();
    }

    public void invoke() {
        try {
            String mirror = PACKAGE + "." + schema + "$$" + host;
            Class<?> clazz = Class.forName(mirror);
            Method targetMethod = clazz.getMethod("invoke", String.class, ParamsWrapper.class);
            Object target = RouterHelper.getIntance().getMirrorClass(mirror);
            if (target == null) {
                target = clazz.newInstance();
                RouterHelper.getIntance().addMirrorClass(mirror, target);
            }
            targetMethod.invoke(target, path, paramsWrapper);
        } catch (ClassNotFoundException e) {
            capture(new CYRouterException(schema + "$$" + host + " not found !"));
        } catch (NoSuchMethodException e) {
            capture(new CYRouterException(schema + "$$" + host + ":invoke not found !"));
        } catch (Exception e) {
            capture(new CYRouterException(e.getMessage()));
        }
    }

    private void parse(String url) {
        if (TextUtils.isEmpty(url)) {
            capture(new CYRouterException("url is empty !"));
            return;
        }
        try {
            String encoder = URLEncoder.encode(url, "UTF-8");
            URI uri = new URI(encoder);
            schema = uri.getScheme();
            host = uri.getHost();
            path = uri.getPath();
            paramsWrapper = createParamsWrapper(uri.getQuery());
        } catch (UnsupportedEncodingException e) {
            this._e = new CYRouterException("router url is not supported encoding");
        } catch (Exception e) {
            this._e = new CYRouterException(e);
        }
    }

    private ParamsWrapper createParamsWrapper(String query) {
        ParamsWrapper params = new ParamsWrapper();
        params.setPromise(promise);
        params.append(query);
        return params;
    }

    public void setPromise(RPromise promise) {
        this.promise = promise;
    }

    private void capture(Exception ex) {
        promise.capture(ex);
    }

}
