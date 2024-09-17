import request from '@/utils/request'

export function generator(query) {
  return request({
    url: '/ai/img/open-api/generator',
    method: 'post',
    data: query
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
export function myGeneratorInfo(processId) {
  return request({
    url: '/ai/img/process-info?processId=' + processId,
    method: 'get'
  })
}
export function myGeneratorStepInfo(processId) {
  return request({
    url: '/ai/img/process-step-info?processId=' + processId,
    method: 'get'
  })
}
export function remainingUsageTimes() {
  return request({
    url: '/ai/img/open-api/remaining-usage-times',
    method: 'get'
  })
}
export function remainingUsageTimesSelf() {
  return request({
    url: '/ai/img/remaining-usage-times',
    method: 'get'
  })
}
