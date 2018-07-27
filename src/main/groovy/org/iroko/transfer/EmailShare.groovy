package org.iroko.transfer

import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class EmailShare {

    Session session
    List emails
    Properties properties
    
    EmailShare(Properties properties, List emails) {
		session = Session.getInstance(properties,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.get('username'), properties.get('password'))
			}
		  })
        this.emails = emails
        this.properties = properties
    }

    def share(shareUrl) {
		try {
			Message message = new MimeMessage(session)
			message.setFrom(new InternetAddress(properties.get('email')))
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emails.join(',')))
			message.setSubject("Transfer download link")
			message.setText("${shareUrl}\n\nThe link is alive for 14 days")

			Transport.send(message)

			System.out.println("Done: Emails delivered")

		} catch (MessagingException e) {
			throw new MessagingException(e)
		}
    }
}
