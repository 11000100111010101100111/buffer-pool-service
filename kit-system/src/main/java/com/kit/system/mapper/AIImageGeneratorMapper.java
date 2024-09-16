package com.kit.system.mapper;

import com.kit.system.domain.ai.img.entity.ProcessInfoEntity;
import com.kit.system.domain.ai.img.entity.ProcessStepInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AIImageGeneratorMapper {

    public void saveProcessInfo(ProcessInfoEntity processInfo);

    public ProcessInfoEntity findByProcessId(@Param("processId") String processId, @Param("ipOrUserId") String ipOrUserId);

    public void deployProcessStepInfo(ProcessStepInfo stepInfo);

    public List<ProcessStepInfo> findStepInfoByProcessId(@Param("processId") String processId, @Param("ipOrUserId") String ipOrUserId);

    public void updateUrl(@Param("processId") String processId,
                          @Param("url") String ipOrUserId,
                          @Param("result") String result,
                          @Param("resultMessage") String resultMessage);
}
