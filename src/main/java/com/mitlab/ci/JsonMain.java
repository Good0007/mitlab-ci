package com.mitlab.ci;

import com.mitlab.ci.zbox.ZboxSessionResult;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonMain {
    public static void main(String[] args ) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://192.168.60.50:26080/zentao/project.json");
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            ObjectMapper om = new ObjectMapper();
            ZboxSessionResult zr = om.readValue(entity1.getContent(), ZboxSessionResult.class);
            System.out.println(zr.getStatus());
            //System.out.println(zr.getSession().getSessionID());
        } finally {
            response1.close();
        }
    }
}
