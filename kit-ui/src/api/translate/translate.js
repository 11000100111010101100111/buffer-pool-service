import request from '@/utils/request'

export function queryCityLocation(body) {
  return request({
    url: '/open/translate',
    method: 'post',
    body: {
      query: body.query,
      from: body.from,
      to: body.to
    }
  })
}

export function queryLanguage() {
  return request({
    url: '/open/translate/language',
    method: 'get'
  })
}
