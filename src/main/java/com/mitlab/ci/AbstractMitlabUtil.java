package com.mitlab.ci;

import com.mitlab.ci.zbox.ZboxException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractMitlabUtil {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private String accessUrl;

    protected AbstractMitlabUtil(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    protected  <T>T proxyPost(Map<String , Object> bodyParams, Map<String, Object> urlParams, Class<T> clazz, String relativePath) {
        return this.proxyPost(bodyParams, urlParams, null, clazz, relativePath);
    }

    protected  <T>T proxyPost(Map<String , Object> bodyParams, Map<String, Object> urlParams, Map<String, Object> headerParams, Class<T> clazz, String relativePath) {
        T proxyResult = null;
        Set<Map.Entry<String, Object>> requestEntries;
        StringEntity requestEntity;
        if (headerParams != null && "application/json".equals(headerParams.get("Content-Type"))) {
            ObjectMapper om = newObjectMapper();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            try {
                om.writeValue(buffer, bodyParams);
                requestEntity = new StringEntity(new String(buffer.toString(Consts.UTF_8.name())), Consts.UTF_8);
            } catch (Exception e) {
                throw new ZboxException(e.getMessage(), e);
            }
        } else {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (bodyParams != null) {
                requestEntries = bodyParams.entrySet();
                for (Map.Entry<String, Object> entry: requestEntries) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
            requestEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        }

        StringBuilder url = new StringBuilder(this.accessUrl);
        url.append(relativePath);
        if (urlParams != null) {
            requestEntries = urlParams.entrySet();
            String append = "?";
            for (Map.Entry<String, Object> entry: requestEntries) {
                url.append(append).append(entry.getKey()).append("=").append(entry.getValue() == null ? "": entry.getValue().toString());
                if (append.equals("?")) {
                    append = "&";
                }
            }
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Access:" + url.toString());
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpRequestBase httpRequestBase;
        if (bodyParams != null) {
            if(headerParams != null && "PUT".equals(headerParams.get("Method"))) {
                httpRequestBase = new HttpPut(url.toString());
            } else {
                httpRequestBase = new HttpPost(url.toString());
            }
        } else {
            if(headerParams != null && "GET".equals(headerParams.get("Method"))) {
                httpRequestBase = new HttpGet(url.toString());
            } else if(headerParams != null && "DELETE".equals(headerParams.get("Method"))) {
                httpRequestBase = new HttpDelete(url.toString());
            } else {
                httpRequestBase = new HttpDelete(url.toString());
            }
        }

        if (headerParams != null) {
            requestEntries = headerParams.entrySet();
            for (Map.Entry<String, Object> entry: requestEntries) {
                if (entry.getValue() == null) {
                    continue;
                }
                if ("Method".equals(entry.getKey())) {
                    continue;
                }
                httpRequestBase.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        CloseableHttpResponse response = null;
        InputStream content = null;
        try {
            if (httpRequestBase instanceof HttpEntityEnclosingRequestBase) {
                ((HttpEntityEnclosingRequestBase) httpRequestBase).setEntity(requestEntity);
            }
            HttpClientContext context = null;
            response = httpclient.execute(httpRequestBase, context);
            if (clazz == Void.class) {
                proxyResult = null;
                if (logger.isLoggable(Level.INFO)) {
                    StringBuilder statusLine = new StringBuilder();
                    StatusLine status = response.getStatusLine();
                    statusLine.append("{StatusCode:").append(status.getStatusCode()).append(",");
                    statusLine.append("ReasonPhrase:").append(status.getReasonPhrase()).append(",");
                    statusLine.append("ProtocolVersion:").append(status.getProtocolVersion()).append("}");
                    logger.info(statusLine.toString());
                }
            } else {
                ByteArrayOutputStream arrayBuffer = new ByteArrayOutputStream();
                HttpEntity responseEntity = response.getEntity();
                responseEntity.writeTo(arrayBuffer);
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Response:" + new String(arrayBuffer.toByteArray()));
                }
                proxyResult = newObjectMapper().readValue(arrayBuffer.toByteArray(), clazz);
            }
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        } finally {
            if (content != null) {
                try {
                    content.close();
                } catch (IOException e) {
                    throw new ZboxException(e.getMessage(), e);
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new ZboxException(e.getMessage(), e);
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
        return proxyResult;
    }
    
    protected String getJsonString(Map<String , Object> bodyParams, Map<String, Object> urlParams, Map<String, Object> headerParams, String relativePath){
        Set<Map.Entry<String, Object>> requestEntries;
        StringEntity requestEntity;
        if (headerParams != null && "application/json".equals(headerParams.get("Content-Type"))) {
            ObjectMapper om = newObjectMapper();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            try {
                om.writeValue(buffer, bodyParams);
                requestEntity = new StringEntity(new String(buffer.toString(Consts.UTF_8.name())), Consts.UTF_8);
            } catch (Exception e) {
                throw new ZboxException(e.getMessage(), e);
            }
        } else {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (bodyParams != null) {
                requestEntries = bodyParams.entrySet();
                for (Map.Entry<String, Object> entry: requestEntries) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
            requestEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        }

        StringBuilder url = new StringBuilder(this.accessUrl);
        url.append(relativePath);
        if (urlParams != null) {
            requestEntries = urlParams.entrySet();
            String append = "?";
            for (Map.Entry<String, Object> entry: requestEntries) {
                url.append(append).append(entry.getKey()).append("=").append(entry.getValue() == null ? "": entry.getValue().toString());
                if (append.equals("?")) {
                    append = "&";
                }
            }
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Access:" + url.toString());
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpRequestBase httpRequestBase;
        if (bodyParams != null) {
            if(headerParams != null && "PUT".equals(headerParams.get("Method"))) {
                httpRequestBase = new HttpPut(url.toString());
            } else {
                httpRequestBase = new HttpPost(url.toString());
            }
        } else {
            if(headerParams != null && "GET".equals(headerParams.get("Method"))) {
                httpRequestBase = new HttpGet(url.toString());
            } else if(headerParams != null && "DELETE".equals(headerParams.get("Method"))) {
                httpRequestBase = new HttpDelete(url.toString());
            } else {
                httpRequestBase = new HttpDelete(url.toString());
            }
        }

        if (headerParams != null) {
            requestEntries = headerParams.entrySet();
            for (Map.Entry<String, Object> entry: requestEntries) {
                if (entry.getValue() == null) {
                    continue;
                }
                if ("Method".equals(entry.getKey())) {
                    continue;
                }
                httpRequestBase.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        CloseableHttpResponse response = null;
        InputStream content = null;
        try {
            if (httpRequestBase instanceof HttpEntityEnclosingRequestBase) {
                ((HttpEntityEnclosingRequestBase) httpRequestBase).setEntity(requestEntity);
            }
            HttpClientContext context = null;
            response = httpclient.execute(httpRequestBase, context);
            ByteArrayOutputStream arrayBuffer = new ByteArrayOutputStream();
            HttpEntity responseEntity = response.getEntity();
            responseEntity.writeTo(arrayBuffer);
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Response:" + new String(arrayBuffer.toByteArray()));
            }
            return new String(arrayBuffer.toByteArray());
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        } finally {
            if (content != null) {
                try {
                    content.close();
                } catch (IOException e) {
                    throw new ZboxException(e.getMessage(), e);
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new ZboxException(e.getMessage(), e);
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
    }

    public static final ObjectMapper newObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }
}
