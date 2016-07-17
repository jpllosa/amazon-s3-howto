package example;

import java.io.FileInputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class SaveFile {

	// to run: java bucketName
	public static void main(String[] args) throws Exception {
		// C:\Users\USERNAME\.aws\credentials on Windows 7
		// http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/set-up-creds.html
		// In IAM (Identity and Access Management) Management Console create an Amazon S3 policy for S3 access
		AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
		if (credentials == null) {
			System.out.println("Error loading Amazon S3 credentials.");
			return;
		}

		AmazonS3 s3 = new AmazonS3Client(credentials);
		if (!s3.doesBucketExist(args[0])) {
			System.out.println("Specified Amazon S3 bucket does not exist.");
			return;
		}

		PutObjectResult result = s3.putObject(args[0], "howToSave.txt", new FileInputStream("howToSave.txt"),
				new ObjectMetadata());
		if (result == null) {
			System.out.println("file not saved...");
		} else {
			System.out.println("file saved...");
		}
	}

}
