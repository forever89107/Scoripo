package com.my.enumerate;

import lombok.Getter;

import java.util.*;

import static java.util.Optional.*;

@SuppressWarnings("unchecked")
public enum Language {
    ZH_CN("zh_CN", "简体中文", "Simplified Chinese", "Tiếng Trung giản thể", "簡體中文", "중국어 간체", "簡体字中国語", "ภาษาจีนตัวย่อ", "Basitleştirilmiş Çince", "Chinês simplificado", "chino simplificado", "الصينية المبسطة", true),

    ZH_TW("zh_TW", "繁体中文", "Traditional Chinese", "truyền thống Trung Quốc", "繁體中文", "중국어 번체", "繁体字中国語", "ภาษาจีนแบบดั้งเดิม", "Geleneksel çince", "chinês tradicional", "chino tradicional", "الصينية التقليدية", false),

    EN("en", "英语", "English", "Tiếng Anh", "英語", "영어", "英語", "ภาษาอังกฤษ", "ingilizce", "Inglês", "Inglés", "إنجليزي", true),

    VIETNAM("vietnam", "越南语", "Vietnamese", "Tiếng Việt", "越南語", "베트남어", "ベトナム語", "ภาษาเวียดนาม", "Vietnam", "vietnamita", "vietnamita", "فيتنامي", true),

    KOREA("korea", "韩语", "Korean", "Hàn Quốc", "韓語", "한국인", "韓国語", "เกาหลี", "Koreli", "coreano", "coreano", "كوريا", false),

    JAPANESE("japanese", "日语", "Japanese", "tiếng Nhật", "日語", "일본어", "日本語", "ญี่ปุ่น", "Japonca", "japonês", "japonés", "اليابانية", true),

    THAI("thai", "泰语", "Thai", "Tiếng thái", "泰國語", "태국어", "タイ語", "ภาษาไทย", "Tayland", "tailandês", "tailandés", "التايلاندية", true),

    TURKISH("turkish", "土耳其文", "Turkish", "Thổ Nhĩ Kỳ", "土耳其文", "터키어", "トルコ語", "ตุรกี", "Türk", "turco", "turco", "اللغة التركية", true),

    PORTUGUESE("portuguese", "葡萄牙语", "Portuguese", "Bồ Đào Nha", "葡萄牙語", "터키어", "포르투갈", "โปรตุเกส", "Portekiz", "Português", "portugués", "البرتغالية", true),

    SPANISH("spanish", "西班牙文", "Spanish", "người Tây Ban Nha", "西班牙文", "스페인의", "スペイン語", "สเปน", "İspanyol", "espanhol", "español", "الأسبانية", true),

    ARABIC("arabic", "阿拉伯语", "Arabic", "Thổ Nhĩ Kỳ", "阿拉伯語", "터키어", "トルコ語", "ตุรกี", "Türk", "Arábica", "árabe", "عربي", true),
    ;
    @Getter
    private final String code;
    private final String desc;
    private final String enDesc;
    private final String vietnamDesc;
    private final String zh_TWDesc;
    private final String koreaDesc;
    private final String japaneseDesc;
    private final String thaiDesc;
    private final String turkishDesc;
    private final String portugueseDesc;
    private final String spanishDesc;
    private final String arabicDesc;
    private final Boolean enable;

    private static Map<String, Language> languageMap = new HashMap<String, Language>();
    private static Set<Language> enabledLanguage = new HashSet<Language>();

    static {
        for (Language languageEnum : Language.values()) {
            languageMap.put(languageEnum.getCode(), languageEnum);
            if (languageEnum.enable) {
                enabledLanguage.add(languageEnum);
            }
        }
    }

    Language(String enDesc, String vietnamDesc, String zh_TWDesc, String koreaDesc,
             String japaneseDesc, String thaiDesc, String turkishDesc, String portugueseDesc, String spanishDesc,
             String arabicDesc, String code, String desc, Boolean enable) {
        this.code = code;
        this.enDesc = enDesc;
        this.vietnamDesc = vietnamDesc;
        this.zh_TWDesc = zh_TWDesc;
        this.koreaDesc = koreaDesc;
        this.japaneseDesc = japaneseDesc;
        this.thaiDesc = thaiDesc;
        this.turkishDesc = turkishDesc;
        this.portugueseDesc = portugueseDesc;
        this.spanishDesc = spanishDesc;
        this.arabicDesc = arabicDesc;
        this.desc = desc;
        this.enable = enable;
    }

    public String getMessage() {
        return desc;
    }

    public String getMessage(Language language) {
        switch (ofNullable(language).orElse(ZH_CN)) {
            case EN:
                return enDesc;
            case VIETNAM:
                return vietnamDesc;
            case ZH_TW:
                return zh_TWDesc;
            case KOREA:
                return koreaDesc;
            case JAPANESE:
                return japaneseDesc;
            case THAI:
                return thaiDesc;
            case TURKISH:
                return turkishDesc;
            case PORTUGUESE:
                return portugueseDesc;
            case SPANISH:
                return spanishDesc;
            case ARABIC:
                return arabicDesc;
            default:
                return desc;
        }
    }

    public static List<String> getLanguageCode() {
        List<String> languageCode = new ArrayList<String>();
        languageCode.add(Language.EN.getCode());
        languageCode.add(Language.ZH_CN.getCode());
        languageCode.add(Language.VIETNAM.getCode());
        languageCode.add(Language.THAI.getCode());
        languageCode.add(Language.TURKISH.getCode());
        languageCode.add(Language.JAPANESE.getCode());
        languageCode.add(Language.ARABIC.getCode());
        return languageCode;
    }

    public static Language getLanguage(String name) {
        return languageMap.get(name);
    }

    public static Set<Language> getEnabledLanguage() {
        return enabledLanguage;
    }

}
