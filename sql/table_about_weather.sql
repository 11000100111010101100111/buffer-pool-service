drop table if exists weather_district_info;
create table weather_district_info
(
    id                int auto_increment
        primary key,
    no                varchar(10)   default ''                not null,
    province          varchar(100)  default ''                not null,
    city              varchar(100)  default ''                not null,
    city_geo_code     varchar(6)    default '000000'          null comment '创建人ID',
    district          varchar(100)  default ''                null comment '创建时间',
    district_geo_code char(6)       default '000000'          null comment '是否删除标记, 1:已删除，0：未删除',
    lon               decimal(9, 6) default 0.000000          null,
    lat               decimal(9, 6) default 0.000000          null,
    type              char          default '0'               null comment '省0（直辖市1），省会2，市3，县4（区5）',
    createBy          varchar(64)                             null comment '创建人ID',
    createTime        datetime      default CURRENT_TIMESTAMP null comment '创建时间',
    `delete`          char          default '0'               null comment '是否删除标记, 1:已删除，0：未删除',
    updateTime        datetime      default CURRENT_TIMESTAMP null comment '删除时间',
    updateBy          varchar(64)                             null comment '删除人ID',
    remark            varchar(200)  default ''                null comment '备注信息',
    status            char          default '1'               null comment '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '城市-经纬信息表'
  ROW_FORMAT = Dynamic;



create table weather_type_pic_mapping
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `code`       varchar(5)   not null DEFAULT '',
    `name`       varchar(50)  not null DEFAULT '',
    `day_name`   varchar(50)  not null DEFAULT '',
    `night_name` varchar(50)  not null DEFAULT '',
    `path`       varchar(100) not null default '',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime              DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
    `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '天气-图片对应关系表'
  ROW_FORMAT = Dynamic;

INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (46, '00', '晴', 'Sunny', 'Clear', '/icon/weather/weather_Sunny.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (47, '01', '多云', 'Cloudy', 'Cloudy', '/icon/weather/weather_Cloudy.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (48, '02', '阴', 'Overcast', 'Overcast', '/icon/weather/weather_Overcast.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (49, '03', '阵雨', 'Shower', 'Shower', '/icon/weather/weather_Shower.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (50, '04', '雷阵雨', 'Thundershower', 'Thundershower', '/icon/weather/weather_Thundershower.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (51, '05', '雷阵雨伴有冰雹', 'Thundershower with hail', 'Thundershower with hail',
        '/icon/weather/weather_Thundershower with hail.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (52, '06', '雨夹雪', 'Sleet', 'Sleet', '/icon/weather/weather_Sleet.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (53, '07', '小雨', 'Light rain', 'Light rain', '/icon/weather/weather_Light rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (54, '08', '中雨', 'Moderate rain', 'Moderate rain', '/icon/weather/weather_Moderate rain.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (55, '09', '大雨', 'Heavy rain', 'Heavy rain', '/icon/weather/weather_Heavy rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (56, '10', '暴雨', 'Storm', 'Storm', '/icon/weather/weather_Storm.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (57, '11', '大暴雨', 'Heavy storm', 'Heavy storm', '/icon/weather/weather_Heavy storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (58, '12', '特大暴雨', 'Severe storm', 'Severe storm', '/icon/weather/weather_Severe storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (59, '13', '阵雪', 'Snow flurry', 'Snow flurry', '/icon/weather/weather_Snow flurry.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (60, '14', '小雪', 'Light snow', 'Light snow', '/icon/weather/weather_Light snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (61, '15', '中雪', 'Moderate snow', 'Moderate snow', '/icon/weather/weather_Moderate snow.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (62, '16', '大雪', 'Heavy snow', 'Heavy snow', '/icon/weather/weather_Heavy snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (63, '17', '暴雪', 'Snowstorm', 'Snowstorm', '/icon/weather/weather_Snowstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (64, '18', '雾', 'Fog', 'Fog', '/icon/weather/weather_Fog.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (65, '19', '冻雨', 'Ice rain', 'Ice rain', '/icon/weather/weather_Ice rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (66, '20', '沙尘暴', 'Duststorm', 'Duststorm', '/icon/weather/weather_Duststorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (67, '21', '小到中雨', 'Light to moderate rain', 'Light to moderate rain',
        '/icon/weather/weather_Light to moderate rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (68, '22', '中到大雨', 'Moderate to heavy rain', 'Moderate to heavy rain',
        '/icon/weather/weather_Moderate to heavy rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (69, '23', '大到暴雨', 'Heavy rain to storm', 'Heavy rain to storm', '/icon/weather/weather_Heavy rain to storm.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (70, '24', '暴雨到大暴雨', 'Storm to heavy storm', 'Storm to heavy storm',
        '/icon/weather/weather_Storm to heavy storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null,
        '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (71, '25', '大暴雨到特大暴雨', 'Heavy to severe storm', 'Heavy to severe storm',
        '/icon/weather/weather_Heavy to severe storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (72, '26', '小到中雪', 'Light to moderate snow', 'Light to moderate snow',
        '/icon/weather/weather_Light to moderate snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (73, '27', '中到大雪', 'Moderate to heavy snow', 'Moderate to heavy snow',
        '/icon/weather/weather_Moderate to heavy snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (74, '28', '大到暴雪', 'Heavy snow to snowstorm', 'Heavy snow to snowstorm',
        '/icon/weather/weather_Heavy snow to snowstorm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (75, '29', '浮尘', 'Dust', 'Dust', '/icon/weather/weather_Dust.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (76, '30', '扬沙', 'Sand', 'Sand', '/icon/weather/weather_Sand.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (77, '31', '强沙尘暴', 'Sandstorm', 'Sandstorm', '/icon/weather/weather_Sandstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (78, '32', '浓雾', 'Dense fog', 'Dense fog', '/icon/weather/weather_Dense fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (79, '33', '龙卷风', 'Tornado', 'Tornado', '/icon/weather/weather_Tornado.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (80, '34', '弱高吹雪', 'Weak high blow snow', 'Weak high blow snow', '/icon/weather/weather_Weak high blow snow.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (81, '35', '轻雾', 'Mist', 'Mist', '/icon/weather/weather_Mist.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (82, '49', '强浓雾', 'Heavy dense fog', 'Heavy dense fog', '/icon/weather/weather_Heavy dense fog.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (83, '53', '霾', 'Haze', 'Haze', '/icon/weather/weather_Haze.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (84, '54', '中度霾', 'Moderate haze', 'Moderate haze', '/icon/weather/weather_Moderate haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (85, '55', '重度霾', 'Severe haze', 'Severe haze', '/icon/weather/weather_Severe haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (86, '56', '严重霾', 'Hazardous haze', 'Hazardous haze', '/icon/weather/weather_Hazardous haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (87, '57', '大雾', 'Heavy fog', 'Heavy fog', '/icon/weather/weather_Heavy fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (88, '58', '特强浓雾', 'Extra-heavy dense fog', 'Extra-heavy dense fog',
        '/icon/weather/weather_Extra_heavy dense fog.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (89, '301', '雨', 'Rain', 'Rain', '/icon/weather/weather_Rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (90, '302', '雪', 'Snow', 'Snow', '/icon/weather/weather_Snow.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (91, '-1', '未知', 'unKnow', 'unKnow', '/icon/weather/weather_un_know.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');



INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (46, '00', '晴', 'Sunny', 'Clear', '/icon/weather/weather_Sunny.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (47, '01', '多云', 'Cloudy', 'Cloudy', '/icon/weather/weather_Cloudy.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (48, '02', '阴', 'Overcast', 'Overcast', '/icon/weather/weather_Overcast.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (49, '03', '阵雨', 'Shower', 'Shower', '/icon/weather/weather_Shower.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (50, '04', '雷阵雨', 'Thundershower', 'Thundershower', '/icon/weather/weather_Thundershower.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (51, '05', '雷阵雨伴有冰雹', 'Thundershower with hail', 'Thundershower with hail',
        '/icon/weather/weather_Thundershower with hail.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (52, '06', '雨夹雪', 'Sleet', 'Sleet', '/icon/weather/weather_Sleet.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (53, '07', '小雨', 'Light rain', 'Light rain', '/icon/weather/weather_Light rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (54, '08', '中雨', 'Moderate rain', 'Moderate rain', '/icon/weather/weather_Moderate rain.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (55, '09', '大雨', 'Heavy rain', 'Heavy rain', '/icon/weather/weather_Heavy rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (56, '10', '暴雨', 'Storm', 'Storm', '/icon/weather/weather_Storm.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (57, '11', '大暴雨', 'Heavy storm', 'Heavy storm', '/icon/weather/weather_Heavy storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (58, '12', '特大暴雨', 'Severe storm', 'Severe storm', '/icon/weather/weather_Severe storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (59, '13', '阵雪', 'Snow flurry', 'Snow flurry', '/icon/weather/weather_Snow flurry.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (60, '14', '小雪', 'Light snow', 'Light snow', '/icon/weather/weather_Light snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (61, '15', '中雪', 'Moderate snow', 'Moderate snow', '/icon/weather/weather_Moderate snow.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (62, '16', '大雪', 'Heavy snow', 'Heavy snow', '/icon/weather/weather_Heavy snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (63, '17', '暴雪', 'Snowstorm', 'Snowstorm', '/icon/weather/weather_Snowstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (64, '18', '雾', 'Fog', 'Fog', '/icon/weather/weather_Fog.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (65, '19', '冻雨', 'Ice rain', 'Ice rain', '/icon/weather/weather_Ice rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (66, '20', '沙尘暴', 'Duststorm', 'Duststorm', '/icon/weather/weather_Duststorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (67, '21', '小到中雨', 'Light to moderate rain', 'Light to moderate rain',
        '/icon/weather/weather_Light to moderate rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (68, '22', '中到大雨', 'Moderate to heavy rain', 'Moderate to heavy rain',
        '/icon/weather/weather_Moderate to heavy rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (69, '23', '大到暴雨', 'Heavy rain to storm', 'Heavy rain to storm', '/icon/weather/weather_Heavy rain to storm.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (70, '24', '暴雨到大暴雨', 'Storm to heavy storm', 'Storm to heavy storm',
        '/icon/weather/weather_Storm to heavy storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null,
        '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (71, '25', '大暴雨到特大暴雨', 'Heavy to severe storm', 'Heavy to severe storm',
        '/icon/weather/weather_Heavy to severe storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (72, '26', '小到中雪', 'Light to moderate snow', 'Light to moderate snow',
        '/icon/weather/weather_Light to moderate snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (73, '27', '中到大雪', 'Moderate to heavy snow', 'Moderate to heavy snow',
        '/icon/weather/weather_Moderate to heavy snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (74, '28', '大到暴雪', 'Heavy snow to snowstorm', 'Heavy snow to snowstorm',
        '/icon/weather/weather_Heavy snow to snowstorm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (75, '29', '浮尘', 'Dust', 'Dust', '/icon/weather/weather_Dust.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (76, '30', '扬沙', 'Sand', 'Sand', '/icon/weather/weather_Sand.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (77, '31', '强沙尘暴', 'Sandstorm', 'Sandstorm', '/icon/weather/weather_Sandstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (78, '32', '浓雾', 'Dense fog', 'Dense fog', '/icon/weather/weather_Dense fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (79, '33', '龙卷风', 'Tornado', 'Tornado', '/icon/weather/weather_Tornado.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (80, '34', '弱高吹雪', 'Weak high blow snow', 'Weak high blow snow', '/icon/weather/weather_Weak high blow snow.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (81, '35', '轻雾', 'Mist', 'Mist', '/icon/weather/weather_Mist.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (82, '49', '强浓雾', 'Heavy dense fog', 'Heavy dense fog', '/icon/weather/weather_Heavy dense fog.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (83, '53', '霾', 'Haze', 'Haze', '/icon/weather/weather_Haze.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (84, '54', '中度霾', 'Moderate haze', 'Moderate haze', '/icon/weather/weather_Moderate haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (85, '55', '重度霾', 'Severe haze', 'Severe haze', '/icon/weather/weather_Severe haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (86, '56', '严重霾', 'Hazardous haze', 'Hazardous haze', '/icon/weather/weather_Hazardous haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (87, '57', '大雾', 'Heavy fog', 'Heavy fog', '/icon/weather/weather_Heavy fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (88, '58', '特强浓雾', 'Extra-heavy dense fog', 'Extra-heavy dense fog',
        '/icon/weather/weather_Extra_heavy dense fog.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (89, '301', '雨', 'Rain', 'Rain', '/icon/weather/weather_Rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (90, '302', '雪', 'Snow', 'Snow', '/icon/weather/weather_Snow.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (91, '-1', '未知', 'unKnow', 'unKnow', '/icon/weather/weather_un_know.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');