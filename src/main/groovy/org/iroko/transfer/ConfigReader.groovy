package org.iroko.transfer

import java.io.File
import java.io.FileReader
import java.util.Properties

class ConfigReader {
    
    Properties props

    ConfigReader() throws FileNotFoundException {
        File configFile = new File("${System.getProperty('user.home')}/.config/transfer-cli/transfer.properties")
        FileReader fr = new FileReader(configFile)
		props = new Properties()
        props.load(fr)
    }

    def getProps() {
        props
    }
}
