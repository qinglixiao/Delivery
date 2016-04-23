package com.std.framework.assist;

import java.util.List;

/**
 * Created by gfy on 2016/4/18.
 */
public class BCNetBean<T> {
    public String code;
    public T results;

    public T getResults(){
        return results;
    }

    public void setResults(T results){
        this.results = results;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
