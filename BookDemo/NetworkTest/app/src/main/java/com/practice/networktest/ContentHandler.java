package com.practice.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/1518:12
 * @description 9.3解析XML格式数据
 */
public class ContentHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    //开始xml解析的时候调用
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        //初始化
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    //开始解析某个节点的时候调用
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //记录当前节点名
        nodeName = localName;//当前节点名为app
    }

    //获取节点中内容的时候调用
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //获取时是一个字符一个字符的获取，例如name为google,那么name添加的顺序为g,o,o,g,l,e.一个个拼接
        //根据当前的节点名判断将内容添加到哪一个StringBuilder对象中
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if("name".equals(nodeName)){
            name.append(ch,start,length);
        }else if("version".equals(nodeName)){
            version.append(ch,start,length);
        }
    }

    //完成解析某个节点的时候调用
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if("app".equals(nodeName)) {//当前节点名为/app
            //trim() 去除回车或换行符
            Log.d("ContentHandler", "id is " + id.toString().trim());
            Log.d("ContentHandler", "name is " + name.toString().trim());
            Log.d("ContentHandler", "version is " + version.toString().trim());
            // 最后要将StringBuilder清空掉
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    //完成整个xml解析的时候调用
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
