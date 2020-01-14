package com.std.network.dns;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-12-27.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class STCookieStore implements CookieStore {
    private static final String COOKIE_FIELD = "stcookiestore";

    private Map<URI, List<HttpCookie>> mMapCookies = new ConcurrentHashMap();
    private SharedPreferences sharedPreferences;
    List<HttpCookie> cacheCookies = new ArrayList<>();

    public STCookieStore(Context context) {
        sharedPreferences = context.getSharedPreferences(COOKIE_FIELD, 0);
        Map<String, ?> prefsMap = sharedPreferences.getAll();

        if (prefsMap == null) {
            return;
        }
        URI uri;
        for (String uriStr : prefsMap.keySet()) {
            String cookieJSONArray = (String) prefsMap.get(uriStr);
            ArrayList<HttpCookie> arrayList = new ArrayList<>();
            try {
                uri = new URI(uriStr);

                JSONArray jsonArray = new JSONArray(cookieJSONArray);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    String cookies = jsonArray.getString(i);
                    arrayList.addAll(HttpCookie.parse(cookies));
                }
                mMapCookies.put(uri, arrayList);
            } catch (JSONException ignored) {
            } catch (URISyntaxException ignored) {
            }
        }
    }

    @Override
    public void add(URI uri, HttpCookie cookie) {
        synchronized (STCookieStore.class) {
            List<HttpCookie> cookies = get(uri);
            cacheCookies = new ArrayList<>();
            for (HttpCookie c : cookies) {
                if (!c.getName().equals(cookie.getName()) && !c.hasExpired()) {
                    cacheCookies.add(c);
                }
            }

            cacheCookies.add(cookie);
            mMapCookies.put(uri, cacheCookies);

            JSONArray jsonArray = new JSONArray();
            for (HttpCookie httpCookie : cacheCookies) {
                jsonArray.put(httpCookie.toString());
            }
            sharedPreferences.edit().putString(uri.toString(), jsonArray.toString()).commit();
        }
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        if (mMapCookies.containsKey(uri)) {
            return mMapCookies.get(uri);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<HttpCookie> getCookies() {
        Collection<List<HttpCookie>> values = mMapCookies.values();

        List<HttpCookie> result = new ArrayList<>();
        for (List<HttpCookie> value : values) {
            result.addAll(value);
        }

        return result;
    }

    @Override
    public List<URI> getURIs() {
        Set<URI> keys = mMapCookies.keySet();
        return new ArrayList<>(keys);
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        synchronized (STCookieStore.class) {
            List<HttpCookie> lstCookies = mMapCookies.get(uri);
            boolean ret = lstCookies != null && lstCookies.remove(cookie);

            if (ret) {
                JSONArray jsonArray = new JSONArray();
                for (HttpCookie httpCookie : lstCookies) {
                    jsonArray.put(httpCookie.toString());
                }
                sharedPreferences.edit().putString(uri.toString(), jsonArray.toString()).commit();
            }

            if (lstCookies.size() == 0) {
                sharedPreferences.edit().remove(uri.toString()).commit();
            }
            return ret;
        }
    }

    @Override
    public boolean removeAll() {
        synchronized (STCookieStore.class) {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            for (URI uri : mMapCookies.keySet()) {
                editor.remove(uri.toString());
            }
            editor.commit();

            mMapCookies.clear();
            return true;
        }
    }
}
