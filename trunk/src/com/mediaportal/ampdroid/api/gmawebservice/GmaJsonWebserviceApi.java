package com.mediaportal.ampdroid.api.gmawebservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.mediaportal.ampdroid.api.CustomDateDeserializer;
import com.mediaportal.ampdroid.api.IMediaAccessApi;
import com.mediaportal.ampdroid.api.JsonClient;
import com.mediaportal.ampdroid.api.JsonUtils;
import com.mediaportal.ampdroid.data.EpisodeDetails;
import com.mediaportal.ampdroid.data.FileInfo;
import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.data.MovieFull;
import com.mediaportal.ampdroid.data.MusicAlbum;
import com.mediaportal.ampdroid.data.MusicArtist;
import com.mediaportal.ampdroid.data.MusicTrack;
import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.data.SeriesEpisode;
import com.mediaportal.ampdroid.data.SeriesFull;
import com.mediaportal.ampdroid.data.SeriesSeason;
import com.mediaportal.ampdroid.data.SupportedFunctions;
import com.mediaportal.ampdroid.data.VideoShare;
import com.mediaportal.ampdroid.utils.LogUtils;

public class GmaJsonWebserviceApi implements IMediaAccessApi {
   private GmaJsonWebserviceMovieApi mMoviesAPI;
   private GmaJsonWebserviceSeriesApi mSeriesAPI;
   private GmaJsonWebserviceVideoApi mVideosAPI;
   private GmaJsonWebserviceMusicApi mMusicAPI;

   private String mServer;
   private int mPort;

   private String mUser;
   private String mPass;
   private boolean mUseAuth;

   private final String GET_SUPPORTED_FUNCTIONS = "MP_GetSupportedFunctions";

   private final String JSON_PREFIX = "http://";
   private final String JSON_SUFFIX = "/GmaWebService/MediaAccessService/json";
   private final String STREAM_SUFFIX = "/GmaWebService/MediaAccessService/stream";

   private JsonClient mJsonClient;
   private ObjectMapper mJsonObjectMapper;

   @SuppressWarnings("unchecked")
   public GmaJsonWebserviceApi(String _server, int _port, String _user, String _pass, boolean _auth) {
      mServer = _server;
      mPort = _port;

      mUser = _user;
      mPass = _pass;
      mUseAuth = _auth;

      mJsonClient = new JsonClient(JSON_PREFIX + mServer + ":" + mPort + JSON_SUFFIX, _user, _pass,
            _auth);
      mJsonObjectMapper = new ObjectMapper();
      mJsonObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      CustomDeserializerFactory sf = new CustomDeserializerFactory();
      mJsonObjectMapper.setDeserializerProvider(new StdDeserializerProvider(sf));
      sf.addSpecificMapping(Date.class, new CustomDateDeserializer());

      mMoviesAPI = new GmaJsonWebserviceMovieApi(mJsonClient, mJsonObjectMapper);
      mSeriesAPI = new GmaJsonWebserviceSeriesApi(mJsonClient, mJsonObjectMapper);
      mVideosAPI = new GmaJsonWebserviceVideoApi(mJsonClient, mJsonObjectMapper);
      mMusicAPI = new GmaJsonWebserviceMusicApi(mJsonClient, mJsonObjectMapper);
      // m_musicAPI = new GmaWebserviceMusicApi(m_wcfService,
      // mJsonObjectMapper);
   }

   public GmaJsonWebserviceApi(String _server, int _port) {
      this(_server, _port, "", "", false);
   }

   @Override
   public String getServer() {
      return mServer;
   }

   @Override
   public int getPort() {
      return mPort;
   }

   @Override
   public String getUserName() {
      return mUser;
   }

   @Override
   public String getUserPass() {
      return mPass;
   }

   @Override
   public boolean getUseAuth() {
      return mUseAuth;
   }

   @SuppressWarnings({ "rawtypes", "unchecked" })
   private Object getObjectsFromJson(String _jsonString, Class _class) {
      try {
         Object returnObjects = mJsonObjectMapper.readValue(_jsonString, _class);

         return returnObjects;
      } catch (JsonParseException e) {
         Log.e(LogUtils.LOG_CONST, e.toString());
      } catch (JsonMappingException e) {
         Log.e(LogUtils.LOG_CONST, e.toString());
      } catch (IOException e) {
         Log.e(LogUtils.LOG_CONST, e.toString());
      }
      return null;
   }

   @Override
   public String getAddress() {
      return mServer;
   }

   public SupportedFunctions getSupportedFunctions() {
      String methodName = GET_SUPPORTED_FUNCTIONS;
      String response = mJsonClient.Execute(methodName);

      if (response != null) {
         SupportedFunctions returnObject = (SupportedFunctions) getObjectsFromJson(response,
               SupportedFunctions.class);

         if (returnObject != null) {
            return returnObject;
         } else {
            Log.d(LogUtils.LOG_CONST, "Error parsing result from JSON method " + methodName);
         }
      } else {
         Log.d(LogUtils.LOG_CONST, "Error retrieving data for method" + methodName);
      }
      return null;
   }

   @Override
   public List<VideoShare> getVideoShares() {
      String methodName = "MP_GetVideoShares";
      String response = mJsonClient.Execute(methodName);

      if (response != null) {
         VideoShare[] returnObject = (VideoShare[]) getObjectsFromJson(response, VideoShare[].class);

         if (returnObject != null) {
            return new ArrayList<VideoShare>(Arrays.asList(returnObject));
         } else {
            Log.d(LogUtils.LOG_CONST, "Error parsing result from JSON method " + methodName);
         }
      } else {
         Log.d(LogUtils.LOG_CONST, "Error retrieving data for method" + methodName);
      }
      return null;
   }

   @Override
   public List<FileInfo> getFilesForFolder(String _path) {
      String methodName = "FS_GetFilesFromDirectory";
      String response = mJsonClient.Execute(methodName, JsonUtils.newPair("filepath", _path));

      if (response != null) {
         FileInfo[] returnObject = (FileInfo[]) getObjectsFromJson(response, FileInfo[].class);

         if (returnObject != null) {
            return new ArrayList<FileInfo>(Arrays.asList(returnObject));
         } else {
            Log.d(LogUtils.LOG_CONST, "Error parsing result from JSON method " + methodName);
         }
      } else {
         Log.d(LogUtils.LOG_CONST, "Error retrieving data for method" + methodName);
      }
      return null;
   }

   @Override
   public FileInfo getFileInfo(String _path) {
      String methodName = "FS_GetFileInfo";
      String response = mJsonClient.Execute(methodName, JsonUtils.newPair("filepath", _path));

      if (response != null) {
         FileInfo returnObject = (FileInfo) getObjectsFromJson(response, FileInfo.class);

         if (returnObject != null) {
            return returnObject;
         } else {
            Log.d(LogUtils.LOG_CONST, "Error parsing result from JSON method " + methodName);
         }
      } else {
         Log.d(LogUtils.LOG_CONST, "Error retrieving data for method" + methodName);
      }
      return null;
   }

   @Override
   public List<FileInfo> getFoldersForFolder(String _path) {
      String methodName = "FS_GetDirectoryListByPath";
      String response = mJsonClient.Execute(methodName, JsonUtils.newPair("path", _path));

      if (response != null) {
         String[] returnObject = (String[]) getObjectsFromJson(response, String[].class);

         if (returnObject != null) {
            List<FileInfo> retList = new ArrayList<FileInfo>();

            for (String f : returnObject) {
               retList.add(new FileInfo(f, true));
            }

            return retList;
         } else {
            Log.d(LogUtils.LOG_CONST, "Error parsing result from JSON method " + methodName);
         }
      } else {
         Log.d(LogUtils.LOG_CONST, "Error retrieving data for method" + methodName);
      }
      return null;
   }

   @Override
   public List<Movie> getAllMovies() {
      return mMoviesAPI.getAllMovies();
   }

   @Override
   public int getMovieCount() {
      return mMoviesAPI.getMovieCount();
   }

   @Override
   public MovieFull getMovieDetails(int _movieId) {
      return mMoviesAPI.getMovieDetails(_movieId);
   }

   @Override
   public List<Movie> getMovies(int _start, int _end) {
      return mMoviesAPI.getMovies(_start, _end);
   }

   @Override
   public List<Movie> getAllVideos() {
      return mVideosAPI.getAllMovies();
   }

   @Override
   public int getVideosCount() {
      return mVideosAPI.getMovieCount();
   }

   @Override
   public MovieFull getVideoDetails(int _movieId) {
      return mVideosAPI.getMovieDetails(_movieId);
   }

   @Override
   public List<Movie> getVideos(int _start, int _end) {
      return mVideosAPI.getMovies(_start, _end);
   }

   @Override
   public List<Series> getAllSeries() {
      return mSeriesAPI.getAllSeries();
   }

   @Override
   public List<Series> getSeries(int _start, int _end) {
      return mSeriesAPI.getSeries(_start, _end);
   }

   @Override
   public SeriesFull getFullSeries(int _seriesId) {
      return mSeriesAPI.getFullSeries(_seriesId);
   }

   @Override
   public int getSeriesCount() {
      return mSeriesAPI.getSeriesCount();
   }

   @Override
   public List<SeriesSeason> getAllSeasons(int _seriesId) {
      return mSeriesAPI.getAllSeasons(_seriesId);
   }

   @Override
   public List<SeriesEpisode> getAllEpisodes(int _seriesId) {
      return mSeriesAPI.getAllEpisodes(_seriesId);
   }

   @Override
   public List<SeriesEpisode> getAllEpisodesForSeason(int _seriesId, int _seasonNumber) {
      return mSeriesAPI.getAllEpisodesForSeason(_seriesId, _seasonNumber);
   }

   @Override
   public List<SeriesEpisode> getEpisodesForSeason(int _seriesId, int _seasonNumber, int _begin,
         int _end) {
      return mSeriesAPI.getEpisodesForSeason(_seriesId, _seasonNumber, _begin, _end);
   }

   @Override
   public int getEpisodesCountForSeason(int _seriesId, int _seasonNumber) {
      return mSeriesAPI.getEpisodesCountForSeason(_seriesId, _seasonNumber);
   }

   @Override
   public EpisodeDetails getEpisode(int _seriesId, int _episodeId) {
      return mSeriesAPI.getFullEpisode(_seriesId, _episodeId);
   }

   @Override
   public Bitmap getBitmap(String _url) {
      URL myFileUrl = null;
      Bitmap bmImg = null;
      try {
         myFileUrl = new URL(JSON_PREFIX + mServer + ":" + mPort + STREAM_SUFFIX
               + "/FS_GetImage?path=" + URLEncoder.encode(_url, "UTF-8"));

         if (mUseAuth) {
            Authenticator.setDefault(new Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(mUser, mPass.toCharArray());
               }
            });
         }

         HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
         conn.setDoInput(true);
         conn.connect();
         InputStream is = conn.getInputStream();

         bmImg = BitmapFactory.decodeStream(is);
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return bmImg;
   }

   @Override
   public Bitmap getBitmap(String _url, int _maxWidth, int _maxHeight) {
      URL myFileUrl = null;
      Bitmap bmImg = null;
      try {
         myFileUrl = new URL(JSON_PREFIX + mServer + ":" + mPort + STREAM_SUFFIX
               + "/FS_GetImageResized?path=" + URLEncoder.encode(_url, "UTF-8") + "&maxWidth="
               + _maxWidth + "&maxHeight=" + _maxHeight);

         if (mUseAuth) {
            Authenticator.setDefault(new Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(mUser, mPass.toCharArray());
               }
            });
         }

         HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
         conn.setDoInput(true);
         conn.connect();
         InputStream is = conn.getInputStream();

         bmImg = BitmapFactory.decodeStream(is);
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return bmImg;
   }

   @Override
   public String getDownloadUri(String _filePath) {
      String fileUrl = null;
      try {
         fileUrl = JSON_PREFIX + mServer + ":" + mPort + STREAM_SUFFIX + "/FS_GetMediaItem?path="
               + URLEncoder.encode(_filePath, "UTF-8");
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return fileUrl;

   }
   
   @Override
   public List<VideoShare> getMusicShares() {
      return mMusicAPI.getMusicShares();
      
   }

   @Override
   public MusicTrack getMusicTrack(int trackId) {
      return mMusicAPI.getMusicTrack(trackId);
   }

   @Override
   public MusicAlbum getAlbum(String albumArtistName, String albumName) {
      return mMusicAPI.getAlbum(albumArtistName, albumName);
   }

   @Override
   public List<MusicAlbum> getAllAlbums() {
      return mMusicAPI.getAllAlbums();
   }

   @Override
   public List<MusicAlbum> getAlbums(int _start, int _end) {
      return mMusicAPI.getAlbums(_start, _end);
   }

   @Override
   public int getAlbumsCount() {
      return mMusicAPI.getAlbumsCount();
   }

   @Override
   public List<MusicArtist> getAllArtists() {
      return mMusicAPI.getAllArtists();
   }

   @Override
   public List<MusicArtist> getArtists(int _start, int _end) {
      return mMusicAPI.getArtists(_start, _end);
   }

   @Override
   public int getArtistsCount() {
      return mMusicAPI.getArtistsCount();
   }

   @Override
   public List<MusicAlbum> getAlbumsByArtist(String artistName) {
      return mMusicAPI.getAlbumsByArtist(artistName);
   }

   @Override
   public List<MusicTrack> getSongsOfAlbum(String albumName, String albumArtistName) {
      return mMusicAPI.getSongsOfAlbum(albumName, albumArtistName);
   }

   @Override
   public List<MusicTrack> findMusicTracks(String album, String artist, String title) {
      return mMusicAPI.findMusicTracks(album, artist, title);
   }

   @Override
   public int getMusicTracksCount() {
      return mMusicAPI.getMusicTracksCount();
   }

   @Override
   public List<MusicTrack> getMusicTracks(int _start, int _end) {
      return mMusicAPI.getMusicTracks(_start, _end);
   }

   @Override
   public List<MusicTrack> getAllMusicTracks() {
      return mMusicAPI.getAllMusicTracks();
   }

   @Override
   public List<MusicAlbum> getMusicAlbumsByArtist(String _artist) {
      return mMusicAPI.getAlbumsByArtist(_artist);
   }
}