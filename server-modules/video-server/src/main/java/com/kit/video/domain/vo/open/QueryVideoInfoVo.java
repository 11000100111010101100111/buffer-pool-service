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
    String authName;
    String userLink;
    String title;
    String description;

    VideoAboutInfoVo about;
    List<VideoSrcInfoVo> videoSrc;
    List<VideoTagInfoVo> tags;

    @Data
    public static class VideoSrcInfoVo {
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
            int collectCount;
            int forwardCount;
            int commentCount;
        }

        public static VideoAboutInfoVo genericVideoAboutInfo(
                int like,
                int collection,
                int forward,
                int comment,
                String userLink ) {
            UrlInfoVo urlInfoVo = new UrlInfoVo();
            urlInfoVo.setUrl(userLink);
            VideoInfoVo videoInfoVo = new VideoInfoVo();
            videoInfoVo.setLikeCount(like);
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
        String link;

        public static VideoTagInfoVo genericTag(String key, String name, String link) {
            VideoTagInfoVo tag = new VideoTagInfoVo();
            tag.setLabel(name);
            tag.setKey(key);
            tag.setLink(link);
            return tag;
        }
    }
}
