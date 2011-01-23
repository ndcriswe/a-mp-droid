package com.mediaportal.ampdroid.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mediaportal.ampdroid.database.ColumnProperty;

public class EpisodeDetails extends SeriesEpisode {

   // Div Info
   private String Summary; // Summary
   private String GuestStarsString; // GuestStars
   private String[] GuestStars;
   private String DirectorsString; // Director
   private String[] Directors;
   private String WritersString; // Writer
   private String[] Writers;
   private Date LastUpdated; // lastupdated
   private String ImdbId;// IMDB_ID
   private String ProductionCode;// ProductionCode

   // Combined
   private int CombinedEpisodeNumber; // Combined_episodenumber
   private int CombinedSeasonNumber; // Combined_season

   // DVD
   private int DvdChapter; // DVD_chapter
   private int DvdDiscid; // DVD_discid
   private int DvdEpisodenumber; // DVD_episodenumber
   private int DvdSeason; // DVD_season

   // absolute ordering
   private int AbsoluteEpisodeNumber;// absolute_number

   // Specials
   private int AirsAfterSeason;// airsafter_season
   private int AirsBeforeEpisode;// airsbefore_episode
   private int AirsBeforeSesaon;// airsbefore_season

   // Physical files
   private EpisodeFile EpisodeFile;// The physical file on disk
   private EpisodeFile EpisodeFile2;// Episode consists of a second file

   @ColumnProperty(value="Summary", type="text")
   @JsonProperty("Summary")
   public String getSummary() {
      return Summary;
   }

   @ColumnProperty(value="Summary", type="text")
   @JsonProperty("Summary")
   public void setSummary(String summary) {
      Summary = summary;
   }

   @ColumnProperty(value="GuestStarsString", type="text")
   @JsonProperty("GuestStarsString")
   public String getGuestStarsString() {
      return GuestStarsString;
   }

   @ColumnProperty(value="GuestStarsString", type="text")
   @JsonProperty("GuestStarsString")
   public void setGuestStarsString(String guestStarsString) {
      GuestStarsString = guestStarsString;
   }

   @ColumnProperty(value="GuestStars", type="text")
   @JsonProperty("GuestStars")
   public String[] getGuestStars() {
      return GuestStars;
   }

   @ColumnProperty(value="GuestStars", type="text")
   @JsonProperty("GuestStars")
   public void setGuestStars(String[] guestStars) {
      GuestStars = guestStars;
   }

   @ColumnProperty(value="DirectorsString", type="text")
   @JsonProperty("DirectorsString")
   public String getDirectorsString() {
      return DirectorsString;
   }

   @ColumnProperty(value="DirectorsString", type="text")
   @JsonProperty("DirectorsString")
   public void setDirectorsString(String directorsString) {
      DirectorsString = directorsString;
   }

   @ColumnProperty(value="Directors", type="text")
   @JsonProperty("Directors")
   public String[] getDirectors() {
      return Directors;
   }

   @ColumnProperty(value="Directors", type="text")
   @JsonProperty("Directors")
   public void setDirectors(String[] directors) {
      Directors = directors;
   }

   @ColumnProperty(value="WritersString", type="text")
   @JsonProperty("WritersString")
   public String getWritersString() {
      return WritersString;
   }

   @ColumnProperty(value="WritersString", type="text")
   @JsonProperty("WritersString")
   public void setWritersString(String writersString) {
      WritersString = writersString;
   }

   @ColumnProperty(value="Writers", type="text")
   @JsonProperty("Writers")
   public String[] getWriters() {
      return Writers;
   }

   @ColumnProperty(value="Writers", type="text")
   @JsonProperty("Writers")
   public void setWriters(String[] writers) {
      Writers = writers;
   }

   @ColumnProperty(value="LastUpdated", type="text")
   @JsonProperty("LastUpdated")
   public Date getLastUpdated() {
      return LastUpdated;
   }

   @ColumnProperty(value="LastUpdated", type="text")
   @JsonProperty("LastUpdated")
   public void setLastUpdated(Date lastUpdated) {
      LastUpdated = lastUpdated;
   }

   @ColumnProperty(value="ImdbId", type="text")
   @JsonProperty("ImdbId")
   public String getImdbId() {
      return ImdbId;
   }

   @ColumnProperty(value="ImdbId", type="text")
   @JsonProperty("ImdbId")
   public void setImdbId(String imdbId) {
      ImdbId = imdbId;
   }

   @ColumnProperty(value="ProductionCode", type="text")
   @JsonProperty("ProductionCode")
   public String getProductionCode() {
      return ProductionCode;
   }

   @ColumnProperty(value="ProductionCode", type="text")
   @JsonProperty("ProductionCode")
   public void setProductionCode(String productionCode) {
      ProductionCode = productionCode;
   }

   @ColumnProperty(value="CombinedEpisodeNumber", type="integer")
   @JsonProperty("CombinedEpisodeNumber")
   public int getCombinedEpisodeNumber() {
      return CombinedEpisodeNumber;
   }

   @ColumnProperty(value="CombinedEpisodeNumber", type="integer")
   @JsonProperty("CombinedEpisodeNumber")
   public void setCombinedEpisodeNumber(int combinedEpisodeNumber) {
      CombinedEpisodeNumber = combinedEpisodeNumber;
   }

   @ColumnProperty(value="CombinedSeasonNumber", type="integer")
   @JsonProperty("CombinedSeasonNumber")
   public int getCombinedSeasonNumber() {
      return CombinedSeasonNumber;
   }

   @ColumnProperty(value="CombinedSeasonNumber", type="integer")
   @JsonProperty("CombinedSeasonNumber")
   public void setCombinedSeasonNumber(int combinedSeasonNumber) {
      CombinedSeasonNumber = combinedSeasonNumber;
   }

   @ColumnProperty(value="DvdChapter", type="integer")
   @JsonProperty("DvdChapter")
   public int getDvdChapter() {
      return DvdChapter;
   }

   @ColumnProperty(value="DvdChapter", type="integer")
   @JsonProperty("DvdChapter")
   public void setDvdChapter(int dvdChapter) {
      DvdChapter = dvdChapter;
   }

   @ColumnProperty(value="DvdDiscid", type="integer")
   @JsonProperty("DvdDiscid")
   public int getDvdDiscid() {
      return DvdDiscid;
   }

   @ColumnProperty(value="DvdDiscid", type="integer")
   @JsonProperty("DvdDiscid")
   public void setDvdDiscid(int dvdDiscid) {
      DvdDiscid = dvdDiscid;
   }

   @ColumnProperty(value="DvdEpisodenumber", type="integer")
   @JsonProperty("DvdEpisodenumber")
   public int getDvdEpisodenumber() {
      return DvdEpisodenumber;
   }

   @ColumnProperty(value="DvdEpisodenumber", type="integer")
   @JsonProperty("DvdEpisodenumber")
   public void setDvdEpisodenumber(int dvdEpisodenumber) {
      DvdEpisodenumber = dvdEpisodenumber;
   }

   @ColumnProperty(value="DvdSeason", type="integer")
   @JsonProperty("DvdSeason")
   public int getDvdSeason() {
      return DvdSeason;
   }

   @ColumnProperty(value="DvdSeason", type="integer")
   @JsonProperty("DvdSeason")
   public void setDvdSeason(int dvdSeason) {
      DvdSeason = dvdSeason;
   }

   @ColumnProperty(value="AbsoluteEpisodeNumber", type="integer")
   @JsonProperty("AbsoluteEpisodeNumber")
   public int getAbsoluteEpisodeNumber() {
      return AbsoluteEpisodeNumber;
   }

   @ColumnProperty(value="AbsoluteEpisodeNumber", type="integer")
   @JsonProperty("AbsoluteEpisodeNumber")
   public void setAbsoluteEpisodeNumber(int absoluteEpisodeNumber) {
      AbsoluteEpisodeNumber = absoluteEpisodeNumber;
   }

   @ColumnProperty(value="AirsAfterSeason", type="integer")
   @JsonProperty("AirsAfterSeason")
   public int getAirsAfterSeason() {
      return AirsAfterSeason;
   }

   @ColumnProperty(value="AirsAfterSeason", type="integer")
   @JsonProperty("AirsAfterSeason")
   public void setAirsAfterSeason(int airsAfterSeason) {
      AirsAfterSeason = airsAfterSeason;
   }

   @ColumnProperty(value="AirsBeforeEpisode", type="integer")
   @JsonProperty("AirsBeforeEpisode")
   public int getAirsBeforeEpisode() {
      return AirsBeforeEpisode;
   }

   @ColumnProperty(value="AirsBeforeEpisode", type="integer")
   @JsonProperty("AirsBeforeEpisode")
   public void setAirsBeforeEpisode(int airsBeforeEpisode) {
      AirsBeforeEpisode = airsBeforeEpisode;
   }

   @ColumnProperty(value="AirsBeforeSesaon", type="integer")
   @JsonProperty("AirsBeforeSesaon")
   public int getAirsBeforeSesaon() {
      return AirsBeforeSesaon;
   }

   @ColumnProperty(value="AirsBeforeSesaon", type="integer")
   @JsonProperty("AirsBeforeSesaon")
   public void setAirsBeforeSesaon(int airsBeforeSesaon) {
      AirsBeforeSesaon = airsBeforeSesaon;
   }

   @JsonProperty("EpisodeFile")
   public EpisodeFile getEpisodeFile() {
      return EpisodeFile;
   }

   @JsonProperty("EpisodeFile")
   public void setEpisodeFile(EpisodeFile episodeFile) {
      EpisodeFile = episodeFile;
   }

   @JsonProperty("EpisodeFile2")
   public EpisodeFile getEpisodeFile2() {
      return EpisodeFile2;
   }

   @JsonProperty("EpisodeFile2")
   public void setEpisodeFile2(EpisodeFile episodeFile2) {
      EpisodeFile2 = episodeFile2;
   }

}
