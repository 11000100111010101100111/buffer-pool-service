import request from '@/utils/request'

export function queryCityLocation(query) {
  return request({
    url: '/map/weather/city-location',
    method: 'get',
    params: query
  })
}

export function querySimpleWeather(query) {
  return request({
    url: "/map/weather",
    method: 'get',
    params: query
  })
}

export function queryWeatherPicPath() {
  return request({
    url: "/map/weather/weather-code",
    method: 'get'
  })
}
