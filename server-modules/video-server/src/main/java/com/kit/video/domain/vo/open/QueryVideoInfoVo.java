package com.kit.video.domain.vo.open;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class QueryVideoInfoVo {
    String pageSrc;
    boolean needShowVideoMenu;
    String srcMatch;
    String currentVideoIndex;
    int transitionDuration;
    double fadeOpacity;
    int duration;
    String ipLocal;
    LocalDateTime createTime;
    LocalDateTime publishTime;
    String authName;
    String userLink;
    String title;
    String description;
    String userAvatar;

    VideoAboutInfoVo about;
    List<VideoSrcInfoVo> videoSrc;
    List<VideoTagInfoVo> tags;

    public void addTag(VideoTagInfoVo tag) {
        if (null == tag) return;
        if (null == this.tags) this.tags = new ArrayList<>();
        this.tags.add(tag);
    }

    @Data
    public static class VideoSrcInfoVo {
        String id;
        Integer index;
        String type;
        String src;

        public static List<VideoSrcInfoVo> generic(String ...url) {
            List<VideoSrcInfoVo> vos = new ArrayList<>();
            for (String s : url) {
                VideoSrcInfoVo vo = new VideoSrcInfoVo();
                vo.setSrc(s);
                vos.add(vo);
            }
            return vos;
        }
    }

    @Data
    public static class VideoAboutInfoVo {
        UrlInfoVo authInfo;
        VideoInfoVo data;

        @Data
        public static class UrlInfoVo {
            String url;
        }

        @Data
        public static class VideoInfoVo {
            int likeCount;
            int dislikeCount;
            int collectCount;
            int forwardCount;
            int commentCount;
        }

        public static VideoAboutInfoVo genericVideoAboutInfo(
                int like,
                int dislikeCount,
                int collection,
                int forward,
                int comment,
                String userLink) {
            UrlInfoVo urlInfoVo = new UrlInfoVo();
            urlInfoVo.setUrl(userLink);
            VideoInfoVo videoInfoVo = new VideoInfoVo();
            videoInfoVo.setLikeCount(like);
            videoInfoVo.setDislikeCount(dislikeCount);
            videoInfoVo.setCollectCount(collection);
            videoInfoVo.setForwardCount(forward);
            videoInfoVo.setCommentCount(comment);
            VideoAboutInfoVo info = new VideoAboutInfoVo();
            info.setAuthInfo(urlInfoVo);
            info.setData(videoInfoVo);
            return info;
        }
    }

    @Data
    public static class VideoTagInfoVo {
        String label;
        String key;
        int useTimes;

        public static VideoTagInfoVo genericTag(String key, String name, int useTimes) {
            VideoTagInfoVo tag = new VideoTagInfoVo();
            tag.setLabel(name);
            tag.setKey(key);
            tag.setUseTimes(useTimes);
            return tag;
        }
    }
}
