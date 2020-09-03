package com.example.youtubedownloader.youtube;

import java.util.ArrayList;

public class YTDownloader {
    private String id;
    private String videoId;
    private String itag;
    private String quality;
    private String qualityLabel;
    private String type;
    private String extension;
    private String resolution;
    private String url;
    private String download_url;
    private String expires;
    private String ipbits;
    private String ip;
    private String view_count;
    private String title;
    private String size;
    private String filename;
    private String description;
    private String mimeType;
    private ArrayList<String> thumbnails;

    public YTDownloader(String id, String videoId, String itag, String quality, String qualityLabel, String type, String extension, String resolution, String url, String download_url, String expires, String ipbits, String ip, String view_count, String title, String size, String filename, String description, String mimeType, ArrayList<String> thumbnails) {
        this.id = id;
        this.videoId = videoId;
        this.itag = itag;
        this.quality = quality;
        this.qualityLabel = qualityLabel;
        this.type = type;
        this.extension = extension;
        this.resolution = resolution;
        this.url = url;
        this.download_url = download_url;
        this.expires = expires;
        this.ipbits = ipbits;
        this.ip = ip;
        this.view_count = view_count;
        this.title = title;
        this.size = size;
        this.filename = filename;
        this.description = description;
        this.mimeType = mimeType;
        this.thumbnails = thumbnails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getItag() {
        return itag;
    }

    public void setItag(String itag) {
        this.itag = itag;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQualityLabel() {
        return qualityLabel;
    }

    public void setQualityLabel(String qualityLabel) {
        this.qualityLabel = qualityLabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getIpbits() {
        return ipbits;
    }

    public void setIpbits(String ipbits) {
        this.ipbits = ipbits;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public ArrayList<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ArrayList<String> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
