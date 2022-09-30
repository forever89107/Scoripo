package scorpio.core.constant;

import lombok.Getter;

@Getter
public enum Gender {
    Female(0, "女"),
    Male(1, "男"),
    Other(2, "其它"),
    ;

    private int code;
    private String desc;

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根據desc獲取Code .
     */
    public static Gender getCode(String desc) {
        for (Gender s : Gender.values()) {
            if (s.getDesc().equals(desc)) return s;
        }
        throw new IllegalArgumentException("查無此描述");
    }
}
