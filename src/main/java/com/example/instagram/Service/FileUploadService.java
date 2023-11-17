package com.example.instagram.Service;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.instagram.Exception.InvalidFileException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class FileUploadService {
    static final private String path = System.getProperty("user.dir") + "\\uploads\\";
    @Value("${aws.secretAccessKey}")
    private String secretAccessKey;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("instagram-post-images-bucket")
    private String bucketName;

    public List<String> storeToLocal(MultipartFile file, String filename) throws Exception {
        if (file.isEmpty())
            throw new InvalidFileException();
        String orignialFilename = file.getOriginalFilename();
        String extension;
        if(orignialFilename != null)
        {
            extension = orignialFilename.substring(orignialFilename.lastIndexOf(".") + 1);
        }
        else
        {
            extension = "";
        }
        String completeFilename = filename + "." + extension; 
        String filePath = path + completeFilename;
        File target = new File(filePath);
        file.transferTo(target);
        return Arrays.asList(new String[]{filePath, completeFilename});
    }

    public String storeToCloud(String filepath, String filename) throws InvalidFileException {
        try {

            S3Client s3 = S3Client.builder()
                    .forcePathStyle(true)
                    .region(Region.EU_NORTH_1)
                    .credentialsProvider(new AwsCredentialsProvider() {
                        @Override
                        public AwsCredentials resolveCredentials() {
                            return AwsBasicCredentials.create(accessKey, secretAccessKey);
                        }
                    })
                    .endpointOverride(URI.create("https://s3.eu-north-1.amazonaws.com"))
                    .build();
            PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(filename).build();
            s3.putObject(request, RequestBody.fromFile(new File(filepath)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return "https://" + bucketName + ".s3.eu-north-1.amazonaws.com/" + filename;
    }
}
