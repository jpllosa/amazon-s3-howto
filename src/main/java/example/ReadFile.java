package example;

import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class ReadFile {

	// to run: java bucketName
	public static void main(String[] args) throws Exception {
		// C:\Users\USERNAME\.aws\credentials on Windows 7
		// http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/set-up-creds.html
		// In IAM (Identity and Access Management) Management Console create an
		// Amazon S3 policy for S3 access
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

		InputStream is = s3.getObject(args[0], "howToSave.txt").getObjectContent();
		if (is != null) {
			int data;
			while ((data = is.read()) >= 0) {
				System.out.print((char) data);
			}
			System.out.println("end of file reached");
		} else {
			System.out.println("file not found...");
		}
		is.close();
	}
}
