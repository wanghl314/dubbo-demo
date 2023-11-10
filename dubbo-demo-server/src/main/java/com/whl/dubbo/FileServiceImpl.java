package com.whl.dubbo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.dubbo.common.utils.StringUtils;

public class FileServiceImpl implements FileService {

    @Override
    public String upload(String name, InputStream is) throws IOException {
        String suffix = "";

        if (StringUtils.isContains(name, '.')) {
            suffix = name.substring(name.lastIndexOf("."));
        }
        String storeFileName = UUID.randomUUID() + suffix;
        Path path = this.getFileStorePath();
        Path storePath = path.resolve(storeFileName);

        if (!Files.exists(storePath.getParent())) {
            Files.createDirectories(storePath.getParent());
        }
        Files.copy(is, storePath, StandardCopyOption.REPLACE_EXISTING);
        return storeFileName;
    }

    @Override
    public InputStream download(String name) throws IOException {
        Path path = this.getFileStorePath();
        Path file = path.resolve(name);

        if (Files.exists(file)) {
            return Files.newInputStream(file);
        }
        return null;
    }

    private Path getFileStorePath() {
        return Paths.get(System.getProperty("user.dir"), "upload");
    }

}
