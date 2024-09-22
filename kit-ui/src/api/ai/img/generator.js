import request from '@/utils/request'

export function generator(query) {
  return request({
    url: '/ai/img/open-api/generator',
    method: 'post',
    data: query
  })
}

export function list(query) {
  return request({
    url: '/ai/img/open-api/list',
    method: 'get',
    params: query
  })
}
export function deleteOne(processId) {
  return request({
    url: '/ai/img/open-api/delete/' + processId,
    method: 'delete'
  })
}

export function generatorInfo(processId) {
  return request({
    url: '/ai/img/open-api/process-info?processId=' + processId,
    method: 'get'
  })
}
export function generatorStepInfo(processId) {
  return request({
    url: '/ai/img/open-api/process-step-info?processId=' + processId,
    method: 'get'
  })
}
export function remainingUsageTimes() {
  return request({
    url: '/ai/img/open-api/remaining-usage-times',
    method: 'get'
  })
}
