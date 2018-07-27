package org.iroko.transfer

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.HttpVersion
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.ContentBody
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.params.CoreProtocolPNames
import org.apache.http.util.EntityUtils

class Transfer {

    HttpClient httpclient
    MultipartEntity mpEntity
    HttpPost httppost
    String URL = "https://transfer.sh"
    List files

    Transfer(List files) {
        httpclient = new DefaultHttpClient()
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1)
        httppost = new HttpPost(URL)
        this.files = files
    }

    def sendFiles() {
        def transferKey

        MultipartEntity mpEntity = new MultipartEntity()

        files.each {mpEntity.addPart("file", new FileBody(it))}

        httppost.setEntity(mpEntity);
        System.out.println("executing request " + httppost.getRequestLine())
        HttpResponse response = httpclient.execute(httppost)
        HttpEntity resEntity = response.getEntity()

        println "${response.getStatusLine()}: Files uploaded"
        if (resEntity != null) {
            def responseStr = EntityUtils.toString(resEntity)
            transferKey = responseStr.split('/')[3]
        }
        if (resEntity != null) {
            resEntity.consumeContent()
        }

        httpclient.getConnectionManager().shutdown()
        
        buildDownloadLink(transferKey, files)
    }

    def buildDownloadLink(String key, List files) {
        def filesPathGroup = files.collect {"${key}/${it.getName()}"}
        "${URL}/(${filesPathGroup.join(',')}).zip"
    }
}
