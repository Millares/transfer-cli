import spock.lang.*
import org.iroko.transfer.ArgsParser


class ArgsParserSpec extends Specification {
    def "testing Args parser with invalid e-mails"() {
        when:
        def args = ['-F', '~/test.file', '-E', 'bademail@']
        ArgsParser parser = new ArgsParser(args)
        println parser.errors
        then:
        parser.hasErrors() == true  
        parser.errors.size == 1
    }

    def "Testing Args parser with invalid files"() {
        when:
        def args = ['-F', '~/test1.file', '-E', 'goodemail@gmail.com']
        ArgsParser parser = new ArgsParser(args)
        println parser.errors
        then:
        parser.hasErrors() == true  
        parser.errors.size == 1
    }
}
