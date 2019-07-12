package com.lmy.base.net.converter;

//import com.facebook.stetho.server.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.lmy.base.net.ApiCode;
import com.lmy.base.net.base.bean.BaseHttpResult;
import com.lmy.base.net.call.ApiException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;


/**
 * 解析返回数据
 * Created by LMY on 2018/3/25.
 */

final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseHttpResult httpStatus = gson.fromJson(response, BaseHttpResult.class);
        if (httpStatus.getCode() == ApiCode.SUCCESS) {
            try {
                return adapter.fromJson(response);
            } finally {
                value.close();
            }
        }
        value.close();
        throw new ApiException(httpStatus.getCode(), httpStatus.getMsg());
    }
}
