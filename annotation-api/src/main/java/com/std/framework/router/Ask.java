package com.std.framework.router;

import android.text.TextUtils;

import com.std.framework.router.exceptions.CYRouterException;
import com.std.framework.router.utils.RouterHelper;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLDecoder;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class Ask {
    private static final String PACKAGE = "me.chunyu.lixiao.router";

    private String schema;
    private String host;
    private String path;

    private RPromise promise;
    private Exception _e;
    private ParamsWrapper paramsWrapper = new ParamsWrapper();

    public Ask(String url) {
        parse(url);
    }

    public Ask(String url, Object params) {
        parse(url);
        paramsWrapper.append(params);
    }

    public Ask(String schema, String host, String path) {
        this(schema, host, path, null);
    }

    public Ask(String schema, String host, String path, Object params) {
        if (TextUtils.isEmpty(host) || TextUtils.isEmpty(path)) {
            _e = new CYRouterException("host or path is empty!");
        } else {
            this.schema = schema;
            this.host = host;
            this.path = path;
            paramsWrapper.append(params);
        }
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
        } catch (IllegalAccessException e) {
            capture(new CYRouterException(e));
        } catch (InvocationTargetException e) {
            capture(new CYRouterException(e.getTargetException()));
        } catch (Exception e) {
            capture(new CYRouterException(e));
        }
    }

    private void parse(String url) {
        if (TextUtils.isEmpty(url)) {
            _e = new CYRouterException("url is empty !");
            return;
        }
        try {
            String decode = URLDecoder.decode(url, "UTF-8");
            URI uri = new URI(decode);
            schema = uri.getScheme();
            host = uri.getHost();
            path = uri.getPath();
            paramsWrapper.withQueryString(uri.getQuery());
        } catch (UnsupportedEncodingException e) {
            this._e = new CYRouterException("router url is not supported encoding");
        } catch (Exception e) {
            this._e = new CYRouterException(e);
        }
    }

    public void setPromise(RPromise promise) {
        this.promise = promise;
        paramsWrapper.setPromise(promise);
    }

    private void capture(Exception ex) {
        promise.capture(ex);
    }

}
