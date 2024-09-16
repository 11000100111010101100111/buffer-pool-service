package io.kit.map.baidu;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kit.map.baidu.vo.BaiDuWeatherResult;
import io.kit.map.core.WeatherAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import io.kit.limitting.LimitLock;

@Service
@Scope("singleton")
public class BaiDuWeatherAPI implements WeatherAPI<BaiDuWeatherResult.Result> {
    @Value("${map.baidu.ak}")
    private String baiDuAk;

    public static final String URL0 = "https://api.map.baidu.com/geocoding/v3?";
    public static final String URL1 = "https://api.map.baidu.com/api_region_search/v1/?";
    public static final String URL2 = "https://api.map.baidu.com/reverse_geocoding/v3?";
    public static final String URL3 = "https://api.map.baidu.com/weather/v1/?";

    private static final LimitLock lock = new LimitLock(25);

    public BaiDuWeatherResult.Result query(String adCode) throws Exception {

        //Map<String, String> params = new LinkedHashMap<>();
        //params.put("address", "广东省深圳市宝安区宝安中心地铁站");
        //params.put("output", "json");
        //params.put("ak", AK);
        //params.put("callback", "showLocation");
        //snCal.requestGetAK(URL, params);

        //Map<String, String> params1 = new LinkedHashMap<>();
        //params1.put("keyword", "广东省深圳市");
        //params1.put("sub_admin", "3");
        //params1.put("ak", AK);
        //params1.put("boundarycode", "440300");
        //snCal.requestGetAK1(URL1, params1);

//        Map<String, String> params2 = new LinkedHashMap<>();
//        params2.put("ak", baiDuAk);
//        params2.put("output", "json");
//        params2.put("coordtype", "wgs84ll");
//        params2.put("extensions_poi", "0");
//        params2.put("location", "22.568467212213326,113.88023805109444");
//        String value = requestGetAK(URL2, params2);
//        if (null == value) return null;
//        Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(value);
//        Object result = parse.get("result");
//        if (!(result instanceof Map)) return null;
//        Map<String, Object> resultMap = (Map<String, Object>) result;
//        Object addressComponent = resultMap.get("addressComponent");
//        if (!(addressComponent instanceof Map)) return null;
//        Map<String, Object> addressComponentMap = (Map<String, Object>) addressComponent;

        //adCode = addressComponentMap.get("adcode");
        if (Objects.isNull(adCode)) return null;

        Map<String, String> params3 = new LinkedHashMap<>();
        params3.put("district_id", adCode);
        params3.put("data_type", "all");
        params3.put("ak", baiDuAk);
        BaiDuWeatherResult baiDuWeatherResult = JSONObject.parseObject(requestGetAK(URL3, params3), BaiDuWeatherResult.class);
        if (Objects.isNull(baiDuWeatherResult)) return null;
        if (baiDuWeatherResult.succeed()) {
            return baiDuWeatherResult.getResult();
        } else {
            throw new RuntimeException(String.format("%s: %s", String.valueOf(baiDuWeatherResult.getMessage()), String.valueOf(baiDuWeatherResult.getStatus())));
        }
    }

//    public static void main(String[] args) throws Exception {
//        BaiDuWeatherAPI snCal = new BaiDuWeatherAPI();
//        Map<String, String> params3 = new LinkedHashMap<>();
//        params3.put("district_id", "110100");
//        params3.put("data_type", "all");
//        params3.put("ak", "EPLNY44yeK71751XeVhKTUDouwzx84DD");
//        snCal.requestGetAK(URL3, params3);
//    }

    public String requestGetAK(String strUrl, Map<String, String> param) throws Exception {
        if (strUrl == null
                || strUrl.trim().length() <= 0
                || param == null
                || param.isEmpty()) {
            return null;
        }

        StringBuilder queryString = new StringBuilder();
        queryString.append(strUrl);
        param.forEach((key, value) -> queryString.append(key)
                .append("=")
                .append(UriUtils.encode(value, "UTF-8"))
                .append("&"));
        queryString.deleteCharAt(queryString.length() - 1);

        try {
            lock.lock();
            URL url = new URL(queryString.toString());
            URLConnection httpConnection = url.openConnection();
            httpConnection.connect();

            try (InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
                 BufferedReader reader = new BufferedReader(isr)) {
                StringBuilder buffer = new StringBuilder();
                String line;
                while (null != (line = reader.readLine())) {
                    buffer.append(line);
                }
                //show(buffer.toString());
                return buffer.toString();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void show(String value) {
        System.out.println("==============");
        System.out.println(JSONObject.toJSONString(
                JSONObject.parseObject(value, Map.class),
                SerializerFeature.PrettyFormat)
        );
    }
}