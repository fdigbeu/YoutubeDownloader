package com.example.youtubedownloader.youtube;

import android.os.AsyncTask;
import android.util.Log;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.VideoFormat;
import com.github.kiulian.downloader.model.quality.VideoQuality;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class YTDownloaderAsync extends AsyncTask<Void, Void, ArrayList<YTDownloader>> {
    private ArrayList<YTDownloader> ytDownloaders;
    private ArrayList<YTDownloader> ytMp4Format;
    private ArrayList<YTDownloader> ytWebmFormat;
    private YoutubeVideo video;
    private YoutubeDownloader downloader;
    private String videoExtension; // mp4, webm, all
    private String videoId;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        downloader = new YoutubeDownloader();
        downloader.addCipherFunctionPattern(2, "\\b([a-zA-Z0-9$]{2})\\s*=\\s*function\\(\\s*a\\s*\\)\\s*\\{\\s*a\\s*=\\s*a\\.split\\(\\s*\"\"\\s*\\)");
        downloader.setParserRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        downloader.setParserRetryOnFailure(1);
    }

    @Override
    protected ArrayList<YTDownloader> doInBackground(Void... voids) {
        try {
            video = downloader.getVideo(videoId);
            List<VideoFormat> videoFormats = video.findVideoWithQuality(VideoQuality.hd1080);
            fillYoutubeVideoData(videoFormats, "hd1080");
            videoFormats = video.findVideoWithQuality(VideoQuality.hd720);
            fillYoutubeVideoData(videoFormats, "hd720");
            videoFormats = video.findVideoWithQuality(VideoQuality.large);
            fillYoutubeVideoData(videoFormats, "large");
            videoFormats = video.findVideoWithQuality(VideoQuality.medium);
            fillYoutubeVideoData(videoFormats, "medium");
            videoFormats = video.findVideoWithQuality(VideoQuality.small);
            fillYoutubeVideoData(videoFormats, "small");
            videoFormats = video.findVideoWithQuality(VideoQuality.tiny);
            fillYoutubeVideoData(videoFormats, "tiny");
        }
        catch (Exception ex){}
        return ytDownloaders;
    }

    @Override
    protected void onPostExecute(ArrayList<YTDownloader> ytDownloaders) {
        super.onPostExecute(ytDownloaders);
        if(ytDownloaders != null && ytDownloaders.size() > 0){
            for (int i=0; i<ytDownloaders.size(); i++){
                if(ytDownloaders.get(i).getThumbnails() != null && ytDownloaders.get(i).getThumbnails().size() > 0){
                    int totalImage = ytDownloaders.get(i).getThumbnails().size();
                    Log.i("TAG_APP_YOUTUBE", "Image : "+ytDownloaders.get(i).getThumbnails().get(totalImage-1));
                    /*for (int j=0; j<ytDownloaders.get(i).getThumbnails().size(); j++){
                        Log.i("TAG_APP_YOUTUBE", "Image : "+ytDownloaders.get(i).getThumbnails().get(j));
                    }*/
                }
                Log.i("TAG_APP_YOUTUBE", "Resolution : "+ytDownloaders.get(i).getResolution());
                Log.i("TAG_APP_YOUTUBE", "Title : "+ytDownloaders.get(i).getTitle());
                Log.i("TAG_APP_YOUTUBE", "Filename : "+ytDownloaders.get(i).getFilename());
                Log.i("TAG_APP_YOUTUBE", "Extension : "+ytDownloaders.get(i).getExtension().toUpperCase());
                Log.i("TAG_APP_YOUTUBE", "Size : "+ytDownloaders.get(i).getSize());
                Log.i("TAG_APP_YOUTUBE", "DownloadUrl : "+ytDownloaders.get(i).getDownload_url());
                Log.i("TAG_APP_YOUTUBE", "----------------------");
            }
        }
    }

    public void initialize(String videoId, String videoExtension){
        this.videoId = videoId;
        this.videoExtension = videoExtension;
    }

    private void fillYoutubeVideoData(List<VideoFormat> videoFormats, String quality){
        try {
            if(ytDownloaders==null){ytDownloaders = new ArrayList<>();}
            if(ytMp4Format==null){ytMp4Format = new ArrayList<>();}
            if(ytWebmFormat==null){ytWebmFormat = new ArrayList<>();}
            //--
            String videoTitle = video.details().title();
            long videoNbView = video.details().viewCount();
            String description = video.details().description();
            List<String> images = video.details().thumbnails();
            ArrayList<String> thumbnails = new ArrayList<>();
            if(images != null && images.size() > 0){
                for (int i=0; i<images.size(); i++){thumbnails.add(images.get(i));}
            }
            //--
            if(videoFormats != null && videoFormats.size() > 0){
                for (int i=0; i<videoFormats.size(); i++){
                    VideoFormat videoFormat = videoFormats.get(i);
                    String qualityLabel = videoFormat.qualityLabel();
                    //String type = videoFormat.type();
                    String mimeType = videoFormat.mimeType();
                    String type = mimeType.replace(";", "").split(" ")[0];
                    String url = videoFormat.url();
                    VideoQuality videoQuality = videoFormat.videoQuality();
                    int height = videoFormat.height();
                    int width = videoFormat.width();
                    String resolution = width+"x"+height;
                    long contentLength = videoFormat.contentLength();
                    String fileSize = getFileSize(contentLength);
                    //Extension extension = videoFormat.extension();
                    String extension = type.replace("video/", "");
                    long duration = videoFormat.duration();
                    //Itag itag = videoFormat.itag();
                    String itag = ""+videoFormat.itag();
                    int bitrate = videoFormat.bitrate();
                    long lastModified = videoFormat.lastModified();
                    String filename = removeAccents(videoTitle);
                    //--
                    YTDownloader ytDownloader = new YTDownloader(videoId, videoId, itag, quality, qualityLabel,  type,  extension, resolution,  url, url, "0", "0.0.0.0", "0.0.0.0", ""+videoNbView, videoTitle, fileSize, filename, description,  mimeType, thumbnails);
                    if(extension.equalsIgnoreCase("mp4")){
                        ytMp4Format.add(ytDownloader);
                    }
                    //--
                    if(extension.equalsIgnoreCase("webm")){
                        ytWebmFormat.add(ytDownloader);
                    }
                }
            }
            //--
            if(videoExtension.equalsIgnoreCase("mp4")){
                ytDownloaders = ytMp4Format;
            }
            else if(videoExtension.equalsIgnoreCase("webm")){
                ytDownloaders = ytWebmFormat;
            }
            else{
                for (int i=0; i<ytMp4Format.size(); i++){ ytDownloaders.add(ytMp4Format.get(i)); }
                for (int i=0; i<ytWebmFormat.size(); i++){ ytDownloaders.add(ytWebmFormat.get(i)); }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e("TAG_APP_ERROR", "YTDownloaderAsync-->fillYoutubeVideoData() : "+ex.getMessage());
        }
    }

    private String getFileSize(long size){
        DecimalFormat df = new DecimalFormat("0.00");
        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTerra = sizeGb * sizeKb;
        if(size < sizeMb)
            return df.format(size / sizeKb)+ " KB";
        else if(size < sizeGb)
            return df.format(size / sizeMb) + " MB";
        else if(size < sizeTerra)
            return df.format(size / sizeGb) + " GB";
        return "0";
    }

    private String removeAccents(String s) {
        String src = s.replace(")", " ").trim();
        String resultat = Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return resultat.replaceAll("\\W","-");
    }
}
