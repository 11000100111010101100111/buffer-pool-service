package io.kit.hook.params;

import lombok.Data;

@Data
public class HistoryPageParam {
    String hookId;
    int pageFrom;
    int pageSize;
}
