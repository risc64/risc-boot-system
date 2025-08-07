package com.risc.boot.modules.base.controller;

import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.constant.SystemConstants;
import com.risc.boot.config.SystemConfig;
import com.risc.boot.modules.base.bo.BaseFile;
import com.risc.boot.modules.base.service.BaseFileService;
import com.risc.boot.modules.system.bo.SysUser;
import com.risc.boot.modules.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @auhtor 李良发
 * @since 2025/7/31 15:49
 */
@RestController
@Slf4j
public class WopiController {

    @Resource
    private BaseFileService baseFileService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SystemConfig systemConfig;

    private final Map<String, String> locks = new HashMap<>();


    /**
     * 获取文件信息
     * @param fileId
     * @return
     * @throws IOException
     */
    @GetMapping("/wopi/files/{fileId}")
    public ResponseEntity<?> getFileMetadata(@PathVariable String fileId, @RequestParam String userId) throws IOException {
        SysUser user = sysUserService.selectByKey(userId);
        if (user == null)  {
            Result<String> result = new Result<>();
            result.setStatusEnum(StatusEnum.PERMISSION_DENIED, null);
            return ResponseEntity.ok(result);
        }

        BaseFile baseFile = baseFileService.selectByKey(fileId);
        if (baseFile == null || StringUtils.isBlank(baseFile.getFilePath())) return ResponseEntity.notFound().build();
        ApplicationHome h = new ApplicationHome(this.getClass());
        String basePath = h.getSource().getParent().toString()+ SystemConstants.FILE_ROOT_PATH + "/";

        Path file = Paths.get(basePath, baseFile.getFilePath());
        if (!Files.exists(file)) return ResponseEntity.notFound().build();

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("BaseFileName", file.getFileName().toString());
        metadata.put("Size", Files.size(file));
        metadata.put("OwnerId", baseFile.getCreateUserUid());
        metadata.put("Version", "v1");
        metadata.put("UserId", user.getUid());
        metadata.put("UserFriendlyName", user.getUserNick());
        metadata.put("UserCanWrite", true);
        metadata.put("PostMessageOrigin", systemConfig.getCollaboraUrl());
        return ResponseEntity.ok(metadata);
    }

    /**
     * 获取文件内容
     * @param fileId
     * @return
     * @throws IOException
     */
    @GetMapping("/wopi/files/{fileId}/contents")
    public ResponseEntity<?> getFileContent(@PathVariable String fileId) throws IOException {

        BaseFile baseFile = baseFileService.selectByKey(fileId);
        if (baseFile == null || StringUtils.isBlank(baseFile.getFilePath())) return ResponseEntity.notFound().build();
        ApplicationHome h = new ApplicationHome(this.getClass());
        String basePath = h.getSource().getParent().toString()+ SystemConstants.FILE_ROOT_PATH + "/";

        Path file = Paths.get(basePath, baseFile.getFilePath());
        if (!Files.exists(file)) return ResponseEntity.notFound().build();

        byte[] data = Files.readAllBytes(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }

    /**
     * 保存文件内容
     * @param fileId
     * @param lock
     * @param data
     * @return
     * @throws IOException
     */
    @PostMapping("/wopi/files/{fileId}/contents")
    public ResponseEntity<?> saveFileContent(@PathVariable String fileId,
                                             @RequestHeader(value = "X-WOPI-Lock", required = false) String lock,
                                             @RequestBody byte[] data) throws IOException {

        BaseFile baseFile = baseFileService.selectByKey(fileId);
        if (baseFile == null || StringUtils.isBlank(baseFile.getFilePath())) return ResponseEntity.notFound().build();
        ApplicationHome h = new ApplicationHome(this.getClass());
        String basePath = h.getSource().getParent().toString()+ SystemConstants.FILE_ROOT_PATH + "/";

        Path file = Paths.get(basePath, baseFile.getFilePath());
        if (!Files.exists(file)) return ResponseEntity.notFound().build();

        String currentLock = locks.getOrDefault(fileId, "");
        if (lock != null && !lock.equals(currentLock)) {
            return ResponseEntity.status(409).header("X-WOPI-Lock", currentLock).body("Lock mismatch");
        }

        Files.write(file, data);
        return ResponseEntity.ok("Saved");
    }

    /**
     * 处理锁定请求
     * @param fileId
     * @param override
     * @param lock
     * @return
     */
    @PostMapping("/wopi/files/{fileId}")
    public ResponseEntity<?> handleLock(@PathVariable String fileId,
                                        @RequestHeader("X-WOPI-Override") String override,
                                        @RequestHeader(value = "X-WOPI-Lock", required = false) String lock) {
        switch (override) {
            case "LOCK":
                locks.put(fileId, lock);
                return ResponseEntity.ok("Lock set");
            case "UNLOCK":
                locks.remove(fileId);
                return ResponseEntity.ok("Lock removed");
            case "REFRESH_LOCK":
                locks.put(fileId, lock);
                return ResponseEntity.ok("Lock refreshed");
            default:
                return ResponseEntity.status(400).body("Unsupported operation");
        }
    }

}
