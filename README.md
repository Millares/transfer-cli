# transfer-cli

transfer-cli is a client for the  [https://transfer.sh](https://transfer.sh) service. The client can indicate one or more files and emails, after uploading the files will be sent by email the link to download these compressed files in a zip file.

How to transfer-cli send the download link by mail? Really very easy, to start using it you need to set up an email.

**Requirements:**
 - Java >= 8
 
 *In each release you can find the file transfer-cli.jar. It's a bit heavy (~ 9 MB) because it contains all the dependencies inside*

## Steps to configure an email account:

 1. Create a file called "transfer.properties" at the address "~ / .config / transfer-cli /"
 2. Configure the mail using the parameters you need, the parameters must be supported by [java mail](https://javaee.github.io/javamail/#Project_Documentation)
 
 **Config gmail example:**
 In the case of gmail it is necessary to activate the SMTP and allow access to unsafe applications

    email=usermail@gmail.com //this parameter is used to define the "from" in the mail header
    username=usermail@gmail.com
    password=NOT_THIS
    mail.smtp.auth=true
    mail.smtp.starttls.enable=true
    mail.smtp.host=smtp.gmail.com
    mail.smtp.port=587

## Sharing files

*All the examples will be done using the jar file*

    java -jar transfer-cli.jar -F /myfile.pdf ~/picture.jpg -E friendemail@outlook.com otherfriend@gmail.com

*It is possible to use as many F or E as needed, in the end they are all uploaded to the same zip file and the link is sent to all the recipients*

    java -jar transfer-cli.jar -F /file.mp3 -E friendemail@outlook.com -F ~/file.pdf -E otherfriend@gmail.com

## For users of archlinux

*You can find it in: [AUR](https://aur.archlinux.org/packages/transfer-cli/) or install it with yaourt or other*

    yaourt -S transfer-cli

