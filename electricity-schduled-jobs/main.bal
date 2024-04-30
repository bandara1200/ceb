import wso2/choreo.sendemail;
import ballerina/io;

configurable string url = ?;
configurable string email = ?;
const emailSubject = "Your monthly bill has arrvied";

// Create a new email client
sendemail:Client emailClient = check new ();

public function main() returns error? {

    // Send the email
    string _ = check emailClient->sendEmail(email, emailSubject, generateEmailBody(url));
    io:println("Successfully sent the email.");
}