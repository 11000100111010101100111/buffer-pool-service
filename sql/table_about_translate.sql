
create table translate_info_cache
(
    `id`               int PRIMARY KEY AUTO_INCREMENT,
    `hash`             varchar(50)   not null DEFAULT '',
    `from`             varchar(5)    not null DEFAULT '',
    `to`               varchar(5)    not null DEFAULT '',

    `trans_result_src` varchar(1000) not null DEFAULT '',
    `trans_result_dst` varchar(5000) not null default '',

    `createBy`         varchar(64) COMMENT '创建人ID',
    `createTime`       datetime               DEFAULT now() COMMENT '创建时间',
    `delete`           char(1)                DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime`       datetime               DEFAULT now() COMMENT '删除时间',
    `updateBy`         varchar(64) COMMENT '删除人ID',
    `remark`           varchar(200)           DEFAULT '' COMMENT '备注信息',
    `status`           char(1)                DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '翻译结果表'
  ROW_FORMAT = Dynamic;
CREATE UNIQUE INDEX translate_info_cache_hash_index ON translate_info_cache (hash);

create table translate_lan
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(100) not null DEFAULT '',
    `code`       varchar(10)  not null DEFAULT '',
    `tag`        varchar(10)  not null DEFAULT '',
    `sort`       varchar(2)   not null DEFAULT '',
    `from_hot`   int          not null default 0 COMMENT '热度',
    `to_hot`     int          not null default 0 COMMENT '热度',
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
  COLLATE = utf8mb4_general_ci COMMENT = '翻译支持的语言类型表'
  ROW_FORMAT = Dynamic;

INSERT INTO translate_lan (`name`, `code`, `tag`, `sort`)
VALUES ('阿拉伯语', 'ara', '是', 'A'),
       ('爱尔兰语', 'gle', '是', 'A'),
       ('奥克语', 'oci', '是', 'A'),
       ('阿尔巴尼亚语', 'alb', '是', 'A'),
       ('阿尔及利亚阿拉伯语', 'arq', '否', 'A'),
       ('阿肯语', 'aka', '否', 'A'),
       ('阿拉贡语', 'arg', '否', 'A'),
       ('阿姆哈拉语', 'amh', '是', 'A'),
       ('阿萨姆语', 'asm', '是', 'A'),
       ('艾马拉语', 'aym', '否', 'A'),
       ('阿塞拜疆语', 'aze', '是', 'A'),
       ('阿斯图里亚斯语', 'ast', '是', 'A'),
       ('奥塞梯语', 'oss', '否', 'A'),
       ('爱沙尼亚语', 'est', '是', 'A'),
       ('奥杰布瓦语', 'oji', '否', 'A'),
       ('奥里亚语', 'ori', '是', 'A'),
       ('奥罗莫语', 'orm', '否', 'A'),
       ('波兰语', 'pl', '是', 'B'),
       ('波斯语', 'per', '是', 'B'),
       ('布列塔尼语', 'bre', '是', 'B'),
       ('巴什基尔语', 'bak', '否', 'B'),
       ('巴斯克语', 'baq', '是', 'B'),
       ('巴西葡萄牙语', 'pot', '否', 'B'),
       ('白俄罗斯语', 'bel', '是', 'B'),
       ('柏柏尔语', 'ber', '是', 'B'),
       ('邦板牙语', 'pam', '否', 'B'),
       ('保加利亚语', 'bul', '是', 'B'),
       ('北方萨米语', 'sme', '否', 'B'),
       ('北索托语', 'ped', '否', 'B'),
       ('本巴语', 'bem', '否', 'B'),
       ('比林语', 'bli', '否', 'B'),
       ('比斯拉马语', 'bis', '否', 'B'),
       ('俾路支语', 'bal', '否', 'B'),
       ('冰岛语', 'ice', '是', 'B'),
       ('波斯尼亚语', 'bos', '是', 'B'),
       ('博杰普尔语', 'bho', '否', 'B'),
       ('楚瓦什语', 'chv', '否', 'C'),
       ('聪加语', 'tso', '否', 'C'),
       ('丹麦语', 'dan', '是', 'D'),
       ('德语', 'de', '是', 'D'),
       ('鞑靼语', 'tat', '是', 'D'),
       ('掸语', 'sha', '否', 'D'),
       ('德顿语', 'tet', '否', 'D'),
       ('迪维希语', 'div', '否', 'D'),
       ('低地德语', 'log', '是', 'D'),
       ('俄语', 'ru', '是', 'E'),
       ('法语', 'fra', '是', 'F'),
       ('菲律宾语', 'fil', '是', 'F'),
       ('芬兰语', 'fin', '是', 'F'),
       ('梵语', 'san', '否', 'F'),
       ('弗留利语', 'fri', '否', 'F'),
       ('富拉尼语', 'ful', '否', 'F'),
       ('法罗语', 'fao', '否', 'F'),
       ('盖尔语', 'gla', '否', 'G'),
       ('刚果语', 'kon', '否', 'G'),
       ('高地索布语', 'ups', '否', 'G'),
       ('高棉语', 'hkm', '是', 'G'),
       ('格陵兰语', 'kal', '否', 'G'),
       ('格鲁吉亚语', 'geo', '是', 'G'),
       ('古吉拉特语', 'guj', '是', 'G'),
       ('古希腊语', 'gra', '否', 'G'),
       ('古英语', 'eno', '否', 'G'),
       ('瓜拉尼语', 'grn', '否', 'G'),
       ('韩语', 'kor', '是', 'H'),
       ('荷兰语', 'nl', '是', 'H'),
       ('胡帕语', 'hup', '否', 'H'),
       ('哈卡钦语', 'hak', '否', 'H'),
       ('海地语', 'ht', '否', 'H'),
       ('黑山语', 'mot', '否', 'H'),
       ('豪萨语', 'hau', '否', 'H'),
       ('吉尔吉斯语', 'kir', '否', 'J'),
       ('加利西亚语', 'glg', '是', 'J'),
       ('加拿大法语', 'frn', '否', 'J'),
       ('加泰罗尼亚语', 'cat', '是', 'J'),
       ('捷克语', 'cs', '是', 'J'),
       ('卡拜尔语', 'kab', '是', 'K'),
       ('卡纳达语', 'kan', '是', 'K'),
       ('卡努里语', 'kau', '否', 'K'),
       ('卡舒比语', 'kah', '否', 'K'),
       ('康瓦尔语', 'cor', '否', 'K'),
       ('科萨语', 'xho', '是', 'K'),
       ('科西嘉语', 'cos', '否', 'K'),
       ('克里克语', 'cre', '否', 'K'),
       ('克里米亚鞑靼语', 'cri', '否', 'K'),
       ('克林贡语', 'kli', '否', 'K'),
       ('克罗地亚语', 'hrv', '是', 'K'),
       ('克丘亚语', 'que', '否', 'K'),
       ('克什米尔语', 'kas', '否', 'K'),
       ('孔卡尼语', 'kok', '否', 'K'),
       ('库尔德语', 'kur', '是', 'K'),
       ('拉丁语', 'lat', '是', 'L'),
       ('老挝语', 'lao', '否', 'L'),
       ('罗马尼亚语', 'rom', '是', 'L'),
       ('拉特加莱语', 'lag', '否', 'L'),
       ('拉脱维亚语', 'lav', '是', 'L'),
       ('林堡语', 'lim', '否', 'L'),
       ('林加拉语', 'lin', '否', 'L'),
       ('卢干达语', 'lug', '否', 'L'),
       ('卢森堡语', 'ltz', '否', 'L'),
       ('卢森尼亚语', 'ruy', '否', 'L'),
       ('卢旺达语', 'kin', '是', 'L'),
       ('立陶宛语', 'lit', '是', 'L'),
       ('罗曼什语', 'roh', '否', 'L'),
       ('罗姆语', 'ro', '否', 'L'),
       ('逻辑语', 'loj', '否', 'L'),
       ('马来语', 'may', '是', 'M'),
       ('缅甸语', 'bur', '是', 'M'),
       ('马拉地语', 'mar', '否', 'M'),
       ('马拉加斯语', 'mg', '是', 'M'),
       ('马拉雅拉姆语', 'mal', '是', 'M'),
       ('马其顿语', 'mac', '是', 'M'),
       ('马绍尔语', 'mah', '否', 'M'),
       ('迈蒂利语', 'mai', '是', 'M'),
       ('曼克斯语', 'glv', '否', 'M'),
       ('毛里求斯克里奥尔语', 'mau', '否', 'M'),
       ('毛利语', 'mao', '否', 'M'),
       ('孟加拉语', 'ben', '是', 'M'),
       ('马耳他语', 'mlt', '是', 'M'),
       ('苗语', 'hmn', '否', 'M'),
       ('挪威语', 'nor', '是', 'N'),
       ('那不勒斯语', 'nea', '否', 'N'),
       ('南恩德贝莱语', 'nbl', '否', 'N'),
       ('南非荷兰语', 'afr', '是', 'N'),
       ('南索托语', 'sot', '否', 'N'),
       ('尼泊尔语', 'nep', '是', 'N'),
       ('葡萄牙语', 'pt', '是', 'P'),
       ('旁遮普语', 'pan', '是', 'P'),
       ('帕皮阿门托语', 'pap', '否', 'P'),
       ('普什图语', 'pus', '否', 'P'),
       ('齐切瓦语', 'nya', '否', 'Q'),
       ('契维语', 'twi', '否', 'Q'),
       ('切罗基语', 'chr', '否', 'Q'),
       ('日语', 'jp', '是', 'R'),
       ('瑞典语', 'swe', '是', 'R'),
       ('萨丁尼亚语', 'srd', '否', 'S'),
       ('萨摩亚语', 'sm', '否', 'S'),
       ('塞尔维亚-克罗地亚语', 'sec', '否', 'S'),
       ('塞尔维亚语', 'srp', '是', 'S'),
       ('桑海语', 'sol', '否', 'S'),
       ('僧伽罗语', 'sin', '是', 'S'),
       ('世界语', 'epo', '是', 'S'),
       ('书面挪威语', 'nob', '是', 'S'),
       ('斯洛伐克语', 'sk', '是', 'S'),
       ('斯洛文尼亚语', 'slo', '是', 'S'),
       ('斯瓦希里语', 'swa', '是', 'S'),
       ('塞尔维亚语（西里尔）', 'src', '否', 'S'),
       ('索马里语', 'som', '是', 'S'),
       ('泰语', 'th', '是', 'T'),
       ('土耳其语', 'tr', '是', 'T'),
       ('塔吉克语', 'tgk', '是', 'T'),
       ('泰米尔语', 'tam', '是', 'T'),
       ('他加禄语', 'tgl', '是', 'T'),
       ('提格利尼亚语', 'tir', '否', 'T'),
       ('泰卢固语', 'tel', '是', 'T'),
       ('突尼斯阿拉伯语', 'tua', '否', 'T'),
       ('土库曼语', 'tuk', '否', 'T'),
       ('乌克兰语', 'ukr', '是', 'W'),
       ('瓦隆语', 'wln', '是', 'W'),
       ('威尔士语', 'wel', '是', 'W'),
       ('文达语', 'ven', '否', 'W'),
       ('沃洛夫语', 'wol', '否', 'W'),
       ('乌尔都语', 'urd', '是', 'W'),
       ('西班牙语', 'spa', '是', 'X'),
       ('希伯来语', 'heb', '是', 'X'),
       ('希腊语', 'el', '是', 'X'),
       ('匈牙利语', 'hu', '是', 'X'),
       ('西弗里斯语', 'fry', '是', 'X'),
       ('西里西亚语', 'sil', '否', 'X'),
       ('希利盖农语', 'hil', '否', 'X'),
       ('下索布语', 'los', '否', 'X'),
       ('夏威夷语', 'haw', '否', 'X'),
       ('新挪威语', 'nno', '是', 'X'),
       ('西非书面语', 'nqo', '否', 'X'),
       ('信德语', 'snd', '否', 'X'),
       ('修纳语', 'sna', '否', 'X'),
       ('宿务语', 'ceb', '否', 'X'),
       ('叙利亚语', 'syr', '否', 'X'),
       ('巽他语', 'sun', '否', 'X'),
       ('英语', 'en', '是', 'Y'),
       ('印地语', 'hi', '是', 'Y'),
       ('印尼语', 'id', '是', 'Y'),
       ('意大利语', 'it', '是', 'Y'),
       ('越南语', 'vie', '是', 'Y'),
       ('意第绪语', 'yid', '否', 'Y'),
       ('因特语', 'ina', '否', 'Y'),
       ('亚齐语', 'ach', '否', 'Y'),
       ('印古什语', 'ing', '否', 'Y'),
       ('伊博语', 'ibo', '否', 'Y'),
       ('伊多语', 'ido', '否', 'Y'),
       ('约鲁巴语', 'yor', '否', 'Y'),
       ('亚美尼亚语', 'arm', '是', 'Y'),
       ('伊努克提图特语', 'iku', '否', 'Y'),
       ('伊朗语', 'ir', '否', 'Y'),
       ('中文(简体)', 'zh', '是', '0Z'),
       ('中文(繁体)', 'cht', '是', '0Z'),
       ('中文(文言文)', 'wyw', '是', 'Z'),
       ('中文(粤语)', 'yue', '是', 'Z'),
       ('扎扎其语', 'zaz', '否', 'Z'),
       ('中古法语', 'frm', '否', 'Z'),
       ('祖鲁语', 'zul', '否', 'Z'),
       ('爪哇语', 'jav', '否', 'Z');