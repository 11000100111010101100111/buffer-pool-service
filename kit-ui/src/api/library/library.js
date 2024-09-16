import request from '@/utils/request'
import {parseStrEmpty} from "@/utils/kit";

// 用户头像上传
export function uploadLibrary(data) {
  return request({
    url: '/api/bank/upload',
    method: 'post',
    type: 'multipart/form-data',
    data: data
  })
}

// 查询题库资源列表
export function listLibrary(query) {
  return request({
    url: '/api/bank/find/page',
    method: 'get',
    params: query
  })
}
