package com.kit.website.service;

import com.kit.website.mapper.WebsiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSiteServer {
    @Autowired
    WebsiteMapper websiteMapper;

    public boolean hasLoved(String userIdOrIp) {
        return websiteMapper.hasLoved(userIdOrIp) > 0;
    }

    public int loveCount() {
        return websiteMapper.loveCount();
    }

    public boolean loveOnce(String userIdOrIp, boolean isUser) {
       try {
           websiteMapper.loveOnce(userIdOrIp, isUser ? "1" : "0", "0", isUser ? userIdOrIp: "", "");
       } catch (Exception e) {
           return false;
       }
       return true;
    }
}
