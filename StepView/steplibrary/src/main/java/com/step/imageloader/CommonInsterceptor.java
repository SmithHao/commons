package com.step.imageloader;

import android.content.Context;

import com.step.Constant;
import com.step.util.ACache;
import com.step.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WangHao
 *
 * @ 创建时间 2017/12/1  16:28
 */

public class CommonInsterceptor implements Interceptor {


    private Context mContext;
    private String  mToken;
    private Request mNewRequest;

    public CommonInsterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        try {
            mToken = ACache.get(mContext).getAsString(Constant.TOKEN);
            LogUtil.logger(1111111+mToken);

                if (mToken != null && Constant.HEADER_TOKEN){//
                //对所有请求添加请求头
                mNewRequest = request.newBuilder()
                        //对所有请求添加请求头
                        .addHeader("Authorization", "Bearer" +" " +mToken)
                        .build();

                if (chain!=null){

                    return  chain.proceed(mNewRequest);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        return chain.proceed(request);
    }
}
