package com.std.framework;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Description:
 * Created by 李晓 ON 2017/11/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class JsonAndXmlConverters {
    @Retention(RetentionPolicy.RUNTIME)
    @interface Json {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Xml {
    }

    static class QualifiedTypeConverterFactory extends Converter.Factory {
        private final Converter.Factory jsonFactory;
        private final Converter.Factory xmlFactory;

        QualifiedTypeConverterFactory(Converter.Factory jsonFactory, Converter.Factory xmlFactory) {
            this.jsonFactory = jsonFactory;
            this.xmlFactory = xmlFactory;
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Json) {
                    return jsonFactory.responseBodyConverter(type, annotations, retrofit);
                }
                if (annotation instanceof Xml) {
                    return xmlFactory.responseBodyConverter(type, annotations, retrofit);
                }
            }
            return null;
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            for (Annotation annotation : parameterAnnotations) {
                if (annotation instanceof Json) {
                    return jsonFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations,
                            retrofit);
                }
                if (annotation instanceof Xml) {
                    return xmlFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations,
                            retrofit);
                }
            }
            return null;
        }
    }

    static class User {
        public String name;
    }

    interface Service {
        @GET("/")
        @Json
        Call<User> exampleJson();

        @GET("/")
        @Xml
        Call<User> exampleXml();
    }
//
//    public static void main(String... args) throws IOException {
//        MockWebServer server = new MockWebServer();
//        server.start();
//        server.enqueue(new MockResponse().setBody("{\"name\": \"Jason\"}"));
//        server.enqueue(new MockResponse().setBody("<user name=\"Eximel\"/>"));
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(server.url("/"))
//                .addConverterFactory(new QualifiedTypeConverterFactory(
//                        GsonConverterFactory.create(),
//                        SimpleXmlConverterFactory.create()))
//                .build();
//
//        Service service = retrofit.create(Service.class);
//
//        User user1 = service.exampleJson().execute().body();
//        System.out.println("User 1: " + user1.name);
//
//        User user2 = service.exampleXml().execute().body();
//        System.out.println("User 2: " + user2.name);
//
//        server.shutdown();
//    }
}
