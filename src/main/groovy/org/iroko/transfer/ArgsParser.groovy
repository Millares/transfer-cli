package org.iroko.transfer

import java.io.File
import org.apache.commons.validator.EmailValidator

class ArgsParser {

    String FILE_STATE = '-F'
    String EMAIL_STATE = '-E'
    def files = []
    def emails = []
    def errors = []

    ArgsParser(def args) {
        def state
        for (arg in args) {
            if (arg == FILE_STATE || arg == EMAIL_STATE) {
                state = arg
            } else if (state && state == FILE_STATE) {
                if (arg[0] == '~') {
                    arg = arg.replace('~', System.getProperty('user.home'))
                }
                File argFile = new File(arg);
                if (!argFile.exists()) {
                    errors.add("File ${argFile.getName()} not exists !!!")
                } else {
                    files.add(argFile)
                }
            } else if (state && state == EMAIL_STATE) {
               if (!EmailValidator.getInstance().isValid(arg)) {
                    errors.add("E-mail ${arg} is wrong !!!")
                } else {
                    emails.add(arg)
                }
            }
        }
    }

    def hasErrors() {
        errors.size > 0
    }
}
