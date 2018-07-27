package org.iroko.transfer

import java.util.Properties

class Main {

    static main(args) {
        
        try{
            ConfigReader config = new ConfigReader()

            def props = config.props
            
            ArgsParser argsParser = new ArgsParser(args)

            def shareUrl
            
            if (argsParser.hasErrors()) {
                argsParser.errors.each {println it}
            } else {
                if (argsParser.files.size > 0) {
                    Transfer transfer = new Transfer(argsParser.files)
                    shareUrl = transfer.sendFiles()
                }
                
                if (argsParser.emails.size > 0 && shareUrl) {
                    EmailShare emailShare = new EmailShare(props, argsParser.emails)
                    emailShare.share(shareUrl)
                }
                
                println shareUrl
            }
        }catch (FileNotFoundException e) {
            println "Config file not found !!"
        }
    }

}
