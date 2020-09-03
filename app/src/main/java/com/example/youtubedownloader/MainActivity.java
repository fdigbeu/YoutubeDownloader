package com.example.youtubedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.youtubedownloader.youtube.YTDownloaderAsync;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.VideoFormat;
import com.github.kiulian.downloader.model.quality.VideoQuality;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private YTDownloaderAsync downloaderAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new FileDownloader().execute();

        downloaderAsync = new YTDownloaderAsync();
        downloaderAsync.initialize("FjVa8I-iOl8", "mp4");
        downloaderAsync.execute();
    }

    class FileDownloader extends AsyncTask<Void, Void, Void>{

        YoutubeVideo video;

        // init downloader
        YoutubeDownloader downloader = new YoutubeDownloader();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            downloader.addCipherFunctionPattern(2, "\\b([a-zA-Z0-9$]{2})\\s*=\\s*function\\(\\s*a\\s*\\)\\s*\\{\\s*a\\s*=\\s*a\\.split\\(\\s*\"\"\\s*\\)");
            downloader.setParserRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
            downloader.setParserRetryOnFailure(1);
            // parsing data
            String videoId = "FjVa8I-iOl8";
            try {
                video = downloader.getVideo(videoId);
                Log.i("TAG_APP_YOUTUBE", "TITLE : "+video.details().title());
                Log.i("TAG_APP_YOUTUBE", "NB VUES : "+video.details().viewCount());
                List<VideoFormat> videoFormats = video.findVideoWithQuality(VideoQuality.hd720);
                if(videoFormats != null && videoFormats.size() > 0){
                    for (int i=0; i<videoFormats.size(); i++){
                        VideoFormat videoFormat = videoFormats.get(i);
                        Log.i("TAG_APP_YOUTUBE", "qualityLabel : "+videoFormat.qualityLabel());
                        Log.i("TAG_APP_YOUTUBE", "type : "+videoFormat.type());
                        Log.i("TAG_APP_YOUTUBE", "mimeType : "+videoFormat.mimeType());
                        Log.i("TAG_APP_YOUTUBE", "url : "+videoFormat.url());
                        Log.i("TAG_APP_YOUTUBE", "videoQuality : "+videoFormat.videoQuality());
                        Log.i("TAG_APP_YOUTUBE", "height : "+videoFormat.height());
                        Log.i("TAG_APP_YOUTUBE", "width : "+videoFormat.width());
                        Log.i("TAG_APP_YOUTUBE", "contentLength : "+videoFormat.contentLength());
                        Log.i("TAG_APP_YOUTUBE", "extension : "+videoFormat.extension());
                        Log.i("TAG_APP_YOUTUBE", "duration : "+videoFormat.duration());
                        Log.i("TAG_APP_YOUTUBE", "itag : "+videoFormat.itag());
                        Log.i("TAG_APP_YOUTUBE", "bitrate : "+videoFormat.bitrate());
                        Log.i("TAG_APP_YOUTUBE", "lastModified : "+videoFormat.lastModified());
                        Log.i("TAG_APP_YOUTUBE", "----------------------");
                    }
                }
                //Log.i("TAG_APP_YOUTUBE", "TITLE : "+video.details().title());
                //Log.i("TAG_APP_YOUTUBE", "TITLE : "+video.details().title());
                //Log.i("TAG_APP_YOUTUBE", "TITLE : "+video.details().title());
                Log.i("TAG_APP_YOUTUBE", "DESCRIPTION : "+video.details().description());
            }
            catch (Exception ex){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}