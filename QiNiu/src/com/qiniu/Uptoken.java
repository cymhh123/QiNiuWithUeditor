package com.qiniu;

import java.util.UUID;

import org.json.JSONException;

import com.qiniu.Mac;
import com.qiniu.Config;
import com.qiniu.PutPolicy;

public class Uptoken {
	public final static String makeUptoken() throws AuthException,
			JSONException {

		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		String bucketName = "test";
		PutPolicy putPolicy = new PutPolicy(bucketName);
		// 可以根据自己需要设置过期时间,sdk默认有设置，具体看源码
		// putPolicy.expires = getDeadLine();
		putPolicy.returnUrl = "http://127.0.0.1:8080/QiNiuCallback.jsp";
		putPolicy.returnBody = "{\"name\": $(fname),\"size\": \"$(fsize)\",\"w\": \"$(imageInfo.width)\",\"h\": \"$(imageInfo.height)\",\"key\":$(etag)}";
		String uptoken = putPolicy.token(mac);
		return uptoken;
	}

	/**
	 * 生成32位UUID 并去掉"-"
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
