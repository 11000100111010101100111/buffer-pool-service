package com.kit.web.controller.map.weather;

import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.R;
import com.kit.system.domain.weather.baidu.entity.CityInfo;
import com.kit.system.domain.weather.baidu.param.QueryCityLocationParam;
import com.kit.system.service.weather.BaiDuWeatherService;
import io.kit.map.baidu.vo.BaiDuWeatherResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Api("地图-天气")
@RestController
@RequestMapping("/map/weather")
public class WeatherController extends BaseController {
    @Autowired
    private BaiDuWeatherService baiDuWeatherService;

    @ApiOperation("根据adCode查询城市天气, 日查询限额5000, 默认英文逗号分割")
    @GetMapping
    public R<Map<String, BaiDuWeatherResult.Now>> get(@RequestParam(name = "split", required = false) String split,
                                                      @RequestParam(name = "adCode", required = false) String adCode) {
        try {
            return R.ok(baiDuWeatherService.findSimple(Stream.of(adCode.split(Optional.ofNullable(split).orElse(","))).collect(Collectors.toSet())));
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @ApiOperation("返回国内所有城市对应的经纬度(lng/lat)坐标和邮编(acCode)")
    @GetMapping("city-location")
    public R<List<CityInfo>> getCityLocation(@RequestParam(name = "lonMin", required = true) String lonMin,
                                             @RequestParam(name = "lonMax", required = true) String lonMax,
                                             @RequestParam(name = "latMin", required = true) String latMin,
                                             @RequestParam(name = "latMax", required = true) String latMax) {
        QueryCityLocationParam param = new QueryCityLocationParam();
        param.setLonMin(lonMin);
        param.setLonMax(lonMax);
        param.setLatMax(latMax);
        param.setLatMin(latMin);
        return R.ok(baiDuWeatherService.queryCityLocation(param));
    }

    @ApiOperation("返回天气类型对于的图片路径")
    @GetMapping("weather-code")
    public R<Map<String, String>> getWeatherPicPath() {
        return R.ok(baiDuWeatherService.getWeatherPicPath());
    }
}
