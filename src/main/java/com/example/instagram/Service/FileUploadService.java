package com.example.instagram.Service;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.instagram.Exception.InvalidFileException;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

@Service
public class FileUploadService {
    static final private String path = System.getProperty("user.dir") + "\\uploads\\";
    @Value("${aws.secretAccessKey}")
    static private String secretAccessKey;

    @Value("${aws.accessKey}")
    static private String accessKey;
    

    public String storeToLocal(MultipartFile file, String filename) throws Exception
    {
        if(file.isEmpty()) throw new InvalidFileException();
        
        /*try(InputStream stream = file.getInputStream()){
            Files.copy(stream, target);
        }
        catch(Exception exception)
        {
            System.out.println(exception.getCause());
            System.out.println(exception.getLocalizedMessage());
            System.out.println(exception.getStackTrace().toString());
            throw exception;
        }*/
        String extension =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        File target = new File(path + filename + "." + extension);
        file.transferTo(target);
        System.out.println("target " + target);
        return "Great";
    }
    
    public String storeToCloud(MultipartFile file, String filename) throws InvalidFileException
    {
        if(file.isEmpty()) throw new InvalidFileException();
        S3AsyncClient client = S3AsyncClient.builder()
                               .credentialsProvider(new AwsCredentialsProvider() {
                                @Override
                                public AwsCredentials resolveCredentials() {
                                    return AwsBasicCredentials.create(accessKey, secretAccessKey);
                                }
                               })
                               .region(Region.EU_NORTH_1)
                               .build();
        S3TransferManager manager = S3TransferManager.builder().s3Client(client).build();
                               
        return null;
    }
    
}
