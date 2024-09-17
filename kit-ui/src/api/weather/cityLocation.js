import request from '@/utils/request'

export function queryCityLocation(query) {
  return request({
    url: '/open/map/weather/city-location',
    method: 'get',
    params: query
  })
}

export function querySimpleWeather(query) {
  return request({
    url: "/open/map/weather",
    method: 'get',
    params: query
  })
}

export function queryWeatherPicPath() {
  return request({
    url: "/open/map/weather/weather-code",
    method: 'get'
  })
}

export function queryCityWeather(adCode) {
  return request({
    url: "/open/map/weather/" + adCode,
    method: 'get'
  })
}
