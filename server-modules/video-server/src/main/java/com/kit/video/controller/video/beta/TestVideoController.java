package com.kit.video.controller.video.beta;

import com.alibaba.fastjson.JSON;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.utils.uuid.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/video/open-api")
public class TestVideoController {
    @GetMapping("mock-video-info")
    public AjaxResult getDemoList(
            @RequestParam("index") Integer indexOf,
            @RequestParam("count") Integer count) {
        if (null == count) count = 20;
        List<Map<String, Object>> result = new ArrayList<>();
//        File path = new File("/apps/kit/videoInfo");
        File path = new File("D:\\DeskTop\\video\\local-use");
        final File[] files = path.listFiles();
        int size = files.length;
        final Random random = new Random();
        for (int index = 0; index < count; index++) {
            if (count < 5 || index > size - 1) {
                int i2 = random.nextInt(size - 1);
                if (i2 < 0) i2 = 0;
                final File file = files[i2];
                try (InputStream stream = new FileInputStream(file)) {
                    result.add(JSON.parseObject(stream, Map.class));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                continue;
            }

            final File file = files[index];
            try (InputStream stream = new FileInputStream(file)) {
                result.add(JSON.parseObject(stream, Map.class));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return AjaxResult.success(result);
    }

    public static void main(String[] args) {
//        final Map<String, Object> stringObjectMap = mockOne(0);
        URLS.forEach((k, v) -> {
//            System.out.println();
//            System.out.println(JSON.toJSONString(mockOne(k)));
            final Map<String, Object> mockOne = mockOne(k);
            final File file = new File(String.valueOf(mockOne.get("currentVideoIndex")) + ".json");
            try (OutputStream outputStream = new FileOutputStream(file)) {
                JSON.writeJSONString(outputStream, mockOne);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getAbsolutePath());
        });
//        System.out.println(JSON.toJSONString(stringObjectMap));
    }

    static Map<String, Object> mockOne(Integer index) {
        final Random random = new Random();
        Map<String, Object> one = new HashMap<>();
        one.put("pageSrc", "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg");
        one.put("needShowVideoMenu", true);
        one.put("srcMatch", "http://8.134.183.234:8000/public/video");
        one.put("currentVideoIndex", UUID.randomUUID().toString());
        one.put("transitionDuration", 3000);
        one.put("fadeOpacity", 1);
        one.put("duration", 3);

        List<Map<String, Object>> videoSrc = new ArrayList<>();
        Map<String, Object> srcObject = new HashMap<>();
        int i2 = null == index ? random.nextInt(URLS.size() - 1) : index;
        if (i2 < 0) i2 = 0;
        final Map<String, Object> urlObject = URLS.get(i2);

        srcObject.put("src", urlObject.get("src"));
        one.put("ipLocal", urlObject.get("ipLocal"));//"广东深圳"
        one.put("createTime", urlObject.get("createTime"));//"2024-9-28 13:03"
        one.put("authName", urlObject.get("authName"));//"二叉树"
        one.put("userLink", urlObject.get("userLink"));//"二叉树"
        one.put("title", urlObject.get("title"));//"二叉树"
        one.put("description", urlObject.get("description"));//"二叉树"
        one.put("about", urlObject.get("about"));//"二叉树"

        videoSrc.add(srcObject);
        one.put("videoSrc", videoSrc);

        one.put("tags", urlObject.get("tags"));
        return one;
    }


    public static Map<String, Object> genericTag(int key, String name, String link) {
        Map<String, Object> tag = new HashMap<>();
        tag.put("label", name);
        tag.put("key", UUID.randomUUID().toString());
        tag.put("link", link);
        return tag;
    }

    public static final Map<String, Object> o1 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_0.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "广东深圳");
        put("createTime", "2024-9-28 13:03");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_7.png");
        put("title", "不知道怎么写标题");
        put("description", "先随便写吧");

        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "IKUN", "#"));
        tags.add(genericTag(1, "小黑子", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_7.png",
                80401, 31, 7816, 43
        ));
    }};

    public static final Map<String, Object> o2 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_1.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "广东深圳");
        put("createTime", "2024-9-28 13:03");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_7.png");
        put("title", "这个标题很简单");
        put("description", "很艰难地想到了这写文字");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "坤哥", "#"));
        tags.add(genericTag(1, "厉不厉害我坤哥", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_7.png",
                5201, 71, 9816, 43
        ));
    }};
    public static final Map<String, Object> o3 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_2.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "西藏拉萨");
        put("createTime", "2024-7-1 15:03");
        put("authName", "南岳");
        put("userLink", "http://8.134.183.234:8000/public/head_2.png");
        put("title", "在路上");
        put("description", "好的风景都在路上");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "风景", "#"));
        tags.add(genericTag(1, "Vlog", "#"));
        tags.add(genericTag(1, "好天气", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_2.png",
                999, 71, 86, 43
        ));
    }};
    public static final Map<String, Object> o4 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_3.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "江苏苏州");
        put("createTime", "2024-9-18 8:03");
        put("authName", "沉默的寡言");
        put("userLink", "http://8.134.183.234:8000/public/head_3.png");
        put("title", "一夜入秋");
        put("description", "再次看见你的时候只能透过秋叶");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "冷空气", "#"));
        tags.add(genericTag(1, "秋天", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_3.png",
                549, 71, 51, 43
        ));
    }};
    public static final Map<String, Object> o5 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_4.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "拉萨日喀则");
        put("createTime", "2024-8-12 10:23");
        put("authName", "@狗蛋");
        put("userLink", "http://8.134.183.234:8000/public/dog_egg.png");
        put("title", "长大想做太空人");
        put("description", "你们仰望的天空就是我俯身窥地的身影");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "万里晴空", "#"));
        tags.add(genericTag(1, "白中", "#"));
        tags.add(genericTag(1, "南航", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/dog_egg.png",
                9501, 71, 73, 43
        ));
    }};
    public static final Map<String, Object> o6 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_5.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "江苏盐城");
        put("createTime", "2024-10-18 6:03");
        put("authName", "飞翔的大鸟");
        put("userLink", "http://8.134.183.234:8000/public/big_bridg.png");
        put("title", "无人机带你看山河");
        put("description", "今天的景色时湖色秋光");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "无人机", "#"));
        tags.add(genericTag(1, "俯视", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/big_bridg.png",
                30, 3, 71, 92
        ));
    }};
    public static final Map<String, Object> o7 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_6.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "新疆阿勒泰");
        put("createTime", "2024-3-13 14:54");
        put("authName", "小滑头的头");
        put("userLink", "http://8.134.183.234:8000/public/head_1.png");
        put("title", "我在新疆阿勒泰等你来偶遇");
        put("description", "你也会来，对吗？");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "阿勒泰", "#"));
        tags.add(genericTag(1, "牛羊成群", "#"));
        tags.add(genericTag(1, "马上看风景", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_1.png",
                520, 71, 666, 3
        ));
    }};
    public static final Map<String, Object> o8 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_7.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "广东深圳");
        put("createTime", "2024-7-18 19:05");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_3.png");
        put("title", "去悬崖边看看大海");
        put("description", "好久不见");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "澎湃", "#"));
        tags.add(genericTag(1, "我想看看浪花", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_3.png",
                451, 71, 52, 43
        ));
    }};
    public static final Map<String, Object> o9 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_8.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "广东深圳");
        put("createTime", "2024-9-28 13:03");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_7.png");
        put("title", "深圳的每一天");
        put("description", "奋斗吧少年，i+1071万=深圳");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "来了就是深圳人", "#"));
        tags.add(genericTag(1, "晚霞", "#"));
        tags.add(genericTag(1, "宝安", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_7.png",
                3401, 71, 9816, 43
        ));
    }};
    public static final Map<String, Object> o10 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_9.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "广东潮汕");
        put("createTime", "2024-6-11 12:22");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_6.png");
        put("title", "旅行日记");
        put("description", "我的vlog");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "抓拍瞬间", "#"));
        tags.add(genericTag(1, "记录美好", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_6.png",
                3401, 71, 9816, 43
        ));
    }};
    public static final Map<String, Object> o11 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_10.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "云南怒江");
        put("createTime", "2024-4-16 14:03");
        put("authName", "小江很忙");
        put("userLink", "http://8.134.183.234:8000/public/head_4.png");
        put("title", "路上的风景");
        put("description", "...");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "雪山", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_4.png",
                3401, 71, 9816, 43
        ));
    }};
    public static final Map<String, Object> o12 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_11.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "甘肃酒泉");
        put("createTime", "2023-12-28 7:03");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_5.png");
        put("title", "硬座直达拉萨");
        put("description", "许巍我辞职了");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "在路上", "#"));
        tags.add(genericTag(1, "找回自己", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_5.png",
                3401, 71, 9816, 43
        ));
    }};
    public static final Map<String, Object> o13 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_12.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "广东深圳");
        put("createTime", "2024-9-28 14:19");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_7.png");
        put("title", "荷塘");
        put("description", "洪湖公园-夏日主题活动");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "荷文化", "#"));
        tags.add(genericTag(1, "夏天", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_7.png",
                3401, 71, 9816, 43
        ));
    }};
    public static final Map<String, Object> o14 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_13.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "香港特别行政区");
        put("createTime", "2024-3-15 15:03");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_7.png");
        put("title", "运镜不需要技术");
        put("description", "CBD都是密集的写字楼，还得是香港，宁要香港一张床不要深圳一套房");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "香港", "#"));
        tags.add(genericTag(1, "建筑群", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_7.png",
                3401, 71, 9816, 43
        ));
    }};
    public static final Map<String, Object> o15 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_14.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "西藏林芝");
        put("createTime", "2024-4-28 9:51");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_6.png");
        put("title", "小闲即欢，小清即静");
        put("description", "万物都是自由诗");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "闲情雅致", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_6.png",
                1201, 67, 3416, 23
        ));
    }};
    public static final Map<String, Object> o16 = new HashMap<String, Object>() {{
        put("src", "http://8.134.183.234:8000/public/video_15.mp4");
        put("pageSrc", "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        put("ipLocal", "内蒙古呼和浩特");
        put("createTime", "2024-5-28 16:14");
        put("authName", "二叉树");
        put("userLink", "http://8.134.183.234:8000/public/head_5.png");
        put("title", "大草原");
        put("description", "只有广袤的草原才有资格留下狼的图腾");
        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(genericTag(0, "大草原", "#"));
        tags.add(genericTag(1, "白云蓝天", "#"));
        put("tags", tags);
        put("about", genericVideoInfo(
                "http://8.134.183.234:8000/public/head_5.png",
                100, 67, 3416, 23
        ));

    }};

    public static Map<String, Map<String, Object>> genericVideoInfo(
            String userLink,
            int like,
            int collection,
            int forward,
            int comment) {
        Map<String, Object> v1 = new HashMap<>();
        v1.put("url", userLink);
        Map<String, Object> v2 = new HashMap<>();
        v2.put("likeCount", like);
        v2.put("collectCount", collection);
        v2.put("forwardCount", forward);
        v2.put("commentCount", comment);
        Map<String, Map<String, Object>> res = new HashMap<>();
        res.put("authInfo", v1);
        res.put("data", v2);
        return res;
    }

    /**
     * authInfo: {
     * url: 'http://8.134.183.234/icon/about-auth.jpg'
     * },
     * data: {
     * likeCount: 100,
     * collectCount: 99,
     * forwardCount: 67,
     * commentCount: 1239
     * }
     * }
     */


    public static final Map<Integer, Map<String, Object>> URLS = new HashMap<Integer, Map<String, Object>>() {{
        put(0, o1);
        put(1, o2);
        put(2, o3);
        put(3, o4);
        put(4, o5);
        put(5, o6);
        put(6, o7);
        put(7, o8);
        put(8, o9);
        put(9, o10);
        put(10, o11);
        put(11, o12);
        put(12, o13);
        put(13, o14);
        put(14, o15);
        put(15, o16);
    }};
}
